# 题解：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/solution/er-cha-shu-de-zui-da-shen-du-by-leetcode/
# 递归（深度优先搜索）时间复杂度
class Solution:
    def maxDepth(self, root: TreeNode) -> int:
        if root is None:
            return 0
        else:
            left_height = self.maxDepth(root.left)
            right_height = self.maxDepth(root.right)
            return max(left_height, right_height) + 1


# 迭代(栈)
# class Solution:
#     def maxDepth(self, root: TreeNode) -> int:
#         stack = []

#         if root is not None:
#             stack.append((1,root))
        
#         max_depth = 0
#         while len(stack) != 0:
#             current_depth, root = stack.pop()
#             if root is not None:
#                 max_depth = max(current_depth, max_depth)
#                 stack.append((current_depth + 1, root.left))
#                 stack.append((current_depth + 1, root.right))
#         return max_depth