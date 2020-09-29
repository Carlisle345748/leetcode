class Solution34 {
    public int[] searchRange(int[] nums, int target) {
        int left = binarySearchLeft(nums, target);
        if (left == -1) {
            return new int[] {-1, -1};
        }
        int right = binarySearchRight(nums, target);
        return new int[] {left, right};

    }

    // 二分查找寻找左侧边界
    private int binarySearchLeft(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 2);
            // 找到目标值的时候，继续向左查找
            if (nums[mid] == target) {
                hi = mid - 1;
            }
            else if (nums[mid] > target) {
                hi = mid - 1;
            }
            else if (nums[mid] < target) {
                lo = mid + 1;
            }
        }
        // 若目标值大于数组中所有元素，lo会发生数组越界，要加上判断
        if (lo < nums.length && nums[lo] == target) {
            return lo;
        }
        return -1;
    }

    private int binarySearchRight(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 2);
            // 找到目标值的时候，继续向右查找
            if (nums[mid] == target) {
                lo = mid + 1;
            }
            else if (nums[mid] > target) {
                hi = mid - 1;
            }
            else if (nums[mid] < target) {
                lo = mid + 1;
            }
        }
        // 若目标值小于数组中所有元素，hi会发生数组越界，要加上判断
        // 实际上hi >= 0 可有可无，因为hi越界的值也是-1
        if (hi >= 0 && nums[hi] == target) {
            return hi;
        }
        return -1;
    }
}