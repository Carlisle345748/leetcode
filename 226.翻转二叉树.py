
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    """
    递归交换节点的左孩子和右孩子
    """
    def invertTree(self, root: TreeNode) -> TreeNode:
        if root is None:
            return None
        else:
            left = self.invertTree(root.left)
            right = self.invertTree(root.right)
            root.left = right
            root.right = left
            return root

class Solution:
    """
    用队列实现迭代：这个方法的思路就是，我们需要交换树中所有节点的左孩子和右孩子。
    因此可以创一个队列来存储所有左孩子和右孩子还没有被交换过的节点。开始的时候，
    只有根节点在这个队列里面。只要这个队列不空，就一直从队列中出队节点，然后互换
    这个节点的左右孩子节点，接着再把孩子节点入队到队列，对于其中的空节点不需要加
    入队列。最终队列一定会空，这时候所有节点的孩子节点都被互换过了，直接返回最初
    的根节点就可以了。
    """
    def invertTree(self, root: TreeNode) -> TreeNode:
        if root is None:
            return None
        queue = [root]
        while len(queue) > 0:
            node = queue[0]
            node.left, node.right = node.right, node.left
            if node.left:
                queue.append(node.left)
            if node.right:
                queue.append(node.right)
            queue = queue[1:]
        return root