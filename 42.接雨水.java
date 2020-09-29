import java.util.LinkedList;

/* https://leetcode-cn.com/problems/trapping-rain-water/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-8/ */

/**
 * 按行计算
 */
class Solution42_1 {
    public int trap(int[] height) {
        int result = 0;
        int maxHeight = 0;
        for (int i : height) { maxHeight = Math.max(i, maxHeight); }

        for (int i = 1; i <= maxHeight; i++) {
            boolean startCount = false;
            int temp = 0;
            for (int j = 1; j < height.length - 1; j++) {
                if (startCount && height[j] < i) { temp++; }
                if (height[j] >= i) {
                    startCount = true;
                    result += temp;
                    temp = 0;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution42_1().trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(new Solution42_2().trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(new Solution42_3().trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(new Solution42_4().trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(new Solution42_5().trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}

/**
 * 按列求，O(n^2)
 */
class Solution42_2 {
    public int trap(int[] height) {
        int result = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int maxLeft = 0;
            for (int j = i - 1; j >= 0; j--) {
                maxLeft = Math.max(height[j], maxLeft);
            }

            int maxRight = 0;
            for (int j = i + 1; j < height.length; j++) {
                maxRight = Math.max(height[j], maxRight);
            }

            int level = Math.min(maxLeft, maxRight);
            result += Math.max(level - height[i], 0);
        }
        return result;
    }
}

/**
 * 动态规划，O(n)
 */
class Solution42_3 {
    public int trap(int[] height) {
        int result = 0;
        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];

        for (int i = 1; i < height.length; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i - 1]);
        }

        for (int i = height.length - 2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i + 1], height[i + 1]);
        }

        for (int i = 1; i < height.length - 1; i++) {
            int level = Math.min(maxLeft[i], maxRight[i]);
            if (level > height[i]) { result += level - height[i]; }
        }
        return result;
    }
}

/**
 * 双指针从两边往中间更新，O(n)
 */
class Solution42_4 {
    public int trap(int[] height) {
        int result = 0;
        int maxLeft = 0, maxRight = 0;
        int left = 1;
        int right = height.length - 2;

        for (int i = 1; i < height.length - 1; i ++) {
            // 我们想要的是maxLeft和maxRight中的最小值
            // height[left - 1] < height[right + 1]，则
            // maxRight >= height[right + 1] > height[left - 1]
            // marLeft不可能大于height[left - 1]，否则在maxLeft处会一直从右往左走，直到
            // 出现height[right + 1] > maxLeft。而此刻刚好正是height[left - 1] < height[right + 1]
            
            // 综上：只有在另一边出现更大的maxLeft/maxRight时候，才遍历交换方向，否则一直都是
            // 从maxLeft/maxRight较小侧到较大侧遍历。
            if (height[left - 1] < height[right + 1]) {
                maxLeft = Math.max(maxLeft, height[left - 1]);
                result += Math.max(0, maxLeft - height[left++]);
            }
            else {
                maxRight = Math.max(maxRight, height[right + 1]);
                result += Math.max(0, maxRight - height[right--]);
            }
        }
        return result;
    }
}

/**
 * 单调栈，O(n)
 */
class Solution42_5 {
    public int trap(int[] height) {
        int result = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        int cur = 0;
        while (cur < height.length) {
            // 如果当前墙cur大于栈顶元素(墙)，则说明栈顶元素(墙)是一个可接雨水的坑
            while (!stack.isEmpty() && height[cur] > height[stack.getFirst()]) {
                int hole = stack.pollFirst();
                if (stack.isEmpty()) { break; } // 如果坑左边没有墙了，就没办法接雨水了
                int distance = cur - stack.getFirst() - 1;  // 左墙和右墙间的距离
                // 左右两边中较矮的墙的高度
                int minHeight = Math.min(height[cur], height[stack.getFirst()]);
                // 雨水体积为 （较矮的墙的高度 - 坑的高度）* 左墙和右墙的距离
                // 注意一个特殊情况：左墙和坑一样高，则左墙实际上还属于坑，但是此时 minHeight - height[hole] = 0
                // 所以雨水体积不会增加，然后就跳过。直到左墙和坑有高度差时，才真正接到雨水
                result +=  (minHeight - height[hole]) * distance;
            }
            // 栈里没有墙了（左边没有墙）或者当前墙比栈顶的墙高
            stack.addFirst(cur++);
        }
        return result;
    }
}

