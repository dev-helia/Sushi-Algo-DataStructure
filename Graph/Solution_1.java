package Graph;

import java.util.LinkedList;
import java.util.Queue;

// 2-d dfs/bfs
// 1.Number of Islands 200
// 2.Flood Fill（像染色） 733
// 3.Max Area of Island 695
// 4. 130. Surrounded Regions
// 5. 1254. Number of Closed Islands
// 6. 1020. Number of Enclaves


/**
 * in-place  or   visited: set -> boolean[][] (📣注意要add!! 不然死循环)
 * dfs : recursion
 * (主函数: 调用 -> for r for c 调用)
 *  (没有给点就要迭代全部for循环内部dfs TC: O(m × n) SC: O(m × n) (visited + stack 超级大岛) 但通常是 O(min(m,n)) 📣📣📣📣主函数可以剪枝 )
 * base  || 注意看结果再看base
 * (act: pre-order -> 中间再扩散)
 * recursion: 左右 -> neighbor -> 上下左右的neighbor     
 * backtrack
 * 
 * bfs : iteraion
 * (一层:左右->neighbor -> 上下左右)
 * 第一层
 * queue.add 
 * 第二层以后while (!queue.isEmpty()) 
 * queue.poll 
 * act 
 * queue.add 
 */
public class Solution_1 {

    // =================== Number of Islands =====================
    /**
     * dfs 岛屿问题
     * 
     * @param grid
     * @return
     * TC: TC: O(m × n)
     * SC:  O(m × n)
     */
    public int numsIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // int col = 0;
        // int row = 0;

        int count = 0;
        // ❎ Set<List<Integer, Integer>> visited = new HashSet<>();
        // ✅ 用 boolean[][] 代替 Set<List<>> 更高效且不易错
        boolean[][] visited = new boolean[rows][cols];

