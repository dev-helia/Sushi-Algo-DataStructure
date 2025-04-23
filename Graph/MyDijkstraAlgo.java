package Graph;

import java.util.*;

// 对象名	表达含义	用于	举例
// Edge	从一个点通向某个邻居 + 权重	图的邻接表结构	new Edge('D', 3) 表示去 D 花费3
// Pair 当前某个点+距离（用于比较大小）最小堆中排队 new Pair('C',5)表示点 C 当前距离5
class Edge {
    // 🟧 graph.put('B', List.of(new Edge('C', 2), new Edge('D', 3)));
    char target;
    int weight;

    public Edge(char target, int weight) {
        this.target = target;
        this.weight = weight;
    }
}

// 🟧 意义? 存进 MinHeap 来选“目前距离最短的点”  “To: C，Current Distance: 5”
class Pair {
    char node;
    int distance;

    public Pair(char node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}

public class MyDijkstraAlgo {
    // 🟧 dist不是int[]吗
    // 图里点是 Character 类型（A, B, C...），所以用 Map 比 int[] 更灵活！因为点名是字母（char），不能直接当数组下标；而 Map<Character, Integer> 可以表示任意字符节点。
    // 最终的“从起点到每个点的最短距离”，全部保存在 dist 这个 Map 里！
    Map<Character, Integer> dist;
    PriorityQueue<Pair> pq;

    public MyDijkstraAlgo(Map<Character, List<Edge>> graph) {
        dist = new HashMap<>();

        for (char node : graph.keySet()) {
            // if (node != 'A') {
            //     dist.put(node, Integer.MAX_VALUE);
            // }
            // 🟧 可以直接放, 后面会覆盖掉
            dist.put(node, Integer.MAX_VALUE);
        }
        dist.put('A', 0);
    }

    /**
     * Relaxation. 
     * BFS.
     * 
     * @param graph
     * @param start
     * tc:
     * sc:
     */
    public void run(Map<Character, List<Edge>> graph, char start) {
        // 初始化最小堆
        // 🟧 为什么这样写
        // 等效写法 pq = new PriorityQueue<>((a, b) -> a.distance - b.distance);

        
        pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.distance));

        pq.add(new Pair(start, 0));

        // iteration
        while (!pq.isEmpty()) {
            // queue.poll
            // 🟧 为什么这就是最有希望的点?
            // 你不停从堆里拿出「当前最短距离的点」，探索它的邻居。 
            // 那最小的不一直都是0 start吗?
            // 📢 A只会进堆一次，也只会被处理一次！
            Pair current = pq.poll();
            char currNode = current.node;
            int currDist = current.distance;

            // 剪枝
            if (currDist > dist.get(currNode))
                continue;

            // for (Edge edge : graph.get(currNode)) {
            //     ...
            //     if (更短路径被发现) {
            //         更新 dist
            //         加入堆等待处理
            //     }
            // }            
            // 🟧 遍历这个点的所有邻居
            for (Edge edge : graph.get(currNode)) {
                char neighbor = edge.target;
                int weight = edge.weight;
                // 🟧 relaxation
                if (dist.get(currNode) + weight < dist.get(neighbor)) {
                    dist.put(neighbor, dist.get(currNode) + weight);
                    pq.add(new Pair(neighbor, dist.get(neighbor)));
                }
            }
        }

    }
}
