package diffMath;

import org.junit.Test;

import java.util.Arrays;


public class BoundaryValueProblemTest {

    public double[] getDelta(double[] expected, double[] actual) {
        double[] delta = new double[expected.length];
        for (int i = 0; i < expected.length; ++i) {
            delta[i] = Math.abs(expected[i] - actual[i]);
        }
        return delta;
    }

    @Test
    public void diff() {
        BoundaryValueProblem equation = new BoundaryValueProblem(0, 1, 10);
        System.out.println(Arrays.toString(getDelta(equation.getYExact(), equation.differenceMethod())));
    }

    @Test
    public void f() {

    }
}
