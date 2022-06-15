package pl.altek.chessanalizer.db.repository;

import org.neo4j.driver.internal.InternalRelationship;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import pl.altek.chessanalizer.db.node.StateNode;

import java.util.List;
import java.util.Optional;

@Repository
public interface StateRepository extends Neo4jRepository<StateNode, String> {

    @Query("MATCH (n)-[r:PLAYER_MOVE]-(n2) " +
            "WHERE n.hash=$nodeHash " +
            "AND r.name=$move " +
            "return n2")
    Optional<StateNode> findRelation(String nodeHash, String move);
}
