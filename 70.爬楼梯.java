/**
 * 动态规划: 实际上是Fibonaci数列
 * f(n) = f(n-1) + f(n-2)
 * n曾楼梯，有f(n+1)种方法
 */
class Solution70_1 {
    public int climbStairs(int n) {
        int prev2 = 0, prev1 = 0, cur = 1;
        for (int i = 1; i <= n; i++) {
            prev2 = prev1;
            prev1 = cur;
            cur = prev2 + prev1;
        }
        return cur;
    }
}


/**
 * 数列递归公式转化为矩阵乘法
 * [[f(n+1)], [f(n)]] = ([[1,1], [1, 0]])^n * [[f(1)], [f(0)]]
 * https://leetcode-cn.com/problems/climbing-stairs/solution/pa-lou-ti-by-leetcode-solution/
 */
class Solution70_2 {
    public int climbStairs(int n) {
        int [][] basic = {{1}, {0}};   // [[f(1)], [f(0)]]
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = multiply(pow(q, n), basic);
        return res[0][0];
    }

    /**
     * 矩阵快速幂 O(logN)
     */
    public int[][] pow(int[][] a, int n) {
        int[][] result = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                result = multiply(result, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return result;
    }

    /**
     * 矩阵乘法
     */
    private int[][] multiply(int[][] a, int[][] b) {
        if (a[0].length != b.length) { throw new IllegalArgumentException(); }
        int[][] result = new int[a.length][b[0].length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                for (int k = 0; k < a[i].length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution70_2().climbStairs(15));
    }
}

/**
 * Fibonaci数列通项公式
 */
class Solution70_3 {
    public int climbStairs(int n) {
        double sqrt5 = Math.sqrt(5);
        double fibn = Math.pow((1 + sqrt5) / 2, n + 1) - Math.pow((1 - sqrt5) / 2, n + 1);
        return (int)(fibn / sqrt5);
    }
}
