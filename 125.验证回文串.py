class Solution:
    def isPalindrome(self, s: str) -> bool:
        pl, pr = 0, len(s) - 1
        while pl < pr:
            while not s[pl].isalnum() and pl < pr:  # 跳过左指针字母数字
                pl += 1
            while not s[pr].isalnum() and pl < pr:  # 跳过右指针字母数字
                pr -= 1
            if s[pl].upper() == s[pr].upper():  # 转换为大写后比较
                    pl += 1
                    pr -= 1
            else:
                return False
        return True
solution = Solution()
print(solution.isPalindrome("A man, a plan, a canal: Panama"))