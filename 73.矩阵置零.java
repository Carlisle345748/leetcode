import java.util.Arrays;

/**
 * 使用第一行和第一列记录当前行和列最后是否应该全是0
 * https://github.com/azl397985856/leetcode/blob/master/problems/73.set-matrix-zeroes.md
 */
class Solution73 {
    public void setZeroes(int[][] matrix) {
        // 标记第一行中本来是否含有0，如果本来有0，那么第一行最后应该全部设为0
        boolean FirstRowZero = false;

        // 遍历整个matrix
        for (int i = 0; i < matrix[0].length; i++) {
            // 判断第一行本来是否有0
            if (matrix[0][i] == 0) { FirstRowZero = true; }
            // 遍历每一列，如果当前元素为0，则对应的第一行和第一列位置设为0
            // 注意，如果第一列中有0，则第一行第一列元素会被设为0，从而在根
            // 据行遍历赋零时可以正确的处理第一列
            for (int j = 1; j < matrix.length; j++) {
                if (matrix[j][i] == 0) {
                    matrix[j][0] = 0;
                    matrix[0][i] = 0;
                }
            }
        }

        // 注意，一定要先遍历第一列，否则如果第一列中本来有0，在遍历第一行时会把整个第一列都设为0，丢失所有信息
        // 遍历第一列，如果当前位置元素为0，则整一行都应该为0
        // 注意这里是j=1开始，由于遍历第一行的时候是从0开始，可以包括第一列的第一个元素，
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                Arrays.fill(matrix[i], 0);
            }
        }

        // 遍历第一行，如果当前位置元素为0，则整一列都应该为0
        // 这里可以顺便判断第一列是否应该全是0，因为如果第一列元素中有0，第一列第一行元素被设为0
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 0; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

        // 如果第一行有本来就有0，则第一行设为0
        if (FirstRowZero) {
            Arrays.fill(matrix[0], 0);
        }
    }
}