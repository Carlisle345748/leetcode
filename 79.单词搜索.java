/**
 * 回溯搜索
 * https://leetcode-cn.com/problems/word-search/solution/zai-er-wei-ping-mian-shang-shi-yong-hui-su-fa-pyth/
 */
class Solution79 {
    private boolean[][] marked;
    private char[][] board;
    private String word;


    public boolean exist(char[][] board, String word) {
        this.marked = new boolean[board.length][board[0].length];
        this.board = board;
        this.word = word;
        boolean isExist = false;

        // 注意循环终止的条件包括isExist = true
        for (int i = 0; i < board.length && !isExist; i++) {
            for (int j = 0; j < board[0].length && !isExist; j++) {
                // 从匹配单词第一个字符的位置开始搜索
                if (board[i][j] == word.charAt(0)) {
                    isExist = dfs(0, i, j);
                }
            }
        }
        return isExist;
    }

    private boolean dfs(int count, int i, int j) {
        // 找到完整单词了，返回true
        if (count == word.length()) {
            return true;
        }
        // 检查是否超出范围，或者是否已经走过
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || marked[i][j]) { return false; }

        // 如果当前位置的字母是单词的下一个字母，则从当前位置继续搜索，否则跳过当前位置
        if (word.charAt(count) == board[i][j]) {
            marked[i][j] = true;
            // 向四个方向搜索
            if (dfs(count + 1, i - 1, j)) { return true; }
            if (dfs(count + 1, i + 1, j)) { return true; }
            if (dfs(count + 1, i, j + 1)) { return true; }
            if (dfs(count + 1, i, j - 1)) { return true; }
            marked[i][j] = false;  // 记得恢复初始状态
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] test = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        System.out.println(new Solution79().exist(test, "ABCCED"));
    }
}