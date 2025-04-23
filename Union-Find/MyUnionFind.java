package Union-Find;


//数字1-n
public class MyUnionFind {
    int[] parent;
    // 用于优化记录
    int[] rank;

    public MyUnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        //  意义：初始化时，每个节点自己就是一棵独立的树（集合）
        //指向自己
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            // 每个人都是一棵“小树”，一开始只有自己，所以树高就是 1！
            rank[i] = 1;
        }
    }

    // 找x的母节点 递归
    public int find(int x) {
        // recursion
        if (parent[x] != x) {
            //路径压缩（find 中那句 parent[x] = find(parent[x])）
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // 合并两集合
    public void union(int x, int y) {
        int rootX = find(x);//x的根节点
        int rootY = find(y);//y的根节点
        // edge
        if (rootX == rootY)
            return;

        // 按秩合并
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
}



class DynamicUnionFind<T> {
    private Map<T, T> parent = new HashMap<>();
    private Map<T, Integer> rank = new HashMap<>();

    // 动态添加元素
    public void add(T x) {
        if (!parent.containsKey(x)) {
            parent.put(x, x);      // 自己是自己的妈
            rank.put(x, 1);        // 初始秩为1
        }
    }

    // 找祖宗
    public T find(T x) {
        if (!parent.containsKey(x)) add(x); // 如果没见过就注册
        if (!x.equals(parent.get(x))) {
            parent.put(x, find(parent.get(x))); // 路径压缩
        }
        return parent.get(x);
    }

    // 合并
    public void union(T x, T y) {
        T rootX = find(x);
        T rootY = find(y);
        if (rootX.equals(rootY)) return;

        int rankX = rank.get(rootX);
        int rankY = rank.get(rootY);

        if (rankX < rankY) {
            parent.put(rootX, rootY);
        } else if (rankX > rankY) {
            parent.put(rootY, rootX);
        } else {
            parent.put(rootY, rootX);
            rank.put(rootX, rankX + 1);
        }
    }
}
