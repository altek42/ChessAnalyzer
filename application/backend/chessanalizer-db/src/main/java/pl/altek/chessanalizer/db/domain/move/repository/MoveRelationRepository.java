package pl.altek.chessanalizer.db.domain.move.repository;

import pl.altek.chessanalizer.db.domain.move.MoveRelation;

import java.util.List;
import java.util.UUID;

public interface MoveRelationRepository {
    List<MoveRelation> findAllByNodeHashAndUserId(String nodeHash, UUID userId);

    List<MoveRelation> findAllByNodeHashAndNotUserId(String nodeHash, UUID userId);
}
