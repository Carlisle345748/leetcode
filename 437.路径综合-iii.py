class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution1:
    """
    https://leetcode-cn.com/problems/path-sum-iii/solution/qian-zhui-he-di-gui-hui-su-by-shi-huo-de-xia-tian/
    前缀和法 best case n worst case n
    """
    def pathSum(self, root: TreeNode, sum: int) -> int:
        prefixSum = {0: 1}
        return self.countPath(root, prefixSum, 0, sum)
    
    def countPath(self, root : TreeNode, prefixSum : dict, currentSum : int , target : int):
        if root is None:
            return 0
        count = 0
        currentSum += root.val
        count += prefixSum.get(currentSum - target, 0)
        prefixSum[currentSum] = prefixSum.get(currentSum, 0) + 1
        count += self.countPath(root.left, prefixSum, currentSum, target)
        count += self.countPath(root.right, prefixSum, currentSum, target)
        prefixSum[currentSum] = prefixSum[currentSum] - 1
        return count


class Solution2:
    """
    计算以当前节点为路径终点的所有路径和 (best case nlogn worst n^2)
    用一个数组储存从树的根节点到当前节点的路径上各节点的root.val，然后计算以当前节点为路径终点的所有路径和
    如果有一个路径和==sum，则找到一条满足条件的路径
    """
    def pathSum(self, root: TreeNode, sum: int) -> int:
        return self.countPath(root, [None] * 1000, sum, 0)

    def countPath(self, root : TreeNode, array : list, target : int, p : int):
        if root is None:
            return 0
        count = 0 if root.val != target else 1
        temp = root.val
        i = p - 1
        while (i >= 0):
            temp += array[i]
            if temp == target:
                count += 1
            i -= 1
        array[p] = root.val
        # 这一步要注意，下面计算左字数和右子树的时候，新的root.val都是储存在p+1的位置
        # 先计算左子树后，在右子树的计算过程中，会覆盖之前计算左子树时写入数组的值从而可
        # 以使两个子树的计算过程不会相互影响
        count += self.countPath(root.left, array, target, p + 1)
        count += self.countPath(root.right, array, target, p + 1)
        return count


class Solution3:
    """
    计算以当前节点为路径终点的所有路径和 (best case nlogn worst n^2)
    用一个list储存以当前节点为路径终点的所有路径和，如果有一个路径和==sum，则找到一条满足条件的路径
    """
    def pathSum(self, root: TreeNode, sum: int) -> int:
        return self.countPath(root, [], sum)

    def countPath(self, root : TreeNode, sumList : list, target : int):
        if root is None:
            return 0
        count = 0
        # 这一步要注意，由于是重新创建了一个list，所以可以使左右子树的计算中
        # 分别使用两份独立的sumList副本，不会相互影响
        sumList = [num + root.val for num in sumList]
        sumList.append(root.val)
        for num in sumList:
            if num == target:
                count += 1
        count += self.countPath(root.left, sumList, target)
        count += self.countPath(root.right, sumList, target)
        return count

class Solution4:
    """
    双重递归：  两层递归效率很差，虽然复杂度差不多，但是递归过程耗时长  (best case nlogn worst n^2)
    第一层递归：遍历树的所有节点，对每个节点进行第二层递归，然后将结果加和
    第二层递归：计算以当前节点为起点，计算每增加一个节点后路径和，直到树的末端，
              如果在过程中找到了一个满足条件的路径，则返回1，否则返回0
    """
    def pathSum(self, root: TreeNode, sum: int) -> int:
        if root is None:
            return 0
        count = self.countPath(root, sum)
        left = self.pathSum(root.left, sum)
        right = self.pathSum(root.right, sum)
        return count + left + right
        
    
    def countPath(self, root : TreeNode, sum : int):
        if root is None:
            return 0
        sum = sum - root.val
        result = 1 if sum == 0 else 0
        return result + self.countPath(root.left, sum) + self.countPath(root.right, sum)