import java.util.List;
import java.util.ArrayList;

/**
 * 回溯法
 *        [1]         [2]     [3]
 *    [1,2] [1,3]  [2,3]
 * [1,2,3]  
 */
class Solution78_1 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        dfs(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void dfs(int[] nums, int begin, ArrayList<Integer> subset, List<List<Integer>> result) {
        for (int i = begin; i < nums.length; i++) {
            // 取一个数字，加入子集
            subset.add(nums[i]);
            // 由于需要求所有子集，所以立刻加入结果集合
            result.add(new ArrayList<>(subset));
            // 下一层递归，begin位置 + 1
            dfs(nums, i + 1, subset, result);
            // 恢复初始状态
            subset.remove(subset.size() - 1);
        }
    }
}

/**
 * 迭代：开始假设输出子集为空，每一步都向子集添加新的整数，并生成新的子集。
 * []
 * [] [1]
 * [] [1] [2] [1,2]
 * [] [1] [2] [1,2] [3] [1,3] [2,3] [1,2,3]
 */
class Solution78_2 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for (int num : nums) {
            // 存放这一代新增加的子集的临时List
            List<List<Integer>> temp = new ArrayList<>();
            // 遍历上一代的所有子集
            for (List<Integer> list : result) {
                // 上一代子集都加入新的数字，就可以形成新的子集
                ArrayList<Integer> newsubset = new ArrayList<>(list);
                newsubset.add(num);
                temp.add(newsubset);
            }
            // 将这一代新产生的子集加入结果集合
            result.addAll(temp);
        }
        return result;
    }
}

/**
 * 用位表示不同的子集
 * 假设有3个数字，有2^3 = 8个子集。对于1个子集，1个数字是否存在子集中可以用一个位来表示，0表示不在子集中，1表示在子集中
 * 用位表示所有的子集：
 * 0000 0001 0010 0011 0100 0101 0110 0111
 * 0-7的二进制表示刚好可以表示所有这些子集。然后通过位移和掩码可以判断每个位是否打开，即每个数字是在子集中，从而求得8个子集
 */
class Solution78_3 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < (1 << nums.length); i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if (((i >> j) & 1) == 1) { temp.add(nums[j]); }
            }
            result.add(temp);
        }
        return result;
    }
}
