/**
 * 定义法：对于一个节点root，其所有左子树的所有节点都不能大于root.val，其所有右子树的所有节点都不能小root.val。
 * 所以，每次向左或向右递归检验，都更新下一个节点的最大值或最小值。
 */
class Solution98_1 {
    public boolean isValidBST(TreeNode root) {
        return isBST(root, null, null);
    }

    private boolean isBST(TreeNode root, Integer min, Integer max) {
        if (root == null) { return true; }
        if (min != null && root.val <= min) { return false; }
        if (max != null && root.val >= max) { return false; }

        return isBST(root.left, min, root.val) && isBST(root.right, root.val, max);
    }

    
}

/**
 * 中序遍历：中序遍历时，判断当前节点是否大于中序遍历的前一个节点，如果大于，说明满足 BST，继续遍历；否则直接返回 false。
 */
class Solution98_2 {
    private long pre = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null)            { return true;  }

        if (!isValidBST(root.left))  { return false; }

        if (root.val <= pre)         { return false; }
        pre = root.val;

        if (!isValidBST(root.right)) { return false; }

        return true;
    }   
}

