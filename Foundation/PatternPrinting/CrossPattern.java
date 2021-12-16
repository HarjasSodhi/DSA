import java.util.*;

public class CrossPattern {
    public static Scanner scn = new Scanner(System.in);

    public static void CL() {
        System.out.println("enter the value of n");
        int n = scn.nextInt();
        for (int row = 1; row <= n; row++) {
            for (int column = 1; column <= n; column++) {
                if ((column == 1 && row == n) || (row == 1 && column == n) || (column == n && row == n) || row == column
                        || row == n - column + 1) {
                    System.out.print("*\t");
                } else {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        CL();
    }
}