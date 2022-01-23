public class dfsQuestions {

    // leetcode 200
    public int numIslands(char[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '0')
                    continue;
                ans++;
                numIslandsHelper(i, j, grid);
            }
        }
        return ans;
    }

    public void numIslandsHelper(int r, int c, char[][] grid) {
        if (r < 0 || c < 0 || r == grid.length || c == grid[0].length || grid[r][c] == '0')
            return;
        grid[r][c] = '0';
        numIslandsHelper(r + 1, c, grid);
        numIslandsHelper(r, c - 1, grid);
        numIslandsHelper(r - 1, c, grid);
        numIslandsHelper(r, c + 1, grid);
    }

    // leetcode 695
    public int maxAreaOfIsland(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0)
                    continue;
                ans = Math.max(ans, maxAreaHelper(i, j, grid));
            }
        }
        return ans;
    }

    public int maxAreaHelper(int r, int c, int[][] grid) {
        if (r < 0 || c < 0 || r == grid.length || c == grid[0].length || grid[r][c] == 0)
            return 0;
        grid[r][c] = 0;
        int ans = 1;
        ans += maxAreaHelper(r + 1, c, grid);
        ans += maxAreaHelper(r, c - 1, grid);
        ans += maxAreaHelper(r - 1, c, grid);
        ans += maxAreaHelper(r, c + 1, grid);
        return ans;
    }

    // leetcode 463
    public int islandPerimeter(int[][] grid) {
        int ans = 0;
        int[][] dir = { { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 } };
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    for (int k = 0; k < 4; k++) {
                        int r = i + dir[k][0];
                        int c = j + dir[k][1];
                        if (r < 0 || c < 0 || r == grid.length || c == grid[0].length || grid[r][c] == 0) {
                            ans++;
                        }
                    }
                }
            }
        }
        return ans;
    }

    // leetcode 130
    public void solve(char[][] board) {
        
    }

}