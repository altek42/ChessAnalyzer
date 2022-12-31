package pl.altek.analyzer.db.domain.state;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends Neo4jRepository<StateNode, String> {
}
