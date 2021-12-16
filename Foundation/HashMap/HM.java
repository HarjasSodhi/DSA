import java.util.*;

public class HM {

    public static Scanner scn = new Scanner(System.in);

    public static void HashMapBasic() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Player1", 98);
        map.put("Player2", 99);
        map.put("Player3", 98);
        map.put("Player3", 95);

        // map.remove("UK");
        if (map.containsKey("Player3"))
            System.out.println(map.get("Player3"));

        // System.out.println(map);
        // ArrayList<String> keys = new ArrayList<>(map.keySet());
        // System.out.println(keys);
        for (String key : map.keySet()) {
            System.out.println(key + " -> " + map.get(key));
        }
    }

    public static void FeqMap(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            // if (map.containsKey(str.charAt(i))) {
            // map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
            // } else {
            // map.put(str.charAt(i), 1);
            // }

            // or

            map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
        }
        System.out.println(map);
    }

    public static void FeqMap(int[] arr, HashMap<Integer, Integer> map) {
        for (int i = 0; i < arr.length; i++) {
            // if (map.containsKey(str.charAt(i))) {
            // map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
            // } else {
            // map.put(str.charAt(i), 1);
            // }

            // or

            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        System.out.println(map);
    }

    public static void IdxMap(String str) {
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            // ArrayList<Integer> temp = new ArrayList<>();
            // if (map.containsKey(str.charAt(i)))
            // temp = map.get(str.charAt(i));
            // temp.add(i);
            // map.put(str.charAt(i), temp);
            map.putIfAbsent(str.charAt(i), new ArrayList<>());
            map.get(str.charAt(i)).add(i);
        }
        System.out.println(map);
    }

    // ANSWERS ARE CORRECT BUT ORDER IS DIFFERENT
    // public static void Common1(int[] arr1, int[] arr2) {
    // HashMap<Integer, Integer> map1 = new HashMap<>();
    // HashMap<Integer, Integer> map2 = new HashMap<>();
    // for (int i = 0; i < Math.max(arr1.length, arr2.length); i++) {
    // if (i < arr1.length)
    // map1.put(arr1[i], i);
    // if (i < arr2.length)
    // map2.put(arr2[i], i);
    // }

    // for (Integer key : map2.keySet()) {
    // if (map1.containsKey(key))
    // System.out.println(key);
    // }
    // }

    // public static void Common2(int[] arr1, int[] arr2) {
    // HashMap<Integer, Integer> map1 = new HashMap<>();
    // HashMap<Integer, Integer> map2 = new HashMap<>();
    // FeqMap(arr1, map1);
    // FeqMap(arr2, map2);
    // for (Integer key : map2.keySet()) {
    // if (map1.containsKey(key) && map1.get(key) > 0 && map2.get(key) > 0) {
    // System.out.println(key);
    // map1.put(key, map1.get(key) - 1);
    // map2.put(key, map2.get(key) - 1);
    // }
    // }
    // }

    public static void LongestConsecutiveSequence(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
        }
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        int Latestkey = pq.remove();
        map.put(Latestkey, new ArrayList<Integer>());
        map.get(Latestkey).add(Latestkey);
        while (pq.size() != 0) {
            int temp = pq.remove();
            ArrayList<Integer> currSeq = map.get(Latestkey);
            int LatestNum = currSeq.get(currSeq.size() - 1);
            if (temp == LatestNum) {
                continue;
            } else if (temp == LatestNum + 1) {
                currSeq.add(temp);
                map.put(Latestkey, currSeq);
            } else {
                ArrayList<Integer> nextSeq = new ArrayList<>();
                nextSeq.add(temp);
                map.put(temp, nextSeq);
                Latestkey = temp;
            }
        }
        int size = 0;
        int LargestSizeKey = 0;
        for (Integer Key : map.keySet()) {
            if (map.get(Key).size() > size) {
                size = map.get(Key).size();
                LargestSizeKey = Key;
            }
        }
        for (Integer num : map.get(LargestSizeKey)) {
            System.out.println(num);
        }
    }

    public static void main(String[] args) {
        // String str = scn.nextLine();
        // FeqMap(str);
        // IdxMap(str);
    }
}