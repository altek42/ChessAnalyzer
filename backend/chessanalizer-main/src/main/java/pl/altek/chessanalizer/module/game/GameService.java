package pl.altek.chessanalizer.module.game;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.altek.chessanalizer.db.entity.GameEntity;
import pl.altek.chessanalizer.openapi.client.chesscomapi.api.PlayerApi;
import pl.altek.chessanalizer.openapi.client.chesscomapi.model.Game;
import pl.altek.chessanalizer.openapi.client.chesscomapi.model.GameList;
import pl.altek.chessanalizer.openapi.model.UpdateGameAction;
import pl.altek.chessanalizer.db.repository.GameRepository;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

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
        GameList gameList = playerApi.playerGameMonthlyArchive("altek42", "2021", "01");
        List<Game> games = gameList.getGames();

        games.forEach(this::processNewGame);
    }

    private void processNewGame(Game game) {
        UUID gameId = game.getUuid();
        if(gameRepository.existsById(gameId)){
            return;
        }
        gameAnalizer.processGame(game);
    }
}
