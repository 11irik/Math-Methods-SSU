package Part2;

public class Program {
    public static void main(String[] args) {
        double[][] arr = new double[2][2];
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                arr[i][j] = i + j;
            }
        }
        System.out.println(arr[1]);
    }
}
