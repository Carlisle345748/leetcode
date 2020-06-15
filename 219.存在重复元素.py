# https://leetcode-cn.com/problems/contains-duplicate-ii/solution/cun-zai-zhong-fu-yuan-su-ii-by-leetcode/
class Solution:
    """
    用哈希表保存数字最后出现的index，如果下一次出现的index和上次出现的index差值小于k，则返回True。
    如果遍历完整个数组都没有满足条件的，返回False。
    """
    def containsNearbyDuplicate(self, nums: list, k: int) -> bool:
        memo = {}
        for i in range(len(nums)):
            if nums[i] not in memo:
                memo[nums[i]] = i
            else:
                if i - memo[nums[i]] <= k:
                    return True
                memo[nums[i]] = i
        return False

class Solution:
    """ 
    遍历数组，维护一个长度为k+1的set。当set长度大于k时，去除当前set中最早加入的元素（因为set无序，所以用数组的标签来找到该元素）
    然后如果set中存在相同的元素，则说明这两个数字出现的index差小于等于k，则返回True。如果遍历完都没有找到，则返回False
    """
    def containsNearbyDuplicate(self, nums: List[int], k: int) -> bool:
        memo = set()
        for i in range(len(nums)):
            if nums[i] in memo:
                return True
            else:
                memo.add(nums[i])
                if len(memo) > k:
                    memo.remove(nums[i-k])
        return False