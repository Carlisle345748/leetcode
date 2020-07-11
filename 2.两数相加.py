class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    """
    https://leetcode-cn.com/problems/add-two-numbers/solution/liang-shu-xiang-jia-by-leetcode/
    """
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        result = ListNode(0)
        cur = result
        plusNext = 0
        while (l1 or l2):
            l1_Val = l1.val if l1 else 0
            l2_Val = l2.val if l2 else 0
            temp = l1_Val + l2_Val + plusNext
            plusNext = temp // 10
            cur.next = ListNode(temp % 10)
            cur = cur.next
            if (l1): l1 = l1.next
            if (l2): l2 = l2.next

        if plusNext != 0:
            cur.next = ListNode(plusNext)
        return result.next