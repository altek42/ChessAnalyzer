package pl.altek.analyzer.common;

import lombok.experimental.FieldNameConstants;

@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum RabbitQueue {
    @FieldNameConstants.Include PROCESS_PGN;
}
