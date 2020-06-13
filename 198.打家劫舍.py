"""
动态规划：在遇到前两家的时候可直接判断，当走到第3家开始：
由于不可以在相邻的房屋闯入，所以在当前位置 n 房屋可盗窃的最大值，
要么是 n-1 房屋可盗窃的最大值，要么就是 n-2 房屋可盗窃的最大值
加上当前房屋的值，二者之间取最大值。

状态转移方程为 dp[n] = max((dp[n-2] + nums[n]), dp[n-1])

"""

# 使用完整的DP table
class Solution:
    def rob(self, nums: List[int]) -> int:
        if not nums:
            return 0
        if len(nums) == 1:
            return nums[0]

        dp = [0] * len(nums)
        dp[0], dp[1] = nums[0], max(nums[0], nums[1])

        for i in range(2, len(nums)):
            dp[i] = max((dp[i-2] + nums[i]), dp[i-1])

        return dp[-1]

# 由于只用到了dp[i-1]和dp[i-2]，可以只保存这个两个值，节省空间
class Solution:
    def rob(self, nums: List[int]) -> int:
        if not nums:
            return 0
        if len(nums) == 1:
            return nums[0]

        prev1, prev2 = nums[0], max(nums[0], nums[1])
        for i in range(2, len(nums)):
            prev1, prev2 = prev2, max(prev1 + nums[i], prev2)
            
        return prev2