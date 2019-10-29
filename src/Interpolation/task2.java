package Interpolation;

import java.util.Arrays;
import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int[] nods = {1, 2, 3, 4};
        int[] funcs = {1, 8, 27, 64};

        for(int i = 0; i < nods.length; ++i) {
            for (int j = nods.length - 1; j >= 0; j--) {
                System.out.printf("%-10s", Math.pow(nods[i], j) + " ");
            }

            System.out.printf("%-10s", "= " + funcs[i]);
            System.out.println();
        }
    }
}