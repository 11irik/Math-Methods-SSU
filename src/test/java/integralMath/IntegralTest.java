package integralMath;

import diffMath.BoundaryValueProblem;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class IntegralTest {

    public double[] getDelta(double[] expected, double[] actual) {
        double[] delta = new double[expected.length];
        for (int i = 0; i < actual.length; ++i) {
            delta[i] = Math.abs(expected[i] - actual[i]);
        }
        return delta;
    }

    @Test
    public void integral() {
        IntegralEquation equation = new IntegralEquation(0.0, 1.0, 10);
        System.out.println("x: " + Arrays.toString(equation.getX()));
        System.out.println("Exact y: " + Arrays.toString(equation.getYExact()));
        System.out.println("Method: " + Arrays.toString(equation.quadro()));
        System.out.println("Delta: " + Arrays.toString(getDelta(equation.getYExact(), equation.quadro())));
    }

    @Test
    public void mnk() {
        IntegralEquation equation = new IntegralEquation(0.0, 1.0, 10);
        System.out.println("x: " + Arrays.toString(equation.getX()));
        System.out.println("Exact y: " + Arrays.toString(equation.getYExact()));
        System.out.println("Method: " + Arrays.toString(equation.mnk()));
        System.out.println("Delta: " + Arrays.toString(getDelta(equation.getYExact(), equation.mnk())));
    }
}