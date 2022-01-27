public class dfsQuestions {
    // remeber-> sometimes you need to let the recursive calls going even if you
    // find the answer.

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
    // public void solve(char[][] board) {
    // for (int i = 0; i < board.length; i++) {
    // for (int j = 0; j < board[0].length; j++) {
    // if (board[i][j] == 'v' || board[i][j] == 'X')
    // continue;
    // solveHelper(i, j, board);
    // }
    // }
    // for (int i = 0; i < board.length; i++) {
    // for (int j = 0; j < board[0].length; j++) {
    // if (board[i][j] == 'v')
    // board[i][j] = 'O';
    // }
    // }
    // }

    // public boolean solveHelper(int r, int c, char[][] board) {
    // if (r == 0 || r == board.length - 1 || c == 0 || c == board[0].length - 1) {
    // return false;
    // }
    // boolean ans = true;
    // board[r][c] = 'v';
    // int[][] dirs = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
    // for (int i = 0; i < 4; i++) {
    // int newR = r + dirs[i][0];
    // int newC = c + dirs[i][1];
    // if (board[newR][newC] == 'O') {
    // ans = ans && solveHelper(newR, newC, board);
    // }
    // }
    // if (ans) {
    // board[r][c] = 'X';
    // }
    // return ans;
    // }

    // leetcode 130
    public void solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if ((i == 0 || i == board.length - 1 || j == 0 || j == board[0].length - 1) && board[i][j] == 'O') {
                    solveHelper(i, j, board);
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'B') {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void solveHelper(int r, int c, char[][] board) {
        board[r][c] = 'B';
        int[][] dirs = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        for (int i = 0; i < 4; i++) {
            int newR = r + dirs[i][0];
            int newC = c + dirs[i][1];
            if (newR >= 0 && newC >= 0 && newR < board.length && newC < board[0].length && board[newR][newC] != 'B'
                    && board[newR][newC] != 'X') {
                solveHelper(newR, newC, board);
            }
        }
    }

    public int closedIsland(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1)
                    continue;
                if (closedIslandHelper(i, j, grid))
                    ans++;
            }
        }
        return ans;
    }

    public boolean closedIslandHelper(int r, int c, int[][] grid) {
        if (r == 0 || r == grid.length - 1 || c == 0 || c == grid[0].length - 1) {
            return false;
        }
        grid[r][c] = 1;
        boolean ans = true;
        int[][] dirs = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        for (int i = 0; i < 4; i++) {
            int newR = r + dirs[i][0];
            int newC = c + dirs[i][1];
            if (grid[newR][newC] == 0) {
                ans = closedIslandHelper(newR, newC, grid) && ans;
            }
        }
        return ans;
    }

    // // leetcode 1020
    public int numEnclaves(int[][] grid) {
        boolean[][] vis = new boolean[grid.length][grid[0].length];
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (vis[i][j] || grid[i][j] == 0)
                    continue;
                ans += numEnclavesHelpler(i, j, grid, vis);
            }
        }
        return ans;
    }

    public int numEnclavesHelpler(int r, int c, int[][] grid, boolean[][] vis) {
        if (r == 0 || r == grid.length - 1 || c == 0 || c == grid[0].length - 1) {
            return 0;
        }
        vis[r][c] = true;
        boolean flag = true;
        int ans = 0;
        int[][] dirs = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        for (int i = 0; i < 4; i++) {
            int newR = r + dirs[i][0];
            int newC = c + dirs[i][1];
            if (!vis[newR][newC] && grid[newR][newC] == 1) {
                int temp = numEnclavesHelpler(newR, newC, grid, vis);
                if (temp == 0)
                    flag = false;
                else {
                    ans += temp;
                }
            }
        }
        return flag ? ans + 1 : 0;
    }

    // leetcode 1905
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int ans = 0;
        for (int i = 0; i < grid2.length; i++) {
            for (int j = 0; j < grid2[0].length; j++) {
                if (grid2[i][j] == 1) {
                    if (countSubIslandsHelper(i, j, grid1, grid2))
                        ans++;
                }
            }
        }
        return ans;
    }

    public boolean countSubIslandsHelper(int r, int c, int[][] grid1, int[][] grid2) {
        if (grid1[r][c] == 0)
            return false;
        grid2[r][c] = 0;

        boolean ans = true;
        int[][] dirs = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        for (int i = 0; i < 4; i++) {
            int newR = r + dirs[i][0];
            int newC = c + dirs[i][1];
            if (newR >= 0 && newC >= 0 && newR < grid1.length && newC < grid1[0].length && grid2[newR][newC] == 1) {
                ans = countSubIslandsHelper(newR, newC, grid1, grid2) && ans;
                // position of ans matters
                // code was not working properly because calls were not being made because exp
                // was -> ans && func()
                // now it works properly
            }
        }
        return ans;
    }

}