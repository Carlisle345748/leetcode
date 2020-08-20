/**
 * 荷兰国旗问题，快速排序中的Three-way partition使用的方法
 */
class Solution75 {
    public void sortColors(int[] nums) {
        int pRed = 0, pUnknown = 0;
        int pBlue = nums.length - 1;
        while (pUnknown <= pBlue) {
            // 当前指针指向红色，则当前元素与红色指针指向的元素交换
            if (nums[pUnknown] == 0) {
                swap(nums, pUnknown, pRed);
                pRed++;
                pUnknown++;
            }
            // 当前指针指向白色，无需交换，直接指针+1
            else if (nums[pUnknown] == 1) {
                pUnknown++;
            } 
            // 当前指针指向蓝色，则当前元素与蓝色指针指向的元素交换
            // 注意此时当前指针不需+1，因为从蓝色指针处交换过来的元素还没检查
            else if (nums[pUnknown] == 2) {
                swap(nums, pUnknown,pBlue);
                pBlue--;
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}