import java.util.*;

public class PrintIncreasing {
    public static Scanner scn = new Scanner(System.in);

    public static void IP(int a, int n) {
        if (a > n)
            return;
        System.out.println(a);
        IP(a + 1, n);
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        IP(1, n);
    }
}