package net.xelor.client.engine.matrix;

import net.xelor.client.utils.ToBigDecimalBiFunction;

import java.math.BigDecimal;
import java.util.Random;

public abstract class HeavyMatrixBuilder {
    private final Random random = new Random();
    private final int rows;
    private final int columns;

    public HeavyMatrixBuilder(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public abstract Matrix getMatrix();

    public HeavyMatrix randomMatrixGenerator(int min, int max) {
        return build((integer, integer2) -> BigDecimal.valueOf(random.nextInt(max) * 2L - Math.abs(min)));
    }

    public HeavyMatrix build(ToBigDecimalBiFunction<Integer, Integer> biFunction) {
        HeavyMatrix matrix = new HeavyMatrix(rows, columns);
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                matrix.data[x][y] = biFunction.applyAsBigDecimal(x, y);
            }
        }
        return matrix;
    }
}
