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
    private double begin;
    private double end;

    public BoundaryValueProblem(double begin, double end, int n) {
        this.n = n;
        this.begin = begin;
        this.end = end;
        h = (end - begin) / (n-1);

        x = new double[n+1];
        yExact = new double[n];
        p = new double[n];
        q = new double[n];
        f = new double[n];

        x[0] = begin;
        x[n-1] = end;
        for (int i = 1; i < n-1; ++i) {
            x[i] =  x[i-1] + h;
        }
        for (int i = 0; i < n; ++i) {
            p[i] = x[i] * x[i];
            q[i] = x[i] * x[i] * x[i];
            f[i] = f(x[i]);
            yExact[i] = x[i] * x[i] * (x[i] - 1.0);
        }
    }

    public double[] differenceMethod() {
        double[] a = new double[n-3];
        double[] b = new double[n-2];
        double[] c = new double[n-3];
        double[] d = new double[n-2];
        for (int i = 0; i < n-2; ++i) {
            d[i] = f[i+1];
        }

        System.out.println("x: " + Arrays.toString(x));
        System.out.println("yExact: " + Arrays.toString(yExact));


        for (int i = 0; i < n-3; ++i) {
            a[i] = 1 / (h * h) - p[i+1] / (2 * h);
            b[i] = 2 / (h * h) - q[i+1];
            c[i] = 1 / (h * h) + p[i+1] / (2 * h);
        }
        b[n-3] = 2 / (h * h) - q[n-2];


        Matrix matrix = new Matrix(a, b, c);
        LinearSystem linearSystem = new LinearSystem(matrix, d, d.length);
        double[] y = new double[n];
        y[0] = y[n-1] = 0;
        double[] tridiagonal = linearSystem.tridiagonalAlgorithm();
        for (int i = 0; i < tridiagonal.length; ++i) {
            y[i+1] = tridiagonal[i];
        }
        System.out.println("Method: " + Arrays.toString(y));

        return y;
    }

    public void undeterminedCoefficientsMethod() {

    }

    public double[] getYExact() {
        return Arrays.copyOf(yExact, yExact.length);
    }

    private static double f(double x) {
        //todo fixme
        return Math.pow(x, 6) + 2 * Math.pow(x, 4) - 2 * Math.pow(x, 3) + 6 * x - 2;
    }

}
