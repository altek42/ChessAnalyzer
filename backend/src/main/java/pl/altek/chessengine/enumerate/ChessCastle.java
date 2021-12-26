package pl.altek.chessengine.enumerate;

import lombok.Getter;

public enum ChessCastle {
    LONG("O-O-O"),
    SHORT("O-O");

    ChessCastle(String symbol) {
        this.symbol = symbol;
    }

    @Getter
    private String symbol;

    public static ChessCastle fromSymbol(String symbol){
        if(LONG.getSymbol().equals(symbol)) {
            return LONG;
        }
        if(SHORT.getSymbol().equals(symbol)) {
            return SHORT;
        }
        return null;
    }
}
