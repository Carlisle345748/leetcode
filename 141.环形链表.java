/**
 * 哈希表：记录出现过的节点
 */
class Solution141_1 {
    public boolean hasCycle(ListNode head) {
        if (head == null) { return false; }
        HashSet<ListNode> nodeSet = new HashSet<>();
        while (head != null) {
            if (nodeSet.contains(head)) {
                return true;
            }
            nodeSet.add(head);
            head = head.next;
        }
        return false;
    }
}

/**
 * 双指针：
 * 快指针+慢指针。快指针每次移动2个节点，慢指针每次移动一个节点。如果是环形链表，快指针最终肯定会倒回来追上慢指针
 * 如果中途出现了指针为null，则肯定不是环形链表
 */
class Solution141_2 {
    public boolean hasCycle(ListNode head) {
        if (head == null) { return false; }
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && slow != null) {
            if (fast == slow) { return true; }
            slow = slow.next;
            fast = fast.next;
            if (fast != null) { fast = fast.next; }  // 快指针每次移动2个节点
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode test = ListNode.build(-21,10,17,8,4,26,5,35,33,-7,-16,27,-12,6,29,-12,5,9,20,14,14,2,13,-24,21,23,-21,5);
        System.out.println(new Solution141_2().hasCycle(test));
    }
}