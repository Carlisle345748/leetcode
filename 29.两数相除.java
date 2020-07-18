/**
 * 二进制除法思维，可以使用递归也可以使用迭代，这里使用了迭代
 * 举个例子: 45 / 2    二进制表达为: 101101 / 10
 * 初始结果result = 0。先让除数2不断左移，直到再左移就大于45，此时除数为100000，
 * 然后101101 - 100000 = 1101，结果加上100000 / 10 即 1 << 4。除数右移动1位
 * 然后就是，1101 / 10000，除数大于被除数，跳过这一轮，除数再右移1位。然后就是
 * 1011/ 1000，重复上述步骤，直到剩余的数字小于原始除数10。
 * https://leetcode-cn.com/problems/divide-two-integers/solution/xiao-xue-sheng-du-hui-de-lie-shu-shi-suan-chu-fa-b/
 */
class Solution {
    public int divide(int dividend, int divisor) {
        int result = 0;
        int count = 0;
        boolean positive = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        if (divisor == 1) return dividend;
        if (divisor == -1) return -dividend;
        dividend = -Math.abs(dividend);
        divisor = -Math.abs(divisor);
        // 为了避免divisor + divisor整数溢出，变加法为减法
        while (dividend - divisor <= divisor)
        {
            count++;
            divisor <<= 1;
        }
        while (count >= 0)
        {
            if (divisor >= dividend)
            {
                result += 1 <<count;
                dividend -= divisor;  // 递归转迭代的关键
            }
            count--;
            divisor >>= 1;
        }
        return (positive) ? result : -result;
    }
}

/**
 * 十进制除法思维，可以递归也可以迭代，这里用了递归
 * 举个例子：11 / 3
 * 首先11比3大，结果至少是1，然后我让3翻倍，就是6，发现11比3翻倍后还要大，那么结果就至少是2了。
 * 那我让这个6再翻倍，得12 > 11。此时可以知道最终结果肯定在2和4之间。也就是说2再加上某个数，
 * 这个数是多少呢？我让11减去刚才最后一次的结果6，剩下5，我们计算5是3的几倍，也就是除法。由此
 * 就产生了递归
 */
class Solution2 {
    public int divide(int dividend, int divisor) {
        boolean positive = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        if (divisor == 1) return dividend;
        if (divisor == -1) return -dividend;
        dividend = -Math.abs(dividend);
        divisor = -Math.abs(divisor);
        int result = div(dividend, divisor);
        return (positive) ? result : -result;
    }

    int div(int dividend, int divisor)
    {
        if (dividend > divisor) return 0;
        int originalDivisor = divisor;
        int count = 1;
        // 为了避免divisor + divisor整数溢出，变加法为减法
        while (dividend - divisor <= divisor)
        {
            count += count;
            divisor <<= 1;
        }
        return count + div(dividend - divisor, originalDivisor);
    }
}