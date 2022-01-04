import java.util.*;

public class HashmapQuestions {
    // 692
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            if (map.get(a) == map.get(b)) {
                return b.compareTo(a);
            }
            return map.get(a) - map.get(b);
        });

        for (String s : map.keySet()) {
            pq.add(s);
            if (pq.size() > k) {
                pq.remove();
            }
        }

        List<String> ans = new LinkedList<>();
        while (pq.size() != 0) {
            ans.add(0, pq.remove());
        }
        return ans;
    }

    // 128
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> map = new HashSet<>();
        for (int ele : nums)
            map.add(ele);

        int len = 0;
        for (int ele : nums) {
            if (!map.contains(ele))
                continue;

            int prev = ele - 1, next = ele + 1;
            map.remove(ele);
            while (map.contains(prev))
                map.remove(prev--);
            while (map.contains(next))
                map.remove(next++);

            len = Math.max(len, next - prev - 1);
        }

        return len;
    }

    // leetcode 781
    public int numRabbits(int[] answers) {
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int e : answers) {
            if (!map.containsKey(e)) {
                map.put(e, e);
                ans += e + 1;
            } else {
                map.put(e, map.get(e) - 1);
                if (map.get(e) == 0)
                    map.remove(e);
            }
        }
        ans += Math.abs(map.getOrDefault(0, 0));
        return ans;
    }

    // leetcode 1218
    public int longestSubsequence(int[] arr, int difference) {
        int ans = 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int e : arr) {
            // chechking while filling is imp because if check after completely filling,then
            // we might not get the subaaray
            map.put(e, map.getOrDefault(e - difference, 0) + 1);
            ans = Math.max(map.get(e), ans);
        }
        return ans;
    }

    // leetcode 1424
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        int size = 0;
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                size++;
                int diag = i + j;
                if (!map.containsKey(diag))
                    map.put(diag, new ArrayList<>());

                ArrayList<Integer> temp = map.get(diag);
                int num = nums.get(i).get(j);
                temp.add(num);
                map.put(diag, temp);
            }
        }

        int[] ans = new int[size];
        int i = 0;
        int idx = 0;
        while (true) {
            if (!map.containsKey(i))
                break;
            for (int k = map.get(i).size() - 1; k >= 0; k--) {
                ans[idx] = map.get(i).get(k);
                idx++;
            }
            i++;
        }
        return ans;
    }

    // leetcode 1027
    // public int longestArithSeqLength(int[] nums) {
    // uses dp
    // }

    // leetcode 954
    public boolean canReorderDoubled(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        Integer[] ARR = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            ARR[i] = arr[i];
        }
        Arrays.sort(ARR, (a, b) -> {
            return Math.abs(a) - Math.abs(b);
        });
        for (int e : ARR) {
            if (map.get(e) == 0)
                continue;
            if (map.getOrDefault(2 * e, 0) == 0)
                return false;
            map.put(e, map.get(e) - 1);
            map.put(2 * e, map.get(2 * e) - 1);
        }
        return true;
    }

    // leetcode 127
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>();
        for (String s : wordList) {
            set.add(s);
        }
        if (!set.contains(endWord))
            return 0;
        LinkedList<String> que = new LinkedList<>();
        int level = 1;
        que.addFirst(beginWord);
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                String s = que.removeFirst();
                char[] temp = s.toCharArray();
                for (int i = 0; i < temp.length; i++) {
                    for (char j = 'a'; j <= 'z'; j++) {
                        if (temp[i] == j)
                            continue;
                        temp[i] = j;
                        String newStr = new String(temp);
                        if (endWord.equals(newStr)) {
                            return level + 1;
                        }
                        if (set.contains(newStr)) {
                            que.addLast(newStr);
                            set.remove(newStr);
                        }
                    }
                    temp = s.toCharArray();
                }
            }
            level++;
        }
        return 0;
    }

    // leetcode 380
    class RandomizedSet {
        private HashMap<Integer, Integer> set;
        private ArrayList<Integer> arr;
        private int size;

        public RandomizedSet() {
            set = new HashMap<>();
            arr = new ArrayList<>();
            size = 0;
        }

        public boolean insert(int val) {
            if (set.containsKey(val)) {
                return false;
            }
            set.put(val, size);
            arr.add(val);
            size++;
            return true;
        }

        public boolean remove(int val) {
            if (set.containsKey(val)) {
                int idx = set.get(val);
                int temp = arr.get(size - 1);
                arr.set(idx, temp);
                set.put(temp, idx);
                arr.remove(size - 1);
                set.remove(val);
                size--;
                return true;
            }
            return false;
        }

        public int getRandom() {
            System.out.println(size);
            int rand = (int) Math.floor((Math.random() * size - 1));
            System.out.println(rand);
            return arr.get(rand);
        }
    }

    // leetcode 447
    public int numberOfBoomerangs(int[][] points) {
        int n = points.length;
        if (n < 3)
            return 0;
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;
                int[] p1 = points[i];
                int[] p2 = points[j];
                int dist = dist(p1, p2);
                map.put(dist, map.getOrDefault(dist, 0) + 1);
            }
            for (Integer e : map.values()) {
                ans += e * (e - 1);
            }
            map.clear();
        }
        return ans;
    }

    public int dist(int[] p1, int[] p2) {
        return ((p2[0] - p1[0]) * (p2[0] - p1[0])) + ((p2[1] - p1[1]) * (p2[1] - p1[1]));
    }

    // leetcode 149
    public int maxPoints(int[][] points) {
        int ans = 1;
        int n = points.length;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                int gcd = gcd(x, y);
                x /= gcd;
                y /= gcd;
                String s = y + "@" + x;
                map.put(s, map.getOrDefault(s, 1) + 1);
                max = map.get(s);
                ans = Math.max(ans, max);
            }
            map.clear();
        }
        return ans;
    }

    public int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

}