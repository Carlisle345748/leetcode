class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

"""
迭代法
首先，我们设定一个哨兵节点 prehead ，这可以在最后让我们比较容易地返回合并后的链表。

我们维护一个 new_node 节点，我们需要做的是调整它的 next 指针。然后，我们重复以下过程，
直到 l1 或者 l2 指向了 null ：如果 l1 当前节点的值小于等于 l2 ，我们就把 l1 当前
的节点接在 指针 节点的后面同时将 l1 指针往后移一位。否则，我们对 l2 做同样的操作。
不管我们将哪一个元素接在了后面，我们都需要把 new_node 向后移一位。在循环终止的时候，
l1 和 l2 至多有一个是非空的。由于输入的两个链表都是有序的，所以不管哪个链表是非空的，
它包含的所有元素都比前面已经合并链表中的所有元素都要大。这意味着我们只需要简单地将非
空链表接在合并链表的后面，并返回合并链表即可。
"""
class Solution:
    def mergeTwoLists(self, l1: ListNode, l2: ListNode) -> ListNode:
        prehead = ListNode(None)
        new_node = prehead
        while l1 and l2:
            if l1.val > l2.val:
                new_node.next = l2
                l2 = l2.next
            else:
                new_node.next = l1
                l1 = l1.next
            new_node = new_node.next   

        if l1 is not None or l2 is not None:
            new_node.next = l1 if l1 else l2

        return prehead.next                 

# 递归法
# 该递归的思路比较复杂，可以参考
# https://leetcode-cn.com/problems/merge-two-sorted-lists/solution/hua-jie-suan-fa-21-he-bing-liang-ge-you-xu-lian-bi/
# 将排序过程分解为重复的子问题：
# 对于两个链表l1, l2，选取两个链表中头节点值较小的节点（比如l1），将l1指向“排序好的剩余链表（l1.next和l2）”
# 使l1成为一个排序好的链表，然后返回l1。为了获得“排序好的剩余链表（l1.next和l2）”，需要对l1.next和l2重复上述操作，
# 直到其中一个链表为空，则直接指向另外一个链表（单个链表本身是有序的）。在递归的过程中，程序修改链表的指针，连个链表中
# 头节点值更小的链表指向排序好的剩余链表，每次选取头节点值更小的过程完成了排序。
# class Solution:
#     def mergeTwoLists(self, l1: ListNode, l2: ListNode) -> ListNode:
#         if l1 is None:
#             return l2
#         elif l2 is None:
#             return l1
#         elif l1.val < l2.val:
#             l1.next = self.mergeTwoLists(l1.next, l2)
#             return l1
#         elif l2.val <= l1.val:
#             l2.next = self.mergeTwoLists(l1, l2.next)
#             return l2


l1 = ListNode(-9)
l1.next = ListNode(3)
l2 = ListNode(5)
l2.next = ListNode(7)
solution = Solution()
tt = solution.mergeTwoLists(l1, l2)

