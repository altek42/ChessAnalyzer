package pl.altek.chessanalizer.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

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
}
