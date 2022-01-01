public class QuickSort {

    public static class ListNode {
        int val = 0;
        ListNode next = null;
        ListNode random = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static int size(ListNode head) {
        int size = 0;
        ListNode temp = head;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        return size;
    }

    public static ListNode segregateOnLastIndex(ListNode head) {
        ListNode itr = head;
        ListNode prev = null;
        ListNode Last = head;
        while (Last.next != null)
            Last = Last.next;
        while (itr != null) {
            if (itr.val <= Last.val) {
                if (prev == null) {
                    prev = head;
                } else {
                    prev = prev.next;
                }
                int temp = prev.val;
                prev.val = itr.val;
                itr.val = temp;
            }
            itr = itr.next;
        }
        return prev;
    }

    public static ListNode[] segregate(ListNode head, int pivotIdx) {
        if (head == null || head.next == null)
            return new ListNode[] { null, head, null };
        ListNode itr = head;
        ListNode Last = head;
        while (Last.next != null)
            Last = Last.next;
        ListNode idx = head;
        while (pivotIdx-- > 0) {
            idx = idx.next;
        }

        ListNode sm = new ListNode(-1);
        ListNode lg = new ListNode(-1);
        ListNode sp = sm;
        ListNode lp = lg;

        while (itr != null) {
            if (itr != idx && itr.val <= idx.val) {
                sp.next = itr;
                sp = itr;
            } else if (itr != idx && itr.val > idx.val) {
                lp.next = itr;
                lp = itr;
            }
            itr = itr.next;
        }

        lp.next = sp.next = idx.next = null;

        return new ListNode[] { sm.next, idx, lg.next };
    }

    public static ListNode quickSort(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode[] ans = QSHelper(head);
        return ans[0];
    }

    public static ListNode[] QSHelper(ListNode head) {
        if (head == null || head.next == null)
            return new ListNode[] { head, head };
        int len = size(head);
        ListNode[] segrNodes = segregate(head, len / 2);
        ListNode[] left = QSHelper(segrNodes[0]);
        ListNode[] right = QSHelper(segrNodes[2]);
        return mergeList(left, segrNodes[1], right);
    }

    public static ListNode[] mergeList(ListNode[] left, ListNode pivot, ListNode[] right) {
        ListNode head = null, tail = null;
        if (left[0] != null && right[0] != null) {
            head = left[0];
            left[1].next = pivot;
            pivot.next = right[0];
            tail = right[1];
        } else if (left[0] != null) {
            head = left[0];
            left[1].next = pivot;
            tail = pivot;
        } else if (right[0] != null) {
            head = pivot;
            pivot.next = right[0];
            tail = right[1];
        } else {
            head = tail = pivot;
        }
        return new ListNode[] { head, tail };
    }
}