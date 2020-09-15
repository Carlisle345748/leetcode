import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 递归中序遍历
 */
class Solution94_1 {
    List<Integer> result = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        inorder(root);
        return result;
    }

    private void inorder(TreeNode root) {
        if (root == null) { return; }
        inorder(root.left);
        result.add(root.val);
        inorder(root.right);

    }
}

/**
 * 迭代法中序遍历
 */
class Solution94_2 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.addLast(root);
                root = root.left;
            }
            root = stack.pollLast();
            result.add(root.val);
            root = root.right;
        }
        return result;
    }

}

/**
 * Morris中序遍历
 */
class Solution94_3 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode temp = cur.left;
                while (temp.right != null && temp.right != cur) {
                    temp = temp.right;
                }
                if (temp.right == cur) {
                    temp.right = null;
                    result.add(cur.val);
                    cur = cur.right;
                }
                else {
                    temp.right = cur;
                    cur = cur.left;
                }
            }
            else {
                result.add(cur.val);
                cur = cur.right;
            }
        }
        return result;
    }
}

/**
 * 颜色标记遍历法（迭代）
 * 其核心思想如下：
 * 使用颜色标记节点的状态，新节点为白色，已访问的节点为灰色。
 * 如果遇到的节点为白色，则将其标记为灰色，然后将其{右子节点、自身、左子节点}依次入栈。
 * 如果遇到的节点为灰色，则将节点的值输出。
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/yan-se-biao-ji-fa-yi-chong-tong-yong-qie-jian-ming/
 *
 * 前序遍历：{右子节点、左子节点、自身}依次入栈
 * 中序遍历：{右子节点、自身、左子节点}依次入栈
 * 前序遍历：{自身、右子节点、左子节点}依次入栈
 */
class Solution94_4 {
    private static class Pair {
        TreeNode node;
        int color;
        public Pair(TreeNode node, int color) {
            this.node = node;
            this.color = color;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        final int WHITE = 0, GRAY = 1;
        List<Integer> result = new ArrayList<>();
        LinkedList<Pair> stack = new LinkedList<>();
        stack.addLast(new Pair(root, WHITE));
        while (!stack.isEmpty()) {
            Pair pair = stack.pollLast();
            if (pair.node == null) { continue; }
            if (pair.color == GRAY) { result.add(pair.node.val); }
            else {
                stack.addLast(new Pair(pair.node.right, WHITE));
                stack.addLast(new Pair(pair.node, GRAY));
                stack.addLast(new Pair(pair.node.left, WHITE));
            }
        }
        return result;
    }


    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node4.left = node2;
        node4.right = node5;
        node2.left = node1;
        node2.right = node3;
        System.out.println(new Solution94_1().inorderTraversal(node4));
        System.out.println(new Solution94_2().inorderTraversal(node4));
        System.out.println(new Solution94_3().inorderTraversal(node4));
        System.out.println(new Solution94_4().inorderTraversal(node4));
    }

}