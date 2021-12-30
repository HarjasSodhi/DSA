import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class DesignQuestions {
    // leetcode 895
    // https://classroom.pepcoding.com/the-placement-program-pitampura-mar-14-2021/116/classvideos/5599#
    class FreqStack {

        private class pair implements Comparable<pair> {
            int val;
            int idx;
            int freq;

            pair(int val, int idx, int freq) {
                this.val = val;
                this.idx = idx;
                this.freq = freq;
            }

            public int compareTo(pair o) {
                if (o.freq == this.freq)
                    return o.idx - this.idx;
                else
                    return o.freq - this.freq;
            }
        }

        private PriorityQueue<pair> pq;
        private int idx;
        private HashMap<Integer, Integer> map;

        public FreqStack() {
            this.pq = new PriorityQueue<>();
            this.idx = 0;
            this.map = new HashMap<>();
        }

        // log(n)
        public void push(int val) {
            map.put(val, map.getOrDefault(val, 0) + 1);
            int freq = map.get(val);
            pq.add(new pair(val, idx, freq));
            idx++;
        }

        // log(n)
        public int pop() {
            pair temp = pq.remove();
            map.put(temp.val, map.get(temp.val) - 1);
            if (map.get(temp.val) == 0) {
                map.remove(temp.val);
            }
            return temp.val;
        }
    }

    class FreqStack_02 {

        private ArrayList<Stack<Integer>> stackList;
        private int maxFreq;
        private HashMap<Integer, Integer> map;

        public FreqStack_02() {
            this.stackList = new ArrayList<>();
            this.maxFreq = 0;
            this.map = new HashMap<>();
            stackList.add(new Stack<>());

        }

        // o(1)
        public void push(int val) {
            map.put(val, map.getOrDefault(val, 0) + 1);
            maxFreq = Math.max(maxFreq, map.get(val));
            if (maxFreq >= stackList.size())
                stackList.add(new Stack<>());
            stackList.get(map.get(val)).push(val);
        }

        // o(1)
        public int pop() {
            int temp = stackList.get(maxFreq).pop();
            if (stackList.get(maxFreq).size() == 0) {
                stackList.remove(maxFreq);
                maxFreq--;
            }
            map.put(temp, map.get(temp) - 1);
            if (map.get(temp) == 0) {
                map.remove(temp);
            }
            return temp;
        }
    }

    // leetcode 155
    // simpler approach -> keep a hashmap of <sizeOfStack and MinValueAtThatSize>
    class MinStack {
        // formula used for this approach :-
        // newMinVal - oldMinVal < 0
        // but this fail for negative values, so:-
        // add newMinVal on Both sides
        // 2*newMinVal - oldMinVal < newMinVal
        LinkedList<Long> st;
        long minSf;

        public MinStack() {
            st = new LinkedList<>();
            minSf = 0;
        }

        public void push(int val) {
            long x = val;
            if (st.size() == 0) {
                st.addFirst(x);
                minSf = x;
                return;
            }

            if (x < minSf) {
                st.addFirst(2 * x - minSf);
                minSf = x;
            } else {
                st.addFirst(x);
            }
        }

        public void pop() {
            if (st.getFirst() < minSf) {
                minSf = 2 * minSf - st.getFirst();
            }

            st.removeFirst();
        }

        public int top() {
            if (st.getFirst() < minSf) {
                return (int) minSf;
            }

            return (int) (long) st.getFirst();
        }

        public int getMin() {
            return (int) minSf;
        }
    }

    // leetcode 636
    // https://classroom.pepcoding.com/the-placement-program-pitampura-mar-14-2021/116/classvideos/5616#
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] ans = new int[n];
        class pair {

            int timeStamp, sleepTime, id;
            boolean isStart;

            pair(String s) {
                String[] arr = s.split(":");
                timeStamp = Integer.parseInt(arr[2]);
                id = Integer.parseInt(arr[0]);
                isStart = arr[1].equals("start");
                sleepTime = 0;
            }
        }

        Stack<pair> st = new Stack<>();
        for (String log : logs) {
            pair temp = new pair(log);
            if (temp.isStart) {
                st.push(temp);
            } else {
                pair startTemp = st.pop();
                ans[startTemp.id] += temp.timeStamp - startTemp.timeStamp - startTemp.sleepTime + 1;
                if (st.size() != 0) {
                    st.peek().sleepTime += temp.timeStamp - startTemp.timeStamp + 1;
                }
            }
        }

        return ans;
    }

    // leetcode 853
    // leetcode 1776 uses same concept
    public int carFleet(int target, int[] position, int[] speed) {
        int ans = 1;
        int n = position.length;
        double[][] arr = new double[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = (double) position[i];
            arr[i][1] = (double) (target - position[i]) / speed[i];
        }

        Arrays.sort(arr, (a, b) -> {
            return (int) (a[0] - b[0]);
        });

        double prevTime = arr[n - 1][1];
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i][1] > prevTime) {
                ans++;
                prevTime = arr[i][1];
            } else {
                // this car will become part of a previously formed fleet
            }
        }
        return ans;
    }

}