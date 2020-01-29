package diffMath;

import linearSystem.LinearSystem;
import linearSystem.Matrix;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BoundaryValueProblem {
    private int n;
    private double begin;
    private double end;
    private double h;

    private double[] x;
    private double[] p;
    private double[] q;
    private double[] f;
    private double[] yExact;

    public BoundaryValueProblem(double begin, double end, int n) {
        this.n = n;
        this.begin = begin;
        this.end = end;
        h = (end - begin) / (n-1);

        x = new double[n];
        p = new double[n];
        q = new double[n];
        f = new double[n];
        yExact = new double[n];

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

    public double[] getYExact() {
        return Arrays.copyOf(yExact, yExact.length);
    }

    public double[] getX() {
        return Arrays.copyOf(x, x.length);
    }

    public double[] differenceMethod() {
        double[] a = new double[n-3];
        double[] b = new double[n-2];
        double[] c = new double[n-3];
        double[] d = new double[n-2];

        for (int i = 0; i < n-2; ++i) {
            d[i] = f[i+1];
        }

        for (int i = 0; i < n-3; ++i) {
            a[i] = 1 / (h * h) - p[i+1] / (2 * h);
            b[i] = 2 / (h * h) - q[i+1];
            c[i] = 1 / (h * h) + p[i+1] / (2 * h);
        }
        b[n-3] = 2 / (h * h) - q[n-2];

        Matrix matrix = new Matrix(a, b, c);
        LinearSystem linearSystem = new LinearSystem(matrix, d, d.length);
        double[] y = new double[n];
        double[] tridiagonal = linearSystem.tridiagonalAlgorithm();

        for (int i = 0; i < tridiagonal.length; ++i) {
            y[i+1] = tridiagonal[i];
        }

        return y;
    }

    public double[] undeterminedCoefficientsMethod() {
        double[][] matrix = new double[n-2][n-2];
        double[] b = new double[n-2];
        for (int i = 0; i < n-2; ++i) {
            b[i] = f[i+1];
        }

        for (int i = 0; i < n-2; ++i) {
            for (int j = 0; j < n-2; ++j) {
                matrix[i][j] = fi2(x[i+1], j) + p[i+1] * fi1(x[i+1], j) + q[i+1] * fi(x[i+1], j);
            }
        }

        double[] a = new LinearSystem(matrix, b).gaussianElimination();
        double[] y = new double[n];

        for (int i = 1; i < n-1; ++i) {
            for (int j = 0; j < n-2; ++j) {
                y[i] += a[j] * fi(x[i], j);
            }
        }

        return y;
    }


    private double f(double x) {
        return Math.pow(x, 6) + 2 * Math.pow(x, 4) - 2 * Math.pow(x, 3) + 6 * x - 2;
    }

    private double fi2(double x, int j) {
        return (j+1) * (j * Math.pow(x, j-1) * (x - end) + Math.pow(x, j)) + (j+1) * Math.pow(x, j);
    }

    private double fi1(double x, int j) {
        return (j+1) * Math.pow(x, j) * (x - end) + Math.pow(x, j-1);
    }

    private double fi(double x, int j) {
        return Math.pow(x, j+1) * (x - end);
    }


}
