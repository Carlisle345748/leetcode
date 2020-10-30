/**
 * Bottom-up 归并排序
 */
class Solution148_1 {
    public ListNode sortList(ListNode head) {
        if (head == null) { return null; }
        // 创建虚节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // 求链表长度
        int listLength = 0;
        while (head != null) {
            head = head.next;
            listLength++;
        }

        // 归并排序
        int window = 1;
        ListNode leftStart, rightStart;
        while (window < listLength) {
            ListNode cur = dummy.next;
            ListNode tail = dummy;
            while (cur != null) {
                // 检测左侧区间的长度是否达到窗口长度
                int length = window;
                leftStart = cur;
                while (length > 0 && cur != null) {
                    cur = cur.next;
                    length--;
                }
                if (length > 0) { break; } // 没有达到窗口长度，不继续merge
                
                // 计算右侧区间的长度
                rightStart = cur;
                length = window;
                while (length > 0 && cur != null) {
                    cur = cur.next;
                    length--;
                }
                
                // merge
                tail = merge(tail, leftStart, rightStart, window, window - length);
                tail.next = cur;  // 连接已经merge的部分和未merge部分(merge操作会打断连接关系，所以这里要修正)
            }
            window *= 2;
        }
        return dummy.next;
    }

    private ListNode merge(ListNode dummy, ListNode left, ListNode right, int leftCount, int rightCount) {
        ListNode cur = dummy;
        while (leftCount > 0 || rightCount > 0) {
            if (leftCount == 0) {
                cur.next = right;
                right = right.next;
                rightCount--;
            }
            else if (rightCount == 0){
                cur.next = left;
                left = left.next;
                leftCount--;
            }
            else {
                if (left.val < right.val) {
                    cur.next = left;
                    left = left.next;
                    leftCount--;
                }
                else {
                    cur.next = right;
                    right = right.next;
                    rightCount--;
                }
            }
            cur = cur.next;
        }
        return cur;
    }



    public static void main(String[] args) {
        ListNode test = ListNode.build(-1, 5, 3, 4, 0);
        ListNode result = new Solution148_1().sortList(test);
        System.out.println();
    }
}


/**
 * 归并排序，递归
 */
class Solution148_2 {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) { return head; }

        ListNode fast = head, slow = head;
        ListNode preSlow = null;  // slow前面的一个节点，用于切断2部分链表
        while (fast != null) {
            preSlow = slow;
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
        }
        preSlow.next = null; // 切断2个链表的连接，成为2个独立的链表

        ListNode left = sortList(head);  // 左链表排序
        ListNode right = sortList(slow); // 右链表排序

        // 创建虚节点
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        
        // merge左右两个链表
        while (left != null && right != null) {
            if (left.val < right.val) {
                cur.next = left;
                left = left.next;
            }
            else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        cur.next = (left == null) ? right : left;

        return dummy.next;
    }


    public static void main(String[] args) {
        ListNode test = ListNode.build(4, 2, 1, 3);
        ListNode result = new Solution148_2().sortList(test);
        System.out.println();
    }
}
