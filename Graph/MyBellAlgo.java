package Graph;

import java.util.*;


// 同上 不用求意义
class Edge {
    char target;
    int weight;

    public Edge(char target, int weight) {
        this.target = target;
        this.weight = weight;
    }
}

class Pair {
    char node;
    int distance;

    public Pair(char node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}

public class MyBellAlgo {
    Map<Character, Integer> dist;

    // 用图先初始化dist
    public MyBellAlgo(Map<Character, List<Edge>> graph) {
        dist = new HashMap<>();

        for (char node : graph.keySet()) {
            dist.put(node, Integer.MAX_VALUE);
        }
        dist.put('A', 0);
    }

    public void run(Map<Character, List<Edge>> graph, char start) {
        // 不用queue 全部遍历一次 就不是bfs
        // v - 1 次 松弛
        // 🟧 为什么是graph.size()
        //  graph.size() 就是节点个数 V
        for (int i = 0; i < graph.size() - 1; i++) {
            for (char u : graph.keySet()) {
                for (Edge edge : graph.get(u)) {
                    char v = edge.target;
                    int weight = edge.weight;

                    if (dist.get(u) != Integer.MAX_VALUE && dist.get(u) + weight < dist.get(v)) {
                        dist.put(v, dist.get(u) + weight);
                    }
                }
            }
        } 
    }

}
