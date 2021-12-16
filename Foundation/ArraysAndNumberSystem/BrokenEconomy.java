import java.util.*;

public class BrokenEconomy {
    public static Scanner scn = new Scanner(System.in);

    public static int[] insert(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        return arr;
    }

    // public static void BE() {
    // int n = scn.nextInt();
    // int[] arr = new int[n];
    // arr = insert(n);
    // int ceil = -1;
    // int floor = -1;
    // int d = scn.nextInt();
    // for (int i = 0; i < n; i++) {
    // if (arr[i] >= d) {
    // if (arr[i] == d) {
    // ceil = floor = arr[i];
    // break;
    // } else {
    // ceil = arr[i];
    // floor = arr[i - 1];
    // break;
    // }
    // }
    // }
    // System.out.println(ceil);
    // System.out.println(floor);
    // }
    public static void BE2() {
        int n = scn.nextInt();
        int[] arr = new int[n];
        arr = insert(n);
        int data = scn.nextInt();
        int ei = arr.length - 1;
        int si = 0;
        int ceil = -1;
        int floor = -1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] == data) {
                floor = arr[mid];
                ceil = arr[mid];
                break;
            } else if (data > arr[mid]) {
                floor = arr[mid];
                si = mid + 1;
            } else {
                ceil = arr[mid];
                ei = mid - 1;
            }
        }
        System.out.println(ceil);
        System.out.println(floor);
    }

    public static void main(String[] args) {
        // BE();
        BE2();

    }

}