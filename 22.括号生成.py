class Solution1:
    def generateParenthesis(self, n: int) -> list:
        """
        DFS回溯: 以二叉树状形式递归遍历所有可能组合，
        （1）当剩余的左括号 > 0，则可以产生一个左分支 temp + “("
        （2）当剩余的左括号小于右括号（右括号必须与已有右左括号匹配），则可以产生一个右分支 temp + ")
        （3）当无剩余括号，则到达树的底部，每一个叶子节点就是一个组合
        """
        result = []
        def getNext(left, right, temp):
            if left == 0 and right == 0:
                result.append(temp)
                return
            if left > 0:
                getNext(left - 1, right, temp + "(")
            if left < right:
                getNext(left, right - 1, temp + ")")

        getNext(n, n, "")
        return result

class Solution2:
    """
    动态规划：状态转移方程
    dp表的每个dp[i]包含i对括号的所有可能组合
    dp[i] = "(" + dp[j] + “)" + dp[i - j - 1]  j = 0,1, ... i-1
    注意，每个j都会产生 len(dp[j]) * dp[i - j - 1]个组合
    """
    def generateParenthesis(self, n: int) -> list:
        dp = [None] * (n + 1)  
        dp[0] = [""]
        for i in range(1, n + 1):
            dp[i] = []
            for j in range(i):
                left = dp[j]
                right = dp[i - j - 1]
                for s1 in left:
                    for s2 in right:
                        dp[i].append("(" + s1 + ")" + s2)
        return dp[n]