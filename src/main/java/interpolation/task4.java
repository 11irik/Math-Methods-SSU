package interpolation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class task4 {
    public static double getDifference(double f1, double f2, double x1, double x2) {
        return ((f2 - f1) / (x2 - x1));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Double[] nods = {1.0, 2.0, 3.0, 4.0};
        Double[] funcs = {1.0, 8.0, 27.0, 64.0};

        String nodesString = Arrays.toString(nods);
        System.out.println(nodesString);

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
}