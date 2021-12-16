import java.util.*;

public class ForEleLoop {
    public static Scanner scn = new Scanner(System.in);

    public static int[] insert(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        return arr;
    }

    public static void display(int[] arr) {
        for (int ele : arr) {
            System.out.println(ele);
        }
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        arr = insert(n);
        display(arr);
    }
}
