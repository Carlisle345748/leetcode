# https://leetcode-cn.com/problems/swap-nodes-in-pairs/solution/liang-liang-jiao-huan-lian-biao-zhong-de-jie-di-19/

class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    """
    迭代法, O(n),O(1) 
    """
    def swapPairs(self, head: ListNode) -> ListNode:
        # 虚节点作为第一组相邻节点的上一个节点
        # p1为相邻节点的第一个节点，p2为第二个节点
        dummy = ListNode(0)
        dummy.next = head
        prev, p1 = dummy, head
        while p1 and p1.next:
            p2 = p1.next
            # 翻转
            prev.next = p2
            p1.next = p2.next
            p2.next = p1
            # 更新p1和prev
            prev = p1
            p1 = p1.next
            
        return dummy.next

class Solution:
    """
    递归法, O(n),O(n) 
    """
    def swapPairs(self, head: ListNode) -> ListNode:
        # 两个相邻节点有一个为None时直接返回第一个节点，
        # 处理了到达链表末尾（包括奇数长度链表）的情况
        if not head or not head.next:
            return head
        p1 = head
        p2 = head.next
        # 翻转
        p1.next = self.swapPairs(p2.next)  # 第一个节点指向下一组相邻节点的第二个节点
        p2.next = p1                       # 第二个节点指向第一个节点
        return p2                          # 返回第二个节点，用于上一层p1.next = self.swapPairs(p2.next) 