package pl.altek.chessanalizer.db.relation;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;
import pl.altek.chessanalizer.db.node.StateNode;

import java.util.UUID;

@Data
@RelationshipProperties
public class MoveRelation {

    @RelationshipId
    @GeneratedValue
    private Long id;

    @TargetNode
    private StateNode state;

    private String name;

    private Long quantity;

    private UUID userId;

    public void increment(){
        this.quantity += 1L;
    }
}
