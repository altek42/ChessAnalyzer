package pl.altek.chessanalizer.db.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import pl.altek.chessanalizer.db.node.StateNode;

import java.util.Optional;

@Repository
public interface StateRepository extends Neo4jRepository<StateNode, String> {
}
