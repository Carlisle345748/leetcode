/**
 * 遍历数组，维护一个表示当前可达到的最远位置的变量maxPos。如果遍历的当前位置i小于maxPos，则说明当前位置不可达，返回false。
 * 如果maxPos >= nums.length - 1，表面可以到达数组的末尾，则返回true。
 */
class Solution55 {
    public boolean canJump(int[] nums) {
        int maxPos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (maxPos < i) { return false; }                // 当前位置不可达
            if (maxPos >= nums.length - 1) { return true; }  // 已经可以到达数组末端
            maxPos = Math.max(maxPos, i + nums[i]);
        }
        return true;
    }
}