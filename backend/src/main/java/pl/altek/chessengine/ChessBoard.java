package pl.altek.chessengine;

import pl.altek.chessengine.enumerate.ChessCastle;
import pl.altek.chessengine.enumerate.ChessPieceColor;
import pl.altek.chessengine.enumerate.ChessPieceType;
import pl.altek.chessengine.model.ChessNextMove;

import java.util.List;

public class ChessBoard {
    private ChessPiece[][] board;
    private ChessPieceColor nextMove = ChessPieceColor.WHITE;
    private final List<Character> PIECE_VALUES = ChessPieceType.pieceValues();
    private final Character PIECE_CAPTURES_CHAR = 'x';

    public static ChessBoard create(){
        ChessBoard board = new ChessBoard();
        board.initializeBoard();
        return board;
    }

    private void initializeBoard(){
        this.board = new ChessPiece[8][8];
        this.board[0][0] = ChessPiece.white(ChessPieceType.ROOK);
        this.board[0][1] = ChessPiece.white(ChessPieceType.KNIGHT);
        this.board[0][2] = ChessPiece.white(ChessPieceType.BISHOP);
        this.board[0][3] = ChessPiece.white(ChessPieceType.QUEEN);
        this.board[0][4] = ChessPiece.white(ChessPieceType.KING);
        this.board[0][5] = ChessPiece.white(ChessPieceType.BISHOP);
        this.board[0][6] = ChessPiece.white(ChessPieceType.KNIGHT);
        this.board[0][7] = ChessPiece.white(ChessPieceType.ROOK);
        for(int i = 0; i < 8; i++){
            this.board[1][i] = ChessPiece.white(ChessPieceType.PAWN);
        }
        for(int i = 0; i < 8; i++){
            this.board[6][i] = ChessPiece.black(ChessPieceType.PAWN);
        }
        this.board[7][0] = ChessPiece.black(ChessPieceType.ROOK);
        this.board[7][1] = ChessPiece.black(ChessPieceType.KNIGHT);
        this.board[7][2] = ChessPiece.black(ChessPieceType.BISHOP);
        this.board[7][3] = ChessPiece.black(ChessPieceType.QUEEN);
        this.board[7][4] = ChessPiece.black(ChessPieceType.KING);
        this.board[7][5] = ChessPiece.black(ChessPieceType.BISHOP);
        this.board[7][6] = ChessPiece.black(ChessPieceType.KNIGHT);
        this.board[7][7] = ChessPiece.black(ChessPieceType.ROOK);

    }

    private ChessPieceColor getNextMoveColor(){
        ChessPieceColor color = this.nextMove;
        this.nextMove = color.equals(ChessPieceColor.WHITE) ? ChessPieceColor.BLACK : ChessPieceColor.WHITE;
        return color;
    }

    private void move(String moveValue){
        ChessNextMove nextMove = parseNextMove(moveValue);
        ChessPiece pieceToMove = nextMove.getPiece();
        if(pieceToMove.getType().equals(ChessPieceType.PAWN)){
            movePawn(nextMove);
        }
    }

    private void movePawn(ChessNextMove nextMove){
        ChessPieceColor color = nextMove.getPieceColor();

    }

    // Posible moves: Bg5, e4, Qxf6, exd5, O-O-O, O-O, Nfd4, Rab3
    public ChessNextMove parseNextMove(String symbol){
        ChessCastle castle = ChessCastle.fromSymbol(symbol);
        if(null != castle){
            return ChessNextMove.castle(symbol, castle, getNextMoveColor());
        }

        Character firstLetter = symbol.charAt(0);
        Character secondLetter = symbol.charAt(1);
        ChessPieceType piece = ChessPieceType.byValue(firstLetter);
        ChessPiece chessPiece = new ChessPiece(piece, getNextMoveColor());

        Character lastColumn = null;
        Character column = firstLetter;
        Character row = secondLetter;

        if(secondLetter.equals(PIECE_CAPTURES_CHAR)){
            column = symbol.charAt(2);
            row = symbol.charAt(3);
            if(ChessNextMove.isColumn(row)){
                lastColumn = column;
                column = row;
                row = symbol.charAt(4);
            }
        } else if(!piece.equals(ChessPieceType.PAWN)) {
            column = symbol.charAt(1);
            row = symbol.charAt(2);
        }
        return new ChessNextMove(
                symbol,
                chessPiece,
                lastColumn,
                column,
                row
        );
    }
}
