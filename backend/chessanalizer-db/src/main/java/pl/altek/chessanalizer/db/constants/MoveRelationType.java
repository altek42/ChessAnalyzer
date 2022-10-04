package pl.altek.chessanalizer.db.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MoveRelationType {
    PLAYER(Constants.PLAYER_VALUE),
    ENEMY(Constants.ENEMY_VALUE);

    @Getter
    private final String value;

    public static class Constants {
        public static final String PLAYER_VALUE = "PLAYER_MOVE";
        public static final String ENEMY_VALUE = "ENEMY_MOVE";
    }
}
