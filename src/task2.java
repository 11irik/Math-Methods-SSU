import java.util.Arrays;
import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int[] nods = {1, 2, 3, 4};

        int[] funcs = {1, 8, 27, 64};

        String nodesString = Arrays.toString(nods);
        System.out.println(nodesString);

        for (int i = 0; i < nods.length; ++i){

            System.out.println((nods[i] + 0.5) * (nods[i] + 0.5) * (nods[i] + 0.5));
        }

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
}