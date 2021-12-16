import java.util.*;

public class Revision {
    public static void printIncreasing(int a, int b) {
        if (a > b)
            return;
        System.out.println(a);
        printIncreasing(a + 1, b);
    }

    public static void printDecreasing(int a, int b) {
        if (a > b)
            return;
        printDecreasing(a + 1, b);
        System.out.println(a);
    }

    public static void printIncreasingDecreasing(int a, int b) {
        if (a > b)
            return;
        System.out.println(a);
        printIncreasingDecreasing(a + 1, b);
        System.out.println(a);
    }

    public static void oddEven(int a, int b) {
        if (a > b)
            return;
        if (a % 2 != 0)
            System.out.println(a);
        oddEven(a + 1, b);
        if (a % 2 == 0)
            System.out.println(a);
    }

    public static int factorial(int n) {
        if (n == 1 || n == 0)
            return 1;
        return n * factorial(n - 1);
    }

    public static int power(int a, int b) {
        if (b == 0)
            return 1;
        return a * power(a, b - 1);
    }

    // O(logn)
    public static int powerBtr(int a, int b) {
        if (b == 0)
            return 1;
        int recans = powerBtr(a, b / 2);
        recans *= recans;
        if (b % 2 != 0)
            recans *= a;
        return recans;
    }

    public static void printArray(int[] arr, int i) {
        if (i == arr.length)
            return;
        System.out.println(arr[i]);
        printArray(arr, i + 1);
    }

    public static void printArrayReverse(int[] arr, int i) {
        if (i == arr.length)
            return;
        printArray(arr, i + 1);
        System.out.println(arr[i]);
    }

    public static int maximum(int[] arr, int i) {
        if (i == arr.length)
            return -(int) 1e9;
        return Math.max(arr[i], maximum(arr, i + 1));
    }

    public static int minimum(int[] arr, int i) {
        if (i == arr.length)
            return (int) 1e9;
        return Math.min(arr[i], maximum(arr, i + 1));
    }

    public static boolean find(int[] arr, int data, int i) {
        if (i == arr.length)
            return false;
        if (arr[i] == data)
            return true;
        boolean res = false;
        res = res || find(arr, data, i + 1);
        // or return false || find(arr, data, i + 1);
        return res;
    }

    public static int firstIndex(int[] arr, int data, int i) {
        if (i == arr.length)
            return -1;
        if (arr[i] == data)
            return i;
        return firstIndex(arr, data, i + 1);
    }

    public static int lastIndex(int[] arr, int data, int i) {
        if (i == arr.length)
            return -1;
        int recans = lastIndex(arr, data, i + 1);
        if (recans == -1)
            if (data == arr[i])
                recans = i;
        return recans;
    }

    public static int[] allIndex(int[] arr, int data, int i, int freq) {
        if (i == arr.length)
            return new int[freq];
        if (data == arr[i])
            freq++;
        int[] recans = allIndex(arr, data, i + 1, freq);
        if (data == arr[i]) {
            recans[recans.length - freq] = i;
            freq--;
        }
        return recans;
    }

    public static ArrayList<String> subsequence(String str, int i) {
        if (i == str.length()) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> recAns = subsequence(str, i + 1);
        ArrayList<String> MyAns = new ArrayList<>(recAns);
        for (String ele : recAns) {
            MyAns.add(str.charAt(i) + ele);
        }
        return MyAns;
    }

    public static int subsequence(String str, int i, String asf, ArrayList<String> ans) {
        if (i == str.length()) {
            ans.add(asf);
            return 1;
        }
        int count = 0;
        count += subsequence(str, i + 1, asf + str.charAt(i), ans);
        count += subsequence(str, i + 1, asf, ans);
        return count;
    }

    public static String[] nokiaKeys = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

    public static int nokiaKeyPad(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        char ch = str.charAt(0);
        String chStr = nokiaKeys[ch - '0'];
        for (int i = 0; i < chStr.length(); i++) {
            count += nokiaKeyPad(str.substring(1), ans + chStr.charAt(i));
        }
        return count;
    }

    public static int stairPath(int n, String psf, ArrayList<String> ans) {
        if (n < 0)
            return 0;
        if (n == 0) {
            ans.add(psf);
            return 1;
        }
        int count = 0;
        for (int i = 1; i <= n; i++) {
            count += stairPath(n - i, psf + i, ans);
        }
        return count;
    }

    public static ArrayList<String> stairPath(int n) {
        if (n == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> myAns = new ArrayList<>();
        for (int i = 1; i <= 3 && n - i >= 0; i++) {
            ArrayList<String> recAns = stairPath(n - i);
            for (String ele : recAns) {
                myAns.add(i + ele);
            }
        }
        return myAns;
    }

    public static int boardPath(int n, String psf, ArrayList<String> ans) {
        if (n < 0)
            return 0;
        if (n == 0) {
            ans.add(psf);
            return 1;
        }
        int count = 0;
        for (int i = 1; i <= 6; i++) {
            count += boardPath(n - i, psf + i, ans);
        }
        return count;
    }

    public static int boardPath(int[] arr, int n, String ans) {
        if (n < 0)
            return 0;
        if (n == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = 0; i <= arr.length; i++) {
            count += boardPath(arr, n - arr[i], ans + arr[i]);
        }
        return count;
    }

    public static int mazePath_HVD(int sr, int sc, int er, int ec, String psf, ArrayList<String> ans) {
        if (sr == er && sc == ec) {
            ans.add(psf);
            return 1;
        }
        int count = 0;
        if (sr + 1 <= er)
            count += mazePath_HVD(sr + 1, sc, er, ec, psf + "v", ans);
        if (sc + 1 <= ec)
            count += mazePath_HVD(sr, sc + 1, er, ec, psf + "h", ans);
        if (sc + 1 <= ec && sr + 1 <= er)
            count += mazePath_HVD(sr + 1, sc + 1, er, ec, psf + "d", ans);
        return count;
    }

    // also can use radius method
    public static int mazePath_HVD_multi(int sr, int sc, int er, int ec, String psf, ArrayList<String> ans) {
        if (sr == er && sc == ec) {
            ans.add(psf);
            return 1;
        }
        int count = 0;
        for (int i = 1; i <= Math.max(ec, er); i++) {
            if (sr + i <= er)
                count += mazePath_HVD_multi(sr + i, sc, er, ec, psf + "V" + i, ans);
            if (sc + i <= ec)
                count += mazePath_HVD_multi(sr, sc + i, er, ec, psf + "H" + i, ans);
            if (sc + i <= ec && sr + i <= er)
                count += mazePath_HVD_multi(sr + 1, sc + i, er, ec, psf + "D" + i, ans);
        }
        return count;
    }

    // https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1#
    // https://www.geeksforgeeks.org/a-variation-of-rat-in-a-maze-multiple-steps-or-jumps-allowed/?ref=rp
    // https://practice.geeksforgeeks.org/problems/special-matrix4201/1#F

    public static void main(String[] args) {

    }
}