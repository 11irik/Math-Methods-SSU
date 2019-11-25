package diffMath;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DifferentialEquationTest {

    @Test
    public void methodEuler() {
        double[] array = new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayList<Double> test = new ArrayList<>();
        for (double x : array) {
            test.add(x * x * (x + 1));
        }
        DifferentialEquation m = new DifferentialEquation("1 + 4 + 3 + 0", 2, array);
        System.out.println(m.methodEuler());
        System.out.println(m.methodBetterEuler());
        System.out.println(m.predictorCorrector());
        System.out.println(test);
    }
}