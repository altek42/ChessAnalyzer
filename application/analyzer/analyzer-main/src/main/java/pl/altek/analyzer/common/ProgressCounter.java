package pl.altek.analyzer.common;

public record ProgressCounter(int progress, int size) {

    public static ProgressCounter of(int progress, int size) {
        return new ProgressCounter(progress, size);
    }

    @Override
    public String toString() {
        return "(" + progress + "/" + size + ")";
    }
}
