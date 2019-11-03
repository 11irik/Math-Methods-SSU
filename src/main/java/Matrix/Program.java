package Matrix;

import java.util.Arrays;

public class Program {
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//
//        System.out.println("Enter n:");
//        int n = Integer.parseInt(in.nextLine());
//        double[][] tempMatrix = new double[n][n];
//        double[] tempFreeColumn = new double[n];
//        System.out.printf("Enter matrix: %d x %d\n", n, n);
//        for (int i = 0; i < n; ++i) {
//            System.out.printf("Enter %d line\n", i);
//            String[] line = in.nextLine().trim().split(" ");
//            for (int j = 0; j < n; ++j) {
//                tempMatrix[i][j] = Integer.parseInt(line[j]);
//            }
//        }
//
//        System.out.println("Enter free column:");
//        String[] line = in.nextLine().trim().split(" ");
//        for (int j = 0; j < n; ++j) {
//            tempFreeColumn[j] = Integer.parseInt(line[j]);
//        }

        double[][] tempMatrix = {{-1, 1, 0, 0, 0}, {1, -2, -2, 0, 0}, {0, 2, -1, 1, 0}, {0, 0, 5, -3, -1}, {0, 0, 0, 1, 1}};
        double[] tempFreeColumn = {-2, 5, -4, 6, -3};

        //todo test
        LinearSystem linearSystem = new LinearSystem(tempMatrix, tempFreeColumn);

        System.out.println(Arrays.toString(linearSystem.gaussianElimination()));

        System.out.println(Arrays.toString(linearSystem.directSweep()));
    }
}
