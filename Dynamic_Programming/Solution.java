class Solution {

    /**
     * 70. Climbing Stairs
     * 
     * @param n
     * @return
     * TC
     * SC
     */
    public int climbStairs(int n) {
        // edge
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;

        // ❎ int[] state = new int[n];
        // 🧧 我靠别忘了＋1 (要算n+1个因为0️⃣也算了啊)
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        // iteration
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 746. Min Cost Climbing Stairs
     * 
     * @param cost
     * @return
     * tc:
     * sc:
     * cost = [10,15,20,299]
     * dp = [0,10,10,]
     * I define dp[i] as the minimum cost to reach the i-th stair. Since we can start from step 0 or step 1 without cost, dp[0] = 0 and dp[1] = 0. Then for each step i ≥ 2, the transition is:dp[i] = min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2]) Finally, we return dp[n], which represents the minimum cost to go past the last stair.
     */
    public int minCostClimbingStairs(int[] cost) {
        // egde
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[1] = cost[0];
        dp[0] = cost[0];

        // iteration
        // c❎ for (int i = 2; i < n; i++) {
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }
    

    /**
     * 509. Fibonacci Number
     * @param n
     * @return
     */
    public int fib(int n) {
        // edge
        // 🧧 你忘记了return, 是你的return
        if (n == 0) return 0;
        if (n == 1) return 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[0] = 0;
        // ❎ dp[2] = 2;
        // iteration

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 198. House Robber
     * 
     * @param nums
     * @return
     * TC
     * SC
     * nums = [1,2,3,1]
     * dp = [0, 1, 2, 4]
     * dp[i] = max(dp[i-1], dp[i-2] + nums[i])
     * dp[i] represents the maximum amount of money that can be robbed from the first i houses, without robbing two adjacent houses.
     * The transition is:
     * dp[i] = max(dp[i - 1], dp[i - 2] + nums[i - 1])
     * dp[i - 1]: skip current house
     * dp[i - 2] + nums[i - 1]: rob current house and skip one before it
     * 
     * 🕓 Time Complexity: O(n)
    We traverse the nums array once and do constant-time operations at each step.
    🧠 Space Complexity: O(n)
    We use a dp array of size n + 1 to store intermediate results.
    If we optimize space using two variables, we can reduce it to O(1).

    I'm solving this with dynamic programming.
    dp[i] is the max money we can rob from the first i houses.
    At each step, we choose to either:

    skip the current house (dp[i-1]),
    
    or rob it (dp[i-2] + nums[i-1])
    
    Time complexity is O(n) and space is O(n) (or O(1) if we optimize with two variables).
    🍪 先看状态定义：dp[i] 是什么意思？
    我们在 House Robber 这道题里定义：
    
    dp[i] = 在 **前 i 个房子** 中，能偷到的最大金额
    ⚠️ 注意这句话！"前 i 个房子" 的意思是：
    房子编号是 0 开始的
    那 dp[1] 表示只考虑了第 0 号房子（第一个房子）
    dp[2] 表示考虑了第 0、1 两个房子
    所以 dp[i] 对应的是 nums[i - 1] 这个房子！ ← 这就是答案！！
     */
    public int rob(int[] nums) {
        // edge
        int n = nums.length;
        if (n == 0)
            return 0;
        if (n == 1) {
            return nums[0];
        }

        // iteration
        int[] dp = new int[n + 1];
        dp[1] = nums[0];
        dp[0] = 0;

        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[n];
    }

    /**
     * 53. Maximum Subarray
     * @param nums
     * @return
     * TC
     * SC
     * nums = [-2,1,-3,4,-1,2,1,-5,4]
     * dp = [0, -2, -1, 3, 2, 1, 0, -3, 4]
     *The key idea is to define dp[i] as the maximum subarray sum ending at index i.
     I’m using dynamic programming to solve this.
     The key idea is to define dp[i] as the maximum subarray sum ending at index i.
     The recurrence relation is:
     dp[i] = max(dp[i - 1] + nums[i], nums[i])
     This means we either:
     Extend the previous subarray, or
     Start fresh from nums[i]
     I also maintain a global maximum value while iterating.
      Time complexity is O(n) since we visit each element once
      Space complexity is O(1) if we use two variables
     */
    public int maxSubArray(int[] nums) {
        // edge
        int n = nums.length;
        if (n == 1)
            return nums[0];
        // iteration
        // 🧧 int[] dp = new int[n + 1];
        // dp[0] = 0;
        // dp[1] = nums[0]; // 0 - i-1
        int[] dp = new int[n];
        dp[0] = nums[0];

        int max = dp[0];

        // for (int i = 2; i <= n; i++) {
        for (int i = 1; i < n; i++) {

            // 🧧 dp[i] = Math.max(nums[i - 1], nums[i - 2] + nums[i - 1]);
            // dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
            dp[i] = Math.max(dp[i - 1] + nums[i], dp[i - 1]);
            max = Math.max(max, dp[i]);

        }
        // return dp[n];
        return max;
    }

    /**
     * Kadane's algo
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int currSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(maxSum, currSum);
        }

        return maxSum;
    }

}