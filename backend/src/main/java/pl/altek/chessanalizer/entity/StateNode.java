package pl.altek.chessanalizer.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;
import pl.altek.chessanalizer.enumerate.Player;

import java.util.List;

@Data
@Node("State")
public class StateNode {
    @Id
    private String hash;

    private String fen;

    private Player nextPlayer;

    @Relationship(type="MOVE", direction = Direction.OUTGOING)
    private List<MoveRelation> moves;

}
