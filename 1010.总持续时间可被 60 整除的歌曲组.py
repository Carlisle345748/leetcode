class Solution:
    def numPairsDivisibleBy60(self, time):
        groups = 0
        memo_list = [0] * 60
        for num in time:
            remainder = num % 60
            memo_list[remainder] = memo_list[remainder] + 1
        # 余数为0或30的组中，组内两两排列组合都有效，组合数公式为n(n-1)/2
        groups += (memo_list[0] * (memo_list[0] - 1))/ 2  # 余数为0
        groups += (memo_list[30] * (memo_list[30] - 1))/ 2  # 余数为30
        # 其他余数，互补的组组合起来成为有效组，比如1和59。组合数公式为m*n
        for i, j in zip(range(1,30), range(59,30,-1)):
            groups += memo_list[i] * memo_list[j]
        return int(groups)


solution = Solution()
result = solution.numPairsDivisibleBy60([30,20,150,100,40])
print(result)
