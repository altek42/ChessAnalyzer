package pl.altek.chessanalizer.db.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "user", schema = "public", catalog = "chessanalizer")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;
    @Basic
    @Column(name = "username", nullable = true, length = 64)
    private String username;
    @Basic
    @Column(name = "email", nullable = true, length = 320)
    private String email;
    @Basic
    @Column(name = "chess_id", nullable = true)
    private UUID chessId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getChessId() {
        return chessId;
    }

    public void setChessId(UUID chessId) {
        this.chessId = chessId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(email, that.email) && Objects.equals(chessId, that.chessId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, chessId);
    }
}
