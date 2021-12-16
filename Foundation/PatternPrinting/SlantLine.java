import java.util.*;

public class SlantLine {
    public static Scanner scn = new Scanner(System.in);

    public static void SL() {
        System.out.println("enter the value of n");
        int n = scn.nextInt();
        for (int row = 1; row <= n; row++) {
            for (int column = 1; column <= n; column++)
                if (column == row) {
                    System.out.print("*\t");
                    continue;
                } else {
                    System.out.print("\t");
                }
                System.out.println();
            }
    }

    public static void main(String[] args) {
        SL();
    }
}
