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

    public static int[] NSOR(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, n);
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int i = 0;
        while (i < n) {
            while (st.peek() != -1 && arr[st.peek()] > arr[i]) {
                ans[st.pop()] = i;
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

    public static int[] Leetcode739(int[]arr){
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, 0);
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int i = 0;
        while (i < n) {
            while (st.peek() != -1 && arr[st.peek()] < arr[i]) {
                int temp=st.pop();
                ans[temp]=i-temp;
            }
            st.push(i);
            i++;
        }
        return ans;
    }

}