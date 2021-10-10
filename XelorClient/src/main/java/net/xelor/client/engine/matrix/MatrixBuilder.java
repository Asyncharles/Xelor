package net.xelor.client.engine.matrix;

import java.util.Random;
import java.util.function.ToDoubleBiFunction;

public abstract class MatrixBuilder {
    private final Random random = new Random();
    private final int rows;
    private final int columns;

    public MatrixBuilder(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public abstract Matrix getMatrix();

    public Matrix randomMatrixGenerator(int min, int max) {
        return build((integer, integer2) -> random.nextInt(max) * 2 - Math.abs(min));
    }

    public Matrix build(ToDoubleBiFunction<Integer, Integer> biFunction) {
        Matrix matrix = new Matrix(rows, columns);
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                matrix.data[x][y] = biFunction.applyAsDouble(x, y);
            }
        }
        return matrix;
    }
}
