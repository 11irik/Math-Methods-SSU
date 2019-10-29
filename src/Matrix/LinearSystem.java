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

//    private void swapColumns(int k, int l) {
//
//    }


    private void multiplyLine(int lineNumber, double k) {
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

    private int selectMainItemLineNumber(int lineNumber, int columnNumber) {
        return matrix.selectMainItem(lineNumber, columnNumber);
    }

    private double diagonalize() {
        int count = 0;
        double determinant = 1;

        for (int i = 0; i < matrix.getRowsCount(); ++i) {
            if (matrix.checkZero(i, i)){
                count++;
                int number = selectMainItemLineNumber(i, i);
                if (number == -1) {
                    break;
                    //TODO
                }
                else {
                    matrix.swapRows(number, i);
                }
            }

            determinant *= matrix.getValue(i, i);
            multiplyLine(i, 1 / matrix.getValue(i, i));
            for (int j = i+1; j < matrix.rowsCount; ++j) {
                multiplyAndSumLines(j, i, -matrix.getValue(j, i));
            }
        }
        //System.out.println(determinant * Math.pow(-1, count));
        return determinant * Math.pow(-1, count);
    }

    double[] gaussian() {
        diagonalize();

        double[] x = new double[matrix.rowsCount];
        for (int i = 0; i < x.length; ++i) {
            x[i] = 0;
        }
        x[x.length -1] = column[x.length -1];

        for (int i = x.length -2; i >= 0; --i) {
            double sum = 0;
            for (int j = x.length-1; j > i; --j) {
                sum += matrix.getValue(i, j) * x[j];
            }
            x[i] = (column[i] - sum);
        }

        return x;
    }

    public Matrix getReverse() {
        Matrix reversedMatrix = new Matrix(matrix.rowsCount, matrix.columnsCount);
        for (int i = 0; i < reversedMatrix.rowsCount; ++i) {
            LinearSystem temp = new LinearSystem(this);
            double[] unitColumn = new double[reversedMatrix.rowsCount];
            for (int j = 0; j < unitColumn.length; ++j) {
                unitColumn[j] = 0;
            }
            unitColumn[i] = 1;
            temp.column = unitColumn;
            double[] iColumn = temp.gaussian();
            for (int j = 0; j < reversedMatrix.columnsCount; ++j) {
                reversedMatrix.setValue(j,i,iColumn[j]);
            }
        }
        return reversedMatrix;
    }

    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < matrix.rowsCount; ++i) {
            for (int j = 0; j < matrix.columnsCount; ++j) {
                temp.append(matrix.getValue(i, j)).append(" ");
            }
            temp.append("\t").append(column[i]);
            temp.append("\n");
        }

        return temp.toString();
    }
}
