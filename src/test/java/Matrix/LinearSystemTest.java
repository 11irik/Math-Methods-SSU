package Matrix;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class LinearSystemTest {
    LinearSystem linearSystem;

    public LinearSystemTest(LinearSystem linearSystem) {
        this.linearSystem = linearSystem;
    }

    @Parameterized.Parameters(name = "{default}")
    public static Iterable<Object[]> getLoadTest() {

        return Arrays.asList(new Object[][] {
                {
                        new LinearSystem(
                            new double[][] {
                                {-1, 1, 0, 0, 0},
                                {1, -2, -2, 0, 0},
                                {0, 2, -1, 1, 0},
                                {0, 0, 5, -3, -1},
                                {0, 0, 0, 1, 1}
                            },
                            new double[] {-2, 5, -4, 6, -3}
                        )
                }
        });
    }


    @Test
    public void gaussianElimination() {
        double[] xs = linearSystem.gaussianElimination();
        double[] actual = linearSystem.getMatrix().multiplyByColumn(xs);;
        double[] expected = linearSystem.getColumn();

        double delta = 0.00001;
        Assert.assertArrayEquals(expected, actual, delta);
    }

    @Test
    public void getInverse() {
    }

    @Test
    public void directSweep() {
        double[] xs = linearSystem.tridiagonalAlgorithm();
        double[] actual = linearSystem.getMatrix().multiplyByColumn(xs);
        double[] expected = linearSystem.getColumn();

        double delta = 0.00001;
        Assert.assertArrayEquals(expected, actual, delta);
    }

    @Test
    public void fixedPointIteration() {
        System.out.println("//////TEST-TEST-TEST///////");
        System.out.println(linearSystem.toString());
        linearSystem.fixedPointIteration();
    }
}