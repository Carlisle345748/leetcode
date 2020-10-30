/**
 * 双指针
 * 快指针每次走2步，慢指针每次走一步。
 * 设快指针走过的路程为f，慢指针走过的路程为s，链表头部到环入口的路程为a，环的长度为b
 * 当快指针和慢指针相遇的时候，设快指针已经在环中循环了n圈，所以:
 * (1) f = 2s
 * (2) f = s + nb
 * 从上两式得到 s = nb
 * 为了求出a，我们巧妙的发现：慢指针再走a步就刚好到达环入口！
 * 新建一个指针prt，指向链表头部。让慢指针和ptr同时前行，当慢指针和ptr相遇时
 * prt和慢指针都刚好走了a步，到达了环入口，即我们的目标节点
 */
class Solution142 {
    public ListNode detectCycle(ListNode head) {
        if (head == null) { return null; }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null) { fast = fast.next; }
            else { return null; }

            if (fast == slow) {
                ListNode ptr = head;
                while(ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        System.out.println(new Solution142().detectCycle(node1));
    }
}