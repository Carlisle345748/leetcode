import java.util.LinkedList;

/**
 * https://github.com/azl397985856/leetcode/blob/master/problems/60.permutation-sequence.md
 */
class Solution60 {
    public String getPermutation(int n, int k) {
        int factor = 1;
        for (int m = 2; m < n; m++) { factor *= m; }
        LinkedList<String> nums = new LinkedList<>();
        for (int i = 1; i <= n; i++) { nums.add(Integer.toString(i)); }
        StringBuilder string = new StringBuilder();

        int choice;
        while (n > 1) {
            // 计算应该选择nums中的哪个数字，假设n-1!=6，k=9，则应该选第二个数字(choice=1)
            // 注意当k % factor = 0 时，应该选 k / factor - 1。(e.g. k = 6, n=6)
            choice = (k >= factor && k % factor == 0) ? k / factor - 1: k / factor;
            string.append(nums.remove(choice));
            // 下一轮选择，nums长度减少，选择的范围也缩小
            k -= choice * factor;
            factor /= n - 1;
            n--;
        }
        string.append(nums.removeFirst());
        return string.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution60().getPermutation(4, 9));
    }
}