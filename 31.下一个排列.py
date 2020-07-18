class Solution:
    """
    下一个排列要同时满足两个要求：
    （1）比当前排列大
    （2）增大的幅度最小

    1.我们希望下一个数比当前数大，这样才满足“下一个排列”的定义。因此只需要将后面的「大数」与
    前面的「小数」交换，就能得到一个更大的数。比如 123456，将 5 和 6 交换就能得到一个更大的
    数 123465。
    2.我们还希望下一个数增加的幅度尽可能的小，这样才满足“下一个排列与当前排列紧邻“的要求。为
    了满足这个要求，我们需要：
        （1）在尽可能靠右的低位进行交换，需要从后向前查找
        （2）将一个尽可能小的「大数」与前面的「小数」交换。比如 123465，下一个排列应该把
            5 和 4 交换而不是把 6 和 4 交换
        （3）将「大数」换到前面后，需要将「大数」后面的所有数重置为升序，升序排列就是最小的排
            列。以 123465 为例：首先按照上一步，交换 5 和 4，得到 123564；然后需要将 5 
            之后的数重置为升序，得到 123546。显然 123546 比 123564 更小，123546 就是
             123465 的下一个排列
    """
    def nextPermutation(self, nums: list) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        i, j = len(nums) - 2, len(nums) - 1
        while (i >= 0 and nums[i] >= nums[i + 1]): i -= 1
        if i == -1:
            nums.reverse()
        else:
            while (j > i and nums[j] <= nums[i]): j -= 1
            nums[i], nums[j] = nums[j], nums[i]
            # 逆转后面的数字，使其升序，保证增大幅度最小
            i, j = i + 1, len(nums) - 1
            while i < j:
                nums[i], nums[j] = nums[j], nums[i]
                i += 1
                j -= 1

s = Solution()
test = [1,4,3,4,5,3]
s.nextPermutation(test)
print(test)