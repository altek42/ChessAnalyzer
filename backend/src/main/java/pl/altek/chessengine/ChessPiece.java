package pl.altek.chessengine;

import lombok.Data;
import pl.altek.chessengine.enumerate.ChessPieceColor;
import pl.altek.chessengine.enumerate.ChessPieceType;

@Data
public class ChessPiece {
    private ChessPieceType type;
    private ChessPieceColor color;

    public ChessPiece(ChessPieceType type, ChessPieceColor color) {
        this.type = type;
        this.color = color;
    }

    public static ChessPiece white(ChessPieceType type){
        return new ChessPiece(type, ChessPieceColor.WHITE);
    }

    public static ChessPiece black(ChessPieceType type){
        return new ChessPiece(type, ChessPieceColor.BLACK);
    }
}
