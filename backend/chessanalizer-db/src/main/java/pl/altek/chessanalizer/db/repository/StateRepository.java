package pl.altek.chessanalizer.db.repository;

import org.neo4j.driver.internal.InternalRelationship;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import pl.altek.chessanalizer.db.constants.MoveRelationType;
import pl.altek.chessanalizer.db.node.StateNode;
import pl.altek.chessanalizer.db.relation.MoveRelation;

import java.util.List;
import java.util.Optional;

@Repository
public interface StateRepository extends Neo4jRepository<StateNode, String> {
}
