import java.util.*;

public class PandC {
    public static int InfiPerm(int[] arr, int target, String asf) {// can take values from front and back of the curr
                                                                   // index
        if (target == 0) {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (target - arr[i] >= 0)
                count += InfiPerm(arr, target - arr[i], asf + arr[i]);
        }
        return count;
    }

    public static int InfiCombi(int[] arr, int target, String asf, int i) {// can take values from curr index and front
                                                                           // of the curr index
        if (target == 0) {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        for (int idx = i; idx < arr.length; idx++) {
            if (target - arr[idx] >= 0)
                count += InfiCombi(arr, target - arr[idx], asf + arr[idx], idx);
        }
        return count;
    }

    public static int FiniCombi(int[] arr, int target, String asf, int i) {// can take values from front of the curr
                                                                           // index only
        if (target == 0) {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        for (int idx = i; idx < arr.length; idx++) {
            if (target - arr[idx] >= 0)
                count += FiniCombi(arr, target - arr[idx], asf + arr[idx], idx + 1);
        }
        return count;
    }

    public static int FiniPerm(int[] arr, int target, String asf) {// can take values from any side but only once
        if (target == 0) {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        for (int idx = 0; idx < arr.length; idx++) {
            int tempVal = arr[idx];
            if (arr[idx] != -1 && target - tempVal >= 0) {
                arr[idx] = -1;
                count += FiniPerm(arr, target - tempVal, asf + tempVal);
                arr[idx] = tempVal;
            }
        }
        return count;
    }

    public static int FiniCombi_subSeq(int[] arr, int target, String asf, int i) {
        if (target == 0 || i >= arr.length) {
            if (target == 0) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if (target - arr[i] >= 0)
            count += FiniCombi_subSeq(arr, target - arr[i], asf + arr[i], i + 1);
        count += FiniCombi_subSeq(arr, target, asf, i + 1);
        return count;
    }

    public static int InfiCombi_subSeq(int[] arr, int target, String asf, int i) {
        if (target == 0 || i >= arr.length) {
            if (target == 0) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if (target - arr[i] >= 0)
            count += InfiCombi_subSeq(arr, target - arr[i], asf + arr[i], i);
        count += InfiCombi_subSeq(arr, target, asf, i + 1);
        return count;
    }

    public static int InfiPerm_subSeq(int[] arr, int target, String asf, int i) {
        if (target == 0 || i >= arr.length) {
            if (target == 0) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if (target - arr[i] >= 0)
            count += InfiPerm_subSeq(arr, target - arr[i], asf + arr[i], 0);
        count += InfiPerm_subSeq(arr, target, asf, i + 1);
        return count;
    }

    public static int FiniPerm_subSeq(int[] arr, int target, String asf, int i, boolean[] vis) {
        if (target == 0 || i >= arr.length) {
            if (target == 0) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if (target - arr[i] >= 0 && !vis[i]) {
            vis[i] = true;
            count += FiniPerm_subSeq(arr, target - arr[i], asf + arr[i], 0, vis);
            vis[i] = false;
        }
        count += FiniPerm_subSeq(arr, target, asf, i + 1, vis);
        return count;
    }

    // https://www.interviewbit.com/problems/subset/
    public static ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> A) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> asf = new ArrayList<>();
        Collections.sort(A);
        FiniCombi2(A, ans, asf, 0);
        return ans;
    }

    public static void FiniCombi2(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> asf,
            int i) {
        ArrayList<Integer> recAns = new ArrayList<>();
        for (int e : asf) {
            recAns.add(e);
        }
        if (!ans.contains(recAns)) {
            ans.add(recAns);
        }
        if (i == A.size())
            return;
        asf.add(A.get(i));
        FiniCombi2(A, ans, asf, i + 1);
        asf.remove(asf.size() - 1);
        FiniCombi2(A, ans, asf, i + 1);
    }

    // tnb : total no. boxes, bno : box no, tnq : total No. queen, qpsf : queen
    // placed soo far, asf : answer soo far
    public static int queenCombination(int tnb, int bno, int tnq, int qpsf, String asf) {
        if (qpsf == tnq) {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        for (int i = bno; i < tnb; i++) {
            count += queenCombination(tnb, i + 1, tnq, qpsf + 1, asf + i);
        }
        return count;
    }

    public static int queenPermutation(int tnb, int bno, int tnq, int qpsf, String asf, boolean[] vis) {
        if (qpsf == tnq) {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < tnb; i++) {
            if (!vis[i]) {
                vis[i] = true;
                count += queenPermutation(tnb, 0, tnq, qpsf + 1, asf + i, vis);
                vis[i] = false;
            }
        }
        return count;
    }

    public static int queenCombination2D(boolean[][] box, int bno, int tnq, String asf) {
        if (tnq == 0) {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        int n = box.length;
        int m = box[0].length;
        for (int i = bno; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            count += queenCombination2D(box, i + 1, tnq - 1, asf + "(" + r + "," + c + ") ");
        }
        return count;
    }

    public static int queenPermutation2D(boolean[][] box, int tnq, String asf) {
        if (tnq == 0) {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        int n = box.length;
        int m = box[0].length;
        for (int i = 0; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (!box[r][c]) {
                box[r][c] = true;
                count += queenPermutation2D(box, tnq - 1, asf + "(" + r + "," + c + ") ");
                box[r][c] = false;
            }
        }
        return count;
    }

    

    public static void main(String[] args) {
       
    }
}