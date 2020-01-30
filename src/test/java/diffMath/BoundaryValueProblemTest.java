package diffMath;

import org.junit.Test;

import java.util.Arrays;


public class BoundaryValueProblemTest {

    public double[] getDelta(double[] expected, double[] actual) {
        double[] delta = new double[expected.length];
        for (int i = 0; i < actual.length; ++i) {
            delta[i] = Math.abs(expected[i] - actual[i]);
        }
        return delta;
    }

    @Test
    public void diff() {
        BoundaryValueProblem equation = new BoundaryValueProblem(0.0, 1.0, 10);
        System.out.println("x: " + Arrays.toString(equation.getX()));
        System.out.println("Exact y: " + Arrays.toString(equation.getYExact()));
        System.out.println("Method: " + Arrays.toString(equation.differenceMethod()));
        System.out.println("Delta: " + Arrays.toString(getDelta(equation.getYExact(), equation.differenceMethod())));
    }

    @Test
    public void undetermined() {
        BoundaryValueProblem equation = new BoundaryValueProblem(0.0, 1.0, 10);
        System.out.println("x: " + Arrays.toString(equation.getX()));
        System.out.println("Exact y: " + Arrays.toString(equation.getYExact()));
        System.out.println("Method: " + Arrays.toString(equation.undeterminedCoefficientsMethod()));
        System.out.println("Delta: " + Arrays.toString(getDelta(equation.getYExact(), equation.undeterminedCoefficientsMethod())));
    }
}
