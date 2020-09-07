/**
 * 动态规划
 * https://github.com/azl397985856/leetcode/blob/master/problems/91.decode-ways.md
 */
class Solution91 {
    public int numDecodings(String s) {
        if (s.length() == 0 || s.charAt(0) == '0') { return 0; }
        int prev2 = 1, prev1 = 1, cur = 0;
        for (int i = 1; i < s.length(); i++) {
            // 当前位的数字
            int one = 10 + s.charAt(i) - '0';
            // 当前位与上一位组成的2位数
            int two = s.charAt(i-1) - '0' * 10 + one;

            // 如果 10 <= two <= 26，则two是有效2位数，这个两位数可以与前i-2位的数字组合，cur += prev2
            // 如果 one != 0, 则one是有效数，可以与前i-1位组合, cur += prev1
            cur = 0;
            if (two >= 10 && two <= 26 ) { cur += prev2; }
            if (one != 0) { cur += prev1; }

            // 记录前i-1和前i-2位的有效组合数
            prev2 = prev1;
            prev1 = cur;
        }
        return cur;
    }
}