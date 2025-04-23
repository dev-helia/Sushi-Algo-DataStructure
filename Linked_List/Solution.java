package Linked_List;
// SLL without sentinel


class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
        next = null;
    }
}

public class Solution {
    /**
     * Reverse the head ListNode using iterative method.
     * @param head the head ListNode
     * @return new ListNode
     * TC:O(n)
     * SC:O(n)
     * ⭐️:[(null,)1,2,3,4,null] flip the arrow
     */
    public ListNode revserseListIterate(ListNode head) {
        // edge case
        if (head == null || head.next == null) {
            return head;
        }
        // main logic
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next; // ✅
            curr.next = prev; // ✅
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /**
     * Reverse the head ListNode using recursive method.
     * @param head the head ListNode
     * @return new ListNode
     * TC:O(n)
     * SC:O(n)
     * ⭐️: [(null,)1,2,3,4,null]
     */
    public ListNode revserseListRecurse(ListNode head) {
        // base case
        if (head == null || head.next == null) {
            return head;
        }
        // recursion body
        ListNode newHead = revserseListRecurse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * Find the middle ListNode given one head.
     * @param head the head ListNode
     * @return the middle ListNode
     * TC:O(n)
     * SC:O(n)
     * [1,2,3,4,4]
     */
    public ListNode findMiddle(ListNode head) {
        // edge case
        if (head == null || head.next == null) {
            return head;
        }

        // ieteration body
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) { // ⚠️ 这里可能会导致空指针异常
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * Delete the middle head.
     * @param head the head ListNode
     * @return the new ListNode
     * TC:O(n)
     * SC:O(1)
     * [1,2,3] -> [1,3]
     * [1,2,3,4] -> [1,2,4]
     */
    public ListNode deleteMiddle(ListNode head) {
        // edge case
        if (head == null || head.next == null) {
            return null;
        }
        // iteration
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        prev.next = prev.next.next;
        return head;
    }

    /**
     * Restruct the head ListNode.
     * @param head the head ListNode
     * @return new ListNode
     * TC:O(n)
     * SC:O(n)
     * [1,2,3,4,5] -> [1,3,5,2,4]
     */
    public ListNode oddEvenList(ListNode head) {
        // edge case
        if (head == null || head.next == null)
            return head;
        // iteration
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;

        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            odd = odd.next;

            even.next = even.next.next;
            even = even.next;
        }

        odd.next = evenHead;
        return head;
    }
    
    /**
     * The maximum of the pair sum.(pair:one head one tail)
     * Even nodes.
     * @param head the head ListNode
     * @return the maximum of the pair sum
     * TC:O()
     * SC:O()
     * [1,2,4,9] -> 10
     */
    public int maxPairSum(ListNode head) {
        // edge
        if (head == null)
            return 0;
        // iteration
        int maxPairSum = 0; // values are all positive 
        // find the middle -> slow/slow.next
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode secondHalfHead = slow.next;
        // reverse 
        ListNode curr = secondHalfHead.next;
        ListNode prev = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            curr = prev;
            curr = next;
        }
        // pair sum and compare
        ListNode firstHalfHead = head;
        while (secondHalfHead != null) {
            maxPairSum = Math.max(firstHalfHead.val + secondHalfHead.val, maxPairSum);
            firstHalfHead = firstHalfHead.next;
            secondHalfHead = secondHalfHead.next;
        }
        return maxPairSum;
    }
}