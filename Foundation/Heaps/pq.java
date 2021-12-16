import java.util.*;

public class pq {

    // O(nlog(n));
    // S(n);
    public static void KthLargest(int n, int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        for (int i = 0; i < n; i++) {
            pq.add(arr[i]);
        }
        for (int i = 0; i < k; i++) {
            System.out.print(pq.remove() + " ");
        }
    }

    public static void KthSmallest(int n, int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.add(arr[i]);
        }
        for (int i = 0; i < k; i++) {
            System.out.print(pq.remove() + " ");
        }
    }

    // O(nlog(k));
    // S(k);
    public static void KthLargestOptimised(int n, int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            if (i < k) {
                pq.add(arr[i]);
            } else {
                pq.add(arr[i]);
                pq.remove();
            }
        }
        System.out.println(pq.peek());
    }

    public static void KthSmallestOptimised(int n, int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        for (int i = 0; i < arr.length; i++) {
            if (i < k) {
                pq.add(arr[i]);
            } else {
                pq.add(arr[i]);
                pq.remove();
            }
        }
        System.out.println(pq.peek());
    }

    public static void kSortedArray(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < arr.length; i++) {
            if (i < k) {
                pq.add(arr[i]);
            } else {
                pq.add(arr[i]);
                System.out.println(pq.remove());
            }
        }
        while (pq.size() != 0) {
            System.out.println(pq.remove());
        }
    }

    // (n*m)log(n);
    public static void RowSorted2DArray(int[][] arr) {
        // ony each row is sorted you have to sort whole array
        int n = arr.length, m = arr[0].length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int r1 = a / n, c1 = a % m, r2 = b / n, c2 = b % m;
            return arr[r1][c1] - arr[r2][c2];
        });
        for (int i = 0; i < n; i++) {
            pq.add(i * m + 0);
        }
        int[] ans = new int[n * m];
        int idx = 0;
        while (pq.size() != 0) {
            int eidx = pq.remove();
            int r = eidx / n, c = eidx % m;
            ans[idx++] = arr[r][c];
            c++;
            if (c < m) {
                pq.add(r * m + c);
            }
        }
        for (int ele : ans) {
            System.out.println(ele);
        }
    }

}