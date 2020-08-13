import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class Solution49_1 {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> result = new HashMap<>();
        for (String s : strs) {
            // 转化为char[]以排序字符
            char[] temp = s.toCharArray();
            Arrays.sort(temp);
            // 重新转化为String
            String tempString = String.valueOf(temp);
            // HashMap记录结果
            if (!result.containsKey(tempString)) {
                result.put(tempString, new ArrayList<>());
            }
            result.get(tempString).add(s);
        }
        return new ArrayList<>(result.values());
    }
}

class Solution49_2 {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> result = new HashMap<>();
        for (String s : strs) {
            // 使用一个int数组标记出现过字母
            int[] count = new int[26];
            Arrays.fill(count, 0); // 初始化
            for (char c : s.toCharArray()) { count[c - 'a']++; }
            // 把int数组转化为一个字符串，用#分隔以避免混淆"11#1"和"1#11"
            StringBuilder stringBuilder = new StringBuilder();
            for (int i : count) {
                stringBuilder.append(i);
                stringBuilder.append("#");
            }
            String tempString = stringBuilder.toString();
            // HashMap记录结果
            if (!result.containsKey(tempString)) {
                result.put(tempString, new ArrayList<>());
            }
            result.get(tempString).add(s);
        }
        return new ArrayList<>(result.values());
    }
}