"""
1.对于一艘承载力为max_load船来说，我们必然会在不超过其承载力的前提下贪心地往上装载货物，这样才能使得运送包裹所花费的时间最短。
2.如果船在承载力为max_load的条件下可以完成在D天内送达包裹的任务，那么任何承载力大于max_load的条件下依然也能完成任务。
3.我们可以让这个承载力max_load从max(weights)开始（即所有包裹中质量最大包裹的重量，低于这个重量我们不可能完成任务），
逐渐增大承载力max_load，直到K可以让我们在D天内送达包裹。此时max_load即为我们所要求的最低承载力。
4.逐渐增大承载力max_load的方法效率过低，让我们用二分查找的方法来优化它。
    - 判断在承载力为max_load的情形下能否在D天内送达所有包裹。我们所要做的就是按照传送带上货物的顺序，依次且尽可能多地往船上装载货物，
      当该艘船无法装下更多货物时，我们换一搜船，同时将天数加1。当运输完所有货物后，我们判断所用的天数是否小于等于D。
    - 用二分查找寻找最低承载力，如果天数>D，则在[middle + 1, high]中寻找，如果天数<=D，此时可以D天内运送完货物，
      但是不知道是否为最低承载力，所以在[low, middle]，middle可能为最低承载力，故保留。直到low = high，此时承载力
      已经无法更低，得到最低承载力。
"""

class Solution:
    def shipWithinDays(self, weights, D):
        low = max(weights)
        high = sum(weights)
        # 不需检查low=high的情况，所以不用low <= high, 因为目标肯定在区间内
        while low < high:
            max_load = int((low + high) / 2)
            # 计算装载全部货物所需天数
            day ,current_load = 1, 0
            for good in weights:
                if current_load + good <= max_load:
                    current_load += good
                else:
                    day += 1
                    current_load = good
            # 确定搜索区间
            if day <= D:
                high = max_load
            else:
                low = max_load + 1

        return int((low + high) / 2)


solution = Solution()
print(solution.shipWithinDays([1,2,3,1,1], 4))


