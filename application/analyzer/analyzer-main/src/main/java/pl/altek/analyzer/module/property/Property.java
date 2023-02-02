package pl.altek.analyzer.module.property;

import java.util.Optional;

public enum Property {
    API_CHESSBOARD_URL,
    API_CHESSCOM_URL;

    public static Optional<Property> optionalValueOf(String name) {
        try {
            Property value = Property.valueOf(name);
            return Optional.of(value);
        } catch (Exception ignored) {
        }
        return Optional.empty();
    }
}
