package Tree_DFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// dfs -> recursion ->inorder/preorder/postorder
class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
}

public class Solution {
    /**
     * Print the tree through inorder.
     * @param root the tree 
     * TC: O(n)
     * SC: O(h)
     */
    public void printInorder(TreeNode root) {
        if (root == null)
            return;
        printInorder(root.left);
        System.out.println(root.val);
        printInorder(root.right);
    }

    /**
     * Print the tree through preorder.
     * @param root the tree
     * TC:O(n)
     * SC: O(h)
     */
    public void printPreorder(TreeNode root) {
        if (root == null)
            return;
        System.out.println(root.val);
        printPreorder(root.left);
        printPreorder(root.right);
    }

    /**
     * 
     * Prinr the tree through postorder.
     * @param root the tree
     * TC:O(n)
     * SC: O(h)
     */
    public void printPostorder(TreeNode root) {
        if (root == null)
            return;
        printPostorder(root.left);
        printPostorder(root.right);
        System.out.println(root.val);
    }


    /**
     * Path Sum I
     * Given the root of a binary tree and an integer targetSum, 
             * return true if the tree has a root-to-leaf path 
             * such that adding up all the values along the path equals targetSum.
     * /**
        * Definition for a binary tree node.
        * public class TreeNode {
        *     int val;
        *     TreeNode left;
        *     TreeNode right;
        *     TreeNode() {}
        *     TreeNode(int val) { this.val = val; }
        *     TreeNode(int val, TreeNode left, TreeNode right) {
        *         this.val = val;
        *         this.left = left;
        *         this.right = right;
        *     }
        * }
     * @param root
     * @param targetSum
     * @return
     * TC: O(n)
     * SC: O(h)
     * -1000 <= Node.val <= 1000
     * -1000 <= targetSum <= 1000
     * 逻辑上是后序遍历
     */
    public boolean pathSum_one(TreeNode root, int targetSum) {
        if (root == null)
            return false;
        // 出错:
        // 🧡 如果是叶子节点，并且它的值正好等于 targetSum
        if (root.left == null && root.right == null && root.val == targetSum) {
            return true;
        }
        return pathSum_one(root.left, targetSum - root.val) || pathSum_one(root.right, targetSum - root.val);
    }

    /**
     * Path Sum II
     * Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node
     * values in the path equals targetSum. Each path should be returned as a list of the node values, not node
     * references.A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node
     * with no children.
     * @param root
     * @param targetSum
     * @return
     * TC: O(n)
     * SC: O(h)
     * [5] -> [[5]]
     * ❓ 共享了list?
     * ❓ 回溯要删除?
     */
    public List<List<Integer>> pathSum_two(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> l = new ArrayList<>();
        dfs(root, targetSum, l, res);
        return res;
    }

    // 同一个l的引用
    // 同一个res的引用
    // l 是共享引用，list 变了，res 里的内容也会变
    // 你走完一条路径后没“擦掉”，导致路径越来越长
    // 后序 分开
    private void dfs(TreeNode root, int targetSum, List<Integer> l, List<List<Integer>> res) {
        // base case
        if (root == null) {
            return;
        }
        l.add(root.val);
        if (root.left == null && root.right == null && root.val == targetSum) {
            // 以前的错误: res.add(l);
            // 不然以前的也会变?
            // 会返回 [[5,4,11,7,2,8,13,4,5,1],[5,4,11,7,2,8,13,4,5,1]]
            res.add(new ArrayList<>(l));
        }
        // l = [5, 4, 11, 2, 7]
        // l = [5, 4, 11, 2, 7, 8, 13, 4, 1] → ✅又到叶子节点！sum 满足！
        // recursion
        dfs(root.right, targetSum - root.val, l, res);
        dfs(root.left, targetSum - root.val, l, res);
        // 以前没有 必须要有 不然的话一直变长 擦掉这一层的一个
        // 换路就要删除
        l.remove(l.size() - 1);
    }



    /**
     * Path Sum III
     * The path does not need to start or end 
     * at the root or a leaf, but it must go downwards 
     * (i.e., traveling only from parent nodes to child nodes).
     * @param root
     * @param targetSum
     * @return
     * 从每一个节点开始都要有一个list
     */

    // 暴力递归 n^2 “是不是从我出发的某段往下路径刚好加起来是 8？”
    //外层是“从每个节点出发尝试作为起点”的递归
    //第一层递归会以每个节点为起点：10、5、3、2、-3、11
    public int pathSum_three_1(TreeNode root, int targetSum) {
        if (root == null)
            return 0;
        return pathSum_three_1(root.left, targetSum) + pathSum_three_1(root.right, targetSum)
                + countCurrent(root, targetSum);
    }

    //第二层递归会从该节点向下尝试找「路径和为 8」的所有组合
    private int countCurrent(TreeNode root, int targetSum) {
        if (root == null)
            return 0;
        if (targetSum == root.val)
            return 1;
        return countCurrent(root.left, targetSum - root.val) + countCurrent(root.right, targetSum - root.val) ;
    }
    
