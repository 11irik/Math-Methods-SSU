package diffMath;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cauchy {
    double[] xs;
    double v = 1;
    double y0 = 2;

    public Cauchy(double[] xs) {
        this.xs = Arrays.copyOf(xs, xs.length);
    }

    public ArrayList<Double> methodEuler() {
        ArrayList<Double> list = new ArrayList<>();
        list.add(y0);
        for (int i = 0; i < xs.length-1; ++i) {
            double h = xs[i+1] - xs[i];
            list.add(list.get(i) + h * Math.pow(xs[i], 3) + (3 + v) * Math.pow(xs[i], 2) + 2 * v * xs[i] - list.get(i));
        }
        return list;
    }

    public ArrayList<Double> betterMethodEuler() {
        ArrayList<Double> list = new ArrayList<>();
        list.add(y0);
        for (int i = 0; i < xs.length-1; ++i) {
            double h = xs[i+1] - xs[i];
            double temp = list.get(i) + h/2 * Math.pow(xs[i], 3) + (3 + v) * Math.pow(xs[i], 2) + 2 * v * xs[i] - list.get(i);
            list.add( list.get(i) + h * (Math.pow( (xs[i] + xs[i+1]) / 2, 3 ) + (3 + v) * Math.pow( (xs[i] + xs[i+1]) / 2, 2 ) + 2 * v * (xs[i] + xs[i+1]) / 2 - temp));
        }
        return list;
    }

    //fixme
    public ArrayList<Double> predictorCorrector() {
        ArrayList<Double> euler = methodEuler();
        ArrayList<Double> list = new ArrayList<>();
        list.add(y0);

        for (int i = 0; i < xs.length-1; ++i) {
            double h = xs[i+1] - xs[i];
            list.add( list.get(i) + h/2 * (Math.pow(xs[i], 3) + (3 + v) * Math.pow(xs[i], 2) + 2 * v * xs[i] - list.get(i) + Math.pow(xs[i+1], 3) + (3 + v) * Math.pow(xs[i+1], 2) + 2 * v * xs[i+1] - euler.get(i)));
        }

        return list;
    }

}
