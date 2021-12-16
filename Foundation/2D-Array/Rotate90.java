import java.util.*;

public class Rotate90 {

    public static Scanner scn = new Scanner(System.in);

    public static int[][] insert(int n) {
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = scn.nextInt();
            }
        }
        return arr;
    }

    public static void Transpose(int arr[][]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }
        Reverse(arr);
    }

    public static void Reverse(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length / 2; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[i][(arr.length) - 1 - j];
                arr[i][(arr.length) - 1 - j] = temp;
            }
        }
        display(arr);
    }

    public static void RT(int[][] arr) {
        Transpose(arr);
    }

    public static void display(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[][] arr = new int[n][n];
        arr = insert(n);
        RT(arr);
    }
}