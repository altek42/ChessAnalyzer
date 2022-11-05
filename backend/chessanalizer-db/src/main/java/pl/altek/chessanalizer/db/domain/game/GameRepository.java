package pl.altek.chessanalizer.db.domain.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.altek.chessanalizer.db.domain.game.GameEntity;

import java.util.UUID;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, UUID> {
}
