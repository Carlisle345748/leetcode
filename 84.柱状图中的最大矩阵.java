import java.util.*;

/**
 * 暴力法，对于每一个可能的高度h，寻找最多有多少个连续柱子高度小于h。就是高度为h的矩形的最大面积
 */
class Solution84_1 {
    public int largestRectangleArea(int[] heights) {
        int max = -1;
        HashSet<Integer> unique = new HashSet<>();
        for (int height : heights) { unique.add(height); }
        for (int min : unique) {
            int counter = 0;
            for (int height : heights) {
                if (height >= min) {
                    counter++;
                } else {
                    max = Math.max(counter * min, max);
                    counter = 0;
                }
            }
            max = Math.max(counter * min, max);
        }
        return max;
    }

}

/**
 * 单调栈，遍历每个柱子，设当前柱子高度为h，求出左侧和右侧的第一个高度小于h的柱子，组成的矩形就是对包含当前柱子。
 * 且高度为h的最大矩形。从从到右和从右到左各遍历一次，求出每个柱子的左边界和右边界
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/zhu-zhuang-tu-zhong-zui-da-de-ju-xing-by-leetcode-/
 */
class Solution84_2 {
    public int largestRectangleArea(int[] heights) {
        if (heights == null) { return 0; }
        Deque<Integer> stack = new LinkedList<>();
        int[] left = new int[heights.length];
        int[] right = new int[heights.length];
        stack.push(-1);
        for (int i = 0; i < heights.length; i++) {
            while (stack.getFirst() != -1 && heights[stack.getFirst()] >= heights[i]) {
                stack.pop();
            }
            left[i] = stack.getFirst();
            stack.push(i);
        }

        stack.clear();
        stack.push(heights.length);

        for (int i = heights.length - 1; i >= 0; i--) {
            while (stack.getFirst() != heights.length && heights[stack.getFirst()] >= heights[i]) {
                stack.pop();
            }
            right[i] = stack.getFirst();
            stack.push(i);
        }

        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            max = Math.max(max, (right[i] - left[i] - 1) * heights[i]);
        }

        return max;
    }
}

/**
 * 优化方法2：只需要一次从左到右的遍历
 * 根据方法2从左到右很显然可以得到左边界，下面证明如何同时求右边界
 * 设柱子i的高度为H(i)
 * 当遍历到柱子i，对于每次出栈的柱子，H(j) >= H(i)。当前柱子i是j的右侧第一个高度小于等于H(j)的柱子，因为是小于等于而不是
 * 严格小于，所以i右侧还可能高度为H(i)的柱子，所以i不是严格的j的右边界。但是当遍历到连续的最右侧高度为H(i)的柱子时，此时求出
 * 的右边界是正确的，而且这个矩形包括了之前的高度为H(i)的矩形。所以最终的结果是正确的。
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/zhu-zhuang-tu-zhong-zui-da-de-ju-xing-by-leetcode-/
 */
class Solution84_3 {
    public int largestRectangleArea(int[] heights) {
        if (heights == null) { return 0; }
        Deque<Integer> stack = new LinkedList<>();
        int[] left = new int[heights.length];
        int[] right = new int[heights.length];
        stack.push(-1);
        for (int i = 0; i < heights.length; i++) {
            while (stack.getFirst() != -1 && heights[stack.getFirst()] >= heights[i]) {
                right[stack.pop()] = i;  // 柱子i可能是j的右边界
            }
            left[i] = stack.getFirst();  // 柱子j是i的左边界
            stack.push(i);
        }
        // 遍历到最后一个柱子时，如果栈内还有元素，则它们的右边界就是最后一个柱子的右边
        while (stack.size() > 1) {
            right[stack.pop()] = heights.length;
        }

        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            max = Math.max(max, (right[i] - left[i] - 1) * heights[i]);
        }

        return max;
    }
}

/**
 * 优化方法3：不需要left和right两个矩阵
 * 构建一个新的数组，添加前后哨兵点。前哨兵避免stack.getFirst()空指针，而且尾哨兵可以保证栈内元素全部出栈
 * 遍历到柱子i，出栈柱子j时，对于柱子j，右边界可能就是i，左边界正好就是栈顶的元素(栈内是单调递增的，所以
 * 栈顶元素就是柱子j左侧第一个高度小于H(j)的柱子)。
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/84-by-ikaruga/
 */
class Solution84_4 {
    public int largestRectangleArea(int[] heights) {
        if (heights == null) { return 0; }
        Deque<Integer> stack = new LinkedList<>();
        int[] newHeight = new int[heights.length + 2];
        System.arraycopy(heights, 0, newHeight, 1, heights.length);

        int max = 0;
        for (int i = 0; i < newHeight.length; i++) {
            while (i > 0 && stack.getFirst() != 0 && newHeight[stack.getFirst()] >= newHeight[i]) {
                int height = newHeight[stack.pop()];
                int left = stack.getFirst();
                max = Math.max((i - left - 1) * height, max);
            }
            stack.push(i);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] test = new int[] {2,1,5,6,2,3};
        var s1 = new Solution84_1();
        var s2 = new Solution84_2();
        var s3 = new Solution84_3();
        var s4 = new Solution84_4();
        System.out.println(s1.largestRectangleArea(test));
        System.out.println(s2.largestRectangleArea(test));
        System.out.println(s3.largestRectangleArea(test));
        System.out.println(s4.largestRectangleArea(test));
    }
}