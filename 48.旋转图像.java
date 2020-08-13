/**
 * 2次操作完成旋转90度：转置+翻转每一行。
 */
class Solution48_1 {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 转置
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // 翻转每一行
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }
    }
}

/**
 * 旋转4个矩阵
 * https://leetcode-cn.com/problems/rotate-image/solution/xuan-zhuan-tu-xiang-by-leetcode/
 */
class Solution48_2 {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 将矩阵分为4个角落的小矩阵，n为奇数时，矩阵中心不包含在4个小矩阵中
        // n为奇数时，矩阵中心在旋转中不会改变
        for (int i = 0; i < (n + 1) / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                // 对于小矩阵的每个元素matrix[i][j]，顺时针旋转90度后，其值变为matrix[n - j - 1][i]
                // 对与小矩阵中的每个元素，都要更新一次
                // 需要一个temp保存第一个元素避免覆盖后无法找回
                int temp = matrix[i][j];
                // 对于例子{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}
                matrix[i][j] = matrix[n - j - 1][i];                  // 1 变成 7
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];  // 7 变成 9
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];  // 9 变成 3
                matrix[j][n - i - 1] = temp;                          // 3 变成 1(保存在temp中)
            }
        }
    }
}

/**
 * 与方法2相同，只不过使用循环表示转移过程
 */
class Solution48_3 {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < (n + 1) / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                int row = i, col = j;
                int temp = matrix[row][col];
                // 循环3次
                for (int k = 0; k < 3; k++) {
                    matrix[row][col] = matrix[n - col - 1][row];
                    int oldRow = row;
                    row = n - col - 1;
                    col = oldRow;
                }
                // 最后一次使用temp
                matrix[row][col] = temp;
            }
        }
    }
}
