package pl.altek.chessanalizer.module.game;

import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.internal.InternalRelationship;
import org.neo4j.driver.internal.value.RelationshipValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.altek.chessanalizer.common.ProgressCounter;
import pl.altek.chessanalizer.db.node.StateNode;
import pl.altek.chessanalizer.db.relation.MoveRelation;
import pl.altek.chessanalizer.db.repository.StateRepository;
import pl.altek.chessanalizer.db.repository.TestRepository;
import pl.altek.chessanalizer.module.game.analizer.GameAnalizer;
import pl.altek.chessanalizer.openapi.client.chesscomapi.api.PlayerApi;
import pl.altek.chessanalizer.openapi.client.chesscomapi.model.Game;
import pl.altek.chessanalizer.openapi.client.chesscomapi.model.GameList;
import pl.altek.chessanalizer.openapi.model.UpdateGameAction;
import pl.altek.chessanalizer.db.repository.GameRepository;

import java.util.List;
import java.util.Optional;
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

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private TestRepository testRepository;

    public void updateGameData(UpdateGameAction body){
        testRepository.test();
//        Optional<StateNode> relation = stateRepository.findRelation("0056992a41fe7b3940d5cb4202ef682d", "e4");

//        GameList gameList = playerApi.playerGameMonthlyArchive("altek42", body.getYear(), body.getMonth());
//        List<Game> games = gameList.getGames();

//        int size = games.size();
//        for (int i = 0; i < size; i++) {
//            Game game = games.get(i);
//            processNewGame(game, ProgressCounter.of(i+1, size));
//        }
    }

    private void processNewGame(Game game, ProgressCounter progressCounter) {
        UUID gameId = game.getUuid();
        if(gameRepository.existsById(gameId)){
            return;
        }
        gameAnalizer.processGame(game, progressCounter);
    }
}
