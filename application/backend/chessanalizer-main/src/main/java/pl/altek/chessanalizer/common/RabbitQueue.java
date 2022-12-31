package pl.altek.chessanalizer.common;

import lombok.experimental.FieldNameConstants;

@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum RabbitQueue {
    @FieldNameConstants.Include PROCESS_PGN;
}
