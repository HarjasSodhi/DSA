public class leetcode421 {
    public class TrieNode {
        TrieNode[] children;

        public TrieNode() {
            children = new TrieNode[2];
        }
    }

    TrieNode root=new TrieNode();

    public int findMaximumXOR(int[] nums) {
        root = new TrieNode();
        int ans = 0;
        for (int e : nums) {
            TrieNode curr = root;
            for (int i = 31; i >= 0; i--) {
                int bit = ((e >> i) & 1);
                if (curr.children[bit] == null)
                    curr.children[bit] = new TrieNode();
                curr = curr.children[bit];
            }
        }

        for (int e : nums) {
            TrieNode curr = root;
            int num = 0;
            for (int i = 31; i >= 0; i--) {
                int bit = ((e >> i) & 1);
                int bitToFind = bit == 1 ? 0 : 1;
                if (curr.children[bitToFind] == null) {
                    curr = curr.children[bit];
                    
                } else {
                    curr = curr.children[bitToFind];
                    num += (int) Math.pow(2, i);
                }
            }
            ans = Math.max(ans, num);
        }

        return ans;
    }
}