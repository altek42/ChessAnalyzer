package pl.altek.analyzer.module.game;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.altek.analyzer.db.domain.game.GameRepository;
import pl.altek.analyzer.db.domain.user.UserEntity;
import pl.altek.analyzer.module.gameAnalyzer.GameAnalyzerService;
import pl.altek.analyzer.module.gameAnalyzer.model.GameResult;
import pl.altek.analyzer.module.user.UserService;
import pl.altek.analyzer.openapi.client.chesscomapi.api.PlayerApi;
import pl.altek.analyzer.openapi.client.chesscomapi.model.Game;
import pl.altek.analyzer.openapi.client.chesscomapi.model.GameList;
import pl.altek.analyzer.openapi.model.UpdateGameAction;

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

    public void updateGameData(UpdateGameAction body) {
        UserEntity user = userService.getCurrentUser();
        GameList gameList = playerApi.playerGameMonthlyArchive(user.getUsername(), body.getYear(), body.getMonth());
        List<Game> games = gameList.getGames();


        int size = games.size();
        for (int i = 0; i < size; i++) {
            Game game = games.get(i);
            processNewGame(game);
        }
    }

    private void processNewGame(Game game) {
        UUID gameId = game.getUuid();
        if (gameRepository.existsById(gameId)) {
            return;
        }
        UUID whitePlayerId = game.getWhite().getUuid();
        UUID currentUserChessId = userService.getCurrentUserChessId();

        boolean isPlayerWhite = currentUserChessId.equals(whitePlayerId);
        String chessComResult = isPlayerWhite ? game.getWhite().getResult() : game.getBlack().getResult();

        gameAnalyzerService.queuePgn(
                game.getPgn(),
                userService.getCurrentUserId(),
                isPlayerWhite,
                GameResult.fromChessComGameResult(chessComResult)
        );
    }
}
