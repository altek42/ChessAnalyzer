package pl.altek.chessengine.enumerate;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum ChessPieceType {
    PAWN('P'),
    BISHOP('B'),
    KNIGHT('N'),
    ROOK('R'),
    QUEEN('Q'),
    KING('K');

    @Getter
    private Character value;

    ChessPieceType(Character value) {
        this.value = value;
    }

    public static List<Character> pieceValues(){
        return Arrays.stream(ChessPieceType.values()).map(ChessPieceType::getValue).toList();
    }

    public static ChessPieceType byValue(Character value){
        return Arrays.stream(ChessPieceType.values())
                .filter( x -> x.getValue().equals(value))
                .findFirst()
                .orElse(ChessPieceType.PAWN);
    }
}
