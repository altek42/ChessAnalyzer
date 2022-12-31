package pl.altek.chessanalizer.db.domain.state;

import lombok.Data;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;
import pl.altek.chessanalizer.db.domain.move.MoveRelationType;
import pl.altek.chessanalizer.db.domain.move.MoveRelation;

import java.util.List;

@Data
@Node("State")
public class StateNode {
    @Id
    private String hash;

    private String fen;

    @Version
    private Long version;

    @Relationship(type = MoveRelationType.Constants.PLAYER_VALUE, direction = Direction.OUTGOING)
    private List<MoveRelation> playerMoves;

    @Relationship(type=MoveRelationType.Constants.ENEMY_VALUE, direction = Direction.OUTGOING)
    private List<MoveRelation> enemyMoves;

}
