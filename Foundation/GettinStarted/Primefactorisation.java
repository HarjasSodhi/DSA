import java.util.*;

public class Primefactorisation {
    public static Scanner scn = new Scanner(System.in);

    public static void PF() {
        System.out.println("enter the number");
        int n = scn.nextInt();
        for (int j = 2; j <= n; j++) {
            int count = 0;
            for (int i = 2; i * i <= j; i++) {

                if (j % i == 0) {
                    count = 1;
                }
            }
            if (count != 1) {

                while (n % j == 0) {
                    n = n / j;
                    System.out.printf("%d ", j);
                }
            }
        }
    }

    public static void main(String[] args) {
        PF();
    }
}