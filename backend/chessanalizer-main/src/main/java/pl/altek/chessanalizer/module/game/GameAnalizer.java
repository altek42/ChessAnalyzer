package pl.altek.chessanalizer.module.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import pl.altek.chessanalizer.db.entity.GameEntity;
import pl.altek.chessanalizer.db.node.StateNode;
import pl.altek.chessanalizer.db.repository.GameRepository;
import pl.altek.chessanalizer.db.repository.StateRepository;
import pl.altek.chessanalizer.openapi.client.chessboardapi.api.ChessApi;
import pl.altek.chessanalizer.openapi.client.chessboardapi.api.SessionApi;
import pl.altek.chessanalizer.openapi.client.chessboardapi.model.MoveDto;
import pl.altek.chessanalizer.openapi.client.chesscomapi.model.Game;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class GameAnalizer {

    private final Pattern PGN_MOVE_REGEX = Pattern.compile("\\. (.*?)? \\{");

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

    @Async("gameAnalizerExecutor")
    public void processGame(Game game){
        List<String> moves = extractMovesFromPGN(game.getPgn());

        String session = sessionApi.sessionControllerCreateSession();
        moves.forEach(x -> processSingleMove(session, x));
        insertGameEntityIntoDB(game.getUuid());
    }

    private void processSingleMove(String session, String move){
        MoveDto moveDto = new MoveDto();
        moveDto.setSessionId(session);
        moveDto.move(move);
        chessApi.chessControllerMoveAction(moveDto);
        String fen = chessApi.chessControllerGetFen(session);
        saveMove(fen, move);
    }

    private void saveMove(String fen, String move){
        String hash = DigestUtils.md5DigestAsHex(fen.getBytes(StandardCharsets.UTF_8));
        Optional<StateNode> nodeOp = stateRepository.findById(hash);
        StateNode state = nodeOp.orElseGet(() -> {
            StateNode node = new StateNode();
            node.setHash(hash);
            node.setFen(fen);
            node.setMoves(new ArrayList<>());
            return node;
            // to trzeba jeszcze zapisać do bazy
            // ale powinno już istnieć bo przy relacji powinno się tworzyć
        });
        // Brakuje ruchu poprzedniego
    }

    private void insertGameEntityIntoDB(UUID gameId){
        GameEntity gameEntity = new GameEntity();
        gameEntity.setId(gameId);
        gameEntity.setUserId(mockUserId);
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
