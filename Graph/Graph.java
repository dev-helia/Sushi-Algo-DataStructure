package Graph;

import java.util.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// 连通图
public class Graph {
    //点（Node）：0，1，2，3  
    //边（Edge）：0-1，0-2，1-3  

    // 连通图
    //      0
    //     / \
    //    1   2
    //    |
    //    3

    // =============== adjacency list ===================
    //Map<Integer, List<Integer>> 想象成：
    //key 是一个节点
    //value 是这个节点连着的所有节点列表
    Map<Integer, List<Integer>> graph = new HashMap<>();

    // 建边 0 - 1
    graph.putIfAbsent(0,new ArrayList<>());
    graph.putIfAbsent(1,new ArrayList<>());
    graph.get(0).add(1);
    graph.get(1).add(0); // 因为是无向图

    // 建边 0 - 2
    graph.putIfAbsent(2,new ArrayList<>());
    graph.get(0).add(2);
    graph.get(2).add(0);

    // 建边 1 - 3
    graph.putIfAbsent(3,new ArrayList<>());
    graph.get(1).add(3);
    graph.get(3).add(1);

    // {
    //   0: [1, 2],
    //   1: [0, 3],
    //   2: [0],
    //   3: [1]
    // }

    // 0 --> 1, 2
    // 1 --> 0, 3
    // 2 --> 0
    // 3 --> 1

    // ================= Adjacency matrix ==============
    //     0  1  2  3
    // 0 [ 0, 1, 1, 0 ]
    // 1 [ 1, 0, 0, 1 ]
    // 2 [ 1, 0, 0, 0 ]
    // 3 [ 0, 1, 0, 0 ]
    //每个 [i][j] = 1 表示 i 和 j 之间有边（是相连的）
    // ⚠️ 如果是无向图，这个矩阵一定是「对称」的：[i][j] == [j][i]

    int[][] matrix = new int[4][4]; // 4个节点

    // 0-1
    matrix[0][1]=1;matrix[1][0]=1;

    // 0-2
    matrix[0][2]=1;matrix[2][0]=1;

    // 1-3
    matrix[1][3]=1;matrix[3][1]=1;

    

    // ====================  一维的顶点编号 ✔️遍历 visited ===================
    /**
     * List dfs
     * @param node
     * @param graph
     */
    // 必须有的用来base
    Set<Integer> visited = new HashSet<>(); // 标记已访问

    public void dfs(int node, Map<Integer, List<Integer>> graph) {
        // base 

        // 必须
        if (visited.contains(node)) return; // 已访问就跳过

        // recursion
        visited.add(node); 
        System.out.println("访问:" + node);
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            dfs(neighbor, graph);
        }
    }

    /**
     * List bfs
     * @param start
     * @param graph
     */
    public void bfs(int start, Map<Integer, List<Integer>> graph) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            // poll
            int node = queue.poll();
            // act
            System.out.println("访问节点: " + node);
            // add
            for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    /**
     * Matrix dfs 
     */
    Set<Integer> visited = new HashSet<>(); // 标记已访问
    public void dfs(int start, int[][] graph) {
        // base
        if (visited.contains(start)) return;

        visited.add(start);

        System.out.println("已经访问" + start);

        // recursion
        // int[][] graph = {
        // 0  1  2  3
        // {0, 1, 1, 0}, // 0
        // {1, 0, 0, 1}, // 1
        // {1, 0, 0, 0}, // 2
        // {0, 1, 0, 0}  // 3
        // };
        for (int i = 0; i < graph.length; i++) {
            if (graph[start][i] == 1 && !visited.contains(i)) {
                dfs(i, graph);
            }
        }
    }

    /**
     * Matrix bfs
     */
    Set<Integer> visited = new HashSet<>();
    Queue<Integer> queue = new LinkedList<>();

    public void bfsMatrix(int[][] graph, int start) {
        // 起点：加进队列，标记已访问
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            // 取出当前正在访问的节点
            int node = queue.poll();
            System.out.println("访问节点: " + node);

            // 开始扫描这一整行，看还有没有邻居
            for (int i = 0; i < graph.length; i++) {
                // 如果有边 & 没访问过
                if (graph[node][i] == 1 && !visited.contains(i)) {
                    queue.add(i);
                    visited.add(i);
                }
            }
        }
    }

} 