import java.util.*;

public class Questions {

    public class Node {
        int val;
        List<Node> children;
        Node left;
        Node right;

        public Node() {
            this.val = 0;
            children = new ArrayList<>();
            left = right = null;
        }

    }

    // leetcode 589
    public List<Integer> preorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;
        preorder(root, ans);
        return ans;
    }

    public void preorder(Node root, List<Integer> ans) {
        ans.add(root.val);
        for (Node e : root.children) {
            preorder(e, ans);
        }
    }

    // leetcode 590
    public List<Integer> postorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;
        postorder(root, ans);
        return ans;
    }

    public void postorder(Node root, List<Integer> ans) {
        for (Node e : root.children) {
            postorder(e, ans);
        }
        ans.add(root.val);
    }

    // https://www.pepcoding.com/resources/online-java-foundation/generic-tree/are-trees-mirror-in-shape-official/ojquestion
    public static boolean areMirror(Node n1, Node n2) {
        if (n1 == null || n2 == null) {
            if (n1 == null && n2 == null)
                return true;
            return false;
        }
        if (n1.children.size() != n2.children.size()) {
            return false;
        }
        boolean ans = true;
        int len = n1.children.size();
        for (int i = 0; i < len; i++) {
            ans = ans && areMirror(n1.children.get(i), n2.children.get(len - i - 1));
            if (!ans)
                return ans;
        }

        return ans;
    }

    // lca
    public static Node lca(Node root, Node p, Node q) {
        if (p == null || q == null || root == null)
            return null;

        ArrayList<Node> arr1 = new ArrayList<>();
        ArrayList<Node> arr2 = new ArrayList<>();
        nodeToRootPath(root, p, arr1);
        nodeToRootPath(root, q, arr2);

        int i = arr1.size() - 1;
        int j = arr2.size() - 1;
        Node ans = null;
        while (i >= 0 && j >= 0 && arr1.get(i).val == arr2.get(j).val) {
            ans = arr1.get(i);
            i--;
            j--;
        }
        return ans;
    }

    public static boolean nodeToRootPath(Node root, Node data, List<Node> ans) {
        if (root == null)
            return false;
        boolean check = nodeToRootPath(root.left, data, ans) || nodeToRootPath(root.right, data, ans);
        if (root.val == data.val || check) {
            ans.add(root);
            return true;
        }
        return check;
    }

    // lca no-space
    public static Node ans = null;

    public static Node lcaOptimised(Node root, Node p, Node q) {
        lcaOptimisedHelper(root, p, q);
        return ans;
    }

    public static int lcaOptimisedHelper(Node root, Node p, Node q) {
        if (root == null)
            return 0;
        int num = 0;
        num += lcaOptimisedHelper(root.left, p, q);
        num += lcaOptimisedHelper(root.right, p, q);

        if (root.val == p.val || root.val == q.val) {
            num++;
        }

        if (num == 2 && ans == null) {
            ans = root;
        }

        return num;
    }

    // kdown
    public static void kDown(Node root, int k, List<Integer> ans) {
        if (root == null)
            return;

        if (k == 0) {
            ans.add(root.val);
            return;
        }

        kDown(root.left, k - 1, ans);
        kDown(root.right, k - 1, ans);

        return;
    }

    // kdown with Blocker
    public static void kDown(Node root, int k, List<Integer> ans, Node Blocker) {
        if (root == null)
            return;

        if (root == Blocker)
            return;

        if (k == 0) {
            ans.add(root.val);
            return;
        }

        kDown(root.left, k - 1, ans, Blocker);
        kDown(root.right, k - 1, ans, Blocker);

        return;
    }

    // leetcode 863
    public List<Integer> kAway(Node root, Node target, int k) {
        List<Integer> ans = new ArrayList<>();
        List<Node> ntrp = new ArrayList<>();
        kAwayHelper1(root, k, ans, ntrp, target);
        return ans;
    }

    public boolean kAwayHelper1(Node root, int k, List<Integer> ans, List<Node> ntrp, Node target) {
        if (root == null) {
            return true;
        }
        if (root.val == target.val) {
            kDown(root, k, ans, null);
            ntrp.add(root);
            return true;
        }
        if (kAwayHelper1(root.left, k, ans, ntrp, target)) {
            kDown(root, k - ntrp.size(), ans, ntrp.get(ntrp.size() - 1));
            ntrp.add(root);
            return true;
        }
        if (kAwayHelper1(root.right, k, ans, ntrp, target)) {
            kDown(root, k - ntrp.size(), ans, ntrp.get(ntrp.size() - 1));
            ntrp.add(root);
            return true;
        }
        return false;
    }

    public List<Integer> kAwayHelper2(Node root, int k, List<Integer> ans, Node target) {
        List<Node> ntrp = new ArrayList<>();
        nodeToRootPath(root, target, ntrp);
        Node Blocker = null;
        int i = 0;
        for (Node e : ntrp) {
            kDown(e, k - i, ans, Blocker);
            Blocker = e;
            i++;
        }
        return ans;
    }

    public int levelAway = 0;

    // sir returns int in his answer but this answer is also fine
    public Node kAwayHelperOptimised(Node root, int k, List<Integer> ans, Node target) {
        if (root == null) {
            return null;
        }
        if (root.val == target.val) {
            kDown(root, k, ans, null);
            levelAway++;
            return root;
        }
        Node left = kAwayHelperOptimised(root.left, k, ans, target);
        if (left != null) {
            kDown(root, k - levelAway, ans, left);
            levelAway++;
            return root;
        }
        Node right = kAwayHelperOptimised(root.right, k, ans, target);
        if (right != null) {
            kDown(root, k - levelAway, ans, right);
            levelAway++;
            return root;
        }
        return null;
    }

    // https://www.geeksforgeeks.org/burn-the-binary-tree-starting-from-the-target-node/
    public static ArrayList<ArrayList<Integer>> burnTree(Node root, Node target) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        BurningTreeFind(root, target, ans);
        return ans;
    }

    public static int BurningTreeFind(Node root, Node data, ArrayList<ArrayList<Integer>> ans) {
        if (root == null) {
            return -1;
        }

        if (root == data) {
            fillBurningList(root, ans, 0, null);
            return 1;
        }

        int left = BurningTreeFind(root.left, data, ans);
        if (left != -1) {
            fillBurningList(root, ans, left, root.left);
            return left + 1;
        }

        int right = BurningTreeFind(root.right, data, ans);
        if (right != -1) {
            fillBurningList(root, ans, right, root.right);
            return right + 1;
        }

        return -1;
    }

    public static void fillBurningList(Node root, ArrayList<ArrayList<Integer>> ans, int idx, Node blocker) {
        if (root == null)
            return;
        if (root == blocker)
            return;

        ans.get(idx).add(root.val);
        fillBurningList(root.left, ans, idx + 1, blocker);
        fillBurningList(root.right, ans, idx + 1, blocker);
    }

    // with water nodes
    public static void fillBurningLisWithtWater(Node root, ArrayList<ArrayList<Integer>> ans, int idx, Node blocker,
            HashSet<Node> water) {
        if (root == null || root == blocker || water.contains(root))
            return;

        ans.get(idx).add(root.val);
        fillBurningLisWithtWater(root.left, ans, idx + 1, blocker, water);
        fillBurningLisWithtWater(root.right, ans, idx + 1, blocker, water);
    }

}