package pl.altek.chessanalizer.module.game;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.altek.chessanalizer.common.ProgressCounter;
import pl.altek.chessanalizer.module.game.analizer.GameAnalizer;
import pl.altek.chessanalizer.openapi.client.chesscomapi.api.PlayerApi;
import pl.altek.chessanalizer.openapi.client.chesscomapi.model.Game;
import pl.altek.chessanalizer.openapi.client.chesscomapi.model.GameList;
import pl.altek.chessanalizer.openapi.model.UpdateGameAction;
import pl.altek.chessanalizer.db.repository.GameRepository;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class GameService {
    @Autowired
    private PlayerApi playerApi;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameAnalizer gameAnalizer;

    public void updateGameData(UpdateGameAction body){
        GameList gameList = playerApi.playerGameMonthlyArchive("altek42", body.getYear(), body.getMonth());
        List<Game> games = gameList.getGames();

        int size = games.size();
        for (int i = 0; i < size; i++) {
            Game game = games.get(i);
            processNewGame(game, ProgressCounter.of(i+1, size));
        }
    }

    private void processNewGame(Game game, ProgressCounter progressCounter) {
        UUID gameId = game.getUuid();
        if(gameRepository.existsById(gameId)){
            return;
        }
        gameAnalizer.processGame(game, progressCounter);
    }
}
