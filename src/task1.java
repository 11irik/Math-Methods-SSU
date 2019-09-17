public class task1 {
    public static void main(String[] args) {
        int v1 = 1;
        int v2 = 2;
        double eps = 0.1;
        int r = 5;

        for(int i = -r; i <= r; ++i){
            double s1 = v1 * i;
            double sum = s1;
            int k = 1;
            int n = 1;
            int minus = -1;
            while (Math.abs(s1) >= eps){
                s1 = minus * s1 * v1 * v1 * i * i / ((k + 1) * (k + 2));
                sum += s1;
                k += 2;
                n++;
            }
            System.out.printf("x = %d n = %d S(x) = %f delta = %f\n", i, n, sum, sum - Math.sin(v1 * i));
        }
    }
}
