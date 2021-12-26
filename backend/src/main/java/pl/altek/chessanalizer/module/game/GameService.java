package pl.altek.chessanalizer.module.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.altek.chessanalizer.db.entity.GameEntity;
import pl.altek.chessanalizer.openapi.client.chessapi.model.Game;
import pl.altek.chessanalizer.openapi.client.chessapi.model.GameList;
import pl.altek.chessanalizer.openapi.model.UpdateGameAction;
import pl.altek.chessanalizer.repository.GameRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GameService {

    @Autowired @Qualifier("mockedGameList")
    private GameList mockedGameList;

    @Autowired @Qualifier("mockUserId")
    private UUID mockUserId;

    @Autowired
    private GameRepository gameRepository;

    private final Pattern PGN_MOVE_REGEX = Pattern.compile("\\. (.*?)? \\{");

    public void updateGameData(UpdateGameAction body){
        List<Game> games = mockedGameList.getGames();
        games.forEach(this::insertNewGame);
    }

    private void insertNewGame(Game game) {
        UUID gameId = game.getUuid();
        if(gameRepository.existsById(gameId)){
            return;
        }
        List<String> moves = extractMovesFromPGN(game.getPgn());

        insertGameEntityIntoDB(gameId);
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
