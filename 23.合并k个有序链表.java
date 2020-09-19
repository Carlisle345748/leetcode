import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 优先队列法
 * 使用优先队列储存所有链表，key为链表的第一个元素。每次从优先队列中取出的链表的第一个元素都是最小值。
 * 然后从链表中删去使用过的节点，(如果链表还有节点)重新放入优先队列。重复知道优先队列为空。
 */
class Solution23_1 {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparing(n -> n.val));
        ListNode result = new ListNode(-1);
        ListNode cur = result;

        for (ListNode node : lists) {
            if (node != null) {
                pq.add(node);
            }
        }

        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll();
            cur.next = minNode;
            cur = cur.next;
            if (minNode.next != null) {
                pq.add(minNode.next);
            }
        }
        return result.next;
    }
}

/**
 * 分治法(归并排序思想)
 */
class Solution23_2 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) { return null; }
        return sort(lists, 0, lists.length - 1);
    }

    private ListNode sort(ListNode[] lists, int lo, int hi) {
        // 当区间内只有一个链表，可以直接返回
        if (lo == hi) { return lists[lo]; }

        int mid = lo + ((hi - lo) >> 1);

        // 对左侧的所有链表和右侧的所有链表进行排序，并合成一个左链表和右链表
        ListNode left = sort(lists, lo, mid);
        ListNode right = sort(lists, mid + 1, hi);
        // 合并左侧和右侧链表
        return merge(left, right);
    }

    // 合并两个有序链表
    private ListNode merge(ListNode left, ListNode right) {
        if (left == null && right == null) { return null; }
        if (left == null) { return right; }
        if (right == null) { return left; }


        ListNode result = new ListNode(-1);
        ListNode cur = result;
        while (left != null || right != null) {
            if      (left == null)         { cur.next = right; right = right.next; }
            else if (right == null)        { cur.next = left; left = left.next; }
            else if (left.val < right.val) { cur.next = left; left = left.next; }
            else                           { cur.next = right; right = right.next; }
            cur = cur.next;
        }
        return result.next;
    }
}

/**
 * 顺序合并
 * 最朴素的方法：用一个变量 result 来维护以及合并的链表，第 i 次循环把第 i 个链表和 result 合并，答案保存到 result 中。
 */
class Solution23_3 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) { return null; }
        ListNode result = null;
        for (ListNode list : lists) {
            result = merge(result, list);
        }
        return result;
    }

    // 合并两个有序链表
    private ListNode merge(ListNode left, ListNode right) {
        if (left == null && right == null) { return null; }
        if (left == null) { return right; }
        if (right == null) { return left; }

        ListNode result = new ListNode(-1);
        ListNode cur = result;
        while (left != null || right != null) {
            if      (left == null)         { cur.next = right; right = right.next; }
            else if (right == null)        { cur.next = left; left = left.next; }
            else if (left.val < right.val) { cur.next = left; left = left.next; }
            else                           { cur.next = right; right = right.next; }
            cur = cur.next;
        }
        return result.next;
    }
}