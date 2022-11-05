package pl.altek.chessanalizer.db.domain.move.repository;

import pl.altek.chessanalizer.db.domain.move.MoveRelation;
import pl.altek.chessanalizer.db.domain.move.MoveRelationType;
import pl.altek.chessanalizer.db.domain.state.StateNode;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MoveRelationRepository {
    Optional<MoveRelation> findAndIncreaseQuantity(String sourceNodeHash, String name, MoveRelationType relationType);
    void save(StateNode sourceNode, MoveRelation moveRelation, MoveRelationType relationType);

    List<MoveRelation> findAllByNodeHashAndUserId(String nodeHash, UUID userId);
    List<MoveRelation> findAllByNodeHashAndNotUserId(String nodeHash, UUID userId);
}
