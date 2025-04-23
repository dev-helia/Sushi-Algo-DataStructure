package Tree_BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
}

public class Solution {
    /**
     * bfs
     * 
     * @param root
     * TC: O(n) 每个节点访问一次
     * SC: O(n) 最坏情况下队列大小为一整层节点数
     */
    public void bfs(TreeNode root) {
        // edge
        if (root == null)
            return;

        // iteration

        // ❎: Queue<TreeNode> queue = new PriorityQueue<>();
        Queue<TreeNode> queue = new LinkedList<>(); // 用 LinkedList 实现队列
        queue.add(root);
        // ❎ while (root.left != null || root.right != null) {
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            System.out.println(curr.val);
            // 如果有左右孩子就加进队列
            if (curr.left != null) {
                queue.add(curr.left);
            }
            if (curr.right != null) {
                queue.add(curr.right);
            }
        }
    }

    public void bfs_layer(TreeNode root) {
        // edge
        if (root == null)
            return;
        //iteration
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                System.out.println(curr.val);
                if (curr.left != null)
                    queue.add(curr.left);
                if (curr.right != null) 
                    queue.add(curr.right);
            }
            
        }
    }

    // 层序
    public List<Integer> rightSideView(TreeNode root) {
       List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size(); // 当前层的节点数量

            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();

                // ✅ 每层的最后一个节点加入结果
                if (i == size - 1) {
                    res.add(curr.val);
                }

                // 加入下一层节点
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
        }

        return res;
    }

    public List<Integer> leftSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size(); // 每一层的节点数量

            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();

                // ✅ 每层的第一个节点加入结果
                if (i == 0) {
                    res.add(curr.val);
                }

                // 加入左右子节点
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
        }

        return res;
    }

}

