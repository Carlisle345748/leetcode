"""
使用栈，遍历输入字符串
如果当前字符为左半边括号时，则将其压入栈中
如果遇到右半边括号时，分类讨论：
    1）如栈不为空且为对应的左半边括号，则取出栈顶元素，继续循环
    2）若此时栈为空，则直接返回 false
    3）若不为对应的左半边括号，反之返回 false
"""

class Solution:
    def isValid(self, s: str) -> bool:
        pair = {'(':')', '[':']', '{':'}'}
        stack = []
        for i in s:
            if i in pair:
                stack.append(i)
            else:
                if len(stack) != 0:
                    if i != pair.get(stack.pop()):
                        return False
                else:
                    return False
        return len(stack) == 0


solution = Solution()
test = "[]]"
print(solution.isValid(test))

# 用特殊标记处理边界条件，代码更简介，但是可读性稍差
# class Solution:
#     def isValid(self, s: str) -> bool:
#         pair = {'(':')', '[':']', '{':'}'}
#         stack = ['0']  # 使用特殊字符，处理遇到右括号而栈内为空的情况
#         for i in s:
#             if i in pair:
#                 stack.append(i)
#             else:
#                 if i != pair.get(stack.pop()):
#                     return False
#         return len(stack) == 1
