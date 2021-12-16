import java.util.*;

public class Swastika {
    public static Scanner scn = new Scanner(System.in);

    public static void SW() {
        System.out.println("enter the value of n");
        int n = scn.nextInt();
        for (int row = 1; row <= n; row++) {
            for (int column = 1; column <= n; column++) {
                if (column == (n / 2) + 1 || row == (n / 2) + 1 || (column <= n / 2 && row == 1)
                        || (column >= (n / 2) + 1 && row == n) || (row >= (n / 2) + 1 && column == 1)
                        || (row <= n / 2 && column == n))
                    System.out.print("*\t");
                else {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        SW();
    }
}
