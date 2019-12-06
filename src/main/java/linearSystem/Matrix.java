package linearSystem;

import java.util.Arrays;

public class Matrix {
    private int rowsCount;
    private int columnsCount;
    private double[][] array;

    public Matrix(int rowCount, int columnCount) {
        this.rowsCount = rowCount;
        this.columnsCount = columnCount;
        array = new double[rowCount][columnCount];
    }

    public Matrix(double[][] array) {
        rowsCount = array.length;
        columnsCount = array[0].length;
        this.array = new double[rowsCount][columnsCount];
        for (int i = 0; i < rowsCount; ++i) {
            System.arraycopy(array[i], 0, this.array[i], 0, columnsCount);
        }
    }

    public Matrix(double[] a, double[] b, double[] c) {
        rowsCount = b.length;
        columnsCount = b.length;
        array = new double[rowsCount][columnsCount];
        setZeroes();
        for (int i = 0; i < rowsCount - 1; ++i) {
            array[i][i] = b[i];
            array[i+1][i] = a[i];
            array[i][i+1] = c[i];
        }
        array[rowsCount-1][rowsCount-1] = b[rowsCount - 1];
    }

    public Matrix(Matrix matrix) {
        rowsCount = matrix.rowsCount;
        columnsCount = matrix.columnsCount;
        this.array = new double[rowsCount][columnsCount];
        for (int i = 0; i < rowsCount; ++i) {
            if (columnsCount >= 0) System.arraycopy(matrix.array[i], 0, this.array[i], 0, columnsCount);
        }
    }

    public int getRowsCount() {
        return rowsCount;
    }

    public int getColumnsCount() {
        return columnsCount;
    }

    public void setZeroes() {
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; ++j) {
                array[i][j] = 0;
            }
        }
    }

    public void swapRows(int l, int j) {
        if (l < 0 || j < 0 || l > rowsCount - 1 || j > rowsCount - 1) {
            throw new NullPointerException();
        }
        double[] temp = array[j];
        array[j] = array[l];
        array[l] = temp;
    }

    public void multiplyRow(int rowNumber, double k) {
        if (rowNumber < 0 || rowNumber > rowsCount - 1) {
            throw new NullPointerException();
        }

        for (int i = 0; i < columnsCount; ++i) {
            array[rowNumber][i] *= k;
        }
    }

    public void sumRows(int sumRowNumber, int termRowNumber) {
        if (sumRowNumber < 0 || termRowNumber < 0 || sumRowNumber > rowsCount - 1 || termRowNumber > rowsCount - 1) {
            throw new NullPointerException();
        }
        for (int i = 0; i < columnsCount; ++i) {
            array[sumRowNumber][i] += array[termRowNumber][i];
        }
    }

    public double[] multiplyRows(int multipliedRowNumber, double[] row) {
        if (multipliedRowNumber < 0 || multipliedRowNumber > rowsCount - 1) {
            throw new NullPointerException();
        }
        double[] temp = Arrays.copyOf(array[multipliedRowNumber], array.length);
        for (int i = 0; i < columnsCount; ++i) {
            temp[i] *= row[i];
        }
        return temp;
    }

    public int getRowSum(int rowNumber) {
        if (rowNumber < 0 || rowNumber > rowsCount - 1) {
            throw new NullPointerException();
        }

        int sum = 0;
        for (int i = 0; i < columnsCount; ++i) {
            sum += array[rowNumber][i];
        }
        return sum;
    }

    public static double getRowSum(double[] row) {
        double sum = 0;
        for (int i = 0; i < row.length; ++i) {
            sum += row[i];
        }
        return sum;
    }

    public void multiplyAndSumRows(int sumRowNumber, int termRowNumber, double k) {
        if (sumRowNumber < 0 || termRowNumber < 0 || sumRowNumber > rowsCount - 1 || termRowNumber > rowsCount - 1) {
            throw new NullPointerException();
        }
        for (int i = 0; i < columnsCount; ++i) {
            array[sumRowNumber][i] += array[termRowNumber][i] * k;
        }
    }

    public double[] multiplyByColumn(double[] column) {
        //todo change exception type
        if (column.length != columnsCount) {
            throw new NegativeArraySizeException();
        }
        double[] temp = new double[column.length];
        for (int i = 0; i < rowsCount; ++i) {
            double[] row = multiplyRows(i, column);
            temp[i] = Matrix.getRowSum(row);
        }
        return temp;
    }

    public int selectMainItem(int rowNumber, int columnNumber) {
        if (rowNumber < 0 || columnNumber < 0 || rowNumber > rowsCount - 1 || columnNumber > columnsCount - 1) {
            throw new NullPointerException();
        }
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

    public boolean checkZero(int rowNumber, int columnNumber) {
        if (rowNumber < 0 || columnNumber < 0 || rowNumber > rowsCount - 1 || columnNumber > columnsCount - 1) {
            throw new NullPointerException();
        }
        double eps = 0.0000001;
        return (Math.abs(array[rowNumber][columnNumber]) <= eps);
    }

    public double getValue(int rowNumber, int columnNumber) {
        if (rowNumber < 0 || columnNumber < 0 || rowNumber > rowsCount - 1 || columnNumber > columnsCount - 1) {
            throw new NullPointerException();
        }
        return array[rowNumber][columnNumber];
    }

    public void setValue(int rowNumber, int columnNumber, double element) {
        if (rowNumber < 0 || columnNumber < 0 || rowNumber > rowsCount - 1 || columnNumber > columnsCount - 1) {
            throw new NullPointerException();
        }
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
