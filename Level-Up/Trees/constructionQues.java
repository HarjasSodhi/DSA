public class constructionQues {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
            this.right = this.left = null;
        }
    }

    // // leetcode 105
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(inorder, preorder, 0, 0, inorder.length - 1);
    }

    public TreeNode buildTreeHelper(int[] inorder, int[] preorder, int idx, int isi, int iei) {
        if (isi > iei || iei < isi)
            return null;
        if (isi == iei)
            return new TreeNode(inorder[isi]);
        TreeNode root = new TreeNode(preorder[idx]);
        int i = isi;
        while (inorder[i] != preorder[idx])
            i++;
        int offset = (i - isi) + 1;

        root.left = buildTreeHelper(inorder, preorder, idx + 1, isi, i - 1);
        root.right = buildTreeHelper(inorder, preorder, idx + offset, i + 1, iei);
        return root;
    }

    // leetcode 106
    public TreeNode buildTreePost(int[] inorder, int[] postorder) {
        return buildTreePostHelper(inorder, postorder, postorder.length - 1, 0, inorder.length - 1);
    }

    public TreeNode buildTreePostHelper(int[] inorder, int[] postorder, int idx, int isi, int iei) {
        if (isi > iei || iei < isi)
            return null;
        if (isi == iei)
            return new TreeNode(inorder[isi]);
        TreeNode root = new TreeNode(postorder[idx]);
        int i = isi;
        while (inorder[i] != postorder[idx])
            i++;
        int offset = iei - i + 1;
        root.left = buildTreePostHelper(inorder, postorder, idx - offset, isi, i - 1);
        root.right = buildTreePostHelper(inorder, postorder, idx - 1, i + 1, iei);
        return root;
    }

    // leetcode 108
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBSTHelper(int[] nums, int si, int ei) {
        if (si == ei)
            return new TreeNode(nums[si]);
        int mid = (si + ei) / 2;
        TreeNode midNode = new TreeNode(nums[mid]);
        if (si != mid) {
            midNode.left = sortedArrayToBSTHelper(nums, si, mid - 1);
        }
        midNode.right = sortedArrayToBSTHelper(nums, mid + 1, ei);
        return midNode;
    }

    // 1008
    public TreeNode bstFromPreorder(int[] preorder) {
        return bstFromPreorderHelperOptimised(preorder, new int[1], Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public TreeNode bstFromPreorderHelper(int[] preorder, int si, int ei) {
        if (si > ei)
            return null;
        TreeNode root = new TreeNode(preorder[si]);
        if (si == ei) {
            return root;
        }
        int idx = si;
        while (idx < preorder.length && preorder[idx] <= preorder[si])
            idx++;
        root.left = bstFromPreorderHelper(preorder, si + 1, idx - 1);
        root.right = bstFromPreorderHelper(preorder, idx, ei);
        return root;
    }

    public TreeNode bstFromPreorderHelperOptimised(int[] preorder, int[] idx, int min, int max) {
        if (idx[0] == preorder.length || preorder[idx[0]] > max || preorder[idx[0]] < min) {
            return null;
        }
        TreeNode node = new TreeNode(preorder[idx[0]]);
        int curr = preorder[idx[0]];
        idx[0]++;
        node.left = bstFromPreorderHelperOptimised(preorder, idx, min, curr);
        node.right = bstFromPreorderHelperOptimised(preorder, idx, curr, max);
        return node;
    }

    // 449
    public class Codec {

        public void getPre(TreeNode root, StringBuilder sb) {
            if (root == null) {
                return;
            }
            sb.append(root.val + ",");
            getPre(root.left, sb);
            getPre(root.right, sb);
        }

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            getPre(root, sb);
            return sb.toString();
        }

        // // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.equals(""))
                return null;

            String[] arr = data.split(",");
            int[] preorder = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                preorder[i] = Integer.parseInt(arr[i]);
            }
            return bstFromPreorder(preorder);
        }
    }

    // 297
    public class Codec2 {

        public void getPre(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append("n,");
                return;
            }
            sb.append(root.val + ",");
            getPre(root.left, sb);
            getPre(root.right, sb);
        }

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            getPre(root, sb);
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] arr = data.split(",");
            return construct(arr, new int[] { 0 });
        }

        public TreeNode construct(String[] arr, int[] idx) {
            if (arr[idx[0]].equals("n")) {
                idx[0]++;
                return null;
            }
            TreeNode root = new TreeNode(Integer.parseInt(arr[idx[0]]));
            idx[0]++;
            root.left = construct(arr, idx);
            root.right = construct(arr, idx);
            return root;
        }
    }

}