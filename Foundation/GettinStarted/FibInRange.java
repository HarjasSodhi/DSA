import java.util.*;
public class FibInRange {
    public static Scanner scn = new Scanner(System.in);

    public static void FIR() {
        System.out.println("enter the nth position");
        int n = scn.nextInt();
        int a = 0;
        int b = 1;
        int c;
        for (int i = 0; i < n; i++) {
            System.out.println(a);
            c = a + b;
            a = b;
            b = c;
        }
    }

    public static void main(String[] args) {
        FIR();
    }
}