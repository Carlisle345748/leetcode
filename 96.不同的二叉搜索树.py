class Solution1:
    """
    当有n个节点，则有n-1个节点作为子树节点，根节点的两个子树的节点数有以下组合:
    (0, n-1), (1, n-2), (2, n-3) .... (n-3, 2), (n-2, 1), (n-1, 0)
    两个子树可以看作独立的树，可计算出组合数，左右子树的组合数相乘得到n个节点二叉树的组合数。
    由此可得到动态规划状态转移方程: dp[i] = dp[0]*dp[i-1] + dp[1]*dp[i-2] .... dp[n-1]*dp[0]
    
    时间复杂度O(n^2) 空间复杂度O(n)
    """
    def numTrees(self, n: int) -> int:
        dp = [0] * (n + 1)
        for i in range(n + 1):
            if i <= 1: dp[i] = 1
            else:
                for j in range(i):
                    dp[i] += dp[j] * dp[i-j-1]
        return dp[n]

class Solution2:
    """
    事实上，上述状态转移方程形成的数列是卡塔兰数(Catlant)，卡塔兰数的递归公式为:
    C0 = 1, Cn+1 = (4n + 2) / (n + 2) * Cn
    因此可以以O(n)时间复杂度和O(1)空间复杂度完成计算
    """
    def numTrees(self, n: int) -> int:
        catlant = 1
        for i in range(1, n):
            catlant = catlant * (4 * i + 2) / (i + 2)
        return int(catlant)

s1 = Solution1()
s2 = Solution2()
print(s1.numTrees(4))
print(s2.numTrees(4))