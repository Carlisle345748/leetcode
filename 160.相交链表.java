/**
 * 双指针
 * 设置两个指针curA和curB，分别遍历链表A和B
 * 当curA到达链尾，则curA定位到headB
 * 当curB到达链尾，则curA定位到headA
 * 设链表A长度为a，链表B长度为b，共同部分长度为c
 * a + (b-c) = b + (a - c)
 * 所以：
 * (1)当c > 0, A和B有相交点，curA和curB会在相交点汇合
 * (2)当c == 0, A和B没有相交点，curA和curB在会同时到达链尾，同时等于null
 */
class Solution160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) { return null; }
        ListNode curA = headA, curB = headB;

        while (curA != curB) {
            curA = curA == null ? headB : curA.next;
            curB = curB == null ? headA : curB.next;
        }
        return curA;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(4);
        ListNode n2 = new ListNode(1);

        ListNode n3 = new ListNode(8);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);

        ListNode n6 = new ListNode(5);
        ListNode n7 = new ListNode(0);
        ListNode n8 = new ListNode(1);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        n6.next = n7;
        n7.next = n8;
        n8.next = n3;

        System.out.println(new Solution160().getIntersectionNode(n1, n6));
    }
}