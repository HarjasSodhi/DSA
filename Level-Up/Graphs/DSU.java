public class DSU {
    // disjoint union set
    // union find
    // https://classroom.pepcoding.com/the-placement-program-pitampura-mar-14-2021/116/classvideos/6744
    // last one hour of video
    public int[] parent;
    public int[] size;

    public int DSUImplementation(int[][] edges) {
        int n = edges.length;
        parent = new int[n];
        size = new int[n];

        // initialise the arrays according to algo
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        boolean ContainsCycle = false;

        // create edges
        for (int[] e : edges) {
            int p1 = findParent(e[0]);
            int p2 = findParent(e[1]);

            if (p1 == p2) {
                ContainsCycle = true;
                continue;
            }

            mergeParent(p1, p2);
        }

        int gcc = 0; // find connected components
        for (int i = 0; i < n; i++) {
            findParent(i);
            if (parent[i] == i)
                gcc++;
        }

        if (ContainsCycle)
            System.out.println("contains cycle");

        return gcc;
    }

    // path compression
    // this is the most important function
    // time complexity is based on inverse ackermann function
    // without this function the complexity of each element could be o(n) which will
    // make the entire function o(n2)
    // but with path complression and deciding the parent change on the bases of
    // size, the complexity of this function is
    // o(alpha(n));
    // where alpha is the inverse ackermann function
    // this function is really fast and should be used whenever needed
    public int findParent(int u) {
        if (parent[u] == u)
            return u; // return absolute parent
        // update parent
        return parent[u] = findParent(parent[u]);
    }

    public void mergeParent(int p1, int p2) {
        // decide which element's parent to update
        if (size[p1] < size[p2]) {
            parent[p1] = p2; // change parent
            size[p2] += size[p1]; // update size due to parent change
        } else {
            parent[p2] = p1;
            size[p1] += size[p2];
        }
    }

    // =================================================================================================================

    // leetcode 684
    public int[] findRedundantConnection(int[][] edges) {
        int[] ans = new int[2];
        int n = edges.length;
        parent = new int[n + 1]; // n+1 size because 1 based indexing
        size = new int[n + 1]; // n+1 size because 1 based indexing

        // initialise the arrays according to algo
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        // create edges
        for (int[] e : edges) {
            int p1 = findParent(e[0]);
            int p2 = findParent(e[1]);

            if (p1 == p2) {
                ans[0] = e[0];
                ans[1] = e[1];
            }

            mergeParent(p1, p2);
        }
        return ans;
    }

}