'''
算法流程：
特判，对于数组长度 length，如果数组为null或者数组长度小于3，返回[]。
对数组进行排序。
遍历排序后数组：
若 nums[i]>0：因为已经排序好，所以后面不可能有三个数加和等于0，直接返回结果。
对于重复元素：跳过，避免出现重复解
令左指针 li=i+1，右指针 hi=n-1，当 li<hi时，执行循环：
当 nums[i]+nums[li]+nums[hi]==0，执行循环，判断左界和右界是否和下一位置重复，去除重复解。并同时将li和hi移到下一位置，寻找新的解
若和大于0，说明nums[hi]太大，hi左移
若和小于0，说明nums[li]太小，li右移

之所以中心值不位于双指针中间，是因为如果中心值在双指针中间时，指针从中心值两侧遍历，重复中心值
带来的重复解需要多种特殊判断条件来处理。比如[0,0,0] [-2,1,1] [-2,-1,-1,2]等。
而中心值位于左侧，则直接跳过重复中心值即可达到去除重复解。
'''

class Solution:
    def threeSum(self, nums):
        result = []
        length = len(nums)
        if nums is None or length < 3:  # None或长度小于3为无效数据
            return result
        else:
            nums.sort()  # 排序，利用有序数组的特性搜寻满足条件的组合
            for i in range(length):
                if nums[i] > 0:  # 当中心值大于0，后面的
                    return result
                if i > 0 and nums[i] == nums[i-1]:
                    continue
                li = i + 1
                hi = length - 1
                while li < hi:
                    sum = nums[i] + nums[li] + nums[hi]
                    if sum == 0:
                        result.append([nums[i], nums[li], nums[hi]])
                        while li < hi and nums[li] == nums[li + 1]:
                            li += 1
                        while li < hi and nums[hi] == nums[hi - 1]:
                            hi -= 1
                        li += 1
                        hi -= 1
                    else:
                        if sum > 0:
                            hi -= 1
                        elif sum < 0:
                            li += 1
            return result


solution = Solution()
print(solution.threeSum([0,0,0,0,0]))