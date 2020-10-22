/**
 * 滑动窗口法
 * 使用数组代替HashMap
 * https://leetcode-cn.com/problems/minimum-window-substring/solution/hua-dong-chuang-kou-ji-bai-liao-100de-javayong-hu-/
 */
class Solution76 {
    public String minWindow(String s, String t) {
        if (s == null || t == null || t.length() == 0 || s.length() == 0) { return ""; }
        char[] text = s.toCharArray();
        int lt = 0, rt = 0;                 // 左指针、右指针
        int ansLeft = 0, ansRight = 0;      // 结果子串的左右边界
        int minLength = Integer.MAX_VALUE;  // 最短子串长度
        int count = 0;                      // 滑动窗口中包含t中字符的数量
        int[] original = new int[128];      // 记录t中每个字符的出现次数
        int[] current = new int[128];       // 记录当前滑动窗口中每个字符的出现次数

        // 计算t中每个字符的出现次数
        for (int i = 0; i < t.length(); i++) {
            original[t.charAt(i)]++;  
        }
        for (rt = 0; rt < text.length; rt++) {
            // 右指针向右拓展至滑动窗口中包含t中的所有字符
            if (original[text[rt]] > 0 && current[text[rt]] < original[text[rt]]) {
                // 每当t中的字符出现，而且出现次数不大于t中的出现次数(最大出现次数)， 则count加一
                count++;
            }
            // 记录字符的出现次数
            current[text[rt]]++;

            // 当滑动窗口中包含t中的所有字符，左指针向右收缩直到滑动窗口不包含t中的所有字符
            if (count == t.length()){
                while (count == t.length()) {
                    current[text[lt]]--; // 收缩
                    // 滑动窗口不包含t中的所有字符
                    if (current[text[lt]] < original[text[lt]]) {
                        count--;
                        // 更新最小覆盖字串
                        if (rt - lt + 1 < minLength) {
                            minLength = rt - lt + 1;
                            ansLeft = lt;
                            ansRight = rt;
                        }
                    }
                    lt++;
                }
            }
        }
        return minLength == Integer.MAX_VALUE ? "" :s.substring(ansLeft, ansRight + 1);
    }
}

/**
 * 滑动窗口法
 * 使用字典
 */
class Solution76_2 {
    public String minWindow(String s, String t) {
        if (s == null || t == null || t.length() == 0 || s.length() == 0) { return ""; }
        char[] text = s.toCharArray();
        int lt = 0, rt = -1;
        int ansLeft = 0, ansRight = 0;
        int minLength = Integer.MAX_VALUE;
        int count = 0;
        HashMap<Character, Integer> original = new HashMap<>();
        HashMap<Character, Integer> current = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            current.put(c, 0);
            original.put(c, original.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < text.length; i++) {
            if (count < t.length() && current.containsKey(text[i])) {
                if (current.get(text[i]) < original.get(text[i])) {
                    count++;
                }
                current.put(text[i], current.get(text[i]) + 1);
            }
            if (count == t.length()){
                rt = i;
                while (lt <= rt) {
                    if (current.containsKey(text[lt])) {
                        current.put(text[lt], current.get(text[lt]) - 1);
                        if (current.get(text[lt]) < original.get(text[lt])) {
                            count--;
                            break;
                        }
                    }
                    lt++;
                }
                if (rt - lt + 1 < minLength) {
                    minLength = rt - lt + 1;
                    ansLeft = lt;
                    ansRight = rt;
                }
                lt++;
            }
        }
        return rt == -1 ? "" :s.substring(ansLeft, ansRight + 1);
    }
}