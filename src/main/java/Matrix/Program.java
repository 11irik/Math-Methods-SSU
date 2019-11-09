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

        double[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[] col = {2, 3, 4};
        Matrix m = new Matrix(arr);
        System.out.println(Arrays.toString(m.multiplyByColumn(col)));
    }
}
