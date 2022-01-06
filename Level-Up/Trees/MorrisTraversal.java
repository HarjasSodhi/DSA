import java.util.ArrayList;

public class MorrisTraversal {

    // https://classroom.pepcoding.com/the-placement-program-pitampura-mar-14-2021/116/classvideos/6338

    public class Node {
        int val;
        Node left;
        Node right;

        public Node() {
            this.val = 0;
            left = right = null;
        }

    }

    public static Node rightMost(Node node, Node curr) {
        while (node.right != null && node.right != curr) {
            node = node.right;
        }
        return node;
    }

    public static ArrayList<Integer> morrisInorder(Node root) {
        Node curr = root;
        ArrayList<Integer> ans = new ArrayList<>();
        while (curr != null) {
            Node left = curr.left;
            if (left == null) {
                ans.add(curr.val);
                curr = curr.right;
            } else {
                Node rm = rightMost(left, curr);
                if (rm.right == null) {
                    rm.right = curr;
                    curr = left;
                } else {
                    rm.right = null;
                    ans.add(curr.val);
                    curr = curr.right;
                }
            }
        }
        return ans;
    }

    public static ArrayList<Integer> morrisPreorder(Node root) {
        Node curr = root;
        ArrayList<Integer> ans = new ArrayList<>();
        while (curr != null) {
            Node left = curr.left;
            if (left == null) {
                ans.add(curr.val);
                curr = curr.right;
            } else {
                Node rm = rightMost(left, curr);
                if (rm.right == null) {
                    ans.add(curr.val);
                    rm.right = curr;
                    curr = left;
                } else {
                    rm.right = null;
                    curr = curr.right;
                }
            }
        }
        return ans;
    }

    // morris post order is not possible

    // leetcode 98
    // public boolean isValidBST(Node root) {
    // maintain ranges
    // }

}