package algo.Binary_Search;

/**
 * int[] nums
 * must be in order (ascending sorted)
 */
public class Solution {
    /**
     * Search the target number's index in the integer array.
     * Return -1 if the number doesn't exist.
     * 
     * @param nums the integer array
     * @param target the target number 
     * @return -1 if the number doesn't exist, index otherwise
     * TC: O(logn)
     * SC: O(1)
     * [1,3,4,5] 4 => 2
     * [1,3,4,5] 2 => -1
     */
    public int binarySearchAccurate_iterative(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) { // must include 
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[mid] < target)
                left = mid + 1;
            if (nums[mid] > target)
                right = mid - 1;
        }
        return -1;
    }

    /**
     * Find the least bigger number (compared with the target number) in the array.
     * 
     * @param nums the integer array
     * @param target the target number
     * @return the least bigger number 如果没有，就返回 nums.length（越界），你可以检查这个情况。
     * TC: O(logn)
     * SC: O(1)
     * [1,3,5,7,9], target = 6 => 3 num[3] = 7
     */
    public int lowerBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int result = -1; // 初始化为没找到

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] >= target) {
                result = mid;      // 记录可能的结果
                right = mid - 1;   // 往左缩，看有没有更小的满足条件
            } else {
                left = mid + 1;
            }
        }

        return result; // 返回的是 index，nums[result] 是值
    }

    
    /**
     * Find the most smaller number (compared with the target number) in the array.
     * 
     * @param nums the integer array
     * @param target the target number
     * @return the most smaller number
     * TC: O(logn)
     * SC: O(1)
     * [1,3,4,5,6] 2 => 2
     */
    public int predecessor(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int result = -1; // 没有比 target 小的值

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                result = mid;      // 记录当前最好的答案
                left = mid + 1;    // 往右找更大的小数
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

}
