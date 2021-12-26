package pl.altek.chessengine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.altek.chessengine.enumerate.ChessCastle;
import pl.altek.chessengine.enumerate.ChessPieceType;
import pl.altek.chessengine.model.ChessNextMove;

import static org.junit.jupiter.api.Assertions.*;

class ChessBoardTest {
    //  0,1,2,3,4,5,6,7
    //  a,b,c,d,e,f,g,h
    //  1,2,3,4,5,6,7,8
    //    Bg5, e4, Qxf6, exd5, O-O-O, O-O, Nfd4, Rab3

    @Test
    void parseNextMove_e4() {
        ChessBoard chessBoard = new ChessBoard();
        ChessNextMove nextMove = chessBoard.parseNextMove("e4");
        ChessPiece piece = nextMove.getPiece();
        ChessCastle castle = nextMove.getCastle();

        assertNull(nextMove.getLastColumnIndex());
        assertEquals(nextMove.getColumnIndex(), 4);
        assertEquals(nextMove.getRowIndex(), 3);
        assertEquals(piece.getType(), ChessPieceType.PAWN);
        assertNull(castle);
    }

    @Test
    void parseNextMove_Bg5() {
        ChessBoard chessBoard = new ChessBoard();
        ChessNextMove nextMove = chessBoard.parseNextMove("Bg5");
        ChessPiece piece = nextMove.getPiece();
        ChessCastle castle = nextMove.getCastle();

        assertNull(nextMove.getLastColumnIndex());
        assertEquals(nextMove.getColumnIndex(), 6);
        assertEquals(nextMove.getRowIndex(), 4);
        assertEquals(piece.getType(), ChessPieceType.BISHOP);
        assertNull(castle);
    }

    @Test
    void parseNextMove_Qxf6() {
        ChessBoard chessBoard = new ChessBoard();
        ChessNextMove nextMove = chessBoard.parseNextMove("Qxf6");
        ChessPiece piece = nextMove.getPiece();
        ChessCastle castle = nextMove.getCastle();

        assertNull(nextMove.getLastColumnIndex());
        assertEquals(nextMove.getColumnIndex(), 5);
        assertEquals(nextMove.getRowIndex(), 5);
        assertEquals(piece.getType(), ChessPieceType.QUEEN);
        assertNull(castle);
    }

    @Test
    void parseNextMove_exd5() {
        ChessBoard chessBoard = new ChessBoard();
        ChessNextMove nextMove = chessBoard.parseNextMove("exd5");
        ChessPiece piece = nextMove.getPiece();
        ChessCastle castle = nextMove.getCastle();

        assertEquals(nextMove.getLastColumnIndex(), 4);
        assertEquals(nextMove.getColumnIndex(), 3);
        assertEquals(nextMove.getRowIndex(), 4);
        assertEquals(piece.getType(), ChessPieceType.PAWN);
        assertNull(castle);
    }

    @Test
    void parseNextMove_Nfd4() {
        ChessBoard chessBoard = new ChessBoard();
        ChessNextMove nextMove = chessBoard.parseNextMove("Nfd4");
        ChessPiece piece = nextMove.getPiece();
        ChessCastle castle = nextMove.getCastle();

        assertEquals(nextMove.getLastColumnIndex(), 5);
        assertEquals(nextMove.getColumnIndex(), 3);
        assertEquals(nextMove.getRowIndex(), 3);
        assertEquals(piece.getType(), ChessPieceType.KNIGHT);
        assertNull(castle);
    }

    @Test
    void parseNextMove_Rab3() {
        ChessBoard chessBoard = new ChessBoard();
        ChessNextMove nextMove = chessBoard.parseNextMove("Rab3");
        ChessPiece piece = nextMove.getPiece();
        ChessCastle castle = nextMove.getCastle();

        assertEquals(nextMove.getLastColumnIndex(), 0);
        assertEquals(nextMove.getColumnIndex(), 1);
        assertEquals(nextMove.getRowIndex(), 2);
        assertEquals(piece.getType(), ChessPieceType.ROOK);
        assertNull(castle);
    }

    @Test
    void parseNextMove_castleLong() {
        ChessBoard chessBoard = new ChessBoard();
        ChessNextMove nextMove = chessBoard.parseNextMove("O-O-O");
        ChessPiece piece = nextMove.getPiece();
        ChessCastle castle = nextMove.getCastle();

        assertNull(nextMove.getLastColumnIndex());
        assertNull(nextMove.getColumnIndex());
        assertNull(nextMove.getRowIndex());
        assertEquals(piece.getType(), ChessPieceType.KING);
        assertNotNull(castle);
        assertEquals(castle, ChessCastle.LONG);
    }

    @Test
    void parseNextMove_castleShort() {
        ChessBoard chessBoard = new ChessBoard();
        ChessNextMove nextMove = chessBoard.parseNextMove("O-O");
        ChessPiece piece = nextMove.getPiece();
        ChessCastle castle = nextMove.getCastle();

        assertNull(nextMove.getLastColumnIndex());
        assertNull(nextMove.getColumnIndex());
        assertNull(nextMove.getRowIndex());
        assertEquals(piece.getType(), ChessPieceType.KING);
        assertNotNull(castle);
        assertEquals(castle, ChessCastle.SHORT);
    }
}