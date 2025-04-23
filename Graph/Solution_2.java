package Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 490 the maze vip
 * 499 the maze vip
 * 505 the maze vip
 * 2077 paths in maze that... vip
 * 1036 escape a large maze
 * 1926 nearest exit from entrance
 */
public class Solution_2 {
    /**
     * 最基础的hasPath, 给定起点和终点
     * @param maze
     * @param sr
     * @param sc
     * @param er
     * @param ec
     * @return
     * tc: mn
     * sc: mn
     */
    public boolean hasPath(int[][] maze, int sr, int sc, int er, int ec) {
        int rows = maze.length;
        int cols = maze[0].length;
        boolean[][] visited = new boolean[rows][cols];

        return dfs(maze, sr, sc, er, ec, visited);
    }

    private boolean dfs(int[][] maze, int row, int col, int er, int ec, boolean[][] visited) {
        int rows = maze.length;
        int cols = maze[0].length;

        // 越界或撞墙或访问过
        if (row < 0 || col < 0 || row >= rows || col >= cols ||
            maze[row][col] == 1 || visited[row][col]) return false;

        // 到终点了！
        if (row == er && col == ec) return true;

        visited[row][col] = true;

        // 四个方向尝试
        return dfs(maze, row - 1, col, er, ec, visited) ||
            dfs(maze, row + 1, col, er, ec, visited) ||
            dfs(maze, row, col - 1, er, ec, visited) ||
            dfs(maze, row, col + 1, er, ec, visited);
    }

    // ==================== 1926. Nearest Exit from Entrance in Maze ==================== 
    /**
     * An exit is defined as an empty cell 
     * that is at the border of the maze. 
     * The entrance does not count as an exit.
     * @param maze
     * @param entrance
     * @return the number of steps to get out of the maze
     * -1 if there is no exit to get out
     */
    public int nearestExit(char[][] maze, int[] entrance) {
        int rows = maze.length;
        int cols = maze[0].length;
        int steps = 0;

        int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];

        queue.add(entrance);
        // 🟧 要死啊
        visited[entrance[0]][entrance[1]] = true;


        while (!queue.isEmpty()) {
            // 🟧 按照层
            int size = queue.size(); // 一层扩散多少个点
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int row = curr[0], col = curr[1];

                for (int[] d : dirs) {
                    int newRow = row + d[0];
                    int newCol = col + d[1];

                    // 🧱 先越界判断，再访问
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols &&
                        maze[newRow][newCol] == '.' && !visited[newRow][newCol]) {
                        
                        // ✅ 判断是否是出口（不等于入口，并且在边界）
                        if (newRow == 0 || newRow == rows - 1 || newCol == 0 || newCol == cols - 1) {
                            return steps + 1;
                        }

                        visited[newRow][newCol] = true;
                        queue.add(new int[]{newRow, newCol});
                    }
                }
            }
            steps++;
        }
        //     int[] curr = queue.poll();
        //     int row = curr[0];
        //     int col = curr[1];

        //     for (int[] d : dirs) {
        //         int newRow = row + d[0];
        //         int newCol = col + d[1];
        //         //  🟧 `equals('.')` 不对！因为 `maze` 是一个 `char[][]`，你得用 `maze[r][c] == '.'`
        //         // 🟧
        //         if ( row > 0 && col > 0 && row < rows && col < cols
        //                 && maze[newRow][newCol] == '.' && !visited[newRow][newCol]) {
        //             if (newRow == 0 || newRow == rows - 1 || newCol == 0 || newCol == cols - 1) {
        //                 return step;
        //             }
        //             queue.add(new int[] { newRow, newCol });
        //         }
        //     }

        //     step++;
        // }
        // return -1;
        return -1;
    }
}
