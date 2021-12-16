import java.util.*;

public class MirroredTriangle {
    public static Scanner scn = new Scanner(System.in);

    public static void MT() {
        System.out.println("enter the value of n");
        int n = scn.nextInt();
        int nst = 1;
        int nsp = n - 1;
        for (int row = 1; row <= n; row++) {
            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");
            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");
            nst++;
            nsp--;
            System.out.println();
        }

    }

    public static void main(String[] args) {
        MT();
    }
}
