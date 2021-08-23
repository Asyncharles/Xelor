package net.xelor.client.utils;

public class Pair<L, R> {

    private final L lValue;
    private final R rValue;

    public Pair(L lValue, R rValue) {
        this.lValue = lValue;
        this.rValue = rValue;
    }

    public static <L, R> Pair<L, R> of(L leftValue, R rightValue) {
        return new Pair<>(leftValue, rightValue);
    }

    public L getLeft() {
        return lValue;
    }

    public R getRight() {
        return rValue;
    }
}
