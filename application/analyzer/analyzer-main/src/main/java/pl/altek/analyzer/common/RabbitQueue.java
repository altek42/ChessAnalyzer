package pl.altek.analyzer.common;

import lombok.Getter;
import lombok.experimental.FieldNameConstants;

@Getter
@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum RabbitQueue {
    @FieldNameConstants.Include PROCESS_PGN(false),
    @FieldNameConstants.Include PROPERTIES_CHANGED(true);

    private final Boolean exclusive;

    RabbitQueue(Boolean exclusive) {
        this.exclusive = exclusive;
    }
}
