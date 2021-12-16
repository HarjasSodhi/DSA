import java.util.*;

public class FindData {
    public static Scanner scn = new Scanner(System.in);

    public static int[] insert(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        return arr;
    }

    public static boolean FD(int[] arr, int i, int data) {
        if (i == arr.length) {
            return false;
        }
        if (arr[i] == data)
            return true;
        boolean ans = FD(arr, i + 1, data);
        return ans;
    }

    public static boolean FD2(int[] arr, int i, int data) {
        if (i == arr.length) {
            return false;
        }
        boolean ans = FD(arr, i + 1, data);
        if (ans)
            return true;

        return arr[i]==data;
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        arr = insert(n);
        int data = scn.nextInt();
        int i = 0;
        boolean DATA = FD2(arr, i, data);
        System.out.println(DATA);
    }
}