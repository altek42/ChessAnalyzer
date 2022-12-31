package pl.altek.analyzer.module.gameAnalyzer.model;

import lombok.Data;
import lombok.ToString;
import pl.altek.analyzer.db.domain.move.GameResult;

import java.util.UUID;

@Data
@ToString
public class GameRabbitModel {
    private String pgn;
    private boolean isUserWhite;
    private UUID userId;
    private GameResult gameResult;
}
