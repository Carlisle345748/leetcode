/**
 * 动态规划 - 使用柱状图的优化暴力方法，O(MN^2)
 * dp[i][j]为第i行中，第j个格子前的连续的'1'的数量
 * 比如：{'1','0','1','1','1'}，dp[i] = [1,0,1,2,3]
 * 然后，对于dp[i][j]，可以计算以dp[i][j]为右下角的最大矩形。
 * 遍历dp[k][j]，k= i, i-1, i-2, ...0，记录当前的最小值，即为矩形的宽度，高度为i - k + 1
 * 然后计算矩形面积，然后记录最大矩阵的面积
 * https://leetcode-cn.com/problems/maximal-rectangle/solution/zui-da-ju-xing-by-leetcode/
 */
class Solution85_1 {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) { return 0; }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') { continue; }
                dp[i][j] = j == 0 ? 1 :dp[i][j-1] + 1;  // 计算连续的'1'的数量
                int minWidth = dp[i][j]; // 矩形的宽度
                for (int k = i; k >= 0 && dp[k][j] > 0; k--) {
                    minWidth = Math.min(minWidth, dp[k][j]);     // 更新宽度，宽度只可以更小，不可以变大
                    max = Math.max(max, minWidth * (i - k + 1)); // 计算面积
                }
            }
        }
        return max;
    }
}

/**
 * 单调栈：利用84.柱状图中的最大矩形中的算法，可以解决该问题 O(NM)
 * 求matrix[0:i] i = 1,2,3...n中的最大矩形，可以看作一个子问题，解决所有子问题题就解决了目标问题
 * 对于1个子问题的matrix，一列中从底向上连续的'1'的数量可以看作柱状图中一个柱子的高度，从而可以转化为84题的问题
 * 然后套用84题的单调栈算法，计算出柱状图中的最大矩形，得到子问题的解。解决所有子问题，就可以得到最大矩形。
 */
class Solution85_2 {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) { return 0; }
        int n = matrix[0].length;
        int max = 0;
        Deque<Integer> stack = new LinkedList<>();
        int[] heights = new int[n + 1];  // 加入尾哨兵
        for (char[] row : matrix) {
            // 进入84题解法，解决子问题
            stack.push(-1);  // 头哨兵节点
            for (int j = 0; j < heights.length; j++) {
                // 更新heights
                if (j < n) {
                    heights[j] = row[j] == '0' ? 0 : heights[j] + 1;
                }
                // 单调栈计算每个包含柱子j，以柱子j高度为高度的最大矩形
                while (stack.getFirst() != -1 && heights[stack.getFirst()] >= heights[j]) {
                    int height = heights[stack.pop()];
                    int left = stack.getFirst();
                    int right = j;
                    max = Math.max(max, (right - left - 1) * height);
                }
                stack.push(j);
            }
            // 相比84题，由于要多次重复利用stack，所以每解决一个子问题，就清理栈(栈中还存存有头尾哨兵节点)
            stack.clear();
        }
        return max;
    }

}

/**
 * 动态规划：同样转化为84题的子问题，但是采用动态规划，利用当前子问题的解求下个子问题的解 O(NM)
 * 使用84题中的解法3，计算每个柱子i的左边界leftMin[i]和右边界rightMin[i]
 * 已知子问题 matrix[0:k]的leftMin和rightMin，要求解matrix[0:k+1]
 * 计算左边界，在第k+1行，对于柱子i，如果0到leftMin[i]之间，在j处出现了'0'，则leftMin[i]更新为j，否则维持不变
 * 同理，右边界则寻找rightMin[i]到n之间的'0'，如果找到了就更新rightMin[i]
 * 然后根据新的leftMin和rightMin计算子问题matrix[0:k+1]的最大矩形
 * 解决所有子问题则得到最终的最大矩形
 */
class Solution85_3 {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) { return 0; }
        int n = matrix[0].length;
        int max = 0;
        int[] heights = new int[n];
        int[] leftMin = new int[n];
        int[] rightMin = new int[n];
        Arrays.fill(leftMin, -1);
        Arrays.fill(rightMin, n);
        for (char[] row : matrix) {
            // 更新heights
            for (int i = 0; i < n; i++) {
                heights[i] = row[i] == '0' ? 0 : heights[i] + 1;
            }

            // 计算左边界
            int boundary = -1;
            for (int i = 0; i < n; i++) {
                if (row[i] == '1') {
                    // 如果boundary大于leftMin[i]，则更新左边界
                    leftMin[i] = Math.max(boundary, leftMin[i]);
                }
                else {
                    leftMin[i] = -1; // 初始化为-1
                    boundary = i;    // 更新边界
                }
            }

            // 计算右边界
            boundary = n;
            for (int i = n - 1; i >= 0; i--) {
                if (row[i] == '1') {
                    // 如果boundary小于rightMin[i]，则更新左边界
                    rightMin[i] = Math.min(boundary, rightMin[i]);
                }
                else {
                    rightMin[i] = n; // 初始化为n
                    boundary = i;    // 更新边界
                }
            }

            // 计算最大矩形
            for (int i = 0; i < n; i++) {
                max = Math.max(max, (rightMin[i] - leftMin[i] - 1) * heights[i]);
            }

        }
        return max;
    }

    public static void main(String[] args) {
        char[][] test = new char[][] {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        System.out.println(new Solution85_1().maximalRectangle(test));
        System.out.println(new Solution85_2().maximalRectangle(test));
        System.out.println(new Solution85_3().maximalRectangle(test));
    }
5