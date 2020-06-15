"""
https://leetcode-cn.com/problems/remove-linked-list-elements/solution/yi-chu-lian-biao-yuan-su-by-leetcode/
"""

class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution1:
    """
    用哨兵节点
    """
    def removeElements(self, head: ListNode, val: int) -> ListNode:
        sentinel = ListNode(0)
        sentinel.next = head
        current, prev = head, sentinel
        while current:
            if current.val == val:
                prev.next = current.next
            else:
                prev = current
            current = current.next
        
        return sentinel.next

class Solution2:
    """
    不用哨兵节点
    """
    def removeElements(self, head: ListNode, val: int) -> ListNode:
        prev = None
        current = head
        while current:
            if current.val == val:
                if prev is None:
                    head = current.next
                else:
                    prev.next = current.next
            else:
                prev = current
            current = current.next
        
        return head



node1 = ListNode(1)
node2 = ListNode(1)
node1.next = node2

s1 = Solution()
s1.removeElements(node1, 1)