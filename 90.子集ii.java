import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 回溯法，与78题同理，增加去除重复值的步骤。
 */
class Solution90_1 {
    private List<List<Integer>> result;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        result = new ArrayList<>();
        result.add(new ArrayList<>());
        dfs(0, nums, new ArrayList<Integer>());
        return result;
    }

    private void dfs(int begin, int[] nums, List<Integer> path) {
        for (int i = begin; i < nums.length; i++) {
            // 如果发现连续尝试添加了2个相同数字，则剪枝
            if (i > begin && nums[i] == nums[i - 1]) { continue; }
            path.add(nums[i]);
            result.add(new ArrayList<>(path));
            dfs(i+ 1, nums, path);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        new Solution90_1().subsetsWithDup(new int[] {1,2,2});
    }
}

/**
 * https://leetcode-cn.com/problems/subsets-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-19/
 * 迭代法，与78题同理。一次添加一个数字，在上一步的结果集中加上新数字，但是加上去除重复值的步骤。
 * 由于存在重复数字，当发现当前处理的数字和上一个是相同数字，则当前数字只添加到上一步新增加的子集中
 * （在其他子集中加2次相同数字会产生重复）
 */
class Solution90_2 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> prev = new ArrayList<>();
        result.add(new ArrayList<>());

        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> temp = new ArrayList<>();
            // 如果是不重复数字，则在result中所有子集加上新数字
            // 如果是重复数字，则仅在上一步产生的新子集中加上新数字（在其他子集中加2次相同数字会产生重复）
            List<List<Integer>> target = (i > 0 && nums[i] == nums[i-1]) ? prev : result;
            for (List<Integer> list : target) {
                List<Integer> newSubset = new ArrayList<>(list);
                newSubset.add(nums[i]);
                temp.add(newSubset);
            }
            result.addAll(temp);
            prev = temp;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution90_2().subsetsWithDup(new int[]{1, 2, 2}));
    }
}

/**
 * https://leetcode-cn.com/problems/subsets-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-19/
 * 位运算法，与78题同理，添加去除重复值的步骤
 * 由于存在重复数字，不同的位组合可能代表相同的子集，对于重复子集的位组合，我们只取重复数字最先出现且连续出现的位组合，例如
 * 2 2 2 2 2
 * 1 1 0 0 0 -> [  2 2       ]
 * 1 0 1 0 0 -> [  2 2       ]
 * 0 1 1 0 0 -> [  2 2       ]
 * 0 1 0 1 0 -> [  2 2       ]
 * 0 0 0 1 1 -> [  2 2       ]
 * 我们只取一个个组合。判断的方法为：出现重复数字时，检查第一个重复数字的位是否为0，如果为0则舍去当前位组合。
 */
class Solution90_3 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int j = 0; j < (1 << nums.length); j++) {
            boolean isValid = true; // 是否为重复子集
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if ((j >> i & 1) == 1) {
                    // 检查是否是重复子集。
                    if (i > 0 && nums[i] == nums[i-1] && (j >> (i-1) & 1) == 0) {
                        isValid = false;
                        break;
                    }
                    temp.add(nums[i]);
                }
            }
            if (isValid) { result.add(temp); }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution90_3().subsetsWithDup(new int[]{1, 2, 2}));
    }
}