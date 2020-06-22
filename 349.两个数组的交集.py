class Solution:
    """
    hsah-table (dict实现)
    遍历第一个数组，hash-table记录出现过的数字，然后遍历第二个数组，如果在hsah-table里出现过，
    就是两数组交集元素。
    """
    def intersection(self, nums1: List[int], nums2: List[int]) -> List[int]:
        memo = {}
        result = []
        for num in nums1:
            if num not in memo:
                memo[num] = 1
        for num in nums2:
            if num in memo and memo[num] == 1:
                memo[num] = 2
                result.append(num)
        return result

class Solution:
    """
    hsah-table (set实现)
    遍历第一个数组，hash-table记录出现过的数字，然后遍历第二个数组，如果在hsah-table里出现过，
    就是两数组交集元素。
    """
    def intersection(self, nums1: List[int], nums2: List[int]) -> List[int]:
        memo = set()
        result = set()
        for num in nums1:
            if num not in memo:
                memo.add(num)
        for num in nums2:
            if num in memo and num not in result:
                result.add(num)
        return list(result)


class Solution:
    """
    双指针
    两个数组先排序，然后一个数组一个指针（p1和p2）。如果nums1[p1]和nums2[p2]相等，则是交集元素。
    如果不等，则将值较小的指针往前移动，直到任意一个数组被遍历完。
    """
    def intersection(self, nums1: List[int], nums2: List[int]) -> List[int]:
        nums1.sort()
        nums2.sort()
        result = set()
        p1, p2 = 0, 0
        while p1 < len(nums1) and p2 < len(nums2):
            if (nums1[p1] == nums2[p2]):
                result.add(nums1[p1])
                p1 += 1
            else:
                if nums1[p1] > nums2[p2]:
                    p2 += 1
                elif nums1[p1] < nums2[p2]:
                    p1 += 1
        return list(result)