package Matrix;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinearSystemTest {
    private LinearSystem linearSystem;
    double[][] tempMatrix = {{-1, 1, 0, 0, 0}, {1, -2, -2, 0, 0}, {0, 2, -1, 1, 0}, {0, 0, 5, -3, -1}, {0, 0, 0, 1, 1}};
    double[] tempFreeColumn = {-2, 5, -4, 6, -3};


    @Test
    public void gaussianElimination() {
        LinearSystem linearSystem = new LinearSystem(tempMatrix, tempFreeColumn);

        double[] xS = linearSystem.gaussianElimination();
        double[] actual = new double[linearSystem.getSize()];
        double[] expected = linearSystem.getColumn();
        Matrix temp = linearSystem.getMatrix();
        for (int i = 0; i < linearSystem.getSize(); ++i) {
            double[] row = temp.multiplyRows(i, xS);
            double sum = 0;
            for (int j = 0; j < row.length; ++j) {
                sum += row[j];
            }
            actual[i] = sum;
        }
        double delta = 0.00001;
        Assert.assertArrayEquals(expected, actual, delta);
    }

    @Test
    public void getInverse() {
    }

    @Test
    public void directSweep() {
        LinearSystem linearSystem = new LinearSystem(tempMatrix, tempFreeColumn);

        double[] xS = linearSystem.directSweep();
        double[] actual = new double[linearSystem.getSize()];
        double[] expected = linearSystem.getColumn();
        Matrix temp = linearSystem.getMatrix();
        for (int i = 0; i < linearSystem.getSize(); ++i) {
            double[] row = temp.multiplyRows(i, xS);
            double sum = 0;
            for (int j = 0; j < row.length; ++j) {
                sum += row[j];
            }
            actual[i] = sum;
        }
        double delta = 0.00001;
        Assert.assertArrayEquals(expected, actual, delta);
    }
}