public class Trie {

    public class TrieNode {
        TrieNode[] children;
        boolean isWord;

        public TrieNode() {
            children = new TrieNode[26];
            isWord = false;
        }
    }

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode prev = null;
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (curr.children[ch - 'a'] == null) {
                curr.children[ch - 'a'] = new TrieNode();
            }
            prev = curr;
            curr = curr.children[ch - 'a'];
        }
        prev.isWord = true;
    }

    public boolean search(String word) {
        TrieNode prev = null;
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (curr.children[ch - 'a'] == null) {
                return false;
            } else {
                prev = curr;
                curr = curr.children[ch - 'a'];
            }
        }
        return prev.isWord == true;
    }

    public boolean startsWith(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (curr.children[ch - 'a'] == null) {
                return false;
            } else {
                curr = curr.children[ch - 'a'];
            }
        }
        return true;
    }
    
}