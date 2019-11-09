package Matrix;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class LinearSystemTest {
    private LinearSystem linearSystem;
    double[][] tempMatrix = {{-1, 1, 0, 0, 0}, {1, -2, -2, 0, 0}, {0, 2, -1, 1, 0}, {0, 0, 5, -3, -1}, {0, 0, 0, 1, 1}};
    double[] tempFreeColumn = {-2, 5, -4, 6, -3};


    @Test
    public void gaussianElimination() {
        LinearSystem linearSystem = new LinearSystem(tempMatrix, tempFreeColumn);

        double[] xs = linearSystem.gaussianElimination();
        double[] actual = linearSystem.getMatrix().multiplyByColumn(xs);
        double[] expected = linearSystem.getColumn();

        double delta = 0.00001;
        Assert.assertArrayEquals(expected, actual, delta);
    }

    @Test
    public void getInverse() {
    }

    @Test
    public void directSweep() {
        LinearSystem linearSystem = new LinearSystem(tempMatrix, tempFreeColumn);

        double[] xs = linearSystem.tridiagonalAlgorithm();
        double[] actual = linearSystem.getMatrix().multiplyByColumn(xs);
        double[] expected = linearSystem.getColumn();

        double delta = 0.00001;
        Assert.assertArrayEquals(expected, actual, delta);

    }

    @Test
    public void fixedPointIteration() {
        LinearSystem linearSystem = new LinearSystem(tempMatrix, tempFreeColumn);
        linearSystem.fixedPointIteration();
    }
}