import java.util.*;

public class FibTriangle {
    public static Scanner scn = new Scanner(System.in);

    public static int Fib(int pos) {
        if (pos == 1)
            return 0;
        if (pos == 2)
            return 1;
        if (pos == 3)
            return 1;
        if (pos == 4)
            return 2;
        return Fib(pos - 1) + Fib(pos - 2);
    }

    public static void NT() {
        System.out.println("enter the value of n");
        int n = scn.nextInt();
        int num = 1;
        for (int row = 1; row <= n; row++) {
            for (int j = 1; j <= row; j++) {
                System.out.print(Fib(num) + "\t");
                num++;
            }
            System.out.println();

        }
    }

    public static void main(String[] args) {
        NT();
    }
}