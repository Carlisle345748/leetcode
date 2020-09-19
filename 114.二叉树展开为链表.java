import java.util.LinkedList;

/**
 * 后序遍历:先把左右子树都变成单链表，然后再将root和左右子树变成单链表。
 */
class Solution114_1 {
    public void flatten(TreeNode root) {
        root = flat(root);
    }

    private TreeNode flat(TreeNode root) {
        if (root == null) { return null; }

        // 后序遍历，先处理左右子树
        root.left = flat(root.left);
        root.right = flat(root.right);

        // 处理当前节点
        TreeNode cur = root.left;
        if (cur != null) {
            // 找到左子树的最右节点
            while (cur.right != null) {
                cur = cur.right;
            }
            // 把左子树放到右边，右子树接到原左子树的最右节点后
            cur.right = root.right;
            root.right = root.left;
            root.left = null;
        }

        // 返回处理完的节点
        return root;
    }
}

/**
 * 迭代法
 * 首先将整个左子树都放到右边，然后一直向右遍历，把所有左子树不为空的子树
 * 的左子树都放到右边，直到到达末端
 */
class Solution114_2 {
    public void flatten(TreeNode root) {
        while (root != null) {
            // 左子树为null无需处理
            if (root.left == null){
                root = root.right;
            }
            else {
                TreeNode cur = root.left;
                // 找到左子树的最右节点
                while (cur.right != null) {
                    cur = cur.right;
                }
                // 把左子树放到右边，右子树接到原左子树的最右节点后
                cur.right = root.right;
                root.right = root.left;
                root.left = null;

                // 继续向右遍历下一个节点
                root = root.right;
            }
        }
    }
}

/**
 * 前序遍历+展开
 * 前序遍历的顺序恰好就是链表的顺序。在前序遍历的过程中，prev储存上一个访问的节点，cur为当前访问节点
 * 设prev.right = cur; cur.left = null; prev = cur; 即可在前序遍历的同时生成链表
 */
class Solution114_3 {
    public void flatten(TreeNode root) {
        if (root == null) { return; }

        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode prev = new TreeNode(-1);  // sentinel
        stack.addFirst(root);

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pollFirst();
            if (cur != null) {
                // 生成单链表
                prev.right = cur;
                prev.left = null;

                // 先把右子树入栈，后把左子树入栈
                stack.addFirst(cur.right);
                stack.addFirst(cur.left);

                // 记录上一个访问的节点
                prev = cur;
            }
        }
    }
}