class Solution:
    """
    逐位翻转，掩码获取n第一位，乘上翻转后对应的2次幂，然n右移1位。重复直到n=0
    """
    def reverseBits(self, n: int) -> int:
        num = 0
        power = 31
        while n:
            num += (n & 1) << power
            n >>= 1
            power -= 1
        return num

class Solution:
    """分治：https://leetcode-cn.com/problems/reverse-bits/solution/dian-dao-er-jin-zhi-wei-by-leetcode/"""
    def reverseBits(self, n: int) -> int:
        n = ((n & 0xffff0000) >> 16) | ((n & 0xffff) << 16)
        n = ((n & 0xff00ff00) >> 8) | ((n & 0xff00ff) << 8)
        n = ((n & 0xf0f0f0f0) >> 4) | ((n & 0xf0f0f0f) << 4)
        n = ((n & 0xcccccccc) >> 2) | ((n & 0x33333333) << 2)
        n = ((n & 0xaaaaaaaa) >> 1) | ((n & 0x55555555) << 1)
        return n

class Solution:
    """
    reverseByte是一个8-bit翻转技巧(pure magic)，然后对将32位分成4块依次翻转
    使用cache记录可能出现过8-bit pattern，加快速度
    """
    def reverseBits(self, n: int) -> int:
        cache = {}
        num = 0
        power = 24
        while n:
            num += self.reverByte(n & 0xff, cache) * 2**power
            n >>= 8
            power -= 8
        return num

    def reverByte(self, byte, cache):
        if byte not in cache:
            cache[byte] = (byte * 0x0202020202 & 0x010884422010) % 1023
        return cache[byte]