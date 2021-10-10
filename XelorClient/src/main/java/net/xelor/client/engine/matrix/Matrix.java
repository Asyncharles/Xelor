package net.xelor.client.engine.matrix;


public class Matrix {
    private int rows, columns;
    protected double[][] data;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.data = new double[rows][columns];
    }
}
