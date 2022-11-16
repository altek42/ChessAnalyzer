package pl.altek.chessanalizer.module.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.altek.chessanalizer.openapi.api.GameApiDelegate;
import pl.altek.chessanalizer.openapi.model.UpdateGameAction;

@Service
public class GameDelegate implements GameApiDelegate {

    @Autowired private GameService gameService;

    @Override
    public ResponseEntity<Void> updateGameData(UpdateGameAction body) {
        gameService.updateGameData(body);
        return ResponseEntity.ok().build();
    }
}
