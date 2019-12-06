package interpolation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Interpolation {
    private Double[] nods = {1.0, 2.0, 3.0, 4.0};
    private Double[] funcs = {1.0, 8.0, 27.0, 64.0};

    public Interpolation(Double[] nods) {
        this.nods = Arrays.copyOf(nods, nods.length);
    }

    //paragraph 2
    public void generalForm() {
        for(int i = 0; i < nods.length; ++i) {
            for (int j = nods.length - 1; j >= 0; j--) {
                System.out.printf("%-10s", Math.pow(nods[i], j) + " ");
            }
            System.out.printf("%-10s", "= " + funcs[i] + '\n');
        }
    }

    //paragraph 3
    public void lagrangeForm() {
        for (int i = 0; i < nods.length; ++i) {
            double dot = nods[i] + 0.5;
            double interpolant = 0;

            for (int k = 0; k < nods.length; ++k) {
                float factor = 1;

                for (int n = 0; n < k; ++n) {
                    factor *= (dot - nods[n]) / (nods[k] - nods[n]);
                }

                for (int n = k + 1; n < nods.length; ++n) {
                    factor *= (dot - nods[n]) / (nods[k] - nods[n]);
                }

                interpolant += funcs[k] * factor;
            }

            System.out.println("L(" + dot + ") = " + interpolant);
        }
    }

    //paragraph 4
    public static double getDifference(double f1, double f2, double x1, double x2) {
        return ((f2 - f1) / (x2 - x1));
    }

    public void newtonForm() {
        List<Double> requiredCoefficients = new ArrayList<>();
        List<Double> separatedDifferences = new ArrayList<>(Arrays.asList(funcs));
        requiredCoefficients.add(separatedDifferences.get(0));

        int step = 1;
        int xStart = nods.length - 1;
        int fStart = funcs.length - 1;
        int fEnd = 0;
        while (step < nods.length) {
            int i = fStart;
            int j = xStart;
            while (i > fEnd) {
                separatedDifferences.set(i, getDifference(separatedDifferences.get(i - 1), separatedDifferences.get(i),
                        nods[j - step], nods[j]));
                i--;
                j--;
            }

            requiredCoefficients.add(separatedDifferences.get(i + 1));
            step++;
            fEnd++;
        }
        System.out.println(requiredCoefficients.toString());

        for (int i = 0; i < nods.length; ++i) {
            double dot = nods[i] + 0.5;
            double interpolant = requiredCoefficients.get(0);
            double multiply = 1;

            for (int j = 1; j < requiredCoefficients.size(); ++j) {
                multiply *= dot - nods[j-1];
                interpolant += requiredCoefficients.get(j) * multiply;
            }

            System.out.println("L(" + dot + ") = " + interpolant);
        }
    }

    //paragraph 5


}
