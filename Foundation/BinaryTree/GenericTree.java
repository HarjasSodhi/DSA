import java.util.*;

public class GenericTree {
    public static class Node {
        int data;
        ArrayList<Node> children;

        Node(int data) {
            this.data = data;
            children = new ArrayList<>();
        }
    }

    public static int size(Node node) {
        int size = 1;
        for (int i = 0; i < node.children.size(); i++) {
            size += size(node.children.get(i));
        }
        return size;
    }

    public static int max(Node node) {
        int max = node.data;
        for (Node child : node.children) {
            max = Math.max(max(child), max);
        }
        return max;
    }

    public static int min(Node node) {
        int min = node.data;
        for (Node child : node.children) {
            min = Math.min(min(child), min);
        }
        return min;
    }

    public static int height(Node node) {
        int h = 0;
        for (int i = 0; i < node.children.size(); i++) {
            h = Math.max(height(node.children.get(i)) + 1, h);
        }
        return h;
    }

    public static boolean find(Node node, int val) {
        if (node.data == val)
            return true;
        for (Node child : node.children) {
            if (find(child, val))
                return true;
        }
        return false;
    }

    // public static boolean find(Node root, int data) {
    // if (root.data == data)
    // return true;

    // boolean res = false;
    // for (Node child : root.childs)
    // res = res || find(child, data);

    // return res;
    // }

    public static ArrayList<Integer> NodetoRootPath(Node node, int val) {
        if (node.data == val) {
            ArrayList<Integer> base = new ArrayList<>();
            base.add(node.data);
            return base;
        }
        for (Node child : node.children) {
            ArrayList<Integer> ans = NodetoRootPath(child, val);
            if (ans.size() != 0) {
                ans.add(node.data);
                return ans;
            }
        }
        return new ArrayList<>();
    }

    public static boolean NodeToRootPath2(Node node, int val, ArrayList<Integer> ans) {
        if (node.data == val) {
            ans.add(node.data);
            return true;
        }
        boolean res = false;
        for (Node child : node.children)
            res = res || NodeToRootPath2(child, val, ans);
        if (res)
            ans.add(node.data);
        return res;
    }

    public static int countLeaves(Node node) {
        if (node.children.size() == 0)
            return 1;
        int leaves = 0;
        for (Node child : node.children) {
            leaves += countLeaves(child);
        }
        return leaves;
    }

    public static int sum(Node node) {
        int sum = node.data;
        for (Node child : node.children) {
            sum += sum(child);
        }
        return sum;
    }

    public static int LCA(Node node, int n1, int n2) {
        ArrayList<Integer> l1 = NodetoRootPath(node, n1);
        ArrayList<Integer> l2 = NodetoRootPath(node, n2);
        int lca = -(int) 1e9;
        int i = l1.size() - 1;
        int j = l2.size() - 1;
        while (i >= 0 && j >= 0 && l1.get(i) == l2.get(j)) {
            lca = l1.get(i);
            i--;
            j--;
        }
        return lca;
    }

    public static int DistBetweenNodes(Node node, int n1, int n2) {
        ArrayList<Integer> l1 = NodetoRootPath(node, n1);
        ArrayList<Integer> l2 = NodetoRootPath(node, n2);
        int i = l1.size() - 1;
        int j = l2.size() - 1;
        if (i < 0 || j < 0)
            return 0;
        while (i >= 0 && j >= 0 && l1.get(i) == l2.get(j)) {
            i--;
            j--;
        }

        i++;
        j++;
        return i + j;
    }

    public static boolean isShapeSame(Node n1, Node n2) {
        if (n1.children.size() != n2.children.size())
            return false;
        boolean res = true;
        for (int i = 0; i < n1.children.size(); i++) {
            res = res && isShapeSame(n1.children.get(i), n2.children.get(i));
            if (!res)
                return false;
        }
        return res;
    }

    // data can be checked also
    public static boolean isMirror(Node n1, Node n2) {
        if (n1.children.size() != n2.children.size())
            return false;
        boolean res = true;
        int i = 0;
        int j = n2.children.size() - 1;
        while (i < j) {
            res = res && isMirror(n1.children.get(i), n2.children.get(j));
            if (!res)
                return false;
            i++;
            j--;
        }
        return res;
    }

    public static boolean isSymmetric(Node node) {
        if (node.children.size() == 0)
            return true;
        return isMirror(node, node);
    }

    public static int ceil = (int) 1e9;
    public static int floor = -(int) 1e9;

    // can also be done using object Pairs.
    // set ceil floor as zero by creating a helper function
    public static void ceilAndFloor(Node node, int data) {
        if (node.data > data && node.data < ceil)
            ceil = node.data;
        if (node.data < data && node.data > floor)
            floor = node.data;
        for (Node child : node.children) {
            ceilAndFloor(child, data);
        }
    }

    private static int kthLargestEleHelper(Node node, int ub) {
        int recMax = -(int) 1e9;
        for (Node child : node.children) {
            int recans = kthLargestEleHelper(child, ub);
            recMax = Math.max(recMax, recans);
        }
        return node.data < ub ? Math.max(node.data, recMax) : recMax;
    }

    public static int kthLargestEle(Node node, int k) {
        int ub = (int) 1e9;
        while (k > 0) {
            ub = kthLargestEleHelper(node, ub);
            k--;
        }
        return ub;
    }

    private static Node getTail(Node node) {
        while (node.children.size() != 0) {
            node = node.children.get(0);
        }
        return node;
    }

    public static void Linearize(Node node) {
        for (Node child : node.children) {
            Linearize(child);
        }
        for (int i = node.children.size() - 1; i > 0; i--) {
            Node tail = getTail(node.children.get(i - 1));
            tail.children.add(node.children.get(i));
            node.children.remove(i);
        }
    }

    public static void LevelOrderSequence(Node node) {
        Queue<Node> temp = new LinkedList<>();
        int level = -1;
        temp.add(node);
        while (temp.size() != 0) {
            int newSize = temp.size();
            level++;
            while (newSize > 0) {
                System.out.println(temp.peek().data + " ");
                Node rn = temp.remove();
                for (Node child : rn.children) {
                    temp.add(child);
                }
                newSize--;
            }
            System.out.println();
        }
        System.out.println("Levels in tree =" + level);
    }

    public static void ZigZagPrint(Node node) {
        Stack<Node> mainS = new Stack<>();
        Stack<Node> sideS = new Stack<>();
        int level = 0;
        mainS.push(node);
        while (mainS.size() != 0) {
            int newSize = mainS.size();
            while (newSize > 0) {
                Node rn = mainS.pop();
                System.out.print(rn.data + " ");
                if (level % 2 == 0) {
                    for (Node child : rn.children) {
                        sideS.push(child);
                    }
                } else {
                    for (int i = rn.children.size() - 1; i >= 0; i--) {
                        sideS.push(rn.children.get(i));
                    }
                }
                newSize--;
            }
            mainS = sideS;
            sideS = new Stack<>();
            level++;
            System.out.println();
        }
    }

}