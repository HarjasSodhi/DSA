public class BST {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
            this.right = this.left = null;
        }
    }

    public class Node {
        int data;
        Node left;
        Node right;

        Node(int val) {
            this.data = val;
            this.right = this.left = null;
        }
    }

    // leetcode 701
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode pointer = new TreeNode(val);
        if (root == null)
            return pointer;
        findParent(root, val, pointer);
        return root;
    }

    public void findParent(TreeNode root, int val, TreeNode pointer) {
        if (root.val > val) {
            if (root.left == null) {
                root.left = pointer;
                return;
            } else {
                findParent(root.left, val, pointer);
            }
        } else {
            if (root.right == null) {
                root.right = pointer;
                return;
            } else {
                findParent(root.right, val, pointer);
            }
        }
    }

    // 450
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;
        if (root.val == key) {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                TreeNode temp = root;
                root = root.right;
                while (root.left != null)
                    root = root.left;
                root.left = temp.left;
                return temp.right;
            }
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    // https://practice.geeksforgeeks.org/problems/inorder-successor-in-bst/1/#
    // if left returns null, then i am the ans;
    // if right return null then i cannot be the ans as inorder is sorted and
    // successor should be bigger not smaller
    public Node inorderSuccessor(Node root, Node x) {
        
    }

}