class Solution:
    def twoSum(self, nums, target):
        memo = {}
        for i in range(len(nums)):
            diff = target - nums[i]
            if nums[i] in memo.keys():
                return [memo[nums[i]], i]
            memo[diff] = i


solution = Solution()
print(solution.twoSum([2,7,11,15], 9))