class Solution102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) { return result; }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();
            List<Integer> temp = new LinkedList<>();
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode v = queue.poll();
                if (v != null) {
                    temp.add(v.val);
                    if (v.left != null) { queue.add(v.left); }
                    if (v.right != null) { queue.add(v.right); }
                }
            }
            result.add(temp);
        }
        return result;
    }
}