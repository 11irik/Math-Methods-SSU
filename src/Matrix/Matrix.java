package Matrix;

public class Matrix {
    int rowsCount;
    int columnsCount;
    double[][] array;

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
            for (int j = 0; j < columnsCount; ++j) {
                this.array[i][j] = array[i][j];
            }
        }
    }

    public Matrix(Matrix matrix) {
        rowsCount = matrix.rowsCount;
        columnsCount = matrix.columnsCount;
        this.array = new double[rowsCount][columnsCount];
        for (int i = 0; i < rowsCount; ++i) {
            for (int j = 0; j < columnsCount; ++j) {
                this.array[i][j] = matrix.array[i][j];
            }
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
        //todo exception
        double[] temp = array[j];
        array[j] = array[l];
        array[l] = temp;
    }

    public void multiplyRow(int rowNumber, double k) {
        for (int i = 0; i < columnsCount; ++i) {
            array[rowNumber][i] *= k;
        }
    }

    public void sumRows(int sumRowNumber, int termRowNumber) {
        //todo exception
        for (int i = 0; i < columnsCount; ++i) {
            array[sumRowNumber][i] += array[termRowNumber][i];
        }
    }

    public void multiplyAndSumRows(int sumRowNumber, int termRowNumber, double k) {
        //TODO exception
        for (int i = 0; i < columnsCount; ++i) {
            array[sumRowNumber][i] += array[termRowNumber][i] * k;
        }
    }

    public int selectMainItem(int rowNumber, int columnNumber) {
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
        return (array[rowNumber][columnNumber] == 0.0);
    }

    public double getValue(int rowNumber, int columnNumber) {
        return array[rowNumber][columnNumber];
    }
    public void setValue(int rowNumber, int columnNumber, double element) {
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
