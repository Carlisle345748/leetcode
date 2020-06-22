# https://leetcode-cn.com/problems/power-of-four/solution/4de-mi-by-leetcode/
class Solution1:
    def isPowerOfFour(self, num: int) -> bool:
        return num > 0 and num & (num -1) == 0 and num & 0xaaaaaaaa == 0

from math import log2
class Solution2:
    def isPowerOfFour(self, num: int) -> bool:
        return num > 0 and log2(num) % 2 ==0

class Solution:
    def isPowerOfFour(self, num: int) -> bool:
        return num > 0 and num & (num - 1) == 0 and num % 3 == 1