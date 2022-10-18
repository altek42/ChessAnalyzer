package pl.altek.chessanalizer.common;

import lombok.Getter;

@Getter
public class ProgressCounter {
    private final int size;
    private final int progress;

    public ProgressCounter(int progress, int size) {
        this.progress = progress;
        this.size = size;
    }

    public static ProgressCounter of(int progress, int size){
        return new ProgressCounter(progress, size);
    }

    @Override
    public String toString() {
        return "(" + progress + "/" + size + ")";
    }
}
