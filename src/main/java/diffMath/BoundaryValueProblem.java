package diffMath;

import linearSystem.LinearSystem;
import linearSystem.Matrix;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BoundaryValueProblem {
    private double[] yExact;
    private double[] x;
    private double[] p;
    private double[] q;
    private double[] f;
    private int n;
    private double h;

    public BoundaryValueProblem(double begin, double end, int n) {
        this.n = n;
        h = (end - begin) / (n-1);

        x = new double[n+1];
        yExact = new double[n];
        p = new double[n];
        q = new double[n];
        f = new double[n];

        x[0] = begin;
        for (int i = 0; i < n; ++i) {
            x[i+1] =  x[i] + h;
            p[i] = x[i] * x[i];
            q[i] = x[i] * x[i] * x[i];
            f[i] = f(x[i]);
            yExact[i] = x[i] * x[i] * (x[i] - 1);
        }
    }

    public double[] differenceMethod() {
        double[] a = new double[n-1];
        double[] b = new double[n];
        double[] c = new double[n-1];
        double[] d = Arrays.copyOf(f, f.length);

        for (int i = 0; i < n-1; ++i) {
            a[i] = 1 / (h * h) - p[i] / (2 * h);
            b[i] = 2 / (h * h) - q[i];
            c[i] = 1 / (h * h) + p[i] / (2 * h);
        }
        b[n-1] = 2 / (h * h) - q[n-1];


        Matrix matrix = new Matrix(a, b, c);
        LinearSystem linearSystem = new LinearSystem(matrix, d, d.length);
        double[] y = linearSystem.tridiagonalAlgorithm();

        return y;
    }

    public void undeterminedCoefficientsMethod() {

    }

    public double[] getYExact() {
        return Arrays.copyOf(yExact, yExact.length);
    }

    private static double f(double x) {
        return Math.pow(x, 6) - 10 * Math.pow(x, 5) + 3 * Math.pow(x, 4) - 20 * Math.pow(x, 3) + 6 * x - 20;
    }

}
