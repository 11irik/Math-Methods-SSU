package diffMath;

import java.util.ArrayList;
import java.util.Arrays;

public class Cauchy {
    double[] xs;
    static double v = 1;
    static double y0 = 2;
    ArrayList<Double> expected = new ArrayList<>();

    public Cauchy(double[] xs) {
        this.xs = Arrays.copyOf(xs, xs.length);
        for (double x: xs) {
            expected.add(x * x * (x + 1));
        }
    }

    public ArrayList<Double> getExpected() {
        return expected;
    }

    public double f(double xk, double yk) {
        return Math.pow(xk, 3) + (3 + v) * Math.pow(xk, 2) + 2 * v * xk - yk;
    }

    public ArrayList<Double> methodEuler() {
        ArrayList<Double> list = new ArrayList<>();
        list.add(y0);
        for (int i = 0; i < xs.length-1; ++i) {
            double h = xs[i+1] - xs[i];
            list.add(list.get(i) + h * f(xs[i], list.get(i)));
        }
        return list;
    }

    public ArrayList<Double> betterMethodEuler() {
        ArrayList<Double> list = new ArrayList<>();
        list.add(y0);

        for (int i = 0; i < xs.length-1; ++i) {
            double h = xs[i+1] - xs[i];
            list.add(list.get(i) + h * f(xs[i]+h/2, (list.get(i) + h/2 * f(xs[i], list.get(i)))));
        }
        return list;
    }

    public ArrayList<Double> predictorCorrector() {
        ArrayList<Double> euler = methodEuler();
        ArrayList<Double> list = new ArrayList<>();
        list.add(y0);

        for (int i = 0; i < xs.length-1; ++i) {
            double h = xs[i+1] - xs[i];
            list.add(list.get(i) + h/2 * ( f(xs[i], list.get(i)) + f(xs[i+1], euler.get(i+1))));
        }

        return list;
    }

}
