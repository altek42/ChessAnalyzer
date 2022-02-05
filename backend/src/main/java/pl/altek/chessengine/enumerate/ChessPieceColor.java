package pl.altek.chessengine.enumerate;

import lombok.Getter;

@Getter
public enum ChessPieceColor {
    WHITE("w", -1),
    BLACK("b", 1);

    private String value;
    private Integer direction;

    ChessPieceColor(String value, Integer direction) {
        this.value = value;
        this.direction = direction;
    }
}
