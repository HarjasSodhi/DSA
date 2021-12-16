public class MergeSort {

    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    // pay attention to how long the loop should run as it is differernt from array.
    public static ListNode getNode(ListNode head, int k) {
        if (head == null)
            return null;
        int i = 1;
        ListNode temp = head;
        while (i < k) {
            temp = temp.next;
            i++;
        }
        return temp;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode d1 = new ListNode(-1), c1 = d1, temp1 = l1, temp2 = l2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val < temp2.val) {
                c1.next = temp1;
                temp1 = temp1.next;
            } else {
                c1.next = temp2;
                temp2 = temp2.next;
            }
            c1 = c1.next;
        }
        if (temp1 != null) {
            c1.next = temp1;
        }
        if (temp2 != null) {
            c1.next = temp2;
        }
        return d1.next;
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

    public static ListNode midNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode temp1 = head;
        ListNode temp2 = head;
        while (temp2.next != null && temp2.next.next != null) {
            temp2 = temp2.next.next;
            temp1 = temp1.next;
        }

        return temp1;
    }

    public static ListNode mergeSortNew(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode mNode = midNode(head);
        ListNode nHead = mNode.next;
        mNode.next = null;
        return mergeTwoLists(mergeSortNew(head), mergeSortNew(nHead));
    }

    public static ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null)
            return head;
        int size = size(head);
        return mergeSort(head, 1, size);
    }

    public static ListNode mergeSort(ListNode head, int si, int ei) {
        if (si == ei) {
            ListNode k = getNode(head, si);
            return new ListNode(k.val);
        }
        ListNode l1 = mergeSort(head, si, (si + ei) / 2);
        ListNode l2 = mergeSort(head, ((si + ei) / 2) + 1, ei);
        return mergeTwoLists(l1, l2);
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        if (n == 0 || n == 1)
            return n == 0 ? null : lists[0];
        return mergeKLists(lists, 0, lists.length - 1);
    }

    public static ListNode mergeKLists(ListNode[] lists, int si, int ei) {
        if (si == ei) {
            return lists[si];
        }
        ListNode l1 = mergeKLists(lists, si, (si + ei) / 2);
        ListNode l2 = mergeKLists(lists, ((si + ei) / 2) + 1, ei);
        return mergeTwoLists(l1, l2);
    }

}