        // ❎ dfs(grid, rows, cols, row, col, count, visited);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < rows; c++) {
                if (grid[r][c] == '1' && !visited[r][c]) {
                    dfs(grid, r, c, visited);
                    // dfs结束就++
                    count++;
                }
            }
        }
        return count;
    }

    // ❎ private dfs(char[][] grid, int rows, int cols, int row, int col, int count, Set<List<Integer, Integer>> visited) {}
    private void dfs(char[][] grid, int row, int col, boolean[][] visited) {
        int rows = grid.length;
        int cols = grid[0].length;
        // ❎ 在主函数里面的逻辑已经判断了
        // if(visited.contains(List<>([row,col]))){
        //     return;
        // }
        // if (grid[row][col] == '0')
        //     return;
        // ❎ 主函数已经处理 count++;

        // new base case
        if (row < 0 || col < 0 || row >= rows || col >= cols || grid[row][col] == '0' || visited[row][col]) {
            return;
        }
        // ❎ visted.add(new ArrayList<>(row, col));
        visited[row][col] = true;

        // ❎
        // if (0 < row && row < rows && col < cols && 0 < col) {
        //     dfs(grid, rows, cols, row - 1, col, count, visited);
        //     dfs(grid, rows, cols, row + 1, col, count, visited);
        //     dfs(grid, rows, cols, row, col - 1, count, visited);
        //     dfs(grid, rows, cols, row, col + 1, count, visited);
        // }

        // ✅ 上下左右四个方向递归扩散
        dfs(grid, row - 1, col, visited); // 上
        dfs(grid, row + 1, col, visited); // 下
        dfs(grid, row, col - 1, visited); // 左
        dfs(grid, row, col + 1, visited); // 右
    }

    /**
     * “原地修改版本”
     * 
     * @param grid
     * @return
     * tc:时间复杂度	O(m × n)	每个格子最多访问一次
     * sc:空间复杂度	O(1)（不算递归）	不用额外数组，改在原图
     * example:
     */
    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    dfs(grid, r, c);  // 一次扩散涂掉整个岛
                    count++;
                }
            }
        }

        return count;
    }

    private void dfs(char[][] grid, int row, int col) {
        int rows = grid.length;
        int cols = grid[0].length;

        // base case
        if (row < 0 || col < 0 || row >= rows || col >= cols) return;
        if (grid[row][col] == '0') return;

        grid[row][col] = '0';  // ✅ 直接原地标记为访问过

        // 四个方向扩散
        dfs(grid, row - 1, col); // 上
        dfs(grid, row + 1, col); // 下
        dfs(grid, row, col - 1); // 左
        dfs(grid, row, col + 1); // 右
    }


    /**
     * bfs
     * @param grid
     * @return
     * TC:O(m × n) m × n，每个格子最多访问一次
     * SC: O(m × n) visited[][] 布尔矩阵	O(m × n)  queue 队列（最坏同时塞满一个“超宽的岛”）	O(m × n) 最坏是整张图是一个大岛
     */
    public int numsIslands_bfs(char[][] grid){
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        boolean[][] visited = new boolean[rows][cols];
        // ❎ Queue<?> queue = new LinkedList<>();
        Queue<int[]> queue = new LinkedList<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1' && !visited[r][c]) {
                    queue.add(new int[]{r, c});
                    visited[r][c] = true;
    //             while (!queue.isEmpty()) {
    //                 // base
    //                 if (visited[i][j] || row < 0 || row >= rows || col < 0 || col >= cols || grid[i][j] == '0') {
    //                     continue;
    //                 }
    //                 visited[i][j] = true;
    //                 queue.add();// 上下左右
    //             }
    //         }
    //     }

    //     return count;
    // }
                 while (!queue.isEmpty()) {
                    int[] pos = queue.poll();
                    int row = pos[0];
                    int col = pos[1];

                    // 四个方向
                    int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
                    for (int[] d : dirs) {
                        int newRow = row + d[0];
                        int newCol = col + d[1];

                        if (newRow >= 0 && newRow < rows &&
                            newCol >= 0 && newCol < cols &&
                            grid[newRow][newCol] == '1' &&
                                !visited[newRow][newCol]) {
                                
                            queue.add(new int[]{newRow, newCol});
                            visited[newRow][newCol] = true;
                        }
                    }
                }

                count++; // 一次完整扩散完毕，岛屿数 +1
                }
            }
        }   

        return count;
    }

    // =================== Flood Fill =====================
    /**
     * Flood fill. DFS.
     * 
     * @param image the matrix
     * @param sr the row of targes position
     * @param sc the column of targes position
     * @param color the target color
     * @return the new image
     * TC:O(m × n)，n = 总格子数。每个格子最多访问一次
     * SC:O(n * m)，主要来自递归栈 + visited数组
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int rows = image.length;
        int cols = image[0].length;
        boolean[][] visited = new boolean[rows][cols];

        int startColor = image[sr][sc]; // ✅ 记录原始颜色
        if (startColor == color) return image; // ✅ 不改就不浪费

        dfs(image, sr, sc, startColor, color, visited);
        // dfs(image, sr, sc, rows, cols, color, visited);
        // ❎为什么这个就不需要? 给了点
        // for (int r = 0; r <  ; r++){
        //     for (int c = 0;? ;c++ ) {
        //         if (visited[r][c] )
        //     }
        // }
        
        return image;
    }


    private void dfs(int[][] image, int row, int col, int startColor, int newColor, boolean[][] visited) {
        int rows = image.length;
        int cols = image[0].length;
        // ✅ base case：越界、访问过、颜色不一样都不处理
        // 🟠 注意顺序
        if (row < 0 || row >= rows || col < 0 || col >= cols)
            return;
        if (visited[row][col])
            return;
        if (image[row][col] != startColor)
            return;

        // ✅ 染色 + 标记
        image[row][col] = newColor;
        // 🟠 注意
        visited[row][col] = true;

        // ✅ 四个方向递归
        dfs(image, row - 1, col, startColor, newColor, visited); // 上
        dfs(image, row + 1, col, startColor, newColor, visited); // 下
        dfs(image, row, col - 1, startColor, newColor, visited); // 左
        dfs(image, row, col + 1, startColor, newColor, visited); // 右
    }
    
    /**
     * 原地染色
     * @param image
     * @param sr
     * @param sc
     * @param color
     * @return
     * TC: O(mn)
     * SC: O(1)
     */
    public int[][] floodFill_inplace(int[][] image, int sr, int sc, int color) {
        int startColor = image[sr][sc];
        if (startColor == color) return image;

        dfs(image, sr, sc, startColor, color);
        return image;
    }

    private void dfs(int[][] image, int row, int col, int startColor, int newColor) {
        int rows = image.length;
        int cols = image[0].length;

        // base case
        if (row < 0 || col < 0 || row >= rows || col >= cols)
            return;
        if (image[row][col] != startColor)
            return;

        image[row][col] = newColor; // ✅ 原地染色，也就是原地标记访问

        dfs(image, row - 1, col, startColor, newColor); // 上
        dfs(image, row + 1, col, startColor, newColor); // 下
        dfs(image, row, col - 1, startColor, newColor); // 左
        dfs(image, row, col + 1, startColor, newColor); // 右
    }
    
    /**
     * bfs
     * @param image
     * @param sr
     * @param sc
     * @param color
     * @return 
     * TC:mn
     * SC:mn
     */
    public int[][] floodFill_bfs(int[][] image, int sr, int sc, int color) {
        // edge
        int rows = image.length;
        int cols = image[0].length;
        int startColor = image[sr][sc];
        if (startColor == color)
            return image;
        Queue<int[]> queue = new LinkedList<>();
        image[sr][sc] = color;
        queue.add(new int[] { sr, sc });
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int row = curr[0];
            int col = curr[1];
            int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
            for (int[] d : dir) {
                int newRow = row + d[0];
                int newCol = col + d[1];
                if (0 < newRow && 0 < newCol && newRow < rows && newRow < cols 
                        && image[newRow][newCol] == startColor) {
                    image[newRow][newCol] = color;
                    queue.add(new int[] { newRow, newCol });
                    }
            }
        }
        // iteartion
        return image;
    }
    
    /**
     * 695. Max Area of Island
     * dfs
     * 可以in-place  沉了就行
     * 
     * @param grid
     * @return
     * tc: mn
     * sc: mn
     * ex:
     */
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // 🟠📣 在这里判断
                // 📣📣📣📣📣主函数里的 if 判断，确实是为了提早剪枝，提高效率。
                if (grid[r][c] == 1 && !visited[r][c]) {
                    max = Math.max(max, dfs(visited, grid, r, c));
                }
            }
        }
        return max;
    }

    private int dfs(boolean[][] visited, int[][] grid, int row, int col) {
        int rows = grid.length;
        int cols = grid[0].length;
        // base
        if (row < 0 || col < 0 || row >= rows || col >= cols)
            return 0;
        if (visited[row][col] == true)
            return 0;
        if (grid[row][col] == 0)
            return 0;
    
        visited[row][col] = true;
        // ❎ curr += 1;
        // 🟠 当前格子贡献 1
        int area = 1;


        // recursion
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        for (int[] d : dir) {
            int newRow = row + d[0];
            int newCol = col + d[1];
            area += dfs(visited, grid, newRow, newCol);
        }
        return area;
    }


    /**
     *  bfs
     * @param grid
     * @return
     */
    public int maxAreaOfIsland_bfs(int[][] grid) {
    int maxArea = 0;
    int rows = grid.length, cols = grid[0].length;
    Queue<int[]> queue = new LinkedList<>();

    for (int r = 0; r < rows; r++) {
        for (int c = 0; c < cols; c++) {
            if (grid[r][c] == 1) {
                grid[r][c] = 0;
                queue.add(new int[]{r, c});
                int area = 0;

                while (!queue.isEmpty()) {
                    int[] curr = queue.poll();
                    area++;

                    for (int[] d : new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}}) {
                        int nr = curr[0] + d[0];
                        int nc = curr[1] + d[1];
                        if (nr >= 0 && nc >= 0 && nr < rows && nc < cols && grid[nr][nc] == 1) {
                            grid[nr][nc] = 0;
                            queue.add(new int[]{nr, nc});
                        }
                    }
                }

                maxArea = Math.max(maxArea, area);
            }
        }
    }

    return maxArea;
}

}
