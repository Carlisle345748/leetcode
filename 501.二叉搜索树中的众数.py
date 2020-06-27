class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution1:
    """
    遍历二叉树，哈希表记录所有数字出现的次数和最大出现次数
    然后遍历哈希表，取出出现次数和最大出现次数相等的数字，这些数字就是众数
    """
    def findMode(self, root: TreeNode) -> list:
        if root is None:
            return []
        memo = {'maxCount': 0}
        self.search(root, memo)
        ans = []
        for key in memo:
            if memo[key] == memo['maxCount'] and key != 'maxCount':
                ans.append(key) 
        return ans
    
    def search(self, root, memo):
        if root is not None:
            if root.val not in memo:
                memo[root.val] = 1
            else:
                memo[root.val] += 1
            if memo[root.val] > memo['maxCount']:
                memo['maxCount'] = memo[root.val]
            self.search(root.left, memo)
            self.search(root.right, memo)


class Solution2:
    """
    中序遍历，相当于遍历有序数组，记录上一个遍历的节点，如果当前节点与上一个节点val相同，
    则curTime加1，否则curTime=1。如果curTime > maxTime，清空结果数组，加入当前节点的val
    如果curTime == maxTime，往结果数组加入当前节点val。
    """
    def findMode(self, root: TreeNode) -> list:
        if root is None:
            return []
        ans = []
        self.search(root, None ,ans, 0, 0)
        return ans
    
    def search(self, root, prev ,ans, curTime, maxTime):
        if root is None:
            return curTime, maxTime, prev
        curTime, maxTime, prev = self.search(root.left, prev, ans, curTime, maxTime)

        if prev is None:
            curTime = 1
        else:
            if prev != root.val:
                curTime = 1
            elif prev == root.val:
                curTime += 1
        prev = root.val

        if curTime > maxTime:
            ans.clear()
            ans.append(root.val)
            maxTime = curTime
        elif curTime == maxTime:
            ans.append(root.val)

        curTime, maxTime, prev = self.search(root.right, prev, ans, curTime, maxTime)
        return curTime, maxTime, prev

class Solution:
    """
    使用Morris算法进行中序遍历，其余部分与Solution2一致。
    https://blog.csdn.net/yangfeisc/article/details/45673947?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase
    """
    def findMode(self, root: TreeNode) -> list:
        cur = root
        prev = None
        curTime = 0
        maxTime = 0
        res = []
        while (cur):
            if cur.left is None:
                prev, curTime, maxTime = self.processNewNum(cur, prev, curTime, maxTime, res)
                cur = cur.right
            else:
                temp = cur.left
                while temp.right is not None and temp.right != cur:
                    temp = temp.right
                if temp.right is None:
                    temp.right = cur
                    cur = cur.left
                elif temp.right == cur:
                    prev, curTime, maxTime = self.processNewNum(cur, prev, curTime, maxTime, res)
                    temp.right = None
                    cur = cur.right
        return res

    def processNewNum(self, cur, prev, curTime, maxTime, res):
        if prev is None:
            prev = cur.val
            curTime = 1
        else:
            if cur.val == prev:
                curTime += 1
            else:
                curTime = 1
        if curTime > maxTime:
            res.clear()
            res.append(cur.val)
            maxTime = curTime
        elif curTime == maxTime:
            res.append(cur.val)
        prev = cur.val
        return prev, curTime, maxTime



node6 = TreeNode(6)
node2 = TreeNode(2)
node8 = TreeNode(8)
node0 = TreeNode(0)
node4 = TreeNode(4)
node2_2 = TreeNode(2)
node6_6 = TreeNode(6)
node7 = TreeNode(7)
node9 = TreeNode(9)

node6.left = node2
node6.right = node8
node2.left = node0
node2.right = node4
node4.left = node2_2
node4.right = node6_6
node8.left = node7
node8.right = node9


s2 = Solution2()
s2.findMode(node6)