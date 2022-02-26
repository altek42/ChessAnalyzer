package pl.altek.chessanalizer.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.altek.chessanalizer.db.entity.GameEntity;

import java.util.UUID;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, UUID> {
}
