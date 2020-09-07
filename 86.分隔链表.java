class Solution86 {
    public ListNode partition(ListNode head, int x) {
        ListNode left = new ListNode(-1);  // 存放小于x的node，使用dummy node初始化
        ListNode right = new ListNode(-1); // 存放大于等于x的node，使用dummy node初始化
        
        ListNode lp = left;
        ListNode rp = right;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            // 放到左边
            if (cur.val < x) {
                lp.next = cur;
                lp = lp.next;
            } 
            // 放到右边
            else {
                rp.next = cur;
                rp = rp.next;
            }
        }
        // 记得右链表中的最后一个node的next要设为null，否则可能形成循环链表，左链表最后一个node修改为指向右链表，故不会导致循环
        rp.next = null;
        // 左链表和右链表连接
        lp.next = right.next;
        // 返回结果
        return left.next;
    }
}