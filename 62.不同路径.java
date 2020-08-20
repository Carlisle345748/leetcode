import java.util.Arrays;

/**
 * 动态规划
 * dp[i][j] = dp[i-1][j] + dp[i][j-1]
 */
class Solution62_1 {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 由于只能向下和向右走，第一行和第一列只有1种走法
                if (i == 0 || j == 0) { dp[i][j] = 1; }
                else {
                    // 到达当前格子的走法数 = 到达左边格子的走法数 + 到达上面格子的走法数
                    dp[i][j] = dp[i][j-1] + dp[i-1][j];
                }
            }
        }
        return dp[n-1][m-1];
    }
}

/**
 * 优化空间，O(2n)
 */
class Solution62_2 {
    public int uniquePaths(int m, int n) {
        // 由于只需记录上面的格子和左边的格子，所以可以简化
        // 只记录左侧列和当前列
        int[] leftColumn = new int[n];
        int[] curColumn = new int[n];
        Arrays.fill(leftColumn, 1);
        Arrays.fill(curColumn, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 当前格子 = 左侧格子 + 上方格子
                curColumn[j] = leftColumn[j] + curColumn[j-1];
            }
            leftColumn = curColumn.clone();
        }
        return curColumn[n-1];
    }
}

/**
 * 优化空间，O(n)
 */
class Solution62_3 {
    public int uniquePaths(int m, int n) {
        // 在上一个优化上更进一步
        // 由于是从左到右按列计算，所以到下一列时，未更新的值就是当前列的左侧列的值
        int[] curColumn = new int[n];
        Arrays.fill(curColumn, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // curColumn[j]实际是左侧格子的值， curColumn[j-1]是上方格子的值
                curColumn[j] += curColumn[j-1];
            }
        }
        return curColumn[n-1];
    }
}


/* Python 因为Java做阶乘要避免超过Long的范围，比较麻烦 
   https://leetcode-cn.com/problems/unique-paths/solution/zhe-dao-gao-zhong-de-pai-lie-zu-he-ti-wo-xiang-ni-/
   由于机器人从开始到起点总是要走(m - 1) + (n - 1)步，其中(m - 1)步向右，(n - 1)步向下
   因此只用计算从(n + m - 2)步选(m - 1)步向右排列组合，即C (m - 1) (n + m -2)
*/
// class Solution:
//     def uniquePaths(self, m: int, n: int) -> int:
//         return int(math.factorial(n + m - 2) / (math.factorial(m - 1) * math.factorial(n - 1)))