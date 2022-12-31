package pl.altek.analyzer.db.neo4j.repository;

import pl.altek.analyzer.db.neo4j.model.Move;

import java.util.List;
import java.util.UUID;

public interface MoveRepository {
    List<Move> findAllPlayerMoveByNodeAndUserId(String hash, UUID userId);

    List<Move> findAllEnemyMoveByNodeAndUserId(String hash, UUID userId);
}
