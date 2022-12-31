package pl.altek.chessanalizer.db.neo4j.model;

import lombok.Data;
import pl.altek.chessanalizer.db.neo4j.enumeration.MoveType;

import java.util.UUID;

@Data
public class Move {
    private MoveType type;
    private UUID userId;
    private String name;

    private Long quantity;
    private Long win;
    private Long draw;
    private Long lose;
}
