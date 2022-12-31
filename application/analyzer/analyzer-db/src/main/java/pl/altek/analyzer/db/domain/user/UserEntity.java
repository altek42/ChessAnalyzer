package pl.altek.analyzer.db.domain.user;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "user", schema = "public", catalog = "analyzer")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;
    @Basic
    @Column(name = "username", nullable = true, length = 64)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, length = 60)
    private String password;
    @Basic
    @Column(name = "email", nullable = true, length = 320)
    private String email;
    @Basic
    @Column(name = "chess_id", nullable = true)
    private UUID chessId;
}
