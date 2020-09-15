/**
 * Topological Algorithm + 动态规划
 * 因为只能向下和向右走，这个矩阵组成的图是一个非循环图，可以求出Topological order. 而且可以观察
 * 得只要从上到下，从左到右遍历，就满足Topological order。因此可以转化为动态规划问题，而且不需要
 * 额外空间。直接把原矩阵看作distTo矩阵。从上到下，从左到右更新，每次只需要加上：
 *                     Math.min(grid[i-1][j], grid[i][j-1])
 * 即到上边界和左边界中距离原点更近的距离。在图论中，relax是以一个点为基点relax与其相邻的边，但是这
 * 里可以改变思路，由于从上到下，从左到右遍历，指向当前节点的节点(上，左)都在遍历到当前节点之前就确定
 * 了，所以可以直接取其中距离更小的节点（即取最优的relax结果）。
 */
class Solution64 {
    public int minPathSum(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if      (i == 0 && j == 0) { continue; }
                else if (j == 0)           { grid[i][j] += grid[i-1][j]; }
                else if (i == 0)           { grid[i][j] += grid[i][j-1]; }
                else                       { grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]); }
            }
        }
        return grid[grid.length-1][grid[0].length-1];
    }
}
