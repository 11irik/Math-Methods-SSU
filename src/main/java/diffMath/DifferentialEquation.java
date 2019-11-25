package diffMath;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DifferentialEquation {
    double[] xs;
    Equation equation;
    double y0;

    public DifferentialEquation(String equation, double y0, double[] xs) {
        this.equation = new Equation(equation);
        this.y0 = y0;
        this.xs = Arrays.copyOf(xs, xs.length);
    }

    private double euler(double y, double x1, double x2) {
        return y + (x2 - x1) * (equation.count(x1) - y);
    }

    public ArrayList<Double> methodEuler() {
        ArrayList<Double> list = new ArrayList<>();
        list.add(y0);
        for (int i = 0; i < xs.length-1; ++i) {
//            double h = xs[i+1] - xs[i];
            list.add(euler(list.get(i), xs[i], xs[i+1]));
        }
        return list;
    }

    public ArrayList<Double> methodBetterEuler() {
        ArrayList<Double> list = new ArrayList<>();
        list.add(y0);
        for (int i = 0; i < xs.length-1; ++i) {
            double h = xs[i+1] - xs[i];
            list.add( list.get(i) + h * ( equation.count((xs[i] + xs[i+1])/2)) - (list.get(i) + h/2 * ( equation.count(xs[i]) - list.get(i))) );
        }
        return list;
    }

    public ArrayList<Double> predictorCorrector() {
        ArrayList<Double> euler = methodEuler();
        ArrayList<Double> list = new ArrayList<>();
        list.add(y0);

        for (int i = 0; i < xs.length-1; ++i) {
            double h = xs[i+1] - xs[i];
            list.add( list.get(i) + h/2 * ( equation.count(xs[i]) - list.get (i) + equation.count(xs[i+1]) - euler.get(i)));
        }

        return list;
    }

}
