package net.xelor.client.structures.targets;

import java.util.Objects;

public class Result<T> {
    private final T result;
    private final long resultTimestamp;

    public Result(T t) {
        this.result = t;
        this.resultTimestamp = System.currentTimeMillis();
    }

    public T getResult() {
        return result;
    }

    public long getResultTimestamp() {
        return resultTimestamp;
    }

    public Class<?> getType() {
        return result.getClass();
    }

    public static Result<?> empty() {
        return new Result<>(Long.MAX_VALUE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Result<?> r = (Result<?>) o;
        if (!hasEqualResultType(this, r)) return false;
        return Objects.equals(result, r.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result);
    }

    protected static boolean hasEqualResultType(Result<?> a, Result<?> b) {
        if (a.getType().getComponentType() != null && b.getType().getComponentType() != null) {
            return a.getType().getComponentType().getTypeName().equals(b.getType().getComponentType().getTypeName());
        }
        return a.getType() == b.getType();
    }
}
