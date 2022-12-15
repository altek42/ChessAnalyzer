package pl.altek.chessanalizer.module.gameAnalyzer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import pl.altek.chessanalizer.common.Constants;
import pl.altek.chessanalizer.common.RabbitQueue;
import pl.altek.chessanalizer.db.domain.move.GameResult;
import pl.altek.chessanalizer.db.domain.move.MoveRelation;
import pl.altek.chessanalizer.db.domain.move.MoveRelationType;
import pl.altek.chessanalizer.db.domain.move.repository.MoveRelationRepository;
import pl.altek.chessanalizer.db.domain.state.StateNode;
import pl.altek.chessanalizer.db.domain.state.StateRepository;
import pl.altek.chessanalizer.module.gameAnalyzer.model.GameContext;
import pl.altek.chessanalizer.module.gameAnalyzer.model.GameRabbitModel;
import pl.altek.chessanalizer.module.user.UserService;
import pl.altek.chessanalizer.openapi.client.chessboardapi.api.ChessApi;
import pl.altek.chessanalizer.openapi.client.chessboardapi.api.SessionApi;
import pl.altek.chessanalizer.openapi.client.chessboardapi.model.MoveDto;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class GameAnalyzerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private SessionApi sessionApi;
    @Autowired
    private ChessApi chessApi;
    @Autowired
    private UserService userService;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private MoveRelationRepository moveRelationRepository;

    private final Pattern PGN_MOVE_REGEX = Pattern.compile("\\. (.*?)? \\{");

    public void queuePgn(String pgn, UUID userID, boolean isUserWhite, GameResult gameResult) {
        GameRabbitModel gameRabbitModel = new GameRabbitModel();
        gameRabbitModel.setPgn(pgn);
        gameRabbitModel.setUserId(userID);
        gameRabbitModel.setUserWhite(isUserWhite);
        gameRabbitModel.setGameResult(gameResult);

        rabbitTemplate.convertAndSend(RabbitQueue.Fields.PROCESS_PGN, gameRabbitModel);
    }


    @RabbitListener(queues = RabbitQueue.Fields.PROCESS_PGN, concurrency = "${app.rabbitmq.concurrency}")
    private void processPNG(GameRabbitModel mesage) {
        List<String> moves = extractMovesFromPGN(mesage.getPgn());
        GameContext context = initializeContext(mesage);

        for (String move : moves) {
            processMove(context, move);
        }
        closeSession(context);
    }

    private void processMove(GameContext gameContext, String move) {
        gameContext.setMove(move);
        moveOnBoard(gameContext);
        MoveRelation nextMove = addOrIncrementMove(gameContext);
        gameContext.setState(nextMove.getState());
        gameContext.toggleIsPlayerMove();
    }

    private synchronized MoveRelation addOrIncrementMove(GameContext context) {
        StateNode state = context.getState();
        Optional<MoveRelation> relationOp = moveRelationRepository.findAndIncreaseQuantity(
                state.getHash(),
                context.getMove(),
                context.getMoveRelationType(),
                context.getGameResult()
        );
        return relationOp.orElseGet(
                () -> createNewRelation(context)
        );
    }

    private synchronized MoveRelation createNewRelation(GameContext context) {
        String nextMoveFen = chessApi.chessControllerGetFen(context.getSession());
        StateNode nextState = findOrCreateStateNode(nextMoveFen);
        GameResult gameResult = context.getGameResult();

        MoveRelation moveRelation = new MoveRelation();
        moveRelation.setName(context.getMove());
        moveRelation.setQuantity(1L);
        moveRelation.setState(nextState);
        moveRelation.setUserId(context.getUserId());
        moveRelation.setWin(gameResult == GameResult.WIN ? 1L : 0L);
        moveRelation.setDraw(gameResult == GameResult.DRAW ? 1L : 0L);
        moveRelation.setLose(gameResult == GameResult.LOSE ? 1L : 0L);

        StateNode state = context.getState();
        context.execIsPlayerMove(
                () -> moveRelationRepository.save(state, moveRelation, MoveRelationType.PLAYER),
                () -> moveRelationRepository.save(state, moveRelation, MoveRelationType.ENEMY)
        );
        return moveRelation;
    }

    private void moveOnBoard(GameContext context) {
        MoveDto moveDto = new MoveDto();
        moveDto.setSessionId(context.getSession());
        moveDto.setMove(context.getMove());
        chessApi.chessControllerMoveAction(moveDto);
    }

    private List<String> extractMovesFromPGN(String pgn) {
        String[] pgnLines = pgn.split("\n");
        String gameMovesPgn = pgnLines[pgnLines.length - 1];
        Matcher matcher = PGN_MOVE_REGEX.matcher(gameMovesPgn);
        List<String> gameMoves = new ArrayList<>();
        while (matcher.find()) {
            gameMoves.add(matcher.group(1));
        }
        return gameMoves;
    }

    private GameContext initializeContext(GameRabbitModel game) {
        String session = sessionApi.sessionControllerCreateSession();
        StateNode currentState = findOrCreateStateNode(Constants.INITIAL_FEN);

        GameContext context = new GameContext();
        context.setSession(session);
        context.setState(currentState);
        context.setIsPlayerMove(game.isUserWhite());
        context.setUserId(game.getUserId());
        context.setGameResult(game.getGameResult());
        return context;
    }

    private synchronized StateNode findOrCreateStateNode(String fen) {
        String hash = DigestUtils.md5DigestAsHex(fen.getBytes(StandardCharsets.UTF_8));
        Optional<StateNode> nodeOp = stateRepository.findById(hash);
        return nodeOp.orElseGet(() -> {
            StateNode node = new StateNode();
            node.setHash(hash);
            node.setFen(fen);
            node.setPlayerMoves(new ArrayList<>());
            node.setEnemyMoves(new ArrayList<>());
            node = stateRepository.save(node);
            return node;
        });
    }

    private void closeSession(GameContext context) {
        String sessionId = context.getSession();
        sessionApi.sessionControllerDeleteSession(sessionId);
    }
}
