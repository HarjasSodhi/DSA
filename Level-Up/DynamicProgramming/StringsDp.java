import java.util.*;

public class StringsDp {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        return lps(s, 0, n - 1, dp);
    }

    public int lps(String s, int i, int j, int[][] dp) {
        if (i >= j) {
            return dp[i][j] = (i == j ? 1 : 0);
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        if (s.charAt(i) == s.charAt(j)) {
            return dp[i][j] = lps(s, i + 1, j - 1, dp) + 2;
        } else {
            return dp[i][j] = Math.max(lps(s, i + 1, j, dp), lps(s, i, j - 1, dp));
        }
    }

    public int lpsDP(String s, int I, int J, int[][] dp) {
        int n = s.length();
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (i >= j) {
                    dp[i][j] = (i == j ? 1 : 0);
                    continue;
                }
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = lps(s, i + 1, j - 1, dp) + 2;
                } else {
                    dp[i][j] = Math.max(lps(s, i + 1, j, dp), lps(s, i, j - 1, dp));
                }
            }
        }
        return dp[I][J];
    }

    public int longestCommonSubsequence(String text1, String text2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        if (text1.charAt(n - 1) == text2.charAt(m - 1))
            return dp[n][m] = longestCommonSubsequence(text1, text2, n - 1, m - 1, dp) + 1;
        else {
            return dp[n][m] = Math.max(longestCommonSubsequence(text1, text2, n - 1, m, dp),
                    longestCommonSubsequence(text1, text2, n, m - 1, dp));
        }
    }

    public static int longestCommonSubsequence_DP(String str1, String str2, int N, int M, int[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }

                if (str1.charAt(n - 1) == str2.charAt(m - 1))
                    dp[n][m] = dp[n - 1][m - 1] + 1;// lcss(str1, str2, n - 1, m - 1, dp) + 1;
                else
                    dp[n][m] = Math.max(dp[n - 1][m], dp[n][m - 1]);
            }
        }

        return dp[N][M];
    }

    public int numDistinct(String s, String t, int n, int m) {
        if (n < m) {
            return 0;
        }
        if (m == 0) {
            return 1;
        }
        int count = 0;
        if (s.charAt(n - 1) == t.charAt(m - 1)) {
            count += numDistinct(s, t, n - 1, m - 1);
        }
        count += numDistinct(s, t, n - 1, m);
        return count;
    }

    public int numDistinct(String s, String t, int n, int m, int[][] dp) {
        if (n < m) {
            return dp[n][m] = 0;
        }
        if (m == 0) {
            return dp[n][m] = 1;
        }
        if (dp[n][m] != -1) {
            return dp[n][m];
        }
        int count = 0;
        if (s.charAt(n - 1) == t.charAt(m - 1)) {
            count += numDistinct(s, t, n - 1, m - 1, dp);
        }
        count += numDistinct(s, t, n - 1, m, dp);
        return dp[n][m] = count;
    }

    public int numDistinctTabu(String s, String t) {
        int N = s.length();
        int M = t.length();
        int[][] dp = new int[N + 1][M + 1];
        for (int n = 0; n < N + 1; n++) {
            for (int m = 0; m < M + 1; m++) {
                if (n < m) {
                    dp[n][m] = 0;
                    continue;
                }
                if (m == 0) {
                    dp[n][m] = 1;
                    continue;
                }

                int count = 0;
                if (s.charAt(n - 1) == t.charAt(m - 1)) {
                    count += dp[n - 1][m - 1];// numDistinct(s, t, n - 1, m - 1, dp);
                }
                count += dp[n - 1][m];// numDistinct(s, t, n - 1, m, dp);
                dp[n][m] = count;
            }
        }
        return dp[N][M];
    }

    public int minDistance(String word1, String word2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = n == 0 ? m : n;
        }
        if (dp[n][m] != -1) {
            return dp[n][m];
        }
        if (word1.charAt(n - 1) != word2.charAt(m - 1)) {

            int replace = minDistance(word1, word2, n - 1, m - 1, dp) + 1;
            int delete = minDistance(word1, word2, n - 1, m, dp) + 1;
            int add = minDistance(word1, word2, n, m - 1, dp) + 1;
            return dp[n][m] = Math.min(replace, Math.min(delete, add));
        } else {
            return dp[n][m] = minDistance(word1, word2, n - 1, m - 1, dp);
        }
    }

    public String removeStars(String str) {
        if (str.length() == 0)
            return str;

        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));

        int i = 1;
        while (i < str.length()) {
            while (i < str.length() && sb.charAt(sb.length() - 1) == '*' && str.charAt(i) == '*')
                i++;

            if (i < str.length())
                sb.append(str.charAt(i));
            i++;
        }

        return sb.toString();
    }

    public int isMatch(String s, String p, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            if (n == 0 && m == 0)
                return dp[n][m] = 1; // true
            else if (m == 1 && p.charAt(m - 1) == '*')
                return dp[n][m] = 1;
            else
                return dp[n][m] = 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        char ch1 = s.charAt(n - 1);
        char ch2 = p.charAt(m - 1);

        if (ch1 == ch2 || ch2 == '?') {
            return dp[n][m] = isMatch(s, p, n - 1, m - 1, dp);
        } else if (ch2 == '*') {
            boolean res = false;
            res = res || isMatch(s, p, n - 1, m, dp) == 1; // sequnence of character
            res = res || isMatch(s, p, n, m - 1, dp) == 1; // empty string

            return dp[n][m] = res ? 1 : 0;

        } else
            return dp[n][m] = 0;
    }

    // https://leetcode.com/problems/regular-expression-matching/
    // hw

    public int maxUncrossedLines(int[] nums1, int[] nums2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        if (nums1[n - 1] == nums2[m - 1])
            return dp[n][m] = maxUncrossedLines(nums1, nums2, n - 1, m - 1, dp) + 1;
        else {
            return dp[n][m] = Math.max(maxUncrossedLines(nums1, nums2, n - 1, m, dp),
                    maxUncrossedLines(nums1, nums2, n, m - 1, dp));
        }
    }

    public int maximum(int... arr) {
        int max = arr[0];
        for (int ele : arr)
            max = Math.max(ele, max);

        return max;
    }

    public int maxDotProduct(int[] nums1, int[] nums2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = -(int) 1e8;
        }
        if (dp[n][m] != -(int) 1e9)
            return dp[n][m];
        int val = nums1[n - 1] * nums2[m - 1];
        int acceptBothNumbers = maxDotProduct(nums1, nums2, n - 1, m - 1, dp) + val;
        int a = maxDotProduct(nums1, nums2, n - 1, m, dp);
        int b = maxDotProduct(nums1, nums2, n, m - 1, dp);

        return dp[n][m] = maximum(val, acceptBothNumbers, a, b);
    }

    public static int LongestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0;
        int maxlength = -1;
        int si = -1;
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 0)
                    dp[i][j] = true;
                if (gap == 1 && s.charAt(i) == s.charAt(j))
                    dp[i][j] = true;
                else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                }
                if (dp[i][j]) {
                    count++;
                    if (j - i + 1 > maxlength) {
                        maxlength = j - i + 1;
                        si = i;
                    }
                }
            }
        }
        String ans = s.substring(si, si + maxlength);
        System.out.println(ans);
        return count;
    }

    public static int longestCommonSubstring(String s, String s2) {
        int n = s.length();
        int m = s2.length();
        int count = 0;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                if (n == 0 || m == 0)
                    dp[i][j] = 0;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    count++;
                }
            }
        }
        return count;
    }

    // testcase - fafaaaaabaageeg
    // 132
    public int minCut(String s, int si, int ei, int[] dp, boolean[][] pdp) {
        if (pdp[si][ei])
            return 0;
        if (dp[si] != -1)
            return dp[si];

        int minAns = (int) 1e8;
        for (int cut = si; cut <= ei; cut++) {
            if (pdp[si][cut]) {
                minAns = Math.min(minAns, minCut(s, cut + 1, ei, dp, pdp) + 1);
            }
        }

        return dp[si] = minAns;
    }

    // faafaaaaabaageeg
    public int minCut(String s) {
        int n = s.length();
        boolean[][] pdp = new boolean[n][n];
        for (int gap = 0; gap < n; gap++)
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 0)
                    pdp[i][j] = true;
                else if (gap == 1 && s.charAt(i) == s.charAt(j))
                    pdp[i][j] = true;
                else
                    pdp[i][j] = s.charAt(i) == s.charAt(j) && pdp[i + 1][j - 1];
            }

        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return minCut(s, 0, n - 1, dp, pdp);
    }

    // https://practice.geeksforgeeks.org/problems/count-subsequences-of-type-ai-bj-ck4425/1
    public int fun(String s) {
        int n = s.length();
        long emptyCount = 1, aCount = 0, bCount = 0, cCount = 0, mod = (long) 1e9 + 7;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch == 'a')
                aCount = aCount + (emptyCount + aCount) % mod;
            else if (ch == 'b')
                bCount = bCount + (aCount + bCount) % mod;
            else if (ch == 'c')
                cCount = cCount + (bCount + cCount) % mod;
        }

        return (int) (cCount % mod);
    }

    public boolean wordBreak(String s, ArrayList<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        HashSet<String> hs = new HashSet<>();
        int len = -1;
        for (String str : wordDict) {
            hs.add(str);
            len = Math.max(str.length(), len);
        }
        dp[0] = true;
        for (int i = 0; i <= n; i++) {
            if (!dp[i])
                continue;
            for (int j = 1; j <= len && j + i <= n; j++) {
                String substr = s.substring(i, i + j);
                if (hs.contains(substr)) {
                    dp[i + j] = true;
                }
            }
        }
        return dp[n];
    }

    
    public static String lpss_backEng(String str, int si, int ei, int[][] dp) {
        if (si >= ei) {
            return si == ei ? str.charAt(si) + "" : "";
        }

        if (str.charAt(si) == str.charAt(ei)) {
            return str.charAt(si) + lpss_backEng(str, si + 1, ei - 1, dp) + str.charAt(ei);
        } else if (dp[si + 1][ei] > dp[si][ei - 1]) {
            return lpss_backEng(str, si + 1, ei, dp);
        } else {
            return lpss_backEng(str, si, ei - 1, dp);
        }
    }

    public void wordBreak_backEngg(String s, int idx, boolean[] dp, int maxLen, List<String> wordDict,
            HashSet<String> set, String ssf, List<String> ans) {
        if (idx >= s.length()) {
            ans.add(ssf.substring(0, ssf.length() - 1));
            return;
        }

        for (int l = 1; l <= maxLen && idx + l <= s.length(); l++) {
            if (dp[idx + l]) {
                String substr = s.substring(idx, idx + l);
                if (set.contains(substr)) {
                    wordBreak_backEngg(s, idx + l, dp, maxLen, wordDict, set, ssf + substr + " ", ans);
                }
            }
        }
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        int len = 0, n = s.length();
        for (String ss : wordDict) {
            set.add(ss);
            len = Math.max(ss.length(), len);
        }

        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 0; i <= n; i++) {
            if (!dp[i])
                continue;

            for (int l = 1; l <= len && i + l <= n; l++) {
                String substr = s.substring(i, i + l);
                if (set.contains(substr)) {
                    dp[i + l] = true;
                }
            }
        }

        List<String> ans = new ArrayList<>();
        if (dp[n])
            wordBreak_backEngg(s, 0, dp, len, wordDict, set, "", ans);

        return ans;
    }

}