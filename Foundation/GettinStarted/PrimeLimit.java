import java.util.*;

public class PrimeLimit {
    public static Scanner scn = new Scanner(System.in);

    public static void PL(int a, int b) {
        for (int j = a; j <= b; j++) {
            int count = 0;
            for (int i = 2; i * i <= j; i++) {

                if (j % i == 0) {
                    count = 1;
                }
            }
            if (count != 1) {
                System.out.println(j);
            }
        }
    }

    public static void PLR() {
        System.out.println("enter lower limit");
        int a = scn.nextInt();
        System.out.println("enter upper limit");
        int b = scn.nextInt();
        PL(a, b);
    }

    public static void main(String[] args) {
        PLR();
    }
}
