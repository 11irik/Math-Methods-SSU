package Part2;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Matrix matrix = new Matrix(3, 3);

        //double[][] tempMatrix = new double[3][3];
        //double[] tempFreeColumn = new double[3];

        double[][] tempMatrix = {{3, 2, -5}, {2, -1, 3}, {1, 2, -1}};
        double[] tempFreeColumn = {-1, 13, 9}; //x1 = 3, x2 = 5, x3 = 4;

//        System.out.println("Enter matrix: 3 x 3");
//        for (int i = 0; i < 3; ++i) {
//            System.out.printf("Enter %d line\n", i);
//            String[] line = in.nextLine().trim().split(" ");
//            for (int j = 0; j < 3; ++j) {
//                tempMatrix[i][j] = Integer.parseInt(line[j]);
//            }
//        }

//        System.out.println("Enter free column:");
//        String[] line = in.nextLine().trim().split(" ");
//        for (int j = 0; j < 3; ++j) {
//            tempFreeColumn[j] = Integer.parseInt(line[j]);
//        }
//
        matrix.setMatrix(tempMatrix, tempFreeColumn);



        System.out.println(matrix.Gausa());
    }
}
