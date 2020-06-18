# https://leetcode-cn.com/problems/reverse-linked-list/solution/dong-hua-yan-shi-206-fan-zhuan-lian-biao-by-user74/

# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    """双指针，迭代过程中沿途修改节点指针"""
    def reverseList(self, head: ListNode) -> ListNode:
        if head is None:
            return None
        prev = None
        cur = head
        while cur:
            cur.next, prev, cur = prev, cur, cur.next
        return prev 


class Solution:
    """
    递归。这个方法很tricky，看题解的动画和下面的注释
    """
    def reverseList(self, head: ListNode) -> ListNode:
        if head is None or head.next is None:
            return head
        ans = self.reverseList(head.next) # 在递归中一直都是正序末尾的节点，最后作为结果返回
        # 在递归从底往上走的过程中，修改链表指针
        head.next.next = head  # 下一个节点指向自己
        head.next = None       # 自己的指针设置为None
        return ans  # 最后返回正序末尾节点，即逆序后的首节点