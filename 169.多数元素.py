import time
import random

class Solution1:
    """
    哈希法1：使用哈希映射（HashMap）来存储每个元素以及出现的次数。对于哈希映射中的每个键值对，键表示一个元素，值表示该元素出现的次数。
    当元素出现个数大于n/2时，就找到了众树，直接返回结果。
    """
    def majorityElement(self, nums: list) -> int:
        memo = {}
        for i in nums:
            if i in memo:
                memo[i] += 1
            else:
                memo[i] = 1
            if memo[i] > len(nums) // 2:
                return i

class Solution2:
    """
    哈希法2:使用哈希映射（HashMap）来存储每个元素以及出现的次数。对于哈希映射中的每个键值对，键表示一个元素，值表示该元素出现的次数。
    遍历的过程中维护一个“出现次数最大的值”和其“出现次数”，遍历完成后就获得结果。
    """
    def majorityElement(self, nums: list) -> int:
        memo = {}
        maxInt = None
        maxTimes = 0
        for i in nums:
            if i in memo:
                memo[i] += 1
            else:
                memo[i] = 1
            if memo[i] > maxTimes:
                maxInt = i
                maxTimes = memo[i]
        return maxInt

class Solution3:
    """
    对数组进行排序，由于众数出现次数大于n/2,所以不可能众数全在排序好的数组的前半部分或后半部分，因此n/2位置的值就是众数。
    """
    def majorityElement(self, nums: list) -> int:
        nums.sort()
        return nums[len(nums) // 2]

class Solution4:
    """
    随机法：因为超过n/2的数组下标被众数占据了，这样我们随机挑选一个下标对应的元素并验证，有很大的概率能找到众数。
    """
    def majorityElement(self, nums: list) -> int:
        while True:
            candidate = random.choice(nums)  # 随机选一个
            # 统计出现的次数
            count = 0
            for i in nums:
                if i == candidate:
                    count += 1
                if count > len(nums) // 2:   # 选中了就返回结果
                    return candidate

class Solution5:
    """
    分治：如果数 a 是数组 nums 的众数，如果我们将 nums 分成两部分，那么 a 必定是至少一部分的众数。
    我们可以使用反证法来证明这个结论。假设 a 既不是左半部分的众数，也不是右半部分的众数，那么 a 出现
    的次数少于 l / 2 + r / 2 次，其中 l 和 r 分别是左半部分和右半部分的长度。
    由于 l / 2 + r / 2 <= (l + r) / 2，说明 a 也不是数组 nums 的众数，因此出现了矛盾。所以这个
    结论是正确的。这样以来，我们就可以使用分治法解决这个问题：将数组分成左右两部分，分别求出左半部分的
    众数 a1 以及右半部分的众数 a2，随后在 a1 和 a2 中选出正确的众数。

    我们使用经典的分治算法递归求解，直到所有的子问题都是长度为 1 的数组。长度为 1 的子数组中唯一的数
    显然是众数，直接返回即可。如果回溯后某区间的长度大于 1，我们必须将左右子区间的值合并。如果它们的
    众数相同，那么显然这一段区间的众数是它们相同的值。否则，我们需要比较两个众数在整个区间内出现的次
    数来决定该区间的众数。

    """
    def majorityElement(self, nums: list) -> int:
        if len(nums) == 1:
            return nums[0]  # 子问题的数组长度为1，众数就是自己
        else:
            leftMode = self.majorityElement(nums[:len(nums) // 2])  # 左子区间众数
            rightMode = self.majorityElement(nums[len(nums) // 2:]) # 右子区间众数
            if leftMode == rightMode:  # 如果左右子区间众数相等，则是整个区间的众数
                return leftMode
            else:  
                # 如果左右子区间众数不想等，则统计他们在整个区间分别出现的总次数
                leftMode_times = 0
                rightMode_times = 0
                for i in nums:
                    if i == leftMode:
                        leftMode_times += 1
                    elif i == rightMode:
                        rightMode_times += 1
                # 返回在整个区间出现次数更多的左(右)子区间众数
                return leftMode if leftMode_times > rightMode_times else rightMode

class Solution6:
    """
    Boyer-Moore 投票算法证明：
    假设众数为maj，
    如果当前候选人不是 maj, 则 maj 会和其他非候选人一起反对(count -= 1),所以当前候选人一定会下台(count==0时发生换届选举)
    如果候选人是 maj, 则 maj 会支持自己(count += 1)，其他候选人会反对(count -= 1)，同样因为 maj 票数超过一半，所以 maj 最后一定会成功当选
    具体详见：
    https://leetcode-cn.com/problems/majority-element/solution/duo-shu-yuan-su-by-leetcode-solution/
    """
    def majorityElement(self, nums: list) -> int:
        candidate = nums[0]
        count = 0
        for i in nums:
            if count == 0:
                candidate = i
                count += 1
            else:
                if (i != candidate):
                    count -= 1
                else:
                    count += 1
        return candidate

s1 = Solution1()
s2 = Solution2()
s3 = Solution3()
s4 = Solution4()
s5 = Solution5()
s6 = Solution6()

start1 = time.time()
s1.majorityElement([2,2,1,1,1,2,2])
start2 = time.time()
s2.majorityElement([2,2,1,1,1,2,2])
start3 = time.time()
s3.majorityElement([2,2,1,1,1,2,2])
start4 = time.time()
s4.majorityElement([2,2,1,1,1,2,2])
start5 = time.time()
s5.majorityElement([2,2,1,1,1,2,2])
start6 = time.time()
s6.majorityElement([2,2,1,1,1,2,2])
end = time.time()

print("hash 1     : %e" %(start2 - start1))
print("hash 2     : %e" %(start3 - start2))
print("sort       : %e" %(start4 - start3))
print("random     : %e" %(start5 - start4))
print("devide     : %e" %(start6 - start5))
print("Boyer-Moore: %e" %(end - start6))