import java.util.*;

public class TrianglePrint {
    public static Scanner scn = new Scanner(System.in);

    public static void TP() {
        System.out.println("enter the value of n");
        int n = scn.nextInt();
        int nst = 1;
        for (int row = 1; row <= n; row++) {
            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");
            nst++;
            System.out.println("\t");
        }
    }

    public static void main(String[] args) {
        TP();
    }
}