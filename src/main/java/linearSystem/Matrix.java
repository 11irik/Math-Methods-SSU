package linearSystem;

import java.util.Arrays;

public class Matrix {
    private int rowsCount;
    private int columnsCount;
    private double[][] array;

    Matrix(int rowCount, int columnCount) {
        this.rowsCount = rowCount;
        this.columnsCount = columnCount;
        array = new double[rowCount][columnCount];
    }

    Matrix(double[][] array) {
        rowsCount = array.length;
        columnsCount = array[0].length;
        this.array = new double[rowsCount][columnsCount];
        for (int i = 0; i < rowsCount; ++i) {
            System.arraycopy(array[i], 0, this.array[i], 0, columnsCount);
        }
    }

    Matrix(Matrix matrix) {
        rowsCount = matrix.rowsCount;
        columnsCount = matrix.columnsCount;
        this.array = new double[rowsCount][columnsCount];
        for (int i = 0; i < rowsCount; ++i) {
            if (columnsCount >= 0) System.arraycopy(matrix.array[i], 0, this.array[i], 0, columnsCount);
        }
    }

    int getRowsCount() {
        return rowsCount;
    }

    int getColumnsCount() {
        return columnsCount;
    }

    void setZeroes() {
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; ++j) {
                array[i][j] = 0;
            }
        }
    }

    void swapRows(int l, int j) {
        //todo exception
        double[] temp = array[j];
        array[j] = array[l];
        array[l] = temp;
    }

    void multiplyRow(int rowNumber, double k) {
        for (int i = 0; i < columnsCount; ++i) {
            array[rowNumber][i] *= k;
        }
    }

    void sumRows(int sumRowNumber, int termRowNumber) {
        //todo exception
        for (int i = 0; i < columnsCount; ++i) {
            array[sumRowNumber][i] += array[termRowNumber][i];
        }
    }

    private double[] multiplyRows(int multipliedRowNumber, double[] row) {
        //todo exception
        double[] temp = Arrays.copyOf(array[multipliedRowNumber], array.length);
        for (int i = 0; i < columnsCount; ++i) {
            temp[i] *= row[i];
        }
        return temp;
    }

    private int getRowSum(int rowNumber) {
        int sum = 0;
        for (int i = 0; i < columnsCount; ++i) {
            sum += array[rowNumber][i];
        }
        return sum;
    }

    private static double getRowSum(double[] row) {
        double sum = 0;
        for (int i = 0; i < row.length; ++i) {
            sum += row[i];
        }
        return sum;
    }

    void multiplyAndSumRows(int sumRowNumber, int termRowNumber, double k) {
        //TODO exception
        for (int i = 0; i < columnsCount; ++i) {
            array[sumRowNumber][i] += array[termRowNumber][i] * k;
        }
    }

    public double[] multiplyByColumn(double[] column) {
        //todo check size compatibility
        double[] temp = new double[column.length];
        for (int i = 0; i < rowsCount; ++i) {
            double[] row = multiplyRows(i, column);
            temp[i] = Matrix.getRowSum(row);
        }
        return temp;
    }

    int selectMainItem(int rowNumber, int columnNumber) {
        double mainItem = Math.abs(array[rowNumber][columnNumber]);
        int mainItemNumber = rowNumber;
        for (int i = rowNumber; i < rowsCount; ++i) {
            if (Math.abs(mainItem) < Math.abs(array[i][columnNumber])) {
                mainItem = array[i][columnNumber];
                mainItemNumber = i;
            }
        }
        if (mainItem == 0) {
            return -1;
        }
        else {
            return mainItemNumber;
        }
    }

    boolean checkZero(int rowNumber, int columnNumber) {
        double eps = 0.0000001;
        return (Math.abs(array[rowNumber][columnNumber]) <= eps);
    }

    double getValue(int rowNumber, int columnNumber) {
        return array[rowNumber][columnNumber];
    }
    void setValue(int rowNumber, int columnNumber, double element) {
        array[rowNumber][columnNumber] = element;
    }

    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < rowsCount; ++i) {
            for (int j = 0; j < columnsCount; ++j) {
                temp.append(array[i][j]).append(" ");
            }
            temp.append("\n");
        }
        return temp.toString();
    }
}
