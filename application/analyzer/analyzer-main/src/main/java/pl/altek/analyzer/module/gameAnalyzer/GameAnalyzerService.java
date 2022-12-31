package pl.altek.analyzer.module.gameAnalyzer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.altek.analyzer.common.Constants;
import pl.altek.analyzer.common.RabbitQueue;
import pl.altek.analyzer.common.RefSymbol;
import pl.altek.analyzer.db.domain.move.GameResult;
import pl.altek.analyzer.db.neo4j.argument.SaveStateArgument;
import pl.altek.analyzer.db.neo4j.enumeration.MoveType;
import pl.altek.analyzer.db.neo4j.model.Move;
import pl.altek.analyzer.db.neo4j.model.State;
import pl.altek.analyzer.db.neo4j.repository.StateRepository;
import pl.altek.analyzer.module.gameAnalyzer.model.GameContext;
import pl.altek.analyzer.module.gameAnalyzer.model.GameRabbitModel;
import pl.altek.analyzer.openapi.client.chessboardapi.api.ChessApi;
import pl.altek.analyzer.openapi.client.chessboardapi.api.SessionApi;
import pl.altek.analyzer.openapi.client.chessboardapi.model.MoveDto;

import java.util.ArrayList;
import java.util.List;
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
    private StateRepository stateRepository;

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
        RefSymbol refSymbol = RefSymbol.create();
        log.info("Begin process: " + refSymbol);

        List<String> moves = extractMovesFromPGN(mesage.getPgn());
        GameContext context = initializeContext(mesage);

        for (String move : moves) {
            context.setMove(move);
            processMove(context);
        }
        closeSession(context);
        log.info("End process: " + refSymbol);
    }

    private void processMove(GameContext gameContext) {
        State nextState = moveOnBoard(gameContext);
        Move relation = createRelation(gameContext);

        SaveStateArgument argument = new SaveStateArgument(
                gameContext.getState(),
                nextState,
                relation
        );
        stateRepository.saveMoveRelation(argument);

        gameContext.setState(nextState);
        gameContext.toggleIsPlayerMove();
    }

    private Move createRelation(GameContext gameContext) {
        GameResult gameResult = gameContext.getGameResult();
        Move move = new Move();
        move.setName(gameContext.getMove());
        move.setUserId(gameContext.getUserId());
        move.setWin(gameResult == GameResult.WIN ? 1L : 0L);
        move.setDraw(gameResult == GameResult.DRAW ? 1L : 0L);
        move.setLose(gameResult == GameResult.LOSE ? 1L : 0L);
        move.setType(gameContext.getIsPlayerMove() ? MoveType.PLAYER : MoveType.ENEMY);
        return move;
    }

    private State moveOnBoard(GameContext context) {
        MoveDto moveDto = new MoveDto();
        moveDto.setSessionId(context.getSession());
        moveDto.setMove(context.getMove());
        chessApi.chessControllerMoveAction(moveDto);
        String fen = chessApi.chessControllerGetFen(context.getSession());
        return new State(fen);
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
        State currentState = new State(Constants.INITIAL_FEN);

        GameContext context = new GameContext();
        context.setSession(session);
        context.setState(currentState);
        context.setIsPlayerMove(game.isUserWhite());
        context.setUserId(game.getUserId());
        context.setGameResult(game.getGameResult());
        return context;
    }

    private void closeSession(GameContext context) {
        String sessionId = context.getSession();
        sessionApi.sessionControllerDeleteSession(sessionId);
    }
}
