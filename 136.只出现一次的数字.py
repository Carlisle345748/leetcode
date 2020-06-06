import time
from functools import reduce


class Solution1:
    def singleNumber(self, nums: list) -> int:
        """
        用hash-table记录每个数字出现的次数，最后遍历一次hash-table找到只出现一次的数字
        """
        memo = {}
        for i in nums:
            if i not in memo:
                memo[i] = 1
            elif memo[i] == 1:
                memo[i] = 2
        for i in memo:
            if memo[i] == 1:
                return i

class Solution2:
    """
    将数组nums转化为无重复数字的set，(set的元素之和 * 2) - (nums元素之和) = 只出现一次的数字 
    """
    def singleNumber(self, nums: list) -> int:
        no_dup = set(nums)
        sum1 = sum(no_dup) * 2
        sum2 = sum(nums)
        return sum1 - sum2

class Solution3:
    """
    使用集合存储数字。遍历数组中的每个数字，如果集合中没有该数字，则将该数字加入集合,
    如果集合中已经有该数字，则将该数字从集合中删除，最后剩下的数字就是只出现一次的数字。
    """
    def singleNumber(self, nums: list) -> int:
        memo = set()
        for i in nums:
            if i not in memo:
                memo.add(i)
            else:
                memo.discard(i)
        return memo.pop()

class Solution4:
    """
    使用位运算中的异或运算，由于结合律和交换律，所有出现2次的元素都会等于0，
    剩余的只出现一次的元素与0做异或运算等于其自身
    """
    def singleNumber(self, nums: list) -> int:
        return reduce(lambda x, y: x ^ y, nums)

s1 = Solution1()
s2 = Solution2()
s3 = Solution3()
s4 = Solution4()

start1 =  time.time()
print(s1.singleNumber([1,1,2,2,3,3,4,4,11,5,5,6,6,7,7,8,8,9,9,10,10]))
start2 =  time.time()
print(s2.singleNumber([1,1,2,2,3,3,4,4,11,5,5,6,6,7,7,8,8,9,9,10,10]))
start3 =  time.time()
print(s3.singleNumber([1,1,2,2,3,3,4,4,11,5,5,6,6,7,7,8,8,9,9,10,10]))
start4 =  time.time()
print(s4.singleNumber([1,1,2,2,3,3,4,4,11,5,5,6,6,7,7,8,8,9,9,10,10]))
end =  time.time()


print("hash: %e" %(start2 - start1))
print("set : %e" %(start3- start2))
print("sum : %e" %(start4 - start3))
print("bit : %e" %(end - start4))