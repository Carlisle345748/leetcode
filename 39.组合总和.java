import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

class Solution39 {
    /**
     * 回溯法，递归+剪枝
     * https://leetcode-cn.com/problems/combination-sum/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-2/
     * @param candidates 待选数字
     * @param target 目标和
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>(); 
        // 预先排序，便于排除重复解
        Arrays.sort(candidates);
        searchPath(candidates, target, new LinkedList<Integer>(), 0, result);
        return result;
    }

    private void searchPath(int[] candidates, int target, LinkedList<Integer> path, int begin, List<List<Integer>> result) {
        for (int i = begin; i < candidates.length; i++) {
            int leftNum = target - candidates[i];
            // 如果leftNum < 0，说明加上新数字总和超出target
            if (leftNum >= 0) {
                // 往路径加入新数字
                path.addLast(candidates[i]);
                // 如果leftNum == 0，说明刚好达到目标，将当前路径加入结果集合
                if (leftNum == 0) { result.add(new ArrayList<>(path)); }
                // 如果leftNum  > 0，说明还可以加入新数字，则继续递归添加新数字
                // 注意：为了去除重复解，添加的新数字必须大于等于candidata[i]，
                // 比如[3,2,2]与[2,2,3]重复，规定添加新数字大于等于candidata[i]
                // 可避免重复。因此，在搜索前进行排序，此处设置beign = i。
                else { searchPath(candidates, leftNum, path, i, result); }
                // 检验其他数字之前要去除刚刚添加的数字
                path.removeLast();
            }
        } 
    }
}