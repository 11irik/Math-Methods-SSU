package Matrix;

import java.util.Arrays;

public class LinearSystem {
    private Matrix matrix;
    private double[] column;

    public LinearSystem(int a, int b) {
        this.matrix = new Matrix(a, b);
        this.column = new double[a];
        this.matrix.setZeroes();
        for (int i = 0; i < a; i++) {
            this.column[i] = 0;
        }
    }

    public LinearSystem(LinearSystem linearSystem) {
        this.matrix = new Matrix(linearSystem.matrix);
        this.column = new double[matrix.getRowsCount()];

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
        //TODO exception
        matrix.sumRows(sumLineNumber, termLineNumber);
        column[sumLineNumber] += column[termLineNumber];
    }

    private void multiplyAndSumLines(int sumLineNumber, int termLineNumber, double k) {
        //TODO exception
        matrix.multiplyAndSumRows(sumLineNumber, termLineNumber, k);
        column[sumLineNumber] += column[termLineNumber] * k;
    }

    private double diagonalize() {          //also return determinant
        int count = 0;
        double determinant = 1;

        for (int i = 0; i < matrix.getRowsCount(); ++i) {
            if (matrix.checkZero(i, i)) {
                int number = matrix.selectMainItem(i, i);
                if (number == -1) {
                    break;
                    //TODO
                } else {
                    swapLines(number, i);
                }
                count++;
            }

            determinant *= matrix.getValue(i, i);
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

    public Matrix getReverse() {
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
