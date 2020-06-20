class Solution:
    def isUgly(self, num: int) -> bool:
        while num > 0:
            if num % 5 == 0:
                num /= 5
            elif num % 3 == 0:
                num /= 3
            elif num % 2 == 0:
                num /= 2
            else:
                break
        return True if num == 1 else False

s1 = Solution()
s1.isUgly(8)