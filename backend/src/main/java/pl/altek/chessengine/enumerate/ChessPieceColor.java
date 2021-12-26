package pl.altek.chessengine.enumerate;

public enum ChessPieceColor {
    WHITE("w"),
    BLACK("b");

    private String value;

    ChessPieceColor(String value) {
        this.value = value;
    }
}
