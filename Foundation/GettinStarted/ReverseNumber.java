import java.util.*;

public class ReverseNumber {
    public static Scanner scn = new Scanner(System.in);

    public static void CD() {
        System.out.println("enter the number");
        int d = scn.nextInt();
        int j = d;
        int count = 0;
        while (d != 0) {
            d = d / 10;
            count++;
        }
        RN(count, j);
    }

    public static void RN(int count, int num) {
        int h;
        for (int i = 0; i < count; i++) {
            h = num % 10;
            num = num / 10;
            System.out.println(h);
        }
    }

    public static void main(String[] args) {
        CD();
    }
}