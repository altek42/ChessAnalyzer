package pl.altek.analyzer.db.neo4j.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.Record;
import org.neo4j.driver.types.Relationship;
import org.neo4j.driver.types.TypeSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Repository;
import pl.altek.analyzer.db.neo4j.enumeration.MoveType;
import pl.altek.analyzer.db.neo4j.model.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Slf4j
@Repository
class MoveRepositoryImpl implements MoveRepository {

    @Autowired
    private Neo4jClient client;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public List<Move> findAllPlayerMoveByNodeAndUserId(String hash, UUID userId) {
        return findAllMoveByNodeAndUserIdAndMoveType(hash, userId, MoveType.PLAYER);
    }

    @Override
    public List<Move> findAllEnemyMoveByNodeAndUserId(String hash, UUID userId) {
        return findAllMoveByNodeAndUserIdAndMoveType(hash, userId, MoveType.ENEMY);
    }

    public List<Move> findAllMoveByNodeAndUserIdAndMoveType(String nodeHash, UUID userId, MoveType moveType) {
        Collection<Move> all = client
                .query(createCypher(nodeHash, userId, moveType))
                .fetchAs(Move.class)
                .mappedBy(this::mapResult)
                .all();
        return new ArrayList<>(all);
    }

    private String createCypher(String nodeHash, UUID userId, MoveType moveType) {
        return "match (n)-[r:MOVE_" + moveType + "]->(n2) " +
                "where n.hash='" + nodeHash + "' " +
                "AND r.userId='" + userId + "' " +
                "return r";
    }

    private Move mapResult(TypeSystem t, Record record) {
        Relationship r = record.get("r").asRelationship();
        return mapper.convertValue(r.asMap(), Move.class);
    }
}
