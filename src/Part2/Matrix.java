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

    public double[] getRow(int rowNumber) {
        return matrix[rowNumber];
    }

    public double getElement(int i, int j) {
        return matrix[i][j];
    }

    public Matrix(Matrix m) {
        rowsCount = m.rowsCount;
        columnsCount = m.columnsCount;
        matrix = new double[rowsCount][columnsCount];
        freeColumn = new double[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++)
                matrix[i][j] = m.matrix[i][j];

            freeColumn[i] = m.freeColumn[i];
        }
    }

    public void setMatrix(double[][] matrix, double[] freeColumn) {
        this.matrix = matrix;
        this.freeColumn = freeColumn;
    }

    private void swapRows(int k, int l) {
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

    private void swapColumns(int k, int l) {
        if (k > columnsCount || l > columnsCount) {
            //TODO exception
        }
        else {
            double[] temp = new double[rowsCount];
            for (int i = 0; i < rowsCount; ++i) {
                temp[i] = matrix[i][k];
            }
            for (int i = 0; i < rowsCount; ++i) {
                matrix[i][k] = matrix[i][l];
            }
            for (int i = 0; i < rowsCount; ++i) {
                matrix[i][l] = matrix[i][k];
            }
        }
    }

    private void multiplyRow(int rowNumber, double k) {
        for (int i = 0; i < columnsCount; ++i) {
            matrix[rowNumber][i] *= k;
        }
        freeColumn[rowNumber] *= k;
    }

    private double[] multiplyRow(double[] row, double k) {
        double[] temp = Arrays.copyOf(row, row.length);
        for (int i = 0; i < row.length; ++i) {
            temp[i] *= k;
        }
        return temp;
    }

    private void sumRows(int sumRowNumber, int termRowNumber) {
        //TODO exception
        for (int i = 0; i < columnsCount; ++i) {
            matrix[sumRowNumber][i] += matrix[termRowNumber][i];
        }
        freeColumn[sumRowNumber] += freeColumn[termRowNumber];
    }

    private double[] sumRows(double[] sumRow, double[] termRow) {
        //TODO rows length ==
        double[] temp = Arrays.copyOf(sumRow, sumRow.length);
        for (int i = 0; i < sumRow.length; ++i) {
            temp[i] += termRow[i];
        }
        return temp;
    }

    private void multiplyAndSumRows(int sumRowNumber, int termRowNumber, double k) {
        //TODO exception
        for (int i = 0; i < columnsCount; ++i) {
            matrix[sumRowNumber][i] += matrix[termRowNumber][i] * k;
        }
        freeColumn[sumRowNumber] += freeColumn[termRowNumber] * k;
    }

    private int selectMainItemNumber(int lineNumber, int columnNumber) {
        double mainItem = Math.abs(matrix[lineNumber][columnNumber]);
        int mainItemNumber = lineNumber;
        for (int i = lineNumber; i < rowsCount; ++i) {
            if (Math.abs(mainItem) < Math.abs(matrix[i][columnNumber])) {
                mainItem = matrix[i][columnNumber];
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

    private int selectColumnMainItemNumber(int lineNumber, int columnNumber) {
        double mainItem = Math.abs(matrix[lineNumber][columnNumber]);
        int mainItemNumber = columnNumber;
        for (int i = columnNumber; i < columnsCount; ++i) {
            if (Math.abs(mainItem) < Math.abs(matrix[lineNumber][i])) {
                mainItem = matrix[lineNumber][i];
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

    public double[] Gausa() {
        Matrix temp = new Matrix(this);

        for (int i = 0; i < rowsCount - 1; ++i) {
            if (temp.matrix[i][i] == 0){
                int number = selectMainItemNumber(i, i);
                if (number == -1) {
                    number = selectColumnMainItemNumber(i, i);
                    if (number == -1) {
                        //Global item
                    }
                    else {
                        swapColumns(number, i);
                    }
                }
                else {
                swapRows(number, i);
                }
            }

            temp.multiplyRow(i, 1 / temp.matrix[i][i]);

            for (int j = i+1; j < rowsCount; ++j) {
                temp.multiplyAndSumRows(j, i, -temp.matrix[j][i]);
            }
        }

        temp.multiplyRow(rowsCount-1, 1 / temp.matrix[columnsCount-1][columnsCount-1]);

        double[] x = new double[rowsCount];
        for (int i = 0; i < rowsCount; ++i) {
            x[i] = 0;
        }
        x[rowsCount-1] = temp.freeColumn[rowsCount-1];

        for (int i = rowsCount-2; i >= 0; --i) {
            double sum = 0;
            for (int j = columnsCount-1; j > i; --j) {
                sum += temp.matrix[i][j] * x[j];
            }
            x[i] = (temp.freeColumn[i] - sum);
        }

        return x;
    }

    @Override
    public String toString() {
        String temp = "";
        for (int i = 0; i < rowsCount; ++i) {
            for (int j = 0; j < columnsCount; ++j) {
                temp += matrix[i][j] + " ";
            }
            temp += "\t" + freeColumn[i];
            temp += "\n";
        }

        return temp;
    }


}
