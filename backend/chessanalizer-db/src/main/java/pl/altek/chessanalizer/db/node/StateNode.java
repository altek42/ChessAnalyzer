package pl.altek.chessanalizer.db.node;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;
import pl.altek.chessanalizer.db.constants.MoveRelationType;
import pl.altek.chessanalizer.db.relation.MoveRelation;

import java.util.ArrayList;
import java.util.List;

@Data
@Node("State")
public class StateNode {
    @Id
    private String hash;

    private String fen;

    @Relationship(type = MoveRelationType.Constants.PLAYER_VALUE, direction = Direction.OUTGOING)
    private List<MoveRelation> playerMoves;

    @Relationship(type=MoveRelationType.Constants.ENEMY_VALUE, direction = Direction.OUTGOING)
    private List<MoveRelation> enemyMoves;


}
