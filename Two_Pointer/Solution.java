package Two_Pointer;

public class Solution {
    /**
     * Put the zeroes in the end of the array.(in-place)
     * @param nums the array.
     * TC: O(n)
     * SC: O(1)
     * [0,1,2,0,9] - >[1,2,9,0,0]
     */
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        int slow = 0;
        // traversal
        for (int fast = 0; fast < n; fast++) {
            if (nums[fast] != 0) {
                int temp = nums[fast]
                nums[fast] = nums[slow];
                nums[slow] = temp;
                slow++;
            }
        }
    }

    /**
     * Given k flip chances(0 -> 1), find the maximum of the consecutive ones.
     * @param nums the integer array
     * @param k flip chances
     * @return the maximum of the consecutive ones
     * TC:O(n)
     * SC:O(1)
     * k = 1: [0,0,1,0,1] -> 3
     * greedy?
     * sliding window
     */
    public int longestOnes(int[] nums, int k) {
        // iteration
        int left = 0;
        int maxLength = 0;
        for (int right = 0; right < nums.length; right += 1) {
            if (nums[right] == 0) k--;
            while (k < 0) {
                if (nums[left] == 0) {
                    k++;
                }
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
            // right += 1
        }
        return maxLength;
    }

    /**
     * Give one flip chance, get the longest subarray where all elments are ones.
     * @param nums integer array
     * @return the maximum length of subarray
     * TC: O(n)
     * SC: O(1)
     * [1,0,0,1,1,1] -> 4
     */
    public int longestSubarray(int[] nums) {
        // iteration
        int left = 0;
        int longestLength = 0;
        int zeroCount = 0;
        for (int right = 0; right < nums.length; right += 1) {
            if (nums[right] == 0) {
                zeroCount += 1;
            }
            while (zeroCount > 1) {
                if (nums[left] == 0) zeroCount -= 1;
                left += 1;
            }
            longestLength = Math.max(longestLength, right - left + 1);
        }
        return longestLength;
    }

    /**
     * quick sort (keep the relative pos)
     * @param nums the integer array
     * @param pivot the pivot integer
     * @return nums (in-place)
     * TC:O(n)
     * SC:O(n)
     * [9,3,6,4,1,7,3,2,54] -> [1,2,3,3,9,6,4,7,54]
     */
    public int[] pivotArray(int[] nums, int pivot) {
        // edge
        // iteration
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            if (nums[i] < pivot) {
                res[left] = nums[i];
                left++;
            }
            if (nums[i] > pivot) {
                res[right] = nums[i];
                right--;
            }
        }
        while (left <= right) {
            nums[left] = pivot;
            left++;
            nums[right] = pivot;
            right--;
        }
        return res;
    }
}
