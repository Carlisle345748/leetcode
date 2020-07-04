"""
这道题的对子序列的定义比较特殊：
如果一个字符串可以通过删除原字符串某些字符而不改变原字符顺序得到，那么这个字符串就是原字符串的一个子序列。
因此，因为字符串只含有'a'和'b'，所以无论如何都可以2次删除所有回文子序列（1次删除所有a，1次删除所有b）
如果字符串本身就是回文，那么就可以1次删除。如何字符串为空，则需0次。
"""

class Solution:
    def removePalindromeSub(self, s: str) -> int:
        if (len(s)) == 0: return 0
        return 1 if s == s[::-1] else 2

class Solution:
    def removePalindromeSub(self, s: str) -> int:
        if (len(s)) == 0: return 0
        pl, pr = 0, len(s) - 1
        while pl <= pr:
            if s[pl] != s[pr]:
                return 2
            pl += 1
            pr -= 1
        return 1