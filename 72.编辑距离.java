/**
 * 动态规划
 * https://leetcode-cn.com/problems/edit-distance/solution/bian-ji-ju-chi-by-leetcode-solution/
 */
class Solution72 {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        // 如果其中一个单词为0，则直接采用全部删除/插入就可以完成
        if (m == 0 || n == 0) { return m + n; }

        int[][] dp = new int[m + 1][n + 1];
        // word1转化成空字符串，只需要删除
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        // 空字符串转化为word2，只需要插入
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 如果word1[i] == word2[j],此时插入操作的消耗为dp[i-1][j-1]
                // 重点：由于相邻格子之间只相差一个字符，所以操作数量的差距肯定<=1，只需0步或1步
                // 所以，dp[i-1][j-1]与相邻的dp[i-1][j]和dp[i][j-1]最多只差1
                // 因此，三个格子中的最小值，最多比dp[i-1][j-1]小1
                // 所以min(dp[i-1][j-1], dp[i-1][j] + 1, dp[i][j-1] + 1)的值肯定等于dp[i-1][j-1]
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i-1][j-1];
                }
                // 对比替换、插入、删除操作，选择操作次数最小的一种
                else {
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
                }
            }
        }
        return dp[m][n];
    }
}