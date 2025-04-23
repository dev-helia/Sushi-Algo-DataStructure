package Graph;

import java.util.*;

/**
 * 题号	题名	类型	难度	解法推荐
 * 207	Course Schedule	能不能完成所有课	🔥中	BFS 拓扑判环
 * 210	Course Schedule II	返回学习顺序	🔥中	BFS 拓扑排序
 * 802	Find Eventual Safe States	找最终安全点	中	拓扑排序反向建图
 * 444	Sequence Reconstruction	唯一拓扑序	困难	判断唯一拓扑
 * 1857	Largest Color Value in a Directed Graph	拓扑 + 统计最大颜色路径	困难	拓扑 + DPS
 * 329	Longest Increasing Path in a Matrix	有向图建图后最长路径	困难	拓扑 + DP 
 * 1203	Sort Items by Groups Respecting Dependencies	多层拓扑（组+任务）	困难	双层拓扑 
 * 269	Alien Dictionary（外星词典）	字符顺序推理	困难	字符建图 + 拓扑 
 * 
 */
public class Topo {

    /**
     * 一个bfs 拓扑
     * @param numCourse
     * @param prerequisites
     * @return
     */
    // int numCourses = 4;
    // int[][] prerequisites = {
    //     {1, 0},  // 想学 1，必须先学 0（0 → 1）
    //     {2, 0},  // 想学 2，必须先学 0（0 → 2）
    //     {3, 1},  // 想学 3，必须先学 1（1 → 3）
    //     {3, 2}   // 想学 3，必须先学 2（2 → 3）
    // };
    // 🍒 prerequisites 是啥？
    // 它是一个二维数组，每一项是两个数，表示「依赖关系」。
    // 格式：
    // prerequisites = [[A, B]]
    // 意思：要学 A，必须先学 B
    // 也可以理解成一条有向边：
    // [0, 1, 2, 3] 或 [0, 2, 1, 3] 都是合法的拓扑排序
    public List<Integer> topologicalSort_BFS(int numCourse, int[][] prerequisites) {
        // 构建图和入度表
        // 🟧 构建图
        // 🟧 入度表
        Map<Integer, List<Integer>> graph = new HashMap<>();

        int[] inDegree = new int[numCourse];

        for (int[] pair : prerequisites) {
            int from = pair[1], to = pair[0];
            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(to);
            inDegree[to]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourse; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        if (result.size() != numCourse) {
            return new ArrayList<>();
        }

        return result;
    }

    /**
     * dfs
     * @param numCourse
     * @param prerequisites
     * @return
     */
    // prerequisites = {
    //     {1, 0}, // 0 -> 1
    //     {2, 0}, // 0 -> 2
    //     {3, 1}, // 1 -> 3
    //     {3, 2}  // 2 -> 3
    // };
    // DFS 怎么做？ 我们从 0 开始 DFS：
    // DFS(0)：
    //     → DFS(1)
    //         → DFS(3)
    //     → DFS(2)
    //         → DFS(3)（已经访问过）
    public List<Integer> topologicalSort_DFS(int numCourse, int[][] prerequisites) {
        // 🟧 构建图
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] pair : prerequisites) {
            int from = pair[1], to = pair[0];
            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(to);
        }

        // 🟧 构建visited
        boolean[] visited = new boolean[numCourse];
        // 🟧 构建是否有环的onPath
        boolean[] onPath = new boolean[numCourse];
        // 🟧 用来暂时存着之后倒序的
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < numCourse; i++) {
            if (!visited[i]) {
                if (!dfs(i, graph, visited, onPath, stack)) {
                    return new ArrayList<>(); // 没办法拓扑
                }
            }
        }

        // Stack<Integer> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }
    
    private boolean dfs(int node, Map<Integer, List<Integer>> graph, boolean[] visited,
            boolean[] onPath, Stack<Integer> stack) {
        visited[node] = true;
        onPath[node] = true;
        
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (onPath[neighbor]) return false; // 🟧 为啥
            if (!visited[neighbor]) {
                // 🟧 在 DFS 的递归逻辑里：
                // 这个顺序是：
                // 先访问所有“前置课”，等他们都处理完，才能把当前课入栈！
                if (!dfs(neighbor, graph, visited, onPath, stack)) {
                    return false;
                }
            }
        }
        onPath[node] = false;
        stack.push(node);
        return true;
    }
    
}
