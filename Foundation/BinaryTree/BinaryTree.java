import java.util.ArrayList;
import java.util.Queue;
import java.util.*;

public class BinaryTree {
    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Node(int data) {
            this(data, null, null);
        }
    }

    public static void Preorder(Node root, ArrayList<Integer> ans) {
        if (root == null)
            return;

        ans.add(root.data);
        Preorder(root.left, ans);
        Preorder(root.right, ans);
    }

    public static void Inorder(Node root, ArrayList<Integer> ans) {
        if (root == null)
            return;

        Preorder(root.left, ans);
        ans.add(root.data);
        Preorder(root.right, ans);
    }

    public static void Postorder(Node root, ArrayList<Integer> ans) {
        if (root == null)
            return;

        Preorder(root.left, ans);
        Preorder(root.right, ans);
        ans.add(root.data);
    }
    // public List<Integer> inorderTraversal(TreeNode root) {
    // if(root==null)return new ArrayList<Integer>();
    // List<Integer>myAns=new ArrayList<>();
    // List<Integer>leftAns=inorderTraversal(root.left);
    // myAns.addAll(leftAns);
    // myAns.add(root.val);
    // List<Integer>rightAns=inorderTraversal(root.right);
    // myAns.addAll(rightAns);

    // return myAns;
    // }
    // can be done using this as well

    public static int size(Node node) {
        if (node == null)
            return 0;
        int size = 1;
        size += size(node.left);
        size += size(node.right);
        return size;
    }

    public static int sum(Node node) {
        if (node == null)
            return 0;
        int sum = node.data;
        sum += sum(node.left);
        sum += sum(node.right);
        return sum;
    }

    public static int max(Node node) {
        if (node == null)
            return -(int) 1e9;
        int max = node.data;
        int leftmax = max(node.left);
        int rightmax = max(node.right);
        return Math.max(max, Math.max(leftmax, rightmax));
    }

    public static int min(Node node) {
        if (node == null)
            return (int) 1e9;
        int min = node.data;
        int leftmin = min(node.left);
        int rightmin = min(node.right);
        return Math.min(min, Math.min(leftmin, rightmin));
    }

    public static int heightWRTEdges(Node node) {
        if (node == null)
            return -1;
        int lh = heightWRTEdges(node.left);
        int rh = heightWRTEdges(node.right);
        int th = Math.max(lh, rh) + 1;
        return th;
    }

    public static int heightWRTNodes(Node node) {
        if (node == null)
            return 0;
        int lh = heightWRTEdges(node.left);
        int rh = heightWRTEdges(node.right);
        int th = Math.max(lh, rh) + 1;
        return th;
    }

    public static int countLeaves(Node node) {
        if (node.left == null && node.right == null)
            return 1;
        int leaf = 0;
        if (node.left != null)
            leaf += countLeaves(node.left);
        if (node.right != null)
            leaf += countLeaves(node.right);
        return leaf;
    }

    public static int oneChild(Node node) {
        if (node.left == null && node.right != null) {
            return oneChild(node.right) + 1;
        }
        if (node.left != null && node.right == null) {
            return oneChild(node.left) + 1;
        }
        int oneChild = 0;
        if (node.left != null)
            oneChild += oneChild(node.left);
        if (node.right != null)
            oneChild += oneChild(node.right);
        return oneChild;
    }

    public static void oneChildData(Node node, ArrayList<Integer> ans) {
        if (node.left == null && node.right != null) {
            ans.add(node.data);
            oneChildData(node.right, ans);
        }
        if (node.left != null && node.right == null) {
            ans.add(node.data);
            oneChildData(node.left, ans);
        }
        if (node.left != null)
            oneChildData(node.left, ans);
        if (node.right != null)
            oneChildData(node.right, ans);
    }

    public static boolean findNode(Node node, int val) {
        if (node == null)
            return false;
        if (node.data == val)
            return true;
        return findNode(node.left, val) || findNode(node.right, val);
    }

    public static boolean NodeToRootPath(Node node, int val, ArrayList<Node> ans) {
        if (node == null)
            return false;
        if (node.data == val) {
            ans.add(node);
            return true;
        }
        boolean check = NodeToRootPath(node.left, val, ans) || NodeToRootPath(node.right, val, ans);
        if (check) {
            ans.add(node);
        }
        return check;
    }

    public static ArrayList<Node> nodeToRootPathArray(Node node, int val) {
        if (node == null)
            return null;
        if (node.data == val) {
            ArrayList<Node> list = new ArrayList<>();
            list.add(node);
            return list;
        }
        ArrayList<Node> left = nodeToRootPathArray(node.left, val);
        if (left != null) {
            left.add(node);
            return left;
        }
        ArrayList<Node> right = nodeToRootPathArray(node.right, val);
        if (right != null) {
            right.add(node);
            return right;
        }
        return null;
    }

    public static ArrayList<Node> kthLevelNodes(int k, Node node) {
        if (node == null || k < 0)
            return new ArrayList<Node>();

        if (k == 0) {
            ArrayList<Node> ans = new ArrayList<>();
            ans.add(node);
            return ans;
        }

        ArrayList<Node> left = kthLevelNodes(k - 1, node.left);
        ArrayList<Node> right = kthLevelNodes(k - 1, node.right);

        ArrayList<Node> res = new ArrayList<>();

        res.addAll(left);
        res.addAll(right);
        return res;
    }

    public static void kLevelsDown(int k, Node block, Node node, ArrayList<Integer> ans) {
        if (node == null || k < 0 || node == block)
            return;

        if (k == 0) {
            ans.add(node.data);
        }

        ArrayList<Node> left = kthLevelNodes(k - 1, node.left);
        ArrayList<Node> right = kthLevelNodes(k - 1, node.right);

        ArrayList<Node> res = new ArrayList<>();

        res.addAll(left);
        res.addAll(right);
    }

    public static ArrayList<Integer> kLevelsAwayNodes(Node node, int val, int k) {
        ArrayList<Node> list = new ArrayList<>();
        NodeToRootPath(node, val, list);
        Node block = null;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            kLevelsDown(k - i, block, list.get(i), ans);
            block = list.get(i);
        }
        return ans;
    }

    public static int kAway2(int val, int k, Node node, ArrayList<Integer> ans) {
        if (node == null)
            return -1;
        if (node.data == val) {
            kLevelsDown(k, null, node, ans);
            return 1;
        }
        int left = kAway2(val, k, node.left, ans);
        if (left != -1) {
            kLevelsDown(k - left, node.left, node, ans);
            return left + 1;
        }
        int right = kAway2(val, k, node.right, ans);
        if (right != -1) {
            kLevelsDown(k - right, node.right, node, ans);
            return right + 1;
        }
        return -1;
    }

    public static Node removeLeaves(Node node) {
        if (node == null)
            return null;
        if (node.right == null && node.left == null) {
            node = null;
            return node;
        }
        Node left = removeLeaves(node.left);
        if (left == null)
            node.left = null;
        Node right = removeLeaves(node.right);
        if (right == null)
            node.right = null;
        return node;
    }

    // public static boolean IsBST(Node node) {
    // if (node == null)
    // return true;
    // if (node.left == null && node.right == null)
    // return true;
    // if (node.left == null && node.right != null) {
    // if (node.right.data > node.data)
    // return true;
    // else
    // return false;
    // }
    // if (node.left != null && node.right == null) {
    // if (node.left.data < node.data)
    // return true;
    // else
    // return false;
    // }
    // boolean left = IsBST(node.left);
    // if (!left)
    // return false;

    // boolean right = IsBST(node.right);
    // if (!right)
    // return false;

    // if (node.left.data > node.data || node.right.data < node.data)
    // return false;

    // return true;
    // }

    public static Node prev = null;

    public static boolean isBST(Node node) {
        if (node == null)
            return true;

        if (!isBST(node.left))
            return false;

        if (prev != null && prev.data > node.data)
            return false;
        prev = node;

        if (!isBST(node.right))
            return false;

        return true;
    }

    public static class IsBSTPair {
        boolean isBST = true;
        int maxEle = -(int) 1e9;
        int minEle = (int) 1e9;
    }

    public static IsBSTPair isBst2(Node node) {
        if (node == null)
            return new IsBSTPair();

        IsBSTPair left = isBst2(node.left);
        if (!left.isBST)
            return left;
        IsBSTPair right = isBst2(node.right);
        if (!right.isBST)
            return right;

        IsBSTPair self = new IsBSTPair();
        self.isBST = false;

        if (left.maxEle < node.data && node.data < right.minEle) {
            self.maxEle = Math.max(left.maxEle, node.data);
            self.minEle = Math.min(right.minEle, node.data);
            self.isBST = true;
        }

        return self;
    }

    public static boolean isBal(Node node) {
        if (node == null)
            return true;
        if (!isBal(node.left))
            return false;
        if (!isBal(node.right))
            return false;
        int lh = heightWRTNodes(node.left);
        int rh = heightWRTNodes(node.right);
        if (Math.abs(lh - rh) > 1)
            return false;
        return true;

    }

    public static class isBalPair {
        int lh = 0;
        int rh = 0;
        boolean isBal = true;
    }

    public static isBalPair isBal2(Node node) {
        if (node == null)
            return new isBalPair();

        isBalPair left = isBal2(node.left);
        if (!left.isBal)
            return left;
        isBalPair right = isBal2(node.right);
        if (!right.isBal)
            return right;

        isBalPair curr = new isBalPair();
        curr.lh = Math.max(left.lh, left.rh) + 1;
        curr.rh = Math.max(right.lh, right.rh) + 1;

        if (Math.abs(curr.lh - curr.rh) > 1) {
            curr.isBal = false;
        }
        return curr;
    }

    public static Node LCA(Node node, int n1, int n2) {
        ArrayList<Node> l1 = nodeToRootPathArray(node, n1);
        ArrayList<Node> l2 = nodeToRootPathArray(node, n2);
        Node lca = null;
        int i = l1.size() - 1;
        int j = l2.size() - 1;
        while (i >= 0 && j >= 0 && l1.get(i) == l2.get(j)) {
            lca = l1.get(i);
            i--;
            j--;
        }
        return lca;
    }

    public static int isBal3helper(Node node) {
        if (node == null)
            return 0;
        int left = isBal3helper(node.left);
        if (left == -2)
            return -2;
        int right = isBal3helper(node.right);
        if (right == -2)
            return -2;
        if (Math.abs(left - right) > 1)
            return -2;
        return Math.max(left, right) + 1;
    }

    public static boolean isBal3(Node node) {
        if (isBal3helper(node) == -2)
            return false;
        return true;
    }

    public static class TiltPair {
        int tilt = 0;
        int sum = 0;
    }

    public static TiltPair Tilt(Node node) {
        if (node == null)
            return new TiltPair();
        TiltPair left = Tilt(node.left);
        TiltPair right = Tilt(node.right);
        TiltPair curr = new TiltPair();
        curr.sum = node.data + left.sum + right.sum;
        curr.tilt = left.tilt + right.tilt + Math.abs(left.sum - right.sum);
        return curr;
    }

    public static class lBSTPair {
        boolean isBST = true;
        int max = -(int) 1e9;
        int min = (int) 1e9;

        int MaxSize = 0;
        Node MaxBSTNode = null;
    }

    public int diameterOfBinaryTree_(Node root) {
        if (root == null)
            return 0;

        int ld = diameterOfBinaryTree_(root.left);
        int rd = diameterOfBinaryTree_(root.right);

        int lh = heightWRTNodes(root.left);
        int rh = heightWRTNodes(root.right);

        return Math.max(Math.max(ld, rd), lh + rh + 2);
    }

    // {diameter, height}
    public int[] diameterOfBinaryTree_02(Node root) {
        if (root == null)
            return new int[] { 0, -1 };

        int[] ld = diameterOfBinaryTree_02(root.left);
        int[] rd = diameterOfBinaryTree_02(root.right);

        int[] myAns = new int[2];
        myAns[0] = Math.max(Math.max(ld[0], rd[0]), ld[1] + rd[1] + 2);
        myAns[1] = Math.max(ld[1], rd[1]) + 1;

        return myAns;
    }

    int diameter = 0;

    public int diameterOfBinaryTree_03(Node root) {
        if (root == null)
            return -1;

        int ld = diameterOfBinaryTree_03(root.left);
        int rd = diameterOfBinaryTree_03(root.right);

        diameter = Math.max(diameter, ld + rd + 2);
        return Math.max(ld, rd) + 1;
    }

    public static lBSTPair largestBST(Node node) {
        if (node == null)
            return new lBSTPair();

        lBSTPair left = largestBST(node.left);
        lBSTPair right = largestBST(node.right);

        lBSTPair myAns = new lBSTPair();
        if (left.isBST && right.isBST && left.max < node.data && node.data < right.min) {
            myAns.isBST = true;
            myAns.min = Math.min(left.min, node.data);
            myAns.max = Math.max(right.max, node.data);

            myAns.MaxSize = left.MaxSize + right.MaxSize + 1;
            myAns.MaxBSTNode = node;
        } else {
            myAns.isBST = false;
            myAns.MaxSize = Math.max(left.MaxSize, right.MaxSize);
            myAns.MaxBSTNode = left.MaxSize > right.MaxSize ? left.MaxBSTNode : right.MaxBSTNode;
        }

        return myAns;
    }

    // also called BFS traversal(Breadth first search)
    public static void LevelOrderSequence(Node node) {
        Queue<Node> temp = new LinkedList<>();
        int level = -1;
        temp.add(node);
        while (temp.size() != 0) {
            int newSize = temp.size();
            level++;
            while (newSize != 0) {
                if (temp.peek().left != null)
                    temp.add(temp.peek().left);
                if (temp.peek().right != null)
                    temp.add(temp.peek().right);
                System.out.print(temp.remove().data + " ");
                newSize--;
            }
            System.out.println();
        }
        System.out.println("Levels in tree =" + level);
    }

}