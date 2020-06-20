class Solution1:
    def moveZeroes(self, nums: List[int]) -> None:
        """
        把遇到的非零数依次放到数组的前面，遍历完一次数组后，所有的非零数组都按原有顺序排列在前面了，最后再把后面的数字都设为0就好
        """
        nonzero = 0
        for i in range(len(nums)):
            if nums[i] != 0:
                nums[nonzero] = nums[i]
                nonzero += 1
        for i in range(nonzero, len(nums)):
            nums[i] = 0

class Solution2:
    """
    遍历数组，每遇到一个非零数字，它应有的位置要不是原有位置，要不是更前的位置（表明该非零数字与上一个非零数字之间有零出现）。
    用一个nonzero来记录下一个非零数字出现的数组，每当遇到一个非零数，就把nums[i]和nums[nonzero]交换，共有2种情况：
    （1）如果 i == nonzero，说明非零数字保留原始位置，而且该非零数字与上一个非零数字之间没有出现0
    （2）如果 nonzeor < i，说明该非零数字与上一个非零数字之间有零出现，而且nums[nonzero]肯定是零，交换后，nums[i]这个非
        零数字按顺序放到了数组前面，而nums[nonzero]这个零放到了数组后面。
    遍历完数组后，所有非零数字都按顺序放到数组前面，而且中间出现的零都被交换后数组后面，完成目标。
    """
    def moveZeroes(self, nums: List[int]) -> None:
        nonzero = 0
        for i in range(len(nums)):
            if nums[i] != 0:
                nums[i], nums[nonzero] = nums[nonzero], nums[i]
                nonzero += 1