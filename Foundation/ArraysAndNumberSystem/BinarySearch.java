import java.util.*;

public class BinarySearch {
    public static Scanner scn = new Scanner(System.in);

    public static int[] Insert(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        return arr;
    }

    public static int BS(int[] arr) {
        int d = scn.nextInt();
        int si = 0;
        int li = arr.length - 1;
        while (li >= si) {
            int mid = (si + li) / 2;
            if (arr[mid] == d)
                return mid;
            else if (d > arr[mid])
                si = mid + 1;
            else
                li = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        arr = Insert(n);
        int index = BS(arr);
        System.out.println(index);
    }
}