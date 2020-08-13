import java.util.*;
class Solution47 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean flag[] = new boolean[nums.length];
        Arrays.sort(nums);
        backTrace(flag, nums, new ArrayList<>(), result);
        return result;
    }

    private void backTrace(boolean[] flag, int[] nums, ArrayList<Integer> path, List<List<Integer>> result) {
        for (int i = 0; i < nums.length; i++) {
            if (!flag[i]) {
                // 相对于46题，增加了一行去除重复
                // 排序过后，相同的数字都相邻。当上一个数字和这一个数字相同，有2种情况
                //（1）上一个数字flag[i-1] = true，说明上一个数字在上一个层级使用过，不需跳过i
                //（2）上一个数字flag[i-1] = false，说明上一个数字在当前层级使用过（同层回溯会恢复状态），要跳过i
                if (i > 0 && nums[i] == nums[i-1] && !flag[i-1]) continue;
                path.add(nums[i]);
                flag[i] = true;
                if (path.size() == nums.length) {
                    result.add(new ArrayList<>(path));
                }
                else {
                    backTrace(flag, nums, path, result);
                }
                path.remove(path.size() - 1);
                flag[i] = false;
            }
        }
    }
}