import java.util.*;

/**
 * 回溯法：使用标记数组，标记已经使用过的数字
 * https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
 */
class Solution46_1 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // 建立一个标记数组，用来标记哪些数字用过了
        boolean[] flag = new boolean[nums.length];
        dfs(flag, nums, new ArrayList<>(), result);
        return result;
    }

    private void dfs(boolean[] flag, int[] nums, ArrayList<Integer> path, List<List<Integer>> result) {
        for (int i = 0; i < flag.length; i++)
        {
            // 如果这个数字没被使用过
            if (!flag[i]) {
                // 加入路径
                path.add(nums[i]);
                // 标记数字被使用过
                flag[i] = true;
                if (path.size() == flag.length) {
                    // 如果达到已经填满，则将path加入result
                    result.add(new ArrayList<>(path));
                }
                else {
                    // 否则继续搜索分支搜索
                    dfs(flag, nums, path, result);
                }
                // 恢复初始状态
                flag[i] = false;
                path.remove(path.size()-1);
            }
        }
    }
}

/**
 * 回溯法：不实用标记数组，设待填入新数字的位置为first：
 * 将数组分区为[0, first - 1]为使用过的数字，[first, n-1]是未使用过的数字
 * https://leetcode-cn.com/problems/permutations/solution/quan-pai-lie-by-leetcode-solution-2/
 */
class Solution46_2 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // 将数组转化为ArrayList，方便swap和通过new ArrayList直接拷贝一份加入resutl
        ArrayList<Integer> path = new ArrayList<>(nums.length);
        for (int i : nums) { path.add(i); }
        dfs(path, result, nums.length,0);
        return result;
    }

    private void dfs(ArrayList<Integer> path, List<List<Integer>> result, int size, int first) {
        // 如果待填入位置已经达到末端，则将path加入result
        if (first == size)
        {
            result.add(new ArrayList<>(path));
            return;
        }
        // [first, n-1]都是未使用过的数字
        for (int i = first; i < size; i++)
        {
            // 如果填入nums[i]，则交换first和i
            Collections.swap(path, i, first);
            // 搜索下一层，first+1
            dfs(path, result, size,first + 1);
            // 恢复初始状态
            Collections.swap(path, i, first);
        }
    }
}