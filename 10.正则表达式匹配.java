/**
 * 动态规划实现简单功能的正则表达式匹配
 * 真正的正则表达式匹配用的是NFA
 */
class Solution10 {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) { return false; }
        char[] text = s.toCharArray();
        char[] pattern = p.toCharArray();
        // dp[i][j] 表示s[0:i+1]和[0:j+1]是否匹配
        boolean dp[][] = new boolean[s.length() + 1][p.length() + 1];
        // 初始条件
        dp[0][0] = true;
        for (int i = 0; i <= s.length(); i++) {
            // j从1开始，因为j=0时是无效pattern
            for (int j = 1; j <= p.length(); j++) {
                // 如果是不是*，则单字符匹配
                if (pattern[j - 1] != '*') {
                    if (match(text, pattern, i, j)) {
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
                // 如果当前是*的清空
                else {
                    // 如果和*前的字符匹配
                    if (match(text, pattern, i, j - 1)) {
                        dp[i][j] = dp[i][j-2] ||     // s[0:i] 和 p[0:j-2]匹配，则可以视为星号匹配0个
                                   dp[i-1][j];       // s[0:i-1] 和 p[0:j]匹配，可视为匹配多个，由于dp[i-1][j]依赖dp[i-2][j]
                    }                                // 最终将递归回退到匹配0个或中途发现不匹配
                    else { dp[i][j] = dp[i][j-2]; }  // s[0:i] 和 p[0:j-2]匹配，则可以视为星号匹配0个
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    private boolean match(char[] text, char[] pattern, int i, int j) {
        if (i == 0) { return false; }
        if (pattern[j - 1] == '.') { return true; }
        return pattern[j - 1] == text[i - 1];
    }
}