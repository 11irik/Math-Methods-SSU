package integralMath;

import linearSystem.LinearSystem;

import java.util.Arrays;

public class IntegralEquation {
    private int n;
    private double begin;
    private double end;
    private double h;
    private int v = 1;

    private double[] x;
    private double[] f;
    private double[] yExact;

    public IntegralEquation(double begin, double end, int n) {
        this.n = n;
        this.begin = begin;
        this.end = end;
        h = (end - begin) / (n-1);

        x = new double[n];
        f = new double[n];
        yExact = new double[n];

        x[0] = begin;
        x[n-1] = end;
        for (int i = 1; i < n-1; ++i) {
            x[i] =  x[i-1] + h;
        }

        for (int i = 0; i < n; ++i) {
            f[i] = f(x[i]);
            yExact[i] = v * Math.pow(x[i], 3) + x[i] + v;
        }
    }

    public double[] getYExact() {
        return Arrays.copyOf(yExact, yExact.length);
    }

    public double[] getX() {
        return Arrays.copyOf(x, x.length);
    }

    public double[] quadro() {
        double[][] matrix = new double[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix[i][j] = h * func(x[i], x[j]);
            }
            matrix[i][i] += 1;
        }
        return new LinearSystem(matrix, f).gaussianElimination();
    }

    public double[] mnk() {
        double[][] matrix = new double[n][n];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix[i][j] = Math.pow(x[i], j) + integral(x[i], j);
            }
        }

        double[] a = new LinearSystem(matrix, f).gaussianElimination();
        double[] y = new double[n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                y[i] += a[j] * Math.pow(x[i], j);
            }
        }
        return y;
    }

    private double f(double x) {
        return (3 * v * Math.pow(x, 6) + 4 * v * Math.pow(x, 5) + (3 + 6 * v) * Math.pow(x, 4) +
                (15 * v + 4) * Math.pow(x, 3) + (4 * v + 6) * Math.pow(x, 2) + (6 * v + 12) * x + 12 * v) / 12;
    }

    private double func(double a, double b) {
        return a * b + Math.pow(a, 2) * Math.pow(b, 2) + Math.pow(a, 3) * Math.pow(b, 3);
    }

    private double integral(double x, double k) {
        return x / (k + 2) + (x * x) / (k + 3) + (x * x * x) / (k + 4);
    }
}
