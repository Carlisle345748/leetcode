/**
 * 递归法，看剑指offer的题解
 */
class Solution105_1 {
    private int[] preorder;
    private HashMap<Integer, Integer> inorderMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        // 构造中序遍历的数字和index的map，方便查询
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return build(0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode build(int preStart, int preEnd, int inStart, int inEnd) {
        // 空树
        if (preStart > preEnd && inStart > inEnd) { return null; }
        // 前序遍历的第一个节点就是根节点
        TreeNode root = new TreeNode(preorder[preStart]);
        // 如果只剩下一个值，那这个节点就没有左右子树了
        if (preStart == preEnd) { return root; }

        // 中序遍历中找到根节点的位置，根节点左边的是左子树，右边的是右子树
        // 假设左子树有n个节点，则在前序遍历中，根节点后的n个节点就是左子树，n+1到结尾是右子树
        int offset = inorderMap.get(preorder[preStart]) - inStart;

        // 递归构造左右子树
        root.left = build(preStart + 1, preStart + offset, inStart, inStart + offset - 1);
        root.right = build(preStart + offset + 1, preEnd, inStart + offset + 1, inEnd);

        return root;
    }

}

/**
 * 迭代法，与上面的递归法思路是不同的，迭代法模拟了前序遍历，然后结合中序遍历的结果找到右子树的插入位置
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/cong-qian-xu-yu-zhong-xu-bian-li-xu-lie-gou-zao-9/
 */
class Solution105_2 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) { return null; }
        if (preorder.length == 0 || inorder.length == 0) { return null; }

        TreeNode root = new TreeNode(preorder[0]);
        int inorderIndex = 0;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            // 上一个节点还应有左子树
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            }
            // 上一个节点没有左子树了，前序遍历的下一个值是栈中某个节点的右子树，结合中序遍历找到该节点
            else {
                // 栈中的节点的顺序和它们在前序遍历中出现的顺序是一致的，而且每一个节点的右儿子都还没有被遍历过，
                // 那么这些节点的顺序和它们在中序遍历中出现的顺序一定是相反的。
                // 如果相反关系对应不是，则前序遍历的下一个值就是上一个节点的右子树
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();  // 记录上一个节点
                    inorderIndex++;
                }
                // 前序遍历的下一个值就是上一个节点的右子树
                node.right = new TreeNode(preorderVal);
                // 右子树也压入栈，继续迭代
                stack.push(node.right);
            }
        }
        return root;
    }
}

/**
 * 上面的迭代法的递归实现
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--22/
 */
class Solution105_3 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder,  inorder, (long)Integer.MAX_VALUE + 1);
    }
    int pre = 0;
    int in = 0;
    private TreeNode buildTreeHelper(int[] preorder, int[] inorder, long stop) {
        //到达末尾返回 null
        if(pre == preorder.length){
            return null;
        }
        //到达停止点返回 null
        //当前停止点已经用了，in 后移
        if (inorder[in] == stop) {
            in++;
            return null;
        }
        int root_val = preorder[pre++];
        TreeNode root = new TreeNode(root_val);
        //左子树的停止点是当前的根节点
        root.left = buildTreeHelper(preorder,  inorder, root_val);
        //右子树的停止点是当前树的停止点
        root.right = buildTreeHelper(preorder, inorder, stop);
        return root;
    }

    public static void main(String[] args) {
        int[] preorder = new int[] {3,9,8,5,4,10,20,15,7};
        int[] inorder = new int[] {4,5,8,10,9,3,15,20,7};
        new Solution105_3().buildTree(preorder, inorder);
    }
}