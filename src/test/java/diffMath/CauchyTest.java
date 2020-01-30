package diffMath;

import org.junit.Test;

import java.util.ArrayList;

public class CauchyTest {

    public static ArrayList<Double> getDelta(ArrayList<Double> expected, ArrayList<Double> actual) {
        ArrayList<Double> delta = new ArrayList<>();
        for (int i = 0; i < actual.size(); ++i) {
            delta.add(Math.abs(expected.get(i) - actual.get(i)));
        }

        return delta;
    }

    @Test
    public void Cauchy() {
        double[] xs = new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Cauchy equation = new Cauchy(xs);
        System.out.println("Expected values");
        System.out.println(equation.getExpected());
        System.out.println("\nEuler's method:");
        System.out.println(equation.methodEuler());;
        System.out.println(getDelta(equation.getExpected(), equation.methodEuler()));
        System.out.println("Better Euler's method:");
        System.out.println(equation.betterMethodEuler());;
        System.out.println(getDelta(equation.getExpected(), equation.betterMethodEuler()));
        System.out.println("Predictor-corrector:");
        System.out.println(equation.predictorCorrector());
        System.out.println(getDelta(equation.getExpected(), equation.predictorCorrector()));
    }
}