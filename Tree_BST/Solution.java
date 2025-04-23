package Tree_BST;

class TreeNode{
    public int val;
    public TreeNode left;
    public TreeNode right;
}

public class Solution {
    /**
     * Delete a node in a binary search tree.
     * @param root the root of the binary search tree
     * @param val the value of the node to be deleted
     * @return the root of the updated binary search tree
     * Time complexity: O(h), where h is the height of the tree.
     * Space complexity: O(1)
     */
    public TreeNode deleteNode(TreeNode root, int val) {
        if (val < root.val) {
            root.left = deleteNode(root.left, val);
        } else if (val > root.val) {
            root.right = deleteNode(root.right, val);
        } else {
            // 这时候找到了要删除的节点 root
            if (root.left == null && root.right == null)
                return null;
            if (root.left == null && root.right != null) {
                return root.right;
            }
            if (root.left != null && root.right == null) {
                return root.left;
            }
            TreeNode successor = findSuccessor(root.right); // 在右子树中找最小的
            root.val = successor.val; // 替换值
            root.right = deleteNode(root.right, successor.val); // 再从右子树删掉这个 successor
        }
        return root; // ✨最关键的闭环return！
    }
    
    private TreeNode findSuccessor(TreeNode root) {
        if (root == null)
            return root;
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    /**
     * 109. Convert Sorted List to Binary Search Tree
     * @param head
     * @return
     * TC
     * SC
     * Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height-balanced binary search tree.
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val); // only one node

        ListNode mid = findMiddle(head);

        TreeNode root = new TreeNode(mid.val);
        root.left = sortedListToBST(head);            // left half
        root.right = sortedListToBST(mid.next);       // right half

        return root;
    }


    // helper function to find the middle:
    private ListNode findMiddle(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // Cut the list to separate left part
        if (prev != null) {
            prev.next = null;
        }

        return slow; // This is the middle node
    }


}
