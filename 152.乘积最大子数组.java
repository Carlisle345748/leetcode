/**
 * 动态规划
 */
class Solution152 {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) { return 0; }

        int currentMax = nums[0];
        int currentMin = nums[0];
        int max = nums[0];

        for (int i = 1; i < nums.length;i++) {
            // 用最大值和最小值的副本来计算，否则第一步修改了最大值会影响最小值的计算
            int tempMin = currentMin, tempMax = currentMax;
            // max(当前数字，当前数字*最大连续乘积(正数*正数)，当前数字*最小连续乘积(负数*负数))
            currentMax = Math.max(nums[i], Math.max(nums[i]*tempMax, nums[i]*tempMin));
            // min(当前数字，当前数字*最大连续乘积(负数*正数)，当前数字*最小连续乘积(正数*负数))
            currentMin = Math.min(nums[i], Math.min(nums[i]*tempMax, nums[i]*tempMin));
            // 更新最大值
            max = Math.max(currentMax, max);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] test = {-1, -2, -9, -6};
        System.out.println(new Solution152().maxProduct(test));
    }
}
