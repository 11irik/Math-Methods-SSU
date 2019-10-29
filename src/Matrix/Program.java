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

        double[][] tempMatrix = {{1, 0, 2}, {2, -1, 1}, {1, 3, -1}};

        //double[][] tempMatrix = {{3, 2, -5}, {2, -1, 3}, {1, 2, -1}};
        double[] tempFreeColumn = {-1, 13, 9}; //x1 = 3, x2 = 5, x3 = 4;
//
//      double[][] tempMatrix = {{1, 2, 3}, {3, 5, 7}, {1, 3, 4}};
//      double[] tempFreeColumn = {3, 0, 1};


        LinearSystem system = new LinearSystem(tempMatrix, tempFreeColumn);

//        System.out.println(Arrays.toString(system.gausa()));

        System.out.println(new Matrix(system.getReverse()));
    }
}
