package net.xelor.client.engine.matrix;

import java.math.BigDecimal;

public class HeavyMatrix {
    private int rows, columns;
    protected BigDecimal[][] data;

    public HeavyMatrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.data = new BigDecimal[rows][columns];
    }
}
