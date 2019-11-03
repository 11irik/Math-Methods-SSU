package Matrix;

import java.util.Arrays;

public class LinearSystem {
    private Matrix matrix;
    private double[] column;
    private int size;

    public LinearSystem(int a, int b) {
        this.matrix = new Matrix(a, b);
        this.column = new double[a];
        this.size = a;
        this.matrix.setZeroes();
        for (int i = 0; i < a; i++) {
            this.column[i] = 0;
        }
    }

    public LinearSystem(LinearSystem linearSystem) {
        this.matrix = new Matrix(linearSystem.matrix);
        this.column = new double[matrix.getRowsCount()];
        this.size = linearSystem.size;

        for (int i = 0; i < column.length; i++) {
            this.column[i] = linearSystem.column[i];
        }
    }

    public LinearSystem(double[][] array, double[] column) {
        if (array.length != column.length) {
            throw new IllegalArgumentException();
        }
        this.matrix = new Matrix(array);
        this.column = Arrays.copyOf(column, column.length);
        this.size = array.length;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public double[] getColumn() {
        return column;
    }

    public int getSize() {
        return size;
    }

    private void swapLines(int l, int j) {
        //todo exception
        matrix.swapRows(l, j);
        double tempX = column[l];
        column[l] = column[j];
        column[j] = tempX;
    }

    private void multiplyLine(int lineNumber, double k) {
        //todo exception
        matrix.multiplyRow(lineNumber, k);
        column[lineNumber] *= k;
    }

    private void sumLines(int sumLineNumber, int termLineNumber) {
        //todo exception
        matrix.sumRows(sumLineNumber, termLineNumber);
        column[sumLineNumber] += column[termLineNumber];
    }

    private void multiplyAndSumLines(int sumLineNumber, int termLineNumber, double k) {
        //todo exception
        matrix.multiplyAndSumRows(sumLineNumber, termLineNumber, k);
        column[sumLineNumber] += column[termLineNumber] * k;
    }

    private double diagonalize() {          //also returns determinant
        int count = 0;
        double determinant = 1;

        for (int i = 0; i < matrix.getRowsCount(); ++i) {
            if (matrix.checkZero(i, i)) {
                int number = matrix.selectMainItem(i, i);
                if (number == -1) {
                    break;
                    //todo
                } else {
                    swapLines(number, i);
                }
                count++;
            }

            multiplyLine(i, 1 / matrix.getValue(i, i));
            for (int j = i + 1; j < matrix.getRowsCount(); ++j) {
                multiplyAndSumLines(j, i, -matrix.getValue(j, i));
            }
        }
        return determinant * Math.pow(-1, count);
    }

    public double[] gaussianElimination() {
        LinearSystem tempSystem = new LinearSystem(this);
        tempSystem.diagonalize();

        double[] x = new double[tempSystem.matrix.getRowsCount()];
        Arrays.fill(x, 0);
        x[x.length - 1] = tempSystem.column[x.length - 1];

        for (int i = x.length - 2; i >= 0; --i) {
            double sum = 0;
            for (int j = x.length - 1; j > i; --j) {
                sum += tempSystem.matrix.getValue(i, j) * x[j];
            }
            x[i] = (tempSystem.column[i] - sum);
        }

        return x;
    }

    public Matrix getInverse() {
        Matrix reversedMatrix = new Matrix(matrix.getRowsCount(), matrix.getColumnsCount());
        for (int i = 0; i < reversedMatrix.getRowsCount(); ++i) {
            LinearSystem temp = new LinearSystem(this);
            double[] unitColumn = new double[reversedMatrix.getRowsCount()];
            Arrays.fill(unitColumn, 0);
            unitColumn[i] = 1;
            temp.column = unitColumn;
            double[] iColumn = temp.gaussianElimination();
            for (int j = 0; j < reversedMatrix.getColumnsCount(); ++j) {
                reversedMatrix.setValue(j, i, iColumn[j]);
            }
        }
        return reversedMatrix;
    }

    public double[] directSweep() {
        double[] pS = new double[size-1];
        double[] qS = new double[size];

        pS[0] = matrix.getValue(0, 1) / matrix.getValue(0, 0) / -1;
        qS[0] = column[0] / matrix.getValue(0, 0);
        for (int i = 1; i < size-1; ++i) {
            pS[i] = matrix.getValue(i, i+1) /
                    (-1 * matrix.getValue(i, i) - matrix.getValue(i, i-1) * pS[i-1]);
            qS[i] = (matrix.getValue(i, i-1) * qS[i-1] - column[i]) /
                    (-1 * matrix.getValue(i, i) - matrix.getValue(i, i-1) * pS[i-1]);
        }
        qS[column.length-1] = (matrix.getValue(size-1, size-2) * qS[size-2] - column[size-1]) /
                (-1 * matrix.getValue(size-1, size-1) - matrix.getValue(size-1, size-2) * pS[size-2]);

        double[] xS = new double[size];
        xS[size-1] = qS[size-1];
        for (int i = size-2; i >= 0; --i) {
            xS[i] = pS[i] * xS[i+1] + qS[i];
        }
        return xS;
    }

    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < matrix.getRowsCount(); ++i) {
            for (int j = 0; j < matrix.getColumnsCount(); ++j) {
                temp.append(matrix.getValue(i, j)).append(" ");
            }
            temp.append("\t").append(column[i]);
            temp.append("\n");
        }
        return temp.toString();
    }
}
