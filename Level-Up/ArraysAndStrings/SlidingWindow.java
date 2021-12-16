import java.util.*;

public class SlidingWindow {

    // leetcode 3
    public int lengthOfLongestSubstring(String s) {
        int start = 0;
        int itr = 0;
        int max = 0;
        int len = s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        while (itr < len) {
            if (map.containsKey(s.charAt(itr)) && map.get(s.charAt(itr)) >= start) {
                max = Math.max(max, itr - start);
                start = map.get(s.charAt(itr)) + 1;
            }
            map.put(s.charAt(itr), itr);
            itr++;
        }
        return Math.max(max, itr - start);
    }

    // leetcode 3 array
    public int lengthOfLongestSubstring2(String s) {
        int si = 0;
        int ei = 0;
        int count = 0;
        int len = s.length();
        int max = 0;
        int[] arr = new int[128];
        while (ei < len) {
            if (arr[s.charAt(ei)] == 1) {
                count = 1;
            }
            arr[s.charAt(ei)]++;
            while (count == 1) {
                if (arr[s.charAt(si)] == 2) {
                    count = 0;
                }
                arr[s.charAt(si)]--;
                si++;
            }
            ei++;
            max = Math.max(max, ei - si);
        }
        return max;
    }

    // lintcode 928
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int si = 0;
        int ei = 0;
        int count = 0;
        int len = s.length();
        int max = 0;
        int[] arr = new int[128];
        while (ei < len) {
            if (arr[s.charAt(ei)] == 0) {
                count++;
            }
            arr[s.charAt(ei)]++;
            while (count > 2) {
                if (arr[s.charAt(si)] == 1) {
                    count--;
                }
                arr[s.charAt(si)]--;
                si++;
            }
            ei++;
            max = Math.max(max, ei - si);
        }
        return max;
    }

    // lintcode 386
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // write your code here
        int si = 0;
        int ei = 0;
        int count = 0;
        int len = s.length();
        int max = 0;
        int[] arr = new int[128];
        while (ei < len) {
            if (arr[s.charAt(ei)] == 0) {
                count++;
            }
            arr[s.charAt(ei)]++;
            while (count > k) {
                if (arr[s.charAt(si)] == 1) {
                    count--;
                }
                arr[s.charAt(si)]--;
                si++;
            }
            ei++;
            max = Math.max(max, ei - si);
        }
        return max;
    }

    // leetcode 76
    public String minWindow(String s, String t) {
        int n = s.length();
        int m = t.length();
        if (n < m) {
            return "";
        }

        int si = 0;
        int ei = 0;
        String ans = "";
        int currAnsLen = (int) 1e9;

        int uniqueCharacters = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            if (map.containsKey(t.charAt(i))) {
                map.put(t.charAt(i), map.get(t.charAt(i)) + 1);
            } else {
                map.put(t.charAt(i), 1);
                uniqueCharacters++;
            }
        }

        while (ei < n) {

            if (map.containsKey(s.charAt(ei))) {
                int tempFreq = map.get(s.charAt(ei));
                if (tempFreq == 1) {
                    uniqueCharacters--;
                }
                map.put(s.charAt(ei), tempFreq - 1);
            }
            ei++;

            while (uniqueCharacters == 0) {
                if (ei - si < currAnsLen) {
                    currAnsLen = ei - si;
                    ans = s.substring(si, ei);
                }
                if (map.containsKey(s.charAt(si))) {
                    int tempFreq = map.get(s.charAt(si));
                    if (tempFreq == 0) {
                        uniqueCharacters++;
                    }
                    map.put(s.charAt(si), tempFreq + 1);
                }
                si++;
            }
        }

        return ans;
    }

    // https://practice.geeksforgeeks.org/problems/smallest-distant-window3132/1
    public String findSubString(String s) {
        int n = s.length();
        int si = 0;
        int ei = 0;
        String ans = "";
        int currAnsLen = (int) 1e9;

        int uniqueCharacters = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), 1);
                uniqueCharacters++;
            }
        }

        while (ei < n) {

            if (map.containsKey(s.charAt(ei))) {
                int tempFreq = map.get(s.charAt(ei));
                if (tempFreq == 1) {
                    uniqueCharacters--;
                }
                map.put(s.charAt(ei), tempFreq - 1);
            }
            ei++;

            while (uniqueCharacters == 0) {
                if (ei - si < currAnsLen) {
                    currAnsLen = ei - si;
                    ans = s.substring(si, ei);
                }
                if (map.containsKey(s.charAt(si))) {
                    int tempFreq = map.get(s.charAt(si));
                    if (tempFreq == 0) {
                        uniqueCharacters++;
                    }
                    map.put(s.charAt(si), tempFreq + 1);
                }
                si++;
            }
        }

        return ans;
    }

    // leetcode 1456
    public int maxVowels(String s, int k) {
        int n = s.length();
        int si = 0;
        int ei = 0;
        int ans = 0;
        int curr = 0;
        while (ei < n) {
            if ((ei - si) == k) {
                if (s.charAt(ei) == 'a' || s.charAt(ei) == 'e' || s.charAt(ei) == 'i' || s.charAt(ei) == 'o'
                        || s.charAt(ei) == 'u') {
                    curr++;
                }
                if (s.charAt(si) == 'a' || s.charAt(si) == 'e' || s.charAt(si) == 'i' || s.charAt(si) == 'o'
                        || s.charAt(si) == 'u') {
                    curr--;
                }
                ans = Math.max(ans, curr);
                ei++;
                si++;
            } else {
                if (s.charAt(ei) == 'a' || s.charAt(ei) == 'e' || s.charAt(ei) == 'i' || s.charAt(ei) == 'o'
                        || s.charAt(ei) == 'u') {
                    curr++;
                    ans = Math.max(ans, curr);
                }
                ei++;
            }
        }
        return ans;
    }

    // leetcode 992
    // https://classroom.pepcoding.com/the-placement-program-pitampura-mar-14-2021/116/classvideos/6109
    public int subarraysWithKDistinct(int[] nums, int k) {
        return subarraysWithKDistinctHelper(nums, k) - subarraysWithKDistinctHelper(nums, k - 1);
    }

    // 1248 is same with a little difference
    public int subarraysWithKDistinctHelper(int[] nums, int k) {
        int si = 0;
        int ei = 0;
        int n = nums.length;
        int ans = 0;
        int count = 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        while (ei < n) {
            if (!map.containsKey(nums[ei]) || map.get(nums[ei]) == 0) {
                map.put(nums[ei], 1);
                count++;
            } else {
                map.put(nums[ei], map.get(nums[ei]) + 1);
            }
            while (count > k) {
                if (map.get(nums[si]) == 1) {
                    map.put(nums[si], 0);
                    count--;
                } else {
                    map.put(nums[si], map.get(nums[si]) - 1);
                }
                si++;
            }
            ei++;
            ans += ei - si;
        }

        return ans;
    }

    // leetcode 239
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - (k - 1)];
        int ansIdx = 0;

        Deque<Integer> que = new ArrayDeque<Integer>();
        int idx = 0;

        while (idx < n) {

            while (que.size() > 0 && idx - que.getFirst() >= k) {
                que.removeFirst();
            }

            while (que.size() > 0 && nums[que.getLast()] < nums[idx]) {
                que.removeLast();
            }

            que.addLast(idx);
            if (idx >= k - 1) {
                ans[ansIdx] = nums[que.getFirst()];
                ansIdx++;
            }
            idx++;
        }

        return ans;
    }

    // leetcode 220 hw
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;
        TreeSet<Long> treeSet = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            long num = nums[i];
            Long floor = treeSet.floor(num);
            Long ceil = treeSet.ceiling(num);

            if ((floor != null && num - floor <= t) || (ceil != null && ceil - num <= t)) {
                return true;
            }

            treeSet.add(num);

            if (i >= k) {
                treeSet.remove((long) nums[i - k]);
            }
        }

        return false;
    }

    // leetcode 904
    public int totalFruit(int[] fruits) {
        int si = 0;
        int ei = 0;
        int ans = 0;
        int n = fruits.length;
        int baskets = 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        // try using arrays

        while (ei < n) {
            if (!map.containsKey(fruits[ei]) || map.get(fruits[ei]) == 0) {
                baskets++;
                map.put(fruits[ei], 1);
            } else {
                map.put(fruits[ei], map.get(fruits[ei]) + 1);
            }

            while (baskets > 2) {
                if (map.get(fruits[si]) == 1) {
                    baskets--;
                    map.put(fruits[si], 0);
                } else {
                    map.put(fruits[si], map.get(fruits[si]) - 1);
                }
                si++;
            }

            ei++;
            ans = Math.max(ans, ei - si);
        }

        return ans;
    }

    // leetcode 930
    public int numSubarraysWithSum(int[] nums, int goal) {
        if (goal == 0) {
            return numSubarraysWithSumHelper(nums, goal);
        }
        return numSubarraysWithSumHelper(nums, goal) - numSubarraysWithSumHelper(nums, goal - 1);
    }

    public int numSubarraysWithSumHelper(int[] nums, int goal) {
        int si = 0;
        int ei = 0;
        int ans = 0;
        int n = nums.length;
        int sum = 0;

        while (ei < n) {
            sum += nums[ei];
            while (sum > goal) {
                sum -= nums[si];
                si++;
            }
            ei++;
            ans += ei - si;
        }

        return ans;
    }

    // leetcode 485
    public int findMaxConsecutiveOnes(int[] nums) {
        int ei = 0;
        int si = 0;
        int n = nums.length;
        int ans = 0;
        while (ei < n) {
            if (nums[ei] == 0)
                si = ei + 1;

            ei++;
            ans = Math.max(ans, ei - si);
        }

        return ans;
    }

    // lintcode 883
    public int findMaxConsecutiveOnesWithFlip(int[] nums) {
        int ei = 0;
        int si = 0;
        int n = nums.length;
        int count = 0;
        int ans = 0;
        while (ei < n) {
            if (nums[ei] == 0) {
                count++;
            }
            while (count > 1) {// because 1 flip is allowed
                if (nums[si] == 0) {
                    count--;
                }
                si++;
            }
            ei++;
            ans = Math.max(ans, ei - si);
        }
        return ans;
    }

    // https://classroom.pepcoding.com/the-placement-program-pitampura-mar-14-2021/116/classvideos/6108
    // leetcode 974
    // sum wale ques
    public int subarraysDivByK(int[] nums, int k) {
        int i = 0;
        int ans = 0;
        int sum = 0;
        int[] remArr = new int[k];
        remArr[0] = 1;
        while (i < nums.length) {
            sum += nums[i];
            sum = sum % k;
            int rem = (sum + k) % k;
            ans += remArr[rem];
            remArr[rem]++;
            i++;
        }
        return ans;
    }

    // leetcode 523
    public boolean checkSubarraySum(int[] nums, int k) {
        int i = 0;
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        while (i < nums.length) {
            sum += nums[i];
            sum = sum % k;
            int rem = (sum + k) % k;
            if (map.containsKey(rem)) {
                if ((i - map.get(rem)) >= 2)
                    return true;
            } else
                map.put(rem, i);
            i++;
        }
        return false;
    }

    // leetcode 525
    public int findMaxLength(int[] nums) {
        int sum = 0;
        int ans = 0;
        int ei = 0;
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(0, -1);

        while (ei < n) {
            sum += nums[ei];
            if (nums[ei] == 0) {
                sum -= 1;
            }
            if (map.containsKey(sum)) {
                ans = Math.max(ans, ei - map.get(sum));
            } else {
                map.put(sum, ei);
            }
            ei++;
        }

        return ans;
    }

    // https://practice.geeksforgeeks.org/problems/count-subarrays-with-equal-number-of-1s-and-0s-1587115620/1
    public int countSubarrWithEqualZeroAndOne(int nums[], int n) {
        int sum = 0;
        int ans = 0;
        int ei = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(0, 1);

        while (ei < n) {
            sum += nums[ei];
            if (nums[ei] == 0) {
                sum -= 1;
            }
            if (map.containsKey(sum)) {
                ans += map.get(sum);
                map.put(sum, map.get(sum) + 1);
            } else {
                map.put(sum, 1);
            }

            ei++;
        }

        return ans;
    }

}