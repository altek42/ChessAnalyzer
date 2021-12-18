package pl.altek.chessanalizer.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import pl.altek.chessanalizer.entity.StateNode;

@Repository
public interface StateRepository extends Neo4jRepository<StateNode, String> {
}
