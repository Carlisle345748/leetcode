class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    """
    一次遍历: 用两个指针，一个快指针一个慢指针，快指针比慢指针领先n+1个节点，当快指针到达
    链表末指向null时，慢指针刚好到达倒数第n+1个节点，然后可以使用慢指针删除倒数第n个节点。
    为了处理只有一个节点的链表，使用一个哨兵节点sentinel，然后返回值为sentinel.next
    """
    def removeNthFromEnd(self, head: ListNode, n: int) -> ListNode:
        sentinel = ListNode(0)
        sentinel.next = head
        fast = head
        slow = sentinel
        count = 0
        while fast:
            if (count >= n):
                slow = slow.next
            fast = fast.next
            count += 1
        slow.next = slow.next.next
        return sentinel.next

node1 = ListNode(1)
node2 = ListNode(2)
node3 = ListNode(3)
node4 = ListNode(4)
node5 = ListNode(5)

node1.next = node2
node2.next = node3
node3.next = node4
node4.next = node5

s1 = Solution()
s1.removeNthFromEnd(node1, 2)