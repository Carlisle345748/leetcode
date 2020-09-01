/**
 * x^n的结果可以拆分为多个x^2、x^4....相乘
 * 将n转化为二进制，二进制转化成十进制的过程刚好与计算幂函数一致
 * 以x^10为例，10的二进制表示为1010，10 = (2^3)*1 + (2^2)*0 + (2^1)*1 + (2^0)*0
 * 而x^10可以表达为x^(2^3)*1 * x^(2^2)*0 * x^(2^1)*1 * x^(2^0)*0
 * 所以可以从二进制出发迭代计算x^n
 */
class Solution50_1 {
    public double myPow(double x, int n) {
        if (n == Integer.MIN_VALUE) { return 1 / (myPow(x, -(n + 1)) * x); } // 应对最小负数和最大正数绝对值差1
        if (n < 0) { return 1 / myPow(x, -n); }  // n为负数时
        double result = 1.0;
        while (n > 0) {
            if ((n & 1) == 1) { result *= x; }  // 当前二进制位为1
            x *= x;                             // x^0 -> x^2 -> x^4 ....
            n >>= 1;                            // 判断下一位
        }
        return result;
    }
}

/**
 * x^n可以拆分成：
 * （1）n为奇数：x^(n/2) * x^(n/2) * x
 * （2）n为偶数：x^(n/2) * x^(n/2)
 * 直到拆分至n=1或n=0时直接返回x或1
 */
class Solution50_2 {
    public double myPow(double x, int n) {
        if (n == Integer.MIN_VALUE) { return 1 / (myPow(x, -(n + 1)) * x); }
        if (n < 0) { return 1 / myPow(x, -n); }
        if (n == 0) { return 1; }
        if (n == 1) { return x; }
        double temp = myPow(x, n / 2);
        if (n % 2 == 1) { return temp * temp * x; }
        else { return temp * temp; }
    }
}