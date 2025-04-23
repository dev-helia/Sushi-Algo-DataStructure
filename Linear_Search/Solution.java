package algo.Linear_Search;

public class Solution {
    public boolean linearSearch(int nums[], int target) {
        // iteration
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) return true;
        }
        return false;
    }
}
