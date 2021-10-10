package net.xelor.client.utils;

import java.math.BigDecimal;

@FunctionalInterface
public interface ToBigDecimalBiFunction<T, U> {
    BigDecimal applyAsBigDecimal(T t, U u);
}
