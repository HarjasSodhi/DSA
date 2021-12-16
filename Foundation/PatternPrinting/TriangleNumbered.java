import java.util.*;

public class TriangleNumbered {
    public static Scanner scn = new Scanner(System.in);

    public static void NT() {
        System.out.println("enter the value of n");
        int n = scn.nextInt();
        int num = 1;
        for (int row = 1; row <= n; row++) {
            for (int j = 1; j <= row; j++) {
                System.out.print(num + "\t");
                num++;
            }
            System.out.println();

        }
    }

    public static void main(String[] args) {
        NT();
    }
}
