import java.util.*;

public class PritntFib {
    public static Scanner scn = new Scanner(System.in);

    public static int Fib(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 0;
        if (n == 2)
            return 1;
        if (n == 3)
            return 1;
        if (n == 3)
            return 2;
        return Fib(n - 1) + Fib(n - 2);
    }

    public static void main(String[] args) {
        System.out.println("enter the nth position");
        int n = scn.nextInt();
        int j = Fib(n);
        System.out.println(j);
    }
}
