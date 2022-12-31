package pl.altek.analyzer.db.domain.move.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.Record;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Relationship;
import org.neo4j.driver.types.TypeSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Repository;
import pl.altek.analyzer.db.domain.move.MoveRelation;
import pl.altek.analyzer.db.domain.move.MoveRelationType;
import pl.altek.analyzer.db.domain.state.StateNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
@Slf4j
class MoveRelationRepositoryImpl implements MoveRelationRepository {

    @Autowired
    private Neo4jClient client;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<MoveRelation> findAllByNodeHashAndUserId(String nodeHash, UUID userId) {
        return findAllByNodeHashAndUserIdAndMoveType(nodeHash, userId, MoveRelationType.PLAYER);
    }

    @Override
    public List<MoveRelation> findAllByNodeHashAndNotUserId(String nodeHash, UUID userId) {
        return findAllByNodeHashAndUserIdAndMoveType(nodeHash, userId, MoveRelationType.ENEMY);
    }

    private List<MoveRelation> findAllByNodeHashAndUserIdAndMoveType(String nodeHash, UUID userId, MoveRelationType relationType) {
        Collection<MoveRelation> all = client
                .query(createSqlFindByHashAndUserIdAndRelationType(nodeHash, userId, relationType))
                .fetchAs(MoveRelation.class)
                .mappedBy(this::mapResult)
                .all();
        return new ArrayList<>(all);
    }

    private String createSqlFindByHashAndUserIdAndRelationType(String nodeHash, UUID userId, MoveRelationType relationType) {
        return "match (n)-[r:" + relationType.getValue() + "]->(n2) " +
                "where n.hash='" + nodeHash + "' " +
                "AND r.userId='" + userId + "' " +
                "return r";
    }

    private MoveRelation mapResultWithDestNode(TypeSystem t, Record record) {
        Relationship r = record.get("r").asRelationship();
        Node n = record.get("n2").asNode();

        MoveRelation move = objectMapper.convertValue(r.asMap(), MoveRelation.class);
        StateNode stateNode = objectMapper.convertValue(n.asMap(), StateNode.class);

        move.setId(r.id());
        move.setState(stateNode);
        return move;
    }

    private MoveRelation mapResult(TypeSystem t, Record record) {
        Relationship r = record.get("r").asRelationship();
        MoveRelation move = objectMapper.convertValue(r.asMap(), MoveRelation.class);
        move.setId(r.id());
        return move;
    }
}
