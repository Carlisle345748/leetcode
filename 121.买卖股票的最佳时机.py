"""
假如计划在第 i 天卖出股票，那么最大利润的差值一定是在[0, i-1] 之间选最
低点买入；所以遍历数组，依次求每个卖出时机的的最大差值，再从中取最大值。

这一个解法是由动态规划的思想演化而来的，动态规划的状态转移方程为：
dp[i] = max(dp[i-1], price-min_price)
在第0～i天范围内，最大的利润是“0～i-1天范围内的最大利润”和“第i天价格-历史最低价”中最大值
上述方程形成的 DP table 可以简化成维护一个max_profit和min_price，构成当前的解法
"""
class Solution:
    def maxProfit(self, prices: list) -> int:
        max_profit = 0
        min_price = 1e9
        for price in prices:
            if price < min_price:
                min_price = price
            max_profit = max(max_profit, price - min_price)
                
        return max_profit


solution = Solution()
print(solution.maxProfit([3,3,5,0,0,3,1,4]))