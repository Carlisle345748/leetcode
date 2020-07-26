class Solution1:
    """
    将数组切割成2部分，至少有一部分是有序的，如果target在有序的部分，则继续在有序部分中二分查找。
    若target不在有序的部分，则在包含逆转的部分二分查找。
    """
    def search(self, nums: list, target: int) -> int:
        if len(nums) == 0: return -1
        lo, hi = 0, len(nums) - 1
        while (lo <= hi):
            mid = int(lo + (hi - lo) / 2)
            if (nums[mid] == target):
                return mid
            if nums[lo] <= nums[mid]: 
                # 前半段有序 
                if nums[lo] <= target and target < nums[mid]:
                    hi = mid - 1
                else:   
                    lo = mid + 1
            else:
                # 后半段有序
                if nums[mid] < target and target <= nums[hi]:
                    lo = mid + 1
                else:
                    hi = mid - 1
        return -1

class Solution2:
    """
    https://leetcode-cn.com/problems/search-in-rotated-sorted-array/solution/ji-jian-solution-by-lukelee/
    nums[0] <= target <= nums[mid]                     (前半段无逆转且target在0-mid)
               target <= nums[mid] < nums[0]           (前半段有逆转，taget在逆转位置-mid)
                         nums[mid] < nums[0] <= target (前半段有逆转，target在0-逆转位置)
    以上三种情况下，target在前半段，其他情况则target在后半段
    nums[0] <= target、target <= nums[mid]、nums[mid] < nums[0]这三个条件，
    当且仅当其中有2个成立时（不可能三个都成立或都不成立），target在前半段。使用异或
    运算可以方便的找出当且仅当其中有2个成立时情况。(1 ^ 1 ^ 0 = 0, 1 ^ 0 ^ 0 = 1)
    """
    def search(self, nums: list, target: int) -> int:
        if len(nums) == 0: return -1
        lo, hi = 0, len(nums) - 1
        while (lo < hi):
            mid = int(lo + (hi - lo) / 2)
            if ((nums[lo] <= target) ^ (target <= nums[mid]) ^ (nums[mid] < nums[lo])):
                lo = mid + 1
            else:
                hi = mid
        return lo if nums[lo] == target else -1

s1 = Solution1()
print(s1.search([2,3,4,5,6,7,8,9,1], 9))