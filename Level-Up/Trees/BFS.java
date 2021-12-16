import java.util.*;

public class BFS {

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

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // single line Print
    public static void LineOrder1(Node root) {
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(root);
        while (que.size() != 0) {
            Node temp = que.removeFirst();
            System.out.print(temp.val + " ");
            if (temp.left != null)
                que.addLast(temp.left);
            if (temp.right != null)
                que.addLast(temp.right);

        }
    }

    // separate lines
    public static void LineOrder2(Node root) {
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(root);
        que.addLast(null);
        while (que.size() != 0) {
            Node temp = que.removeFirst();
            if (temp == null) {
                if (que.size() != 0) {
                    System.out.println();
                    que.addLast(null);
                }
            }
            System.out.print(temp.val + " ");
            if (temp.left != null)
                que.addLast(temp.left);
            if (temp.right != null)
                que.addLast(temp.right);

        }
    }

    // another way
    public static void LineOrder3(Node root) {
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(root);
        while (que.size() != 0) {
            int len = que.size();
            while (len-- > 0) {
                Node temp = que.removeFirst();
                System.out.print(temp.val + " ");
                if (temp.left != null)
                    que.addLast(temp.left);
                if (temp.right != null)
                    que.addLast(temp.right);
            }
            System.out.println();
        }
    }

    // https://practice.geeksforgeeks.org/problems/left-view-of-binary-tree/1
    public static ArrayList<Integer> leftView(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(root);
        boolean flag = false;
        while (que.size() != 0) {
            int len = que.size();
            while (len-- > 0) {
                Node temp = que.removeFirst();
                if (!flag) {
                    ans.add(temp.val);
                    flag = true;
                }
                if (temp.left != null)
                    que.addLast(temp.left);
                if (temp.right != null)
                    que.addLast(temp.right);
            }
            flag = false;
        }
        return ans;
    }

    // another way
    public ArrayList<Integer> leftView2(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        leftViewHelper(root, 1, ans);
        return ans;
    }

    public void leftViewHelper(Node root, int depth, ArrayList<Integer> ans) {
        if (root == null)
            return;

        if (depth > ans.size())
            ans.add(root.val);

        leftViewHelper(root.left, depth + 1, ans);
        leftViewHelper(root.right, depth + 1, ans);
    }

