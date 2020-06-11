# 利用有序数组的特性，双指针往从两侧往中间不断靠拢寻找题解
# 若两数之和大于target，则右指针左移动
# 若两数之和小于targrt，则左指针右移
# 直到找到题解

class Solution:
    def twoSum(self, numbers: list, target: int) -> list:
        pl = 0
        pr = len(numbers) - 1
        while pl < pr:
            if (numbers[pl] + numbers[pr]) == target:
                return [pl + 1, pr + 1]
            elif (numbers[pl] + numbers[pr]) > target:
                pr -= 1
            elif (numbers[pl] + numbers[pr]) < target:
                pl += 1


s1 = Solution()
print(s1.twoSum([5,25,75], 100))