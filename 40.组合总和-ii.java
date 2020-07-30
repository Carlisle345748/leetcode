import java.util.*;

class Solution40 {
    /**
     * 回溯法：递归+剪枝
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // 排序以保证相同元素都在一起，同时避免重复解（题39）
        Arrays.sort(candidates);
        dfs(new LinkedList<>(), target, candidates, 0, result);
        return result;
    }

    private void dfs(LinkedList<Integer> path, int target, int[] candidates, int begin, List<List<Integer>> result) {
        for (int i = begin; i < candidates.length; i++) {
            // 可以让同一层级，不出现相同的元素。而不同层级，可以出现相同元素
            // begin的数字肯定是下一层级的第一个元素。从begin+1开始，就是
            // 同一层级的元素。i > begin && candidates[i] == candidates[i-1]
            // 保证了开始新层级的时候不检查重复，而同层级时检查重复
            if (i > begin && candidates[i] == candidates[i-1]) { 
                continue; 
            }
            // 过滤总和超出target的分支
            int leftSum = target - candidates[i];
            if (leftSum >= 0) {
                // 推入新元素
                path.addLast(candidates[i]);
                // 总和刚好等于target，加入结果集
                if (leftSum == 0) {
                    result.add(new ArrayList<>(path));
                }
                else {
                    dfs(path, leftSum, candidates, i + 1, result);
                }
                // 推出刚刚添加的新元素，恢复初始状态
                path.removeLast();
            }
        }
    }
}