    // leetcode 199
    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        rightViewHelper(root, 1, ans);
        return ans;
    }

    public void rightViewHelper(TreeNode root, int depth, List<Integer> ans) {
        if (root == null)
            return;

        if (depth > ans.size())
            ans.add(root.val);

        rightViewHelper(root.right, depth + 1, ans);
        rightViewHelper(root.left, depth + 1, ans);
    }

    // another way
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);
        boolean flag = false;
        while (que.size() != 0) {
            int len = que.size();
            while (len-- > 0) {
                TreeNode temp = que.removeFirst();
                if (!flag) {
                    ans.add(temp.val);
                    flag = true;
                }
                if (temp.right != null)
                    que.addLast(temp.right);
                if (temp.left != null)
                    que.addLast(temp.left);
            }
            flag = false;
        }
        return ans;
    }

    // find width
    public static void findWidth(Node root, int[] minMax, int width) {
        if (root == null)
            return;

        minMax[0] = Math.min(minMax[0], width);
        minMax[1] = Math.max(minMax[1], width);

        findWidth(root.left, minMax, width - 1);
        findWidth(root.right, minMax, width + 1);
    }

    // vertical order
    public static ArrayList<ArrayList<Integer>> verticalOrder_rec(Node root) {
        int[] minMax = new int[2];
        findWidth(root, minMax, 0);
        int width = minMax[1] - minMax[0] + 1;
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            ans.add(new ArrayList<>());
        }

        verticalOrderHelper(root, 0, ans, Math.abs(minMax[0]));

        return ans;
    }

    // this might give wrong order of elements
    public static void verticalOrderHelper(Node root, int width, ArrayList<ArrayList<Integer>> ans, int minAbs) {
        if (root == null)
            return;

        ans.get(minAbs + width).add(root.val);

        verticalOrderHelper(root.left, width - 1, ans, minAbs);
        verticalOrderHelper(root.right, width + 1, ans, minAbs);
    }

    // another way . without rec
    // this will give correct order of elements
    public static class verticalOrderNodeClass {
        Node root;
        int width;

        verticalOrderNodeClass(Node root, int width) {
            this.root = root;
            this.width = width;
        }

    }

    public static ArrayList<ArrayList<Integer>> verticalOrder(Node root) {
        int[] minMax = new int[2];
        findWidth(root, minMax, 0);
        int width = minMax[1] - minMax[0] + 1;
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            ans.add(new ArrayList<>());
        }

        LinkedList<verticalOrderNodeClass> que = new LinkedList<>();
        que.addLast(new verticalOrderNodeClass(root, 0));
        while (que.size() != 0) {
            int size = que.size();

            while (size-- > 0) {
                verticalOrderNodeClass temp = que.removeFirst();
                ans.get(temp.width + Math.abs(minMax[0])).add(temp.root.val);

                if (temp.root.left != null) {
                    que.addLast(new verticalOrderNodeClass(temp.root.left, temp.width - 1));
                }
                if (temp.root.right != null) {
                    que.addLast(new verticalOrderNodeClass(temp.root.right, temp.width + 1));
                }
            }
        }

        return ans;
    }

    // https://practice.geeksforgeeks.org/problems/top-view-of-binary-tree/1
    public static ArrayList<Integer> topView(Node root) {
        int[] minMax = new int[2];
        findWidth(root, minMax, 0);
        int width = minMax[1] - minMax[0] + 1;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            ans.set(i, 0);
        }
        LinkedList<verticalOrderNodeClass> que = new LinkedList<>();
        que.addLast(new verticalOrderNodeClass(root, 0));
        while (que.size() != 0) {
            int size = que.size();

            while (size-- > 0) {
                verticalOrderNodeClass temp = que.removeFirst();
                if (ans.get(temp.width + Math.abs(minMax[0])) == 0)
                    ans.set(temp.width + Math.abs(minMax[0]), temp.root.val);

                if (temp.root.left != null) {
                    que.addLast(new verticalOrderNodeClass(temp.root.left, temp.width - 1));
                }
                if (temp.root.right != null) {
                    que.addLast(new verticalOrderNodeClass(temp.root.right, temp.width + 1));
                }
            }
        }

        return ans;
    }

    // topview ordered way recursion
    // class link-
    // https://classroom.pepcoding.com/the-placement-program-pitampura-mar-14-2021/116/classvideos/6338#

    public static class levelClass {
        int level;
        int data;

        public levelClass(int level, int data) {
            this.level = level;
            this.data = data;
        }
    }

    public static ArrayList<Integer> topView2(Node root) {
        TreeMap<Integer, levelClass> map = new TreeMap<Integer, levelClass>();
        // map of width and pair of data and level
        ArrayList<Integer> ans = new ArrayList<>();

        topView2Helper(root, map, 0, 0);

        map.forEach((key, value) -> ans.add(value.data));

        return ans;
    }

    public static void topView2Helper(Node root, TreeMap<Integer, levelClass> map, int width, int level) {
        if (root == null)
            return;
        if (map.containsKey(width)) {
            if ((map.get(width)).level > level) {
                map.put(width, new levelClass(level, root.val));
            }
        } else {
            map.put(width, new levelClass(level, root.val));
        }
        topView2Helper(root.left, map, width - 1, level + 1);
        topView2Helper(root.right, map, width + 1, level + 1);
    }

    // https://practice.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1/?company[]=Paytm&page=1&query=company[]Paytmpage1
    // with bfs
    // can be done with recursion also with new class like above
    public static ArrayList<Integer> bottomView(Node root) {
        int[] minMax = new int[2];
        findWidth(root, minMax, 0);
        ArrayList<Integer> ans = new ArrayList<>();
        LinkedList<verticalOrderNodeClass> que = new LinkedList<>();
        int width = minMax[1] - minMax[0] + 1;
        for (int i = 0; i < width; i++) {
            ans.add(0);
        }
        que.addLast(new verticalOrderNodeClass(root, 0));
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                verticalOrderNodeClass temp = que.removeFirst();
                ans.set(temp.width + Math.abs(minMax[0]), temp.root.val);
                if (temp.root.left != null) {
                    que.addLast(new verticalOrderNodeClass(temp.root.left, temp.width - 1));
                }
                if (temp.root.right != null) {
                    que.addLast(new verticalOrderNodeClass(temp.root.right, temp.width + 1));
                }
            }
        }
        return ans;
    }

    // https://practice.geeksforgeeks.org/problems/diagonal-traversal-of-binary-tree/1
    // diagnol traversal
    // try deque approach also-> add root to que. pop the top of the que. add its
    // right node to front of the que and add left node at end of que and repeat
    // till size is 0
    public static ArrayList<Integer> diagonal(Node root) {
        ArrayList<ArrayList<Integer>> diag = new ArrayList<>();

        diagonalHelper(root, 1, diag);

        ArrayList<Integer> ans = new ArrayList<>();
        for (ArrayList<Integer> e : diag) {
            for (int i : e) {
                ans.add(i);
            }
        }

        return ans;
    }

    public static void diagonalHelper(Node root, int diagnol, ArrayList<ArrayList<Integer>> diag) {
        if (root == null)
            return;
        if (diag.size() < diagnol)
            diag.add(new ArrayList<>());
        diag.get(diagnol - 1).add(root.val);

        diagonalHelper(root.left, diagnol + 1, diag);
        diagonalHelper(root.right, diagnol, diag);
    }

}