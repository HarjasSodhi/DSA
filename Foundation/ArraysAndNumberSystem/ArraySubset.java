import java.util.*;

public class ArraySubset {
    public static Scanner scn = new Scanner(System.in);

    public static int[] insert(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        return arr;
    }

    public static void ASUB(int[] arr) {
        int limit = (int) Math.pow(2, arr.length);
        for (int i = 0; i < limit; i++) {
            int temp = i;
            String set = "";
            for (int j = arr.length - 1; j >= 0; j--) {
                int r = temp % 2;
                temp = temp / 2;
                if (r == 0) {
                    set = "-\t" + set;
                } else {
                    set = arr[j] + "\t" + set;
                }
            }
            System.out.println(set);
        }
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        arr = insert(n);
        ASUB(arr);
    }
}