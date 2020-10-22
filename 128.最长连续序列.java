/**
 * 哈希表：首先遍历一次每个数字，加入哈希表中。然后再遍历一次，假设当前数字为num，检查num-1是否在哈希表中
 * (1) num-1不在哈希表中，则num可能是一个连续序列的起点，则在哈希表中寻找连续序列长度，记录最大值
 * (2) num-1在哈希表中，则num不是连续序列的起点，不需检查
 * https://leetcode-cn.com/problems/longest-consecutive-sequence/solution/zui-chang-lian-xu-xu-lie-by-leetcode-solution/
 */
class Solution128_1 {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) { return 0; }
        HashSet<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        int max = 1;
        for (int num : nums) {
            if (!numSet.contains(num - 1)) {
                int currentLength = 1;
                while (numSet.contains(num + 1)) {
                    currentLength++;
                    num++;
                }
                max = Math.max(max, currentLength);
            }
        }
        return max;
    }
}

/**
 * 哈希表：维护每个数字的所处在的连续序列的长度
 * https://leetcode-cn.com/problems/longest-consecutive-sequence/solution/dong-tai-gui-hua-python-ti-jie-by-jalan/
 */
class Solution128_2 {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) { return 0; }
        HashMap<Integer, Integer> numSet = new HashMap<>();
        int max = 0;
        for (int num : nums) {
            if (!numSet.containsKey(num)) {
                // 因为num不在哈希表中，所以如果num-1在哈希表中，num-1的连续区间只能是[num - leftLength, num-1]
                int leftLength = numSet.getOrDefault(num - 1, 0);
                // 因为num不在哈希表中，所以如果num+1在哈希表中，所以num+1的连续区间只能是[num + 1, num + rightLength]
                int rightLength = numSet.getOrDefault(num + 1, 0);
                // 现在num加入哈希表了，所以左区间和右区间组成连续区间
                int currentLength = leftLength+ rightLength + 1;
                max = Math.max(max, currentLength);
                // 更新哈希表
                numSet.put(num, currentLength);
                // 扩增左区间，原本是[num - leftLength, num-1]，现在是[num - leftLength, num + rightLength]
                numSet.put(num - leftLength, currentLength);
                // 扩增右区间，原本是[num + 1, num + rightLength]，现在是[num - leftLength, num + rightLength]
                numSet.put(num + rightLength, currentLength);
            }
        }
        return max;
    }
}

/**
 * UnionFind：连续的数字组成一个connected component，然后计算每个cc的长度
 */
class Solution128_3 {
    private HashMap<Integer, Integer> id;

    private Integer find(int v) {
        Integer root = id.get(v);
        // 不存在的数字返回null
        if (root == null || root == v) { return root; }
        return find(root);
    }

    /**
     * union v 和 w，v成为w的子树
     */
    private void union(int v, int w) {
        Integer p = find(v);
        Integer q = find(w);
        if (p == null || q == null) { return; }
        id.put(p, q);
    }

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) { return 0; }
        // 初始化union find
        id = new HashMap<>();
        for (int num : nums) { id.put(num, num); }
        // 连接num和num+1，如果num+1不在nums中，则连接无效
        for (int num : nums) {
            union(num, num + 1);
        }
        // 计算root到num的距离，就是连续序列长度，然后寻找其中的最大值
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, find(num) - num + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] test = new int[] {100, 1, 200, 4, 3, 2};
        System.out.println(new Solution128_1().longestConsecutive(test));
        System.out.println(new Solution128_2().longestConsecutive(test));
        System.out.println(new Solution128_3().longestConsecutive(test));
    }
}