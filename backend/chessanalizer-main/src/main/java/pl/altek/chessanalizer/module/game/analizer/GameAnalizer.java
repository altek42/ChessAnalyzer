package pl.altek.chessanalizer.module.game.analizer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import pl.altek.chessanalizer.common.ProgressCounter;
import pl.altek.chessanalizer.common.RefSymbol;
import pl.altek.chessanalizer.db.constants.MoveRelationType;
import pl.altek.chessanalizer.db.entity.GameEntity;
import pl.altek.chessanalizer.db.node.StateNode;
import pl.altek.chessanalizer.db.relation.MoveRelation;
import pl.altek.chessanalizer.db.repository.GameRepository;
import pl.altek.chessanalizer.db.repository.MoveRelation.MoveRelationRepository;
import pl.altek.chessanalizer.db.repository.StateRepository;
import pl.altek.chessanalizer.openapi.client.chessboardapi.api.ChessApi;
import pl.altek.chessanalizer.openapi.client.chessboardapi.api.SessionApi;
import pl.altek.chessanalizer.openapi.client.chessboardapi.model.MoveDto;
import pl.altek.chessanalizer.openapi.client.chesscomapi.model.Game;
import pl.altek.chessanalizer.openapi.client.chesscomapi.model.GamePlayer;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class GameAnalizer {

    private final Pattern PGN_MOVE_REGEX = Pattern.compile("\\. (.*?)? \\{");

    @Autowired
    @Qualifier("mockDbUserId")
    private UUID mockDbUserId;

    @Autowired
    @Qualifier("mockUserId")
    private UUID mockUserId;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ChessApi chessApi;

    @Autowired
    private SessionApi sessionApi;

    @Autowired
    private MoveRelationRepository moveRelationRepository;

    @Async("gameAnalizerExecutor")
    @Transactional
    public void processGame(Game game, ProgressCounter pc, RefSymbol refSymbol){
        Long timeStart = System.currentTimeMillis();
        List<String> moves = extractMovesFromPGN(game.getPgn());

        Context context = initializeContext(game);

        int size = moves.size();
        for (int i = 0; i < size; i++) {
            String move = moves.get(i);
            context.setMove(move);
            moveOnBoard(context);
            MoveRelation nextMove = addOrIncrementMove(context);
            context.setState(nextMove.getState());
            context.toggleIsPlayerMove();
        }

        closeSession(context);
//        insertGameEntityIntoDB(game.getUuid());
        log.info("End process: " + refSymbol + " " + pc +
                "; Time elapsed: "+ (System.currentTimeMillis() - timeStart) + " ms" );
    }

    private Context initializeContext(Game game){
        String session = sessionApi.sessionControllerCreateSession();
        String initalBoradFen = chessApi.chessControllerGetFen(session);
        StateNode currentState = findOrCreateStateNode(initalBoradFen);

        Context context = new Context();
        context.setSession(session);
        context.setState(currentState);
        context.setIsPlayerMove(isPlayerBegin(game));
        return context;
    }

    private void closeSession(Context context){
        String sessionId = context.getSession();
        sessionApi.sessionControllerDeleteSession(sessionId);
    }

    private Boolean isPlayerBegin(Game game){
        GamePlayer player = game.getWhite();
        UUID uuid = player.getUuid();
        return uuid.equals(mockUserId);
    }

    private MoveRelation addOrIncrementMove(Context context){
        StateNode state = context.getState();
        Optional<MoveRelation> relationOp = moveRelationRepository.findAndIncreaseQuantity(state.getHash(), context.getMove(), context.getMoveRelationType());
        return relationOp.orElseGet(
                () -> createNewRelation(context)
        );
    }

    private MoveRelation createNewRelation(Context context){
        String nextMoveFen = chessApi.chessControllerGetFen(context.getSession());
        StateNode nextState = findOrCreateStateNode(nextMoveFen);

        MoveRelation moveRelation = new MoveRelation();
        moveRelation.setName(context.getMove());
        moveRelation.setQuantity(1L);
        moveRelation.setState(nextState);
        moveRelation.setUserId(mockDbUserId);

        StateNode state = context.getState();
        context.execIsPlayerMove(
                () -> moveRelationRepository.save(state, moveRelation, MoveRelationType.PLAYER),
                () -> moveRelationRepository.save(state, moveRelation, MoveRelationType.ENEMY)
        );
        return moveRelation;
    }


    private void moveOnBoard(Context context){
        MoveDto moveDto = new MoveDto();
        moveDto.setSessionId(context.getSession());
        moveDto.setMove(context.getMove());
        chessApi.chessControllerMoveAction(moveDto);
    }

    private StateNode findOrCreateStateNode(String fen) {
        String hash = DigestUtils.md5DigestAsHex(fen.getBytes(StandardCharsets.UTF_8));
        Optional<StateNode> nodeOp = stateRepository.findById(hash);
        StateNode state = nodeOp.orElseGet(() -> {
            StateNode node = new StateNode();
            node.setHash(hash);
            node.setFen(fen);
            node.setPlayerMoves(new ArrayList<>());
            node.setEnemyMoves(new ArrayList<>());
            node = stateRepository.save(node);
            return node;
        });
        return state;
    }

    private void insertGameEntityIntoDB(UUID gameId){
        GameEntity gameEntity = new GameEntity();
        gameEntity.setId(gameId);
        gameEntity.setUserId(mockDbUserId);
        gameRepository.save(gameEntity);
    }

    private List<String> extractMovesFromPGN(String pgn){
        String[] pgnLines = pgn.split("\n");
        String gameMovesPgn = pgnLines[pgnLines.length - 1];
        Matcher matcher = PGN_MOVE_REGEX.matcher(gameMovesPgn);
        List<String> gameMoves = new ArrayList<>();
        while (matcher.find()) {
            gameMoves.add(matcher.group(1));
        }
        return gameMoves;
    }
}
