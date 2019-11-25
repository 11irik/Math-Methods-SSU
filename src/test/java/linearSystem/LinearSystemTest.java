package linearSystem;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class LinearSystemTest {
    private LinearSystem linearSystem;

    public LinearSystemTest(LinearSystem linearSystem) {
        this.linearSystem = linearSystem;
    }

    @Parameterized.Parameters()
    public static Iterable<Object[]> defaultSet() {

        return Arrays.asList(new Object[][] {
                {
//                        new LinearSystem(
//                            new double[][] {
//                                {-1, 1, 0, 0, 0},
//                                {1, -2, -2, 0, 0},
//                                {0, 2, -1, 1, 0},
//                                {0, 0, 5, -3, -1},
//                                {0, 0, 0, 1, 1}
//                            },
//                            new double[] {-2, 5, -4, 6, -3}
//                        ),
                        new LinearSystem(
                                new double[][] {
                                        {10, 2, -1},
                                        {-2, -6, -1},
                                        {1, -3, 12},
                                },
                                new double[] {11, -7, 10}
                        ),
//                        new LinearSystem(
//                                new double[][] {
//                                        {10, 2},
//                                        {-2, -6}
//                                },
//                                new double[] {11, -7}
//                        ),
//                        new LinearSystem(
//                                new double[][] {
//                                        {4, -1, -1},
//                                        {1, 5, -2},
//                                        {1, 1, 4}
//                                        },
//                                new double[]{2, 4, 6}
//                                )
                }
        });
    }

    @Test
    public void gaussianElimination() {
        double[] xs = linearSystem.gaussianElimination();
        System.out.println(Arrays.toString(xs));

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

        double delta = 0.001;
        Assert.assertArrayEquals(expected, actual, delta);
    }

    @Test
    public void fixedPointIteration() {
        double[] xs = linearSystem.fixedPointIteration(0.00000001);
        double[] actual = linearSystem.getMatrix().multiplyByColumn(xs);
        double[] expected = linearSystem.getColumn();

        double delta = 0.1;
        Assert.assertArrayEquals(expected, actual, delta);
    }
}