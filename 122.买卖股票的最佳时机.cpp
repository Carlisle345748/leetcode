#include <stdio.h>
int maxProfit(int* prices, int pricesSize);

int main(void)
{
    int test[6] = {3,2,1};
    printf("%d\n", maxProfit(test, 3));
    return 0;
}
 
/*
峰谷法：
股票的价格-时间曲线中有高峰和低谷，或者是连续的上升或降低。我们可以
求出每个相邻低谷和高峰（必须先有低谷后有高峰）的差值。如果我们试图跳
过其中一个峰值来获取更多利润，那么我们最终将失去其中一笔交易中获得的
利润，从而导致总利润的降低。因此，所有相邻低谷和高峰的差值综合就是我
们能获得的最大利润。
https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/mai-mai-gu-piao-de-zui-jia-shi-ji-ii-by-leetcode/
*/
int maxProfit(int* prices, int pricesSize){
    int low = prices[0];
    int high = prices[0];
    int profit = 0;
    int i = 0;
    while (i < pricesSize - 1)
    {
        while (i < pricesSize - 1 && prices[i] >= prices[i+1]) // 先确保有低故，然后买入
            i++;
        low = prices[i];
        while (i < pricesSize - 1 && prices[i] <= prices[i+1]) // 寻找高峰，然后卖出
            i++;
        high = prices[i];
        profit += high - low;
    }
    return profit;
}


/*
峰谷法的简化版。假设每次股票涨价的卖出股票，卖出后（假设我们能预知未来），
如果明天股票价格降低了就不买回来，而如果明天升价了就立刻买回来，我们就能
获得最大的利润。这个利润就是每次股票行涨价的总和（没有降价的损失）。
https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/mai-mai-gu-piao-de-zui-jia-shi-ji-ii-by-leetcode/
*/
// int maxProfit(int* prices, int pricesSize){
//     int i;
//     int profit;
//     for (i = 1, profit = 0; i < pricesSize; i++)
//     {
//         if (prices[i] - prices[i-1] > 0)
//             profit += prices[i] - prices[i-1];
//     }
//     return profit;
// }