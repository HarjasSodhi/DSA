import java.util.*;

public class CheckPrime {

    public static Scanner scn = new Scanner(System.in);

    public static void CP() {
        System.out.println("enter the number");
        int num = scn.nextInt();
        boolean count = false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                count = true;
            }
        }
        if (count == true) {
            System.out.println("number is not prime");
        } else {
            System.out.println("the number is prime");
        }
    }

    public static void main(String[] args) {
        System.out.println("enter the number of numbers you want to test");
        int t = scn.nextInt();
        for (int j = 0; j < t; j++) {
            CP();
        }
    }
}