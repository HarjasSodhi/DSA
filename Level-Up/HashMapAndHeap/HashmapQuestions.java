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

}