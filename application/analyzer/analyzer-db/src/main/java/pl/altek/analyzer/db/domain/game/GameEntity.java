package pl.altek.analyzer.db.domain.game;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "game", schema = "public", catalog = "analyzer")
public class GameEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;
    @Basic
    @Column(name = "user_id", nullable = false)
    private UUID userId;
}
