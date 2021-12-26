package pl.altek.chessengine.model;

import lombok.Getter;
import pl.altek.chessengine.ChessPiece;
import pl.altek.chessengine.enumerate.ChessCastle;
import pl.altek.chessengine.enumerate.ChessPieceColor;
import pl.altek.chessengine.enumerate.ChessPieceType;

@Getter
public class ChessNextMove {
    private ChessPiece piece;
    private Integer rowIndex;        // 1-8
    private Integer columnIndex;     // a-h
    private Integer lastColumnIndex; // a-h
    private ChessCastle castle;
    private String symbol;

    private static final String COLUMNS_VALUES = "abcdefgh";

    private ChessNextMove() {
    }

    public ChessNextMove(String symbol, ChessPiece piece, Character lastColumn, Character column, Character row) {
        this.symbol = symbol;
        this.piece = piece;
        this.rowIndex = Integer.parseInt(row.toString()) - 1;
        this.columnIndex = COLUMNS_VALUES.indexOf(column);
        this.lastColumnIndex = null == lastColumn ? null : COLUMNS_VALUES.indexOf(lastColumn);
        this.castle = null;
    }

    public static ChessNextMove castle(String symbol, ChessCastle castle, ChessPieceColor color){
        ChessNextMove nextMove = new ChessNextMove();
        nextMove.symbol = symbol;
        nextMove.piece = new ChessPiece(ChessPieceType.KING, color);
        nextMove.castle = castle;
        return nextMove;
    }

    public static Boolean isColumn(Character value){
        return COLUMNS_VALUES.contains(value.toString());
    }

    public ChessPieceColor getPieceColor(){
        return this.piece.getColor();
    }
}
