class Solution:
    """
    滑动窗口+hashmap: 设置左指针i，右指针j，用右指针遍历字符串，
    用dict记录每个元素上次出现的位置，如果s[j]在已经在字典中而且
    上次出现的位置>=i（等于是考虑到从头开始时不知道上次重复值的位置）
    更新i = memo[s[j]] + 1，将i设置为重复元素的下一个字符位置，同
    时比较滑动窗口的长度和最大长度记录，更新最大长度记录。右指针j遍历
    完数组后就可得到最大长度。
    """
    def lengthOfLongestSubstring(self, s: str) -> int:
        i = 0
        memo = {}
        maxLength = 0
        for j in range(len(s)):
            if (s[j] in memo and memo[s[j]] >= i):
                maxLength = max(maxLength, j - i)
                i = memo[s[j]] + 1
            if j == len(s) - 1:
                maxLength = max(maxLength, j - i + 1)
            memo[s[j]] = j
        return maxLength

class Solution:
    """
    滑动窗口+hashmap: 设置左指针i，右指针j，用右指针遍历字符串，用set记录
    出现过的字符并递增窗口长度。当s[j]已经在set中，说明出现重复字符，从左指
    针i开始向右遍历删除对应set中的元素，递减窗口长度，直到s[j]不在set中，此
    时左指针也到达了重复字符的下一个字符的位置。在右指针遍历过程中不断更新最
    长滑动窗口长度记录，直到右指针遍历完数组。
    """
    def lengthOfLongestSubstring(self, s: str) -> int:
        i = 0
        memo = set()
        maxLength, curLength = 0, 0
        for j in range(len(s)):
            curLength += 1
            while s[j] in memo:
                memo.remove(s[i])
                i += 1
                curLength -= 1
            maxLength = max(curLength, maxLength)
            memo.add(s[j])
        return maxLength