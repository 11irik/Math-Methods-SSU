package Part2;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Matrix {
    double[][] matrix;
    double[] freeColumn;
    int rowsCount;
    int columnsCount;

    public Matrix(int a, int b) {
        rowsCount = a;
        columnsCount = b;
        matrix = new double[a][b];
        freeColumn = new double[a];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; ++j) {
                matrix[i][j] = 0;
            }
            freeColumn[i] = 0;
        }
    }

    public Matrix(Matrix m) {
        rowsCount = m.rowsCount;
        columnsCount = m.columnsCount;
        for (int i = 0; i < rowsCount; i++)
            for (int j = 0; j < columnsCount; j++)
                matrix[i][j] = m.matrix[i][j];
    }

    public void swapRows(int k, int l) {
        if (k > rowsCount || l > rowsCount) {
            //TODO exception
        }
        else {
            double[] temp = matrix[k];
            matrix[k] = matrix[l];
            matrix[l] = temp;
            double tempX = freeColumn[k];
            freeColumn[k] = freeColumn[l];
            freeColumn[l] = tempX;
        }
    }

    public void Gausa() {

    }


}
