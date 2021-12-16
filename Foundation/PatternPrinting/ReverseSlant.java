import java.util.*;

public class ReverseSlant {
    public static Scanner scn = new Scanner(System.in);

    public static void RSL() {
        System.out.println("enter the value of n");
        int n = scn.nextInt();
        for (int row = n; row > 0; row--) {
            for (int column = 1; column <= n; column++)
                if (column != row) {
                    System.out.print("\t");
                    continue;
                } else {
                    System.out.print("*\t");
                }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        RSL();
    }
}
