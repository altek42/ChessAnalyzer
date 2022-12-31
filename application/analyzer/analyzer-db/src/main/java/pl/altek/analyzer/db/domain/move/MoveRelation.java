package pl.altek.analyzer.db.domain.move;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;
import pl.altek.analyzer.db.domain.state.StateNode;

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

    private Long win;
    private Long draw;
    private Long lose;
}
