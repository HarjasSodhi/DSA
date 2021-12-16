import java.util.*;

public class DisplayDigits {
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
        DD(count, j);
    }

    public static int pow(int count) {
        int i = 1;
        for (int j = 0; j < count; j++) {
            i = i * 10;
        }
        return i;
    }

    public static void DD(int count, int num) {
        for (int i = 1; i <= count; i++) {
            int j = num / pow(count - i);
            num = num % pow(count - i);
            System.out.println(j);
        }
    }

    public static void main(String[] args) {
        CD();
    }
}