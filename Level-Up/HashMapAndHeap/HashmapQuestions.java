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

    

}