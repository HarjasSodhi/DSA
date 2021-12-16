public class Leetcode211 {

    class WordDictionary {
        public class TrieNode {
            TrieNode[] children;
            boolean isWord;

            public TrieNode() {
                children = new TrieNode[26];
                isWord = false;
            }
        }

        TrieNode root;

        public WordDictionary() {
            root = new TrieNode();
        }

        public void addWord(String word) {
            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (curr.children[ch - 'a'] == null) {
                    curr.children[ch - 'a'] = new TrieNode();
                }
                curr = curr.children[ch - 'a'];
            }
            curr.isWord = true;
        }

        public boolean searchHelper(String word, TrieNode root, int idx) {
            if (word.length() == idx) {
                return root.isWord;
            }
            if (word.charAt(idx) == '.') {
                boolean ans = false;
                for (TrieNode q : root.children) {
                    if (q != null) {
                        ans = ans || searchHelper(word, q, idx + 1);
                    }
                }
                return ans;
            } else {
                if (root.children[word.charAt(idx) - 'a'] != null) {
                    return searchHelper(word, root.children[word.charAt(idx) - 'a'], idx + 1);
                } else {
                    return false;
                }
            }
        }

        public boolean search(String word) {
            return searchHelper(word, root, 0);
        }

    }

}