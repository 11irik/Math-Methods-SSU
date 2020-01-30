package interpolation;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Interpolation {
    private Double[] x;
    private Double[] y;
    private int n;

    public Interpolation(Double[] x, Double[] y) {
        this.x = Arrays.copyOf(x, x.length);
        this.y = Arrays.copyOf(y, y.length);
        this.n = x.length;
    }

    //paragraph 2
    public void generalForm() {
        for(int i = 0; i < x.length; ++i) {
            for (int j = x.length - 1; j >= 0; j--) {
                System.out.printf("%-5s", Math.pow(x[i], j) + " ");
            }
            System.out.printf("%-5s", "= " + y[i] + '\n');
        }
    }

    //paragraph 3
    public void lagrangeForm() {
        for (int i = 0; i < x.length; ++i) {
            double dot = x[i] + 0.5;
            double interpolant = 0;

            for (int k = 0; k < x.length; ++k) {
                float factor = 1;

                for (int n = 0; n < k; ++n) {
                    factor *= (dot - x[n]) / (x[k] - x[n]);
                }

                for (int n = k + 1; n < x.length; ++n) {
                    factor *= (dot - x[n]) / (x[k] - x[n]);
                }

                interpolant += y[k] * factor;
            }

            System.out.println("L(" + dot + ") = " + interpolant);
        }
    }

    //paragraph 4
    public double getDifference(double f1, double f2, double x1, double x2) {
        return ((f2 - f1) / (x2 - x1));
    }

    public void newtonForm() {
        List<Double> requiredCoefficients = new ArrayList<>();
        List<Double> separatedDifferences = new ArrayList<>(Arrays.asList(y));
        requiredCoefficients.add(separatedDifferences.get(0));

        int step = 1;
        int xStart = x.length - 1;
        int fStart = y.length - 1;
        int fEnd = 0;
        while (step < x.length) {
            int i = fStart;
            int j = xStart;
            while (i > fEnd) {
                separatedDifferences.set(i, getDifference(separatedDifferences.get(i - 1), separatedDifferences.get(i),
                        x[j - step], x[j]));
                i--;
                j--;
            }

            requiredCoefficients.add(separatedDifferences.get(i + 1));
            step++;
            fEnd++;
        }
        System.out.println(requiredCoefficients.toString());

        for (int i = 0; i < x.length; ++i) {
            double dot = x[i] + 0.5;
            double interpolant = requiredCoefficients.get(0);
            double multiply = 1;

            for (int j = 1; j < requiredCoefficients.size(); ++j) {
                multiply *= dot - x[j-1];
                interpolant += requiredCoefficients.get(j) * multiply;
            }

            System.out.println("L(" + dot + ") = " + interpolant);
        }
    }

    //paragraph 5
    public void splines() {
        ArrayList<Double> list1 = new ArrayList<>();
        ArrayList<Double> list2 = new ArrayList<>();
        double[][] matrix = new double[4*n][4*n];
        double[] b = new double[n*4];

        int k = 0;

        for (int i = 0; i < 4*n; i += 4, k++) {
            matrix[k][i] = 1;
            b[k] = y[k];
        }

        for (int i = 0, c = 0, r = 1; i < n*4; i+=4, c++, r++, k++) {
            double h = x[c+1] - x[c];
            matrix[k][i] = 1;
            matrix[k][i+1] = h;
            matrix[k][i+2] = h * h;
            matrix[k][i+3] = h * h * h;
            b[k] = y[r];
        }

        for (int i = 1, c = 0; i+4 < n*4; i+=4, c++, k++) {
            double h = x[c+1] - x[c];
            matrix[k][i] = 1;
            matrix[k][i+1] = 2 * h;
            matrix[k][i+2] = 3 * h * h;
            matrix[k][i+4] = -1;
        }

        for (int i = 2, c = 0; i+4 < n*4; i+=4, c++, k++) {
            double h = x[c+1] - x[c];
            matrix[k][i] = 2;
            matrix[k][i+1] = 6 * h;
            matrix[k][i+4] = -2;
        }

        matrix[k][2] = 2;
        k++;
        double h = x[n] - x[n-1];
        matrix[k][n*4-2] = 2;
        matrix[k][n*4-1] = 6*h;
        for (int i = 0; i < n*4; ++i) {
            for (int j = 0; j < n*4; ++j) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("- " + b[i]);
        }
        System.out.println("");
    }

}
