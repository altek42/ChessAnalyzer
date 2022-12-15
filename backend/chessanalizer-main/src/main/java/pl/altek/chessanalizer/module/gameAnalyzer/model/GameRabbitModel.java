package pl.altek.chessanalizer.module.gameAnalyzer.model;

import lombok.Data;
import lombok.ToString;
import pl.altek.chessanalizer.db.domain.move.GameResult;

import java.util.UUID;

@Data
@ToString
public class GameRabbitModel {
    private String pgn;
    private boolean isUserWhite;
    private UUID userId;
    private GameResult gameResult;
}
