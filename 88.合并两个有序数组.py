# 双指针，从后往前放入新数组。不必复制一份数组，空间复杂度可降到O(1)
class Solution:
    def merge(self, nums1: List[int], m: int, nums2: List[int], n: int) -> None:
        """
        Do not return anything, modify nums1 in-place instead.
        """
        nums1[:] = nums1[:m+n]
        count = m + n - 1
        i, j = m - 1, n - 1
        while i >=0 and j >= 0:
            if nums1[i] > nums2[j]:
                nums1[count] = nums1[i]
                i -= 1
            else:
                nums1[count] = nums2[j]
                j -= 1
            count -= 1
        if j >= 0:
            nums1[:count + 1] = nums2[:j + 1]

solution = Solution()
print(solution.merge([0], 0, [1], 1))

# 双指针，从前往后（需要复制一份数组）
# 一般而言，对于有序数组可以通过 双指针法 达到O(n + m)的时间复杂度。
# 最直接的算法实现是将指针p1 置为 nums1的开头， p2为 nums2的开头，在每一步将最小值放入输出数组中。
# 由于 nums1 是用于输出的数组，需要将nums1中的前m个元素放在其他地方，也就需要 O(m)的空间复杂度。

# class Solution:
#     def merge(self, nums1: List[int], m: int, nums2: List[int], n: int) -> None:
#         """
#         Do not return anything, modify nums1 in-place instead.
#         """
#         nums1_copy = nums1[:m].copy()
#         nums1[:] = []
#         i, j = 0, 0
#         while i < m and j < n:
#             if nums1_copy[i] < nums2[j]:
#                 nums1.append(nums1_copy[i])
#                 i += 1
#             else:
#                 nums1.append(nums2[j])
#                 j += 1
#         nums1[i + j: m + n] = nums1_copy[i:] if i < m else nums2[j:]
