import java.util.Stack;
import java.util.Arrays;
import java.util.LinkedList;

//classLink= https://classroom.pepcoding.com/the-placement-program-pitampura-mar-14-2021/116/classvideos/5533
public class NextGreaterSet {

    public static int[] NGOR(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, n);
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int i = 0;
        while (i < n) {
            while (st.peek() != -1 && arr[st.peek()] < arr[i]) {
                ans[st.pop()] = i;
            }
            st.push(i);
            i++;
        }
        return ans;
    }

    // worst -> o(3n)
    public static int[] NSOR(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, n);// n
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int i = 0;
        while (i < n) {// n
            while (st.peek() != -1 && arr[st.peek()] > arr[i]) {
                ans[st.pop()] = i;// n
            }
            st.push(i);
            i++;
        }
        return ans;
    }

    public static int[] NGOL(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int i = n - 1;
        while (i >= 0) {
            while (st.peek() != -1 && arr[st.peek()] < arr[i]) {
                ans[st.pop()] = i;
            }
            st.push(i);
            i--;
        }
        return ans;
    }

    public static int[] NSOL(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int i = n - 1;
        while (i >= 0) {
            while (st.peek() != -1 && arr[st.peek()] > arr[i]) {
                ans[st.pop()] = i;
            }
            st.push(i);
            i--;
        }
        return ans;
    }

    public int[] nextGreaterElements(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);

        for (int i = 0; i < 2 * n; i++) {
            while (st.getFirst() != -1 && arr[st.getFirst()] < arr[i % n])
                ans[st.removeFirst()] = i % n;

            if (i < n)
                st.addFirst(i);
        }

        return ans;
    }

    // https://practice.geeksforgeeks.org/problems/stock-span-problem-1587115621/1#
    public static int[] calculateSpan(int arr[], int n) {
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        for (int i = 0; i < n; i++) {
            int quant = 1;
            while (st.peek() != -1 && arr[st.peek()] <= arr[i]) {
                quant += ans[st.pop()];
            }
            st.push(i);
            ans[i] = quant;
        }
        return ans;
    }

    // secoond approach
    public static int[] calculateSpan2(int[] arr, int n) {
        int[] ans = new int[n];
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);

        for (int i = 0; i < n; i++) {
            while (st.getFirst() != -1 && arr[st.getFirst()] <= arr[i])
                st.removeFirst();

            ans[i] = i - st.getFirst();
            st.addFirst(i);
        }

        return ans;
    }

    class StockSpanner {

        private Stack<int[]> st = new Stack<>();

        public StockSpanner() {
            int[] setup = { -1, 0 };
            this.st.push(setup);
        }

        public int next(int price) {
            int quant = 1;
            while (st.peek()[0] != -1 && (st.peek()[1]) <= price) {
                quant += st.pop()[0];
            }
            int[] temp = { quant, price };
            st.push(temp);
            return quant;
        }
    }

    public boolean isValid(String str) {
        LinkedList<Character> st = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{')
                st.addFirst(ch);
            else {
                if (st.size() == 0)
                    return false;
                else if (st.getFirst() == '(' && ch != ')')
                    return false;
                else if (st.getFirst() == '[' && ch != ']')
                    return false;
                else if (st.getFirst() == '{' && ch != '}')
                    return false;
                else
                    st.removeFirst();
            }
        }
        return st.size() == 0;
    }

    public static int[] Leetcode739(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, 0);
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int i = 0;
        while (i < n) {
            while (st.peek() != -1 && arr[st.peek()] < arr[i]) {
                int temp = st.pop();
                ans[temp] = i - temp;
            }
            st.push(i);
            i++;
        }
        return ans;
    }

    // leetcode 735
    public int[] asteroidCollision(int[] asteroids) {
        LinkedList<Integer> st = new LinkedList<>();
        for (int i = 0; i < asteroids.length; i++) {
            int curr = asteroids[i];
            if (curr < 0) {
                while (st.size() != 0 && st.getFirst() >= 0 && st.getFirst() < (Math.abs(curr))) {
                    st.removeFirst();
                }
                if (st.size() != 0 && st.getFirst() >= 0) {
                    if (Math.abs(curr) == st.getFirst()) {
                        st.removeFirst();
                    }
                } else {
                    st.addFirst(curr);
                }
            } else {
                st.addFirst(curr);
            }
        }
        int len = st.size();
        int[] ans = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            ans[i] = st.removeFirst();
        }

        return ans;
    }

    // leetcode 946
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        LinkedList<Integer> st = new LinkedList<>();
        int pop = 0;
        for (int i = 0; i < pushed.length; i++) {
            st.addFirst(pushed[i]);
            while (st.size() != 0 && st.getFirst() == popped[pop]) {
                st.removeFirst();
                pop++;
            }
        }
        return st.size() == 0;
    }

    // leetcode 856
    public int scoreOfParentheses(String s) {
        int ans = 0;
        LinkedList<Integer> st = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                st.addFirst(ans);
                ans = 0;
            } else {
                ans = st.removeFirst() + Math.max(2 * ans, 1);
            }
        }
        return ans;
    }

    // leetcode 84
    // 7n
    public int largestRectangleArea01(int[] heights) {
        int ans = 0;
        int[] NSOR = NSOR(heights);// 3n
        int[] NSOL = NSOL(heights);// 3n
        int n = heights.length;

        for (int i = 0; i < n; i++) {// n
            int sl = NSOL[i];
            int sr = NSOR[i];
            sr--;
            sl++;
            ans = Math.max(ans, (sr - sl + 1) * heights[i]);
        }

        return ans;
    }

    // 2n
    public int largestRectangleArea02(int[] heights) {
        int ans = 0;

        Stack<Integer> st = new Stack<>();
        st.push(-1);

        int n = heights.length;
        for (int i = 0; i < n; i++) {
            while (st.peek() != -1 && heights[st.peek()] >= heights[i]) {
                int val = heights[st.pop()];
                ans = Math.max(ans, val * (i - st.peek() - 1));
            }
            st.push(i);
        }

        while (st.peek() != -1) {
            int val = heights[st.pop()];
            ans = Math.max(ans, val * (n - st.peek() - 1));
        }

        return ans;
    }

    public int maximalRectangle(char[][] matrix) {
        int ans = 0;
        int n = matrix.length, m = matrix[0].length;
        if (n == 0 || m == 0)
            return 0;

        int[] heights = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                heights[j] = matrix[i][j] == '0' ? 0 : heights[j] + 1;
            }
            ans = Math.max(ans, largestRectangleArea02(heights));
        }

        return ans;
    }

    public int longestValidParentheses(String s) {
        int ans = 0;
        Stack<Integer> st = new Stack<>();
        int n = s.length();
        st.push(-1);
        for (int i = 0; i < n; i++) {
            if (st.peek() != -1 && s.charAt(st.peek()) == '(' && s.charAt(i) == ')') {
                st.pop();
                ans = Math.max(ans, i - st.peek());
            } else {
                st.push(i);
            }
        }

        return ans;
    }

}