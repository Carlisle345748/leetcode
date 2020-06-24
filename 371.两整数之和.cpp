/*
https://leetcode-cn.com/problems/sum-of-two-integers/solution/wei-yun-suan-xiang-jie-yi-ji-zai-python-zhong-xu-y/
异或运算等效于不进位的加法结果；用&运算找到1和1相加需进位的地方，<<左移获得进位的位置，再加到异或运算的结果上。
如果加上进位后产生新的进位，就重复上述步骤，直到不需要再进位。
设计负数的运算，根据二进制补码：
50 - 45
50 + (-45)
(50 + (2^32 - 45)) % 2^32 = 5  (% 2^23 是截断为32位)
*/
int getSum(int a, int b){
        int temp;
        while (b)
        {
            temp = a;
            a ^= b;
            b = (unsigned int)(temp & b) << 1;
        }
        return a;
}