class Solution92 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n) { return head; }
        // start: m 的前一个节点，end：第m的节点，即翻转后翻转部分末尾节点
        ListNode start = null, end = null;       
        // prev: 当前翻转节点cur的前一个节点(由于m可能等于1，所需需要虚节点)  next: 当前翻转节点cur的下一个节点
        ListNode prev = new ListNode(-1), next;  
        // cur: 当前节点
        ListNode cur = head;                     
        int count = 0;
        while (cur != null && count < n) {
            count++;
            next = cur.next;
            if (count >= m) {
                // 到达翻转位置起点，记录start和end
                if (count == m) { 
                    start = prev;
                    end = cur;
                }
                // 到达翻转位置终点，使用start和end重新组合链表
                if (count == n) {
                    start.next = cur;
                    end.next = next;
                }
                // 翻转节点
                cur.next = prev;
            }
            prev = cur;
            cur = next;
        }
        return (m == 1) ? start.next : head; // 如果m=1，则head已经不是链表起点，否则课直接返回head
    }
}