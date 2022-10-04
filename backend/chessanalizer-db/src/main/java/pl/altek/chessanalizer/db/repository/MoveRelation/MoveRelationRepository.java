package pl.altek.chessanalizer.db.repository.MoveRelation;

import pl.altek.chessanalizer.db.constants.MoveRelationType;
import pl.altek.chessanalizer.db.relation.MoveRelation;

import java.util.Optional;
import java.util.UUID;

public interface MoveRelationRepository {
    Optional<MoveRelation> findAndIncreaseQuantity(String sourceNodeHash, String name, MoveRelationType relationType);
}
