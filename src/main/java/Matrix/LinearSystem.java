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
        //todo size exception
        if (array.length != column.length) {
            throw new IllegalArgumentException();
        }
        this.matrix = new Matrix(array);
        this.column = Arrays.copyOf(column, column.length);
        this.size = array.length;
    }

    public LinearSystem(Matrix matrix, double[] column, int size) {
        //todo size exception
        this.matrix = new Matrix(matrix);
        this.column = Arrays.copyOf(column, column.length);
        this.size = size;
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

    //paragraph 1
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

    //paragraph 2
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

    //paragraph 3
    public double[] tridiagonalAlgorithm() {
        //todo size exception
        double[] ps = new double[size-1];
        double[] qs = new double[size];

        ps[0] = matrix.getValue(0, 1) / matrix.getValue(0, 0) / -1;
        qs[0] = column[0] / matrix.getValue(0, 0);
        for (int i = 1; i < size-1; ++i) {
            ps[i] = matrix.getValue(i, i+1) /
                    (-1 * matrix.getValue(i, i) - matrix.getValue(i, i-1) * ps[i-1]);
            qs[i] = (matrix.getValue(i, i-1) * qs[i-1] - column[i]) /
                    (-1 * matrix.getValue(i, i) - matrix.getValue(i, i-1) * ps[i-1]);
        }
        qs[size-1] = (matrix.getValue(size-1, size-2) * qs[size-2] - column[size-1]) /
                (-1 * matrix.getValue(size-1, size-1) - matrix.getValue(size-1, size-2) * ps[size-2]);

        double[] xs = new double[size];
        xs[size-1] = qs[size-1];
        for (int i = size-2; i >= 0; --i) {
            xs[i] = ps[i] * xs[i+1] + qs[i];
        }
        return xs;
    }

    //paragraph 4
    private LinearSystem getRepresentation() {
        LinearSystem linearSystem = new LinearSystem(this);

        for (int i = 0; i < linearSystem.size; ++i) {
            linearSystem.multiplyLine(i, 1/linearSystem.matrix.getValue(i, i));
            linearSystem.matrix.setValue(i, i, 0);
        }
        return linearSystem;
    }

    private static double max(double[] x)
    {
        double norma = 0;
        for (int i = 0 ; i < x.length; i++)
        {
            norma += x[i] * x[i];
        }
        return Math.sqrt(norma);
    }

    public double[] fixedPointIteration() {
        LinearSystem linearSystem = this.getRepresentation();

        double[] xFirsts = new double[size];
        double[] xSeconds = new double[size];

        double norm;
        double eps = 0.0001;
        do {
            double[] xs = new double[linearSystem.size];
            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) {
                    xs[i] += linearSystem.matrix.getValue(i, j) * xFirsts[j];
                }
            }
            for (int i = 0; i < size; ++i) {
                xSeconds[i] = xs[i] + linearSystem.column[i];
            }
            double[] w = new double[size];
            for (int i = 0; i < size; ++i) {
                w[i] = xSeconds[i] - xFirsts[i];
            }
            System.out.println(Arrays.toString(xSeconds));
            System.out.println(Arrays.toString(xFirsts));
            norm = max(w);
            System.arraycopy(xSeconds, 0, xFirsts, 0, size);

        } while  (norm > eps);

        return null;
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