    int count = 0;

    // hashmap + prefix sum
    // 后序 然后需要remove本层
    public int pathSum_three_2(TreeNode root, int targetSum) {
        // 记录出现次数
        Map<Long, Integer> prefix_map = HashMap<>();
        dfs(root, targetSum, 0, prefix_map);
        return count;
    }

    private void dfs(TreeNode root, int targetSum, int prefix_sum, Map<Long, Integer> prefix_map) {
        // base 
        if (root == null) return;
        // add map
        prefix_map.put(prefix_sum, prefix_map.getOrDefault(prefix_sum, 0) + 1);
        if (prefix_map.contains(prefix_sum - targetSum)) {
            count += prefix_map.getOrDefault(prefix_sum - targetSum, 0);
        }
        // recursion
        dfs(root.right, targetSum, prefix_sum, prefix_map);
        dfs(root.left, targetSum, prefix_sum, prefix_map);

        // 回溯：当前路径退出前，移除当前前缀和出现次数
        // 换路就要删除
        prefix_map.put(currSum, prefix_map.get(currSum) - 1);
    }


    /**
     * Find the maximum depth of the tree.
     * 
     * @param root the root of the tree
     * @return the maximum depth of the tree
     * TC:O(n)
     * SC:O(h)
     */
    public int maxDepth(TreeNode root) {
        // base 
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;
        // recursion
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * Find the lowest lca.
     * 
     * @param root the root of the tree
     * @param p one node
     * @param q one node
     * @return the lowest common ancestor of p and q
     * TC:O(n)
     * SC:O(h)
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base
        if (root == null)
            return null;
        if (root == p || root == q)
            return root;
        // recursion
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null)
            return root;
        return left != null ? left : right;
    }
    
    /**
     * Check if two binary trees are leaf-similar.
     * 
     * @param root1 the root of the first tree
     * @param root2 the root of the second tree
     * @return true if the two binary trees are leaf-similar, false otherwise
     * TC:O(n)
     * SC:O(h)
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        // base
        if (root1 == null && root2 == null)
            return true;
        // recursion
        List<TreeNode> leaves1 = new ArrayList<>();
        List<TreeNode> leaves2 = new ArrayList<>();
        getLeaves(root1, leaves1);
        getLeaves(root2, leaves2);
        return leaves1.equals(root2);
    }

    /**
     * Find the sum of all root-to-leaf numbers.
     * 
     * @param root the root of the tree
     * @return the sum of all root-to-leaf numbers
     * TC:O(n)
     */
    private void getLeaves(TreeNode root, List<TreeNode> leaves) {
        // base
        if (root == null)
            return;
        if (root.left == null && root.right == null) {
            leaves.add(root);
            return;
        }
        // recursion
        getLeaves(root.left, leaves);
        getLeaves(root.right, leaves);
    }

    /**
     * Find the longest zigzag path in a binary tree.(from root)
     * 
     * @param root the root of the tree
     * @return the length of the longest zigzag path in the tree
     * TC: O(n)
     * SC: O(h)
     */
    public int longestZigZag_from_Root(TreeNode root) {
        // base
        if (root == null)
            return 0;

        // recursion
        // dir = 0: go right 
        // dir = 1: go left 
        return Math.max(recursiveZigZag(root, 0), recursiveZigZag(root, 1));
    }

    private int recursiveZigZag(TreeNode root, int dir) {
        // base
        if (root == null)
            return 0;
        if (dir == 0)
            return recursiveZigZag(root.right, 1);
        else
            return recursiveZigZag(root.left, 0);
    }
    

    // global
    private int maxZigzag = 0;

    /**
     * Find the longest zigzag path in a binary tree.(Every node.)
     * 
     * @param root the root of the tree
     * @return the length of the longest zigzag path in the tree
     * TC: O(n)
     * SC: O(h)
     */
    public int longestZigZag(TreeNode root) {
        dfs(root);
        return maxZigzag;
    }


    private void dfs(TreeNode node) {
        if (node == null)
            return;

        // 从当前节点出发，尝试两个方向
        maxZigzag = Math.max(maxZigzag, recursiveZigZag(node.left, 0));
        maxZigzag = Math.max(maxZigzag, recursiveZigZag(node.right, 1));

        // 向下递归
        dfs(node.left);
        dfs(node.right);
    }
    
    int goodCount = 0;

    public int goodNodes(TreeNode root) {
        dfs(root, Integer.MIN_VALUE);
        return goodCount;
    }
    
    private void dfs(TreeNode root, int max) {
        // base
        if (root == null)
            return;
        // recursion
        if (root.val >= max)
            goodCount++;
        max = Math.max(max, root.val);
        dfs(root.left, max);
        dfs(root.right, max);

    }
}
