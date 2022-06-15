package pl.altek.chessanalizer.db.repository.MoveRelation;

import pl.altek.chessanalizer.db.relation.MoveRelation;

import java.util.UUID;

public interface MoveRelationRepository {
    MoveRelation findAndIncreaseQuantity(UUID sourceNodeId, String name, String relationType);
}
