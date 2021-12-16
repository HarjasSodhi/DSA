import java.util.*;

public class BarGraph {
    public static Scanner scn = new Scanner(System.in);

    public static void Insert(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        BG(arr, n);
    }

    public static void BG(int[] arr, int n) {
        int greatest = (int) -1e9 - 1;
        for (int i = 0; i < n; i++) {
            if (arr[i] > greatest)
                greatest = arr[i];
        }
        for (int i = greatest; i > 0; i--) {
            for (int j = 0; j < n; j++) {
                if (arr[j] == i) {
                    System.out.print("*\t");
                    arr[j]--;
                }
                /*
                 * or can also use this if(arr[j]>=i)Systen.out.print("*\t");
                 */
                else {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        Insert(n);
    }
}