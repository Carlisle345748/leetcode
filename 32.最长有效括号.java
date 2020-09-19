import java.util.LinkedList;

class Solution32_1 {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() <= 1) { return 0; }

        int[] dp = new int[s.length()];
        int max = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '(') { dp[i] = 0; }
            else {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = 2 + (i > 2 ? dp[i - 2] : 0);
                }
                else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + 2;
                    dp[i] += (i - dp[i - 1] - 2) > 0 ? dp[i - dp[i - 1] - 2] : 0;
                }
                else { dp[i] = 0; }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}

class Solution32_2 {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() <= 1) { return 0; }
        LinkedList<Integer> stack = new LinkedList<>();
        int max = 0;
        stack.push(-1);
        for(int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            }
            else {
                stack.pop();
                if (stack.isEmpty()) { stack.push(i); }
                else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }
}

class Solution32_3 {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() <= 1) { return 0; }

        int max = 0;
        int right = 0, left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') { left++; }
            if (s.charAt(i) == ')') { right++; }
            if (left == right)      { max = Math.max(max, left * 2); }
            if (right > left)       { left = right = 0; }
        }

        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') { left++; }
            if (s.charAt(i) == ')') { right++; }
            if (left == right)      { max = Math.max(max, left * 2); }
            if (right < left)       { left = right = 0; }
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Solution32_1().longestValidParentheses("(()()(())(()()"));
        System.out.println(new Solution32_2().longestValidParentheses("(()()(())(()()"));
        System.out.println(new Solution32_3().longestValidParentheses("(()()(())(()()"));
    }
}