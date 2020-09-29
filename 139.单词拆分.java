import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * 自己想的一种另类的动态规划 用一个List记录可作为分割点的字符的index。 从左到右检查子符串的所有字符，设当前位置为i
 * 从右到左遍历List，设当前List的元素为start，判断s.substring(start, i)是否位于字典中 如果在字典中，说明从0 ～ i
 * 都是满足条件的。因为0 ～ start满足条件，而且s.substring(start, i)也位于字典中
 */
class Solution139_1 {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return false;
        }

        int maxLength = 0;
        HashSet<String> wordHashDict = new HashSet<>(wordDict.size());
        for (String word : wordDict) {
            maxLength = Math.max(maxLength, word.length());
            wordHashDict.add(word);
        }


        LinkedList<Integer> candidate = new LinkedList<>();
        candidate.add(0);
        for (int i = 1; i <= s.length(); i++) {
            for (int start : candidate) {
                if (i - start > maxLength) { break; }
                if (wordHashDict.contains(s.substring(start, i))) {
                    candidate.addFirst(i);
                    break;
                }
            }
        }
        return candidate.getFirst() == s.length();
    }
}

/**
 * 动态规划
 * dp[i] 表示 0～i-1的子字符串是否满足条件(位于字典中或拆分后单词位于字典中)
 * 状态转移方程：
 * 求dp[i]，则要遍历j = 0,1,2....i-1，j作为分割点
 * 如果存在dp[j] && j ~ i - 1的子字符串位于字典中，则dp[i] = true
 * 因为dp[j]满足条件，而且s.substring(j, i)位于字典中，所以dp[i]也满足条件
 * */
class Solution139_2 {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return false;
        }

        int maxLength = 0;
        HashSet<String> wordHashDict = new HashSet<>(wordDict.size());
        for (String word : wordDict) {
            maxLength = Math.max(maxLength, word.length());
            wordHashDict.add(word);
        }

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= 0 && i - j <= maxLength; j--) {
                if (dp[j] && wordHashDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }
}

/**
 * DFS+记忆化
 * "leetcode"能否break，可以拆分为："l"是否是单词表的单词、剩余子串能否break，"le"是否是单词表的单词、剩余子串能否break……
 * 用 DFS 回溯，考察所有的拆分可能，指针从左往右扫描 s 串：
 * 如果指针的左侧部分是单词表中的单词，则对右侧的剩余子串，递归考察。
 * 如果指针的左侧部分不是单词表的单词，不用看了，回溯，考察别的分支。
 *
 * https://leetcode-cn.com/problems/word-break/solution/shou-hui-tu-jie-san-chong-fang-fa-dfs-bfs-dong-tai/
 */
class Solution139_3 {
    HashSet<String> wordHashDict;  // 用HashSet代替List用于查询
    int maxLength;                 // 字典中最长单词的长度
    Boolean[] memo;                // 记忆递归结果，避免重复运算

    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return false;
        }

        // 初始化memo，memo[s.length()] = true 是start = s.length()时查询的值
        // 此时递归已经到达最后一个字符，字符串已经满足要求了
        memo = new Boolean[s.length()+1];
        memo[s.length()] = true;

        // 初始化wordHashDict，顺便记录最长单词长度
        wordHashDict = new HashSet<>(wordDict.size());
        for (String word : wordDict) {
            maxLength = Math.max(maxLength, word.length());
            wordHashDict.add(word);
        }

        return dfs(s, 0);
    }

    private boolean dfs(String s, int start) {
        // 如果已经算过就直接提取结果
        if (memo[start] != null) { return memo[start]; }

        // 如果start～i这个左侧子字符串在字典中，则递归检查 i~s.length() 这个右侧子串是否满足要求
        // 由于最长单词长度为maxLength，所以左侧字符串长度不用超过maxLength
        for (int i = start; i <= s.length() && i - start <= maxLength; i++) {
            if (wordHashDict.contains(s.substring(start, i))) {
                if (memo[start] = dfs(s, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution139_3().wordBreak("leetcode", Arrays.asList("leet", "code")));
    }
}