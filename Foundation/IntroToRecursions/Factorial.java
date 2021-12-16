import java.util.*;

public class Factorial {
    public static Scanner scn = new Scanner(System.in);

    public static int FP(int n) {
        if (n == 0)
            return 1;
        return n * FP(n - 1);
        // return n==0?1:n*FP(n-1);
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int ans = FP(n);
        System.out.println(ans);
    }
}