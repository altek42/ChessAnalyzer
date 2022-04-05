package pl.altek.chessanalizer.db.node;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;
import pl.altek.chessanalizer.db.relation.MoveRelation;

import java.util.List;

@Data
@Node("State")
public class StateNode {
    @Id
    private String hash;

    private String fen;

    @Relationship(type="PLAYER_MOVE", direction = Direction.OUTGOING)
    private List<MoveRelation> playerMoves;

    @Relationship(type="ENEMY_MOVE", direction = Direction.OUTGOING)
    private List<MoveRelation> enemyMoves;

    public void addPlayerMove(MoveRelation move){
        this.getPlayerMoves().add(move);
    }

    public void addEnemyMove(MoveRelation move){
        this.getEnemyMoves().add(move);
    }
}
