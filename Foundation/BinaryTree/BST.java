import java.util.*;

public class BST {

    public static class Node {
        int data;
        Node left;
        Node right;

        public Node() {

        }

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Node(int data) {
            this(data, null, null);
        }
    }

    // T : O(n), S : O(1)
    public static int size(Node node) {
        return node == null ? 0 : size(node.left) + size(node.right) + 1;
    }

    // T : O(n), S : O(1)
    public static int height(Node node) {
        return node == null ? -1 : Math.max(height(node.left), height(node.right)) + 1;
    }

    // T : O(logn), S : O(1)
    public static int max(Node node) {
        if (node == null)
            return 0;
        while (node.right != null)
            node = node.right;
        return node.data;
    }

    // T : O(logn), S : O(1)
    public static int min(Node node) {
        if (node == null)
            return 0;
        while (node.left != null)
            node = node.left;
        return node.data;
    }

    public static boolean find(Node node, int val) {
        if (node == null)
            return false;

        while (node != null) {
            if (node.data == val)
                return true;
            else if (val < node.data)
                node = node.left;
            else
                node = node.right;
        }
        return false;
    }

    public static int sum(Node node) {
        if (node == null)
            return 0;
        int sum = node.data;
        sum += sum(node.left);
        sum += sum(node.right);
        return sum;
        // or return node==null? 0:sum(node.left)+sum(node.right)+node.data;
    }

    public static ArrayList<Node> nodeToRootPath(Node node, int val) {
        ArrayList<Node> ans = new ArrayList<>();
        while (node != null && node.data != val) {
            ans.add(node);
            if (node.data < val)
                node = node.right;
            else
                node = node.left;
        }
        if (node == null)
            return new ArrayList<Node>();

        Collections.reverse(ans);
        return ans;
    }

    public static int LCA(Node node, int n1, int n2) {
        while (node != null) {
            if (Math.min(n1, n2) <= node.data && Math.max(n1, n2) >= node.data)
                return node.data;
            if (n1 < node.data && n2 < node.data)
                node = node.left;
            else
                node = node.right;
        }
        return 0;
    }

    public static void printInRange(Node node, int min, int max) {
        if (node == null)
            return;
        if (node.data >= min && node.data <= max) {
            System.out.println(node.data);
        }
        if (node.data >= min)
            printInRange(node.left, min, max);
        if (node.data <= max)
            printInRange(node.right, min, max);
    }

    public static Node AddNode(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }
        if (node.data > data)
            node.left = AddNode(node.left, data);
        else
            node.right = AddNode(node.right, data);

        return node;
        // can do iteratively also with checks.
    }

    public static Node RemoveNodes(Node node, int data) {
        if (node == null)
            return new Node();

        if (node.data < data)
            node.right = RemoveNodes(node.right, data);
        else if (node.data > data)
            node.left = RemoveNodes(node.left, data);
        else {
            if (node.left == null || node.right == null)
                return node.left != null ? node.left : node.right;

            int maxEle = max(node.left);
            node.data = maxEle;
            RemoveNodes(node.left, maxEle);
        }

        return node;
    }

    public static int sum = 0;

    public static void ReplaceWithAllLargerValues(Node node) {
        if (node == null)
            return;
        ReplaceWithAllLargerValues(node.right);
        int temp = node.data;
        node.data = sum;
        sum += temp;
        ReplaceWithAllLargerValues(node.left);
    }

    // gfg
    // https://practice.geeksforgeeks.org/problems/add-all-greater-values-to-every-node-in-a-bst/1
    public static void modifyHelper(Node node, int[] arr) {
        if (node == null)
            return;
        modifyHelper(node.right, arr);
        node.data += arr[0];
        arr[0] = node.data;
        modifyHelper(node.left, arr);
    }

    public static Node modify(Node root) {
        int[] arr = new int[1];
        modifyHelper(root, arr);
        return root;
    }

}