# 解答: https://leetcode-cn.com/problems/maximum-subarray/solution/zui-da-zi-xu-he-by-leetcode-solution/


# 动态规划-bottom up
class Solution:
    def maxSubArray(self, nums: list) -> int:
        prev = nums[0]  # 以i-1结尾的最大子序列和，只维护这一个变量就够了
        max = prev
        for i in range(len(nums)):
            if i == 0:
                prev = nums[0]
            else:
                # 如果以nums[i-1]结尾和最大子序列和小于0，那么
                # 以nums[i]极为和最大子序列和就是nums[i]
                # 否则就是nums[i-1] + nums[i]
                prev = prev + nums[i] if prev > 0 else nums[i]
            if prev > max:
                max = prev
        
        return max

## 分治
# class Solution:
#     def maxSubArray(self, nums: List[int]) -> int:
#         result = self.devided_conquer(nums)
#         return result['mSum']

#     def devided_conquer(self, nums):
#         if len(nums) == 1:
#             return {'lSum': nums[0], 'rSum': nums[0],
#                     'mSum': nums[0], 'iSum': nums[0]}
#         else:
#             middle = int(len(nums) / 2)
#             left = self.devided_conquer(nums[:middle])
#             right = self.devided_conquer(nums[middle:])

#             lsum = max(left['lSum'], left['iSum'] + right['lSum'])
#             rsum = max(right['rSum'], right['iSum'] + left['rSum'])
#             iSum = left['iSum'] + right['iSum']
#             mSum = max(left['mSum'], right['mSum'], left['rSum'] + right['lSum'])
#             return {'lSum': lsum, 'rSum': rsum,
#                     'mSum': mSum, 'iSum': iSum}

# 动态规划--递归
# class Solution:
#     def maxSubArray(self, nums: list) -> int:
#         result = self.recursive(nums, nums[0])
#         return result[1]

#     def recursive(self, nums: list, MAX):
#         if len(nums) == 1:
#             MAX = max(nums[0], MAX)
#             return nums[0], MAX
#         else:
#             prev, prev_max = self.recursive(nums[:-1], MAX)
#             current_max = prev + nums[-1] if prev > 0 else nums[-1]
#             MAX = current_max if current_max > prev_max else prev_max
#             return current_max, MAX

solution = Solution()
print(solution.maxSubArray([-2,1,-3,4,-1,2,1,-5,4]))