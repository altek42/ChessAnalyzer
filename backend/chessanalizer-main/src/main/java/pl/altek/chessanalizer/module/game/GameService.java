package pl.altek.chessanalizer.module.game;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.altek.chessanalizer.common.ProgressCounter;
import pl.altek.chessanalizer.common.RefSymbol;
import pl.altek.chessanalizer.db.domain.user.UserEntity;
import pl.altek.chessanalizer.db.domain.game.GameRepository;
import pl.altek.chessanalizer.db.domain.state.StateRepository;
import pl.altek.chessanalizer.module.gameAnalyzer.GameAnalyzerService;
import pl.altek.chessanalizer.module.user.UserService;
import pl.altek.chessanalizer.openapi.client.chesscomapi.api.PlayerApi;
import pl.altek.chessanalizer.openapi.client.chesscomapi.model.Game;
import pl.altek.chessanalizer.openapi.client.chesscomapi.model.GameList;
import pl.altek.chessanalizer.openapi.model.UpdateGameAction;

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
    private GameAnalyzerService gameAnalyzerService;
    @Autowired
    private UserService userService;

    public void updateGameData(UpdateGameAction body){
        UserEntity user = userService.getCurrentUser();
        GameList gameList = playerApi.playerGameMonthlyArchive(user.getUsername(), body.getYear(), body.getMonth());
        List<Game> games = gameList.getGames();

        RefSymbol refSymbol = RefSymbol.create();
        log.info("Begin process: " + refSymbol);

        int size = games.size();
        for (int i = 0; i < size; i++) {
            Game game = games.get(i);
            processNewGame(game, ProgressCounter.of(i+1, size), refSymbol);
        }
    }

    private void processNewGame(Game game, ProgressCounter pc, RefSymbol refSymbol) {
        UUID gameId = game.getUuid();
        if(gameRepository.existsById(gameId)){
            log.info("Omit: " + refSymbol + " " + pc);
            return;
        }
        UUID whitePlayerId = game.getWhite().getUuid();
        UUID currentUserChessId = userService.getCurrentUserChessId();

        gameAnalyzerService.queuePgn(
                game.getPgn(),
                userService.getCurrentUserId(),
                currentUserChessId.equals(whitePlayerId)
        );
    }
}
