package pl.altek.analyzer.module.gameAnalyzer.model;

import java.util.ArrayList;
import java.util.List;

public enum GameResult {
    WIN,
    DRAW,
    LOSE;

    private static final List<String> chessComResultDraw = new ArrayList<>() {{
        add("agreed");
        add("repetition");
        add("stalemate");
        add("insufficient");
        add("50move");
        add("timevsinsufficient");
    }};

    public static GameResult fromChessComGameResult(String result) {
        if ("win".equals(result)) {
            return GameResult.WIN;
        }
        if (chessComResultDraw.contains(result)) {
            return GameResult.DRAW;
        }
        return GameResult.LOSE;
    }
}
