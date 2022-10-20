package pl.altek.chessanalizer.auth;

import java.util.UUID;

public class JwtToken {
    private UUID userId;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setUserId(String userId) {
        this.userId = UUID.fromString(userId);
    }
}
