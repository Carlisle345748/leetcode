class Solution:
    """
    用两个指针，i是慢指针，j是快指针，只要nums[i] == nums[j]，则令nums[i] = nums[j], i += 1。
    当数组遍历完后，数组的前i+1个数就是无重复的数组。
    """
    def removeDuplicates(self, nums: list) -> int:
        if nums:
            i, j = 0, 1
            while j < len(nums):
                if nums[j] != nums[i]:
                    i += 1
                    nums[i] = nums[j]
                j += 1
            return i + 1
        return 0

# class Solution:
#     """
#     删除数组法，遇到重复的数字就从数组中删除该项，删除操作的效率较低
#     """
#     def removeDuplicates(self, nums: list) -> int:
        # if nums:
        #     i = 0
        #     while i < len(nums) - 1:
        #         if nums[i+1] == nums[i]:
        #             del nums[i+1]
        #         elif nums[i+1] != nums[i]:
        #             i += 1
        #     return len(nums)
        # else:
        #     return 0