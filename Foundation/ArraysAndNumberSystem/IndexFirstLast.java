import java.util.*;

public class IndexFirstLast {

    // public static Scanner scn = new Scanner(System.in);

    // public static int[] Insert(int n) {
    // int[] arr = new int[n];
    // for (int i = 0; i < n; i++) {
    // arr[i] = scn.nextInt();
    // }
    // return arr;
    // }

    // public static void FII() {
    // int n = scn.nextInt();
    // int[] arr = new int[n];
    // arr = Insert(n);
    // int d = scn.nextInt();
    // int FirstIndex = -1;
    // int LastIndex = -1;
    // int i = 0;
    // int j = n - 1;
    // while (i < n) {
    // if (arr[i] == d && FirstIndex == -1) {
    // FirstIndex = i;
    // }
    // if (arr[j] == d && LastIndex == -1) {
    // LastIndex = j;
    // }
    // i++;
    // j--;
    // }
    // System.out.println(FirstIndex);
    // System.out.println(LastIndex);
    // }

    // public static void main(String[] args) {
    // FII();
    // }
    // }

    public static Scanner scn = new Scanner(System.in);

    public static int[] Insert(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        return arr;
    }

    public static void IP(int[] arr, int data) {
        int FirstIndex = FI(arr, data);
        int LastIndex = LI(arr, data);
        System.out.println(FirstIndex);
        System.out.println(LastIndex);
    }

    public static int FI(int[] arr, int data) {
        int si = 0;
        int ei = arr.length - 1;
        int FirstIndex = -1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] == data && mid - 1 >= 0) {
                FirstIndex = mid;
                ei = mid - 1;
            } else if (arr[mid] < data) {
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }
        return FirstIndex;
    }

    public static int LI(int[] arr, int data) {
        int si = 0;
        int ei = arr.length - 1;
        int LastIndex = -1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (arr[mid] == data && mid + 1 < arr.length) {
                LastIndex = mid;
                si = mid + 1;
            } else if (arr[mid] < data) {
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }
        return LastIndex;
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        arr = Insert(n);
        int data = scn.nextInt();
        IP(arr, data);
    }

}