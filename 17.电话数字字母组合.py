# https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/solution/tong-su-yi-dong-dong-hua-yan-shi-17-dian-hua-hao-m/
class Solution1:
    """
    递归回溯
    """
    def letterCombinations(self, digits: str) -> list:
        if len(digits) == 0: return []
        letterMap = {'2': "abc", '3': "def", '4': "ghi", '5': "jkl", '6': "mno", '7':"pqrs", '8':"tuv", '9':"wxyz"}
        result = []
        i = 0

        def dfs(combination, i):
            if i == len(digits):
                result.append(combination)
                return
            for letter in letterMap[digits[i]]:
                dfs(combination + letter, i+1)
        
        dfs("", 0)
        return result

class Solution2:
    """
    用队列代替递归
    """
    def letterCombinations(self, digits: str) -> list:
        if len(digits) == 0: return []
        letterMap = {'2': "abc", '3': "def", '4': "ghi", '5': "jkl", '6': "mno", '7':"pqrs", '8':"tuv", '9':"wxyz"}
        result = [""]
        for num in digits:
            n = len(result)
            for i in range(n):
                temp = result[0]
                result = result[1:]
                for letter in letterMap[num]:
                    result.append(temp + letter)
        return result

s1 = Solution1()
print(s1.letterCombinations("23"))
s2 = Solution2()
print(s2.letterCombinations("23"))