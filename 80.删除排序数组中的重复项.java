/**
 * https://github.com/azl397985856/leetcode/blob/master/problems/80.remove-duplicates-from-sorted-array-ii.md
 */
class Solution80 {
    public int removeDuplicates(int[] nums) {
        int i = 0;  // 写指针
        // 读指针
        for (int j = 0; j < nums.length; j++) {  
            // 仅当写指针在前2位，或者读指针的值与写指针的前2位不同时，才会进行写操作（由于是排序数组，所以不可能出现[1,2,1]）
            // 这样就避免了连续写入3个相同项目
            if (i < 2 || nums[j] != nums[i - 2]) {
                nums[i++] = nums[j]; // 进行写操作后，写指针才会前移
            }
        }
        return i;
    }

    public static void main(String[] args) {
        int[] test = new int[] {0,0,1,1,1,1,2,3,3};
        new Solution80().removeDuplicates(test);
    }
}