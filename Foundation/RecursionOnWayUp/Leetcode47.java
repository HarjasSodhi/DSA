import java.util.*;

public class Leetcode47 {
    public static Scanner scn = new Scanner(System.in);

    public static void PRM(int[] ques, int count, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> ans) {
        if (count == ques.length) {
            ArrayList<Integer> base = new ArrayList<>(ans);
            res.add(base);
            return;
        }
        int prev = -11;
        for (int i = 0; i < ques.length; i++) {
            if (ques[i] >= -10) {
                if (prev != ques[i]) {
                    int val = ques[i];
                    ans.add(val);
                    ques[i] = -11;
                    PRM(ques, count + 1, res, ans);
                    ans.remove(ans.size() - 1);
                    ques[i] = val;
                    prev = ques[i];
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] ques = new int[n];
        for (int i = 0; i < n; i++) {
            ques[i] = scn.nextInt();
        }
        int count = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        Arrays.sort(ques);
        PRM(ques, count, res, ans);
        System.out.println(res);
    }
}