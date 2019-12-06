package diffMath;

import linearSystem.LinearSystem;
import linearSystem.Matrix;

import java.util.Arrays;

public class BoundaryValueProblem {
    public static void main(String[] args) {
        int n = 10;
        double begin = 0;
        double end = 1;
        double h = (end - begin) / (n-1);

        double[] x = new double[n];
        double[] yT = new double[n];
        double[] p = new double[n];
        double[] q = new double[n];

        double[] a = new double[n-1];
        double[] b = new double[n];
        double[] c = new double[n-1];
        double[] d = new double[n];


        double[] y = new double[n];



        for (int i = 0; i < n; ++i) {
            x[i] =  i * h;
            p[i] = x[i] * x[i];
            q[i] = p[i] * x[i];
            yT[i] = p[i] * (x[i] - 1);

            b[i] = 2 / (h * h) - q[i];
            d[i] = f(x[i]);
        }

        for (int i = 0; i < n-1; ++i) {
            a[i] = 1 / (h * h) - p[i] / (2 * h);
            c[i] = 1 / (h * h) + p[i] / (2 * h);
        }


        Matrix k = new Matrix(a, b, c);
        LinearSystem s = new LinearSystem(k, d, d.length);
        y = s.tridiagonalAlgorithm();

        for (int i = 0; i < n; ++i) {
            System.out.println(yT[i] - y[i]);
        }


    }

    static double f(double x) {
        return Math.pow(x, 6) - 10 * Math.pow(x, 5) + 3 * Math.pow(x, 4) - 20 * Math.pow(x, 3) + 6 * x - 20;
    }

}
