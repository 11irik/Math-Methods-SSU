package diffMath;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class CauchyTest {

    public static ArrayList<Double> getDelta(ArrayList<Double> expected, ArrayList<Double> actual) {
        ArrayList<Double> delta = new ArrayList<>();
        for (int i = 0; i < expected.size(); ++i) {
            delta.add(Math.abs(expected.get(i) - actual.get(i)));
        }

        return delta;
    }

    @Test
    public void Cauchy() {
        double[] xs = new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        ArrayList<Double> expected = new ArrayList<>();
        for (double x: xs) {
            expected.add(x * x * (x + 1));
        }

        Cauchy equation = new Cauchy(xs);

        System.out.println(getDelta(expected, equation.methodEuler()));
        System.out.println(getDelta(expected, equation.betterMethodEuler()));
        System.out.println(getDelta(expected, equation.predictorCorrector()));
    }
}