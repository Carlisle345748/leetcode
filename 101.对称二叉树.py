"""
如果同时满足下面的条件，两个树互为镜像：
- 它们的两个根结点具有相同的值（或都为空）
- 每个树的右子树都与另一个树的左子树镜像对称（因为子树中还有子树，可以形成递归）
然后采用1）递归法或2）把递归改写队列法

leetcode题解链接：
https://leetcode-cn.com/problems/symmetric-tree/solution/dui-cheng-er-cha-shu-by-leetcode-solution/

注意！答案的第一步都是采用 isMirror(root, root)，虽然这样可以判断出空二叉树，但是会造成二叉树被镜像遍历2遍。
可以另外判断二叉树是否为空，然后直接 isMirror(root.left, root.right)。这样子就只用遍历一遍。
"""


class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


# 递归法
class Solution:
    def isSymmetric(self, root: TreeNode) -> bool:
        return True if root is None else self.isMirror(root.left, root.right)
 
    def isMirror(self, p, q):
        print("运行isMirror")
        if p is None and q is None:
            return True
        elif p is None or q is None: 
            return False
        else:
            return p.val == q.val and self.isMirror(p.left, q.right)\
                   and self.isMirror(p.right, q.left)


# 队列
class Solution:
    def isSymmetric(self, root: TreeNode) -> bool:
        q = []
        if root is None:
            return True
        q.extend([root.left, root.right])
        while len(q) > 0:
            u, v = q[0], q[1]
            q = q[2:]
            if u is None and v is None:
                continue
            elif u is None or v is None:
                return False
            elif u.val != v.val:
                return False
            q.extend([u.left, v.right])
            q.extend([u.right, v.left])
        return True

# Test
d1 = TreeNode(1)

d2_1 = TreeNode(2)
d2_2 = TreeNode(2)

d3_1 = TreeNode(3)
d3_2 = TreeNode(4)
d3_3 = TreeNode(4)
d3_4 = TreeNode(3)

d1.left, d1.right = d2_1, d2_2
d2_1.left, d2_1.right = d3_1, d3_2
d2_2.left, d2_2.right = d3_3, d3_4


solution = Solution()
print(solution.isSymmetric(d1))

