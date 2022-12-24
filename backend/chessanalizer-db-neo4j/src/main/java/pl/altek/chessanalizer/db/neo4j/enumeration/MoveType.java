package pl.altek.chessanalizer.db.neo4j.enumeration;

import lombok.experimental.FieldNameConstants;

@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum MoveType {
    @FieldNameConstants.Include PLAYER,
    @FieldNameConstants.Include ENEMY
}
