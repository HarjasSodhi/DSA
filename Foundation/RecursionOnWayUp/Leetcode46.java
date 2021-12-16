import java.util.*;

public class Leetcode46 {
    public static Scanner scn = new Scanner(System.in);

    public static void PRM(int[] ques, int count, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> ans) {
        if (count == ques.length) {
            ArrayList<Integer> base = new ArrayList<>(ans);
            res.add(base);
            return;
        }
        for (int i = 0; i < ques.length; i++) {
            if (ques[i] >= -10) {
                int val = ques[i];
                ques[i] = -11;
                ans.add(val);
                PRM(ques, count + 1, res, ans);
                ans.remove(ans.size() - 1);
                ques[i] = val;
            }
        }
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] ques = new int[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            ques[i] = scn.nextInt();
        }
        ArrayList<Integer> ans = new ArrayList<>();
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        PRM(ques, count, res, ans);
        System.out.println(res);
    }
}