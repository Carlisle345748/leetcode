import java.util.ArrayList;
import java.util.List;

/**
 * 递归法
 * 所以如果求 1...n 的所有可能。
 *
 * 我们只需要把 1 作为根节点，[ ] 空作为左子树，[ 2 ... n ] 的所有可能作为右子树。
 * 2 作为根节点，[ 1 ] 作为左子树，[ 3...n ] 的所有可能作为右子树。
 * 3 作为根节点，[ 1 2 ] 的所有可能作为左子树，[ 4 ... n ] 的所有可能作为右子树，然后左子树和右子树两两组合。
 * 4 作为根节点，[ 1 2 3 ] 的所有可能作为左子树，[ 5 ... n ] 的所有可能作为右子树，然后左子树和右子树两两组合。
 * ...
 * n 作为根节点，[ 1... n ] 的所有可能作为左子树，[ ] 作为右子树。
 * 至于，[ 2 ... n ] 的所有可能以及 [ 4 ... n ] 以及其他情况的所有可能，可以利用上边的方法，把每个数字作为根节点，
 * 然后把所有可能的左子树和右子树组合起来即可。
 */
class Solution95_1 {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) { return new ArrayList<>(); }
        return subtree(1, n + 1);
    }

    private List<TreeNode> subtree(int begin, int end) {
        List<TreeNode> result = new ArrayList<>();

        if (begin == end) {
            result.add(null);
            return result;
        }

        if (begin + 1 == end) {
            result.add(new TreeNode(begin));
            return result;
        }

        for (int i = begin; i < end; i++) {
            for (TreeNode left : subtree(begin, i)) {
                for (TreeNode right : subtree(i + 1, end)) {
                    result.add(new TreeNode(i, left, right));
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        new Solution95_1().generateTrees(3);
    }
}

/**
 * 动态规划法
 * 仔细分析，可以发现一个规律。首先我们每次新增加的数字大于之前的所有数字，
 * 所以新增加的数字出现的位置只可能是根节点或者是根节点的右孩子，右孩子的
 * 右孩子，右孩子的右孩子的右孩子等等，总之一定是右边。其次，新数字所在位
 * 置原来的子树，改为当前插入数字的左孩子即可，因为插入数字是最大的。
 * https://leetcode-cn.com/problems/unique-binary-search-trees-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-2-7/
 */
class Solution95_2 {
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> prev = new ArrayList<>();
        if (n == 0) { return prev; }
        prev.add(new TreeNode(1));
        if (n == 1) { return prev; }

        for (int i = 2; i <= n; i++) {
            List<TreeNode> cur = new ArrayList<>();
            for (TreeNode subtree : prev) {
                // 新数字作为根节点，形成新的树
                cur.add(new TreeNode(i, treeCopy(subtree), null));
                // 尝试将新数字加到根节点的右孩子，右孩子的右孩子，右孩子的右孩子的右孩子等等
                TreeNode copy = treeCopy(subtree);
                TreeNode temp = copy;
                while (temp.right != null) {
                    // 右孩子的备份指针
                    TreeNode backup = temp.right;
                    // 在右孩子的位置插入新数字，将原本的右孩子作为新数字的左孩子
                    temp.right = new TreeNode(i, backup, null);
                    // 复制一个树加入结果集
                    cur.add(treeCopy(copy));
                    // 恢复树的原状
                    temp.right = backup;
                    // 指针指向右孩子，进入下一步
                    temp = temp.right;
                }
                // 直到右孩子为null，直接将新数字作为右孩子
                temp.right = new TreeNode(i);
                // 最后一次用copy了，不用复制一份了
                cur.add(copy);
            }
            prev = cur;
        }
        return prev;
    }

    /**
     * 复制一个二叉树
     */
    private TreeNode treeCopy(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode newRoot = new TreeNode(root.val);
        newRoot.left = treeCopy(root.left);
        newRoot.right = treeCopy(root.right);
        return newRoot;
    }
    
    public static void main(String[] args) {
        new Solution95_2().generateTrees(3);
    }
}

