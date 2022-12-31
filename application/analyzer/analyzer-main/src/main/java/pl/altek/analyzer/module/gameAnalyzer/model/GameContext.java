package pl.altek.analyzer.module.gameAnalyzer.model;

import lombok.Data;
import pl.altek.analyzer.db.neo4j.model.State;

import java.util.UUID;

@Data
public class GameContext {
    State state;
    String session;
    String move;
    Boolean isPlayerMove;
    UUID userId;
    GameResult gameResult;

    public void toggleIsPlayerMove() {
        this.setIsPlayerMove(!this.getIsPlayerMove());
    }
}
