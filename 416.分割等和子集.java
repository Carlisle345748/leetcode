/**
 * 动态规划
 * dp[i][j]表示[0,i]区间内，是否存在一个子集的和为j
 * 状态转移方程：dp[i][j] = dp[i-1][j] || (j >= nums[i] && dp[i-1][j - nums[i]])
 * 对于nums[i]
 * 如果子集不包含nums[i]，则dp[i][j] = dp[i-1][j]
 * 如果子集包含nums[i]，则dp[i][j] = dp[i-1][j - nums[i]]，如果j < nums[i]则为false
 * 
 * https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/0-1-bei-bao-wen-ti-xiang-jie-zhen-dui-ben-ti-de-yo/
 */
class Solution416_1 {
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) { return false; }
        // 计算整个数字的和，并记录最大值
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) { return false; }     // 如果数组的和是奇数，不可能分成2个和相等的子集
        int target = sum / 2;
        if (maxNum > target) { return false; }  // 如果最大值大于和的一半，则不可能存在子集和为整个数组和的一半

        // dp[i][j]表示[0,i]区间内，是否存在一个子集的和为j
        boolean[][] dp = new boolean[nums.length][target + 1];
        // 第一个数字就是满足目标，则可以直接返回true
        if (nums[0] == target) { return true; }
        // dp[0][0]要设置为true，因为dp[i][0]都依赖dp[0][0]，而dp[i][0]都为true
        dp[0][0] = true;
        // dp[0][j]中，在满足maxNum <= target，dp[0][nums[0]]成立（即选择第一个数）
        dp[0][nums[0]] = true;
        for (int i = 1; i < nums.length;i++) {
            for (int j = 0; j <= target; j++) {
                // 对于nums[i]
                // 如果子集不包含nums[i]，则dp[i][j] = dp[i-1][j]
                // 如果子集包含nums[i]，则dp[i][j] = dp[i-1][j - nums[i]]，如果j < nums[i]则为false
                dp[i][j] = dp[i-1][j] || (j >= nums[i] && dp[i-1][j - nums[i]]);
            }
            // 如果dp[i][target]为true，则已经找到一个满足要求的子集了，可以直接返回true
            if (dp[i][target]) {
                return true;
            }
        }
        return dp[nums.length - 1][target];
    }

    public static void main(String[] args) {
        int[] test = new int[] {1,5,5,10};
        new Solution().canPartition(test);
    }
}

/**
 * 动态规划，优化空间
 */
class Solution416_2 {
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) { return false; }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) { return false; }
        int target = sum / 2;
        if (maxNum > target) { return false; }

        // 状态压缩，由于当前行只依赖它上面一行 「头顶上」 那个位置和「左上角」某个位置的值。
        // 因此，我们可以只开一个一维数组，从后向前依次填表即可。
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        dp[nums[0]] = true;
        for (int i = 1; i < nums.length; i++) {
            for (int j = target; j >= nums[i];j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
            if (dp[target]) { return true; }
        }
        return dp[target];
    }
}