public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int x) { val = x; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    static TreeNode build(Integer... nums) {
        Deque<TreeNode> curLayer = new LinkedList<>();
        TreeNode dummy = new TreeNode();
        curLayer.add(dummy);
        int layerNum = 1;
        int count = 0;
        int n = nums.length;
        while (count < n) {
            int layerLength = Math.min(n - count, layerNum);
            TreeNode node = null;
            for (int i = 0; i < layerLength; i++) {
                if (i % 2 == 0) {
                    node = curLayer.pollFirst();
                    while (node == null) { node = curLayer.pollFirst(); }
                    node.left = nums[count] == null ? null : new TreeNode(nums[count]);
                    count++;
                    curLayer.addLast(node.left);
                }
                else {
                    node.right = nums[count] == null ? null : new TreeNode(nums[count]);
                    count++;
                    curLayer.addLast(node.right);
                }
            }
            layerNum *= 2;
        }
        return dummy.left;
    }

    public static void main(String[] args) {
        TreeNode test = build(-10, 9, 20, null, null, 15, 7);
    }
}