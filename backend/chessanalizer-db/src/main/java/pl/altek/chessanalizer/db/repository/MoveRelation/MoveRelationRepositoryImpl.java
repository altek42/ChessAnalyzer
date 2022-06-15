package pl.altek.chessanalizer.db.repository.MoveRelation;

import org.springframework.stereotype.Repository;
import pl.altek.chessanalizer.db.relation.MoveRelation;

import java.util.UUID;

@Repository
class MoveRelationRepositoryImpl implements MoveRelationRepository {

    @Override
    public MoveRelation findAndIncreaseQuantity(UUID sourceNodeId, String name, String relationType) {
        return null;
    }
}
