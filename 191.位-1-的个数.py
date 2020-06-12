class Solution:
    """
    取第一位，如果是1就count+=1，然后n右移，重复直到n=0
    """
    def hammingWeight(self, n: int) -> int:
        count = 0
        while n:
            if (n & 1) == 1:
                count += 1
            n >>= 1
        return count 

class Solution:
    """
    n & (n-1)可以使n的最后一个1变为0，比如
    n    : 1000 1000
    n - 1: 1000 0111
    所以，可以重复这个操作，每次进行一次count+=1，直到n=0
    """
    def hammingWeight(self, n: int) -> int:
        count = 0
        while n:
            n &= (n - 1)
            count += 1
        return count 