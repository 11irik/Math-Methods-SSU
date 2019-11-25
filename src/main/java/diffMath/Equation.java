package diffMath;

import java.lang.reflect.Array;

public class Equation {
    double[] terms;
    int degree;

    public Equation(String equation) {
        String[] terms = equation.split("(-)|(\\+)");
        degree = terms.length;
        this.terms = new double[degree];
        for (int i = 0; i < terms.length; ++i) {
            this.terms[i] = Double.parseDouble(terms[i]);
        }
    }

    public double count(double x) {
        double result = 0;
        for (int i = 0; i < degree; ++i) {
            result += Math.pow(x, degree-1-i) * terms[i];
        }
        return result;
    }
}
