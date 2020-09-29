/** 
一次遍历: 用两个指针，一个快指针一个慢指针，快指针比慢指针领先n+1个节点，当快指针到达
链表末指向null时，慢指针刚好到达倒数第n-1个节点，然后可以使用慢指针删除倒数第n个节点。
为了处理只有一个节点的链表，使用一个哨兵节点sentinel，然后返回值为sentinel.next
*/
class Solution19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) { return null; }
        ListNode sentinel = new ListNode(-1);
        sentinel.next = head;

        ListNode slow = sentinel;
        ListNode fast = sentinel;
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;

        return sentinel.next;
    }
}