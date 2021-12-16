import java.util.*;

public class DigitFrequency {
    public static Scanner scn = new Scanner(System.in);

    // public static int DF(int num, int digit) {
    // int count = 0;
    // while (num != 0) {
    // int temp = num % 10;
    // num = num / 10;
    // if (temp == digit) {
    // count++;
    // }
    // }
    // return count;
    // }
    public static int[] DF2(double num) {
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[(int) num % 10]++;
            num = num / 10;
        }
        return arr;
    }

    public static void main(String[] args) {
        // int num = scn.nextInt();
        double num = scn.nextInt();
        // int count = DF(num, scn.nextInt());
        // System.out.print(count);
        int[] arr = new int[10];
        arr = DF2(num);
        int querynum = scn.nextInt();
        for (int i = 0; i < querynum; i++) {
            int query = scn.nextInt();
            System.out.println(arr[query]);
        }
    }
}