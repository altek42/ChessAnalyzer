package pl.altek.chessanalizer.module.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.altek.chessanalizer.openapi.client.chessapi.model.Game;
import pl.altek.chessanalizer.openapi.client.chessapi.model.GameList;
import pl.altek.chessanalizer.openapi.model.UpdateGameAction;

import java.util.List;

@Service
public class GameService {

    @Autowired @Qualifier("mockedGameList")
    private GameList mockedGameList;

    public void updateGameData(UpdateGameAction body){
        List<Game> games = mockedGameList.getGames();
        
    }
}
