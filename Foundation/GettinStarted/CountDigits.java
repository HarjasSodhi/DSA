import java.util.*;

public class CountDigits {
    public static Scanner scn = new Scanner(System.in);

    public static void CD() {
        System.out.println("enter the number");
        int d = scn.nextInt();
        int count = 0;
        while (d != 0) {
            d = d / 10;
            count++;
        }
        System.out.printf("The number of digits in the entered number are : %d", count);
    }

    public static void main(String[] args) {
        CD();
    }
}