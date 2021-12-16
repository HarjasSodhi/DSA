class Revision {

    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
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

    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode curr = head.next;
        ListNode prev = head;
        ListNode forward = head.next.next;
        prev.next = null;
        while (curr != null) {
            curr.next = prev;
            prev = curr;
            curr = forward;
            if (forward != null)
                forward = forward.next;
        }
        head = prev;
        return head;
    }

    public static ListNode newhead;

    public static ListNode reverseRecursiveHelper(ListNode node) {
        if (node == null)
            return null;
        ListNode recans = reverseRecursiveHelper(node.next);
        if (recans != null) {
            recans.next = node;
        } else
            newhead = node;
        return node;
    }

    public static ListNode reverseRecursive(ListNode head) {
        if (head.next == null || head == null)
            return head;
        ListNode temp = reverseRecursiveHelper(head);
        temp.next = null;
        return newhead;
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;
        ListNode temp = head;
        String s1 = "";
        while (temp != null) {
            s1 += temp.val;
            temp = temp.next;
        }
        head = reverse(head);
        temp = head;
        String s2 = "";
        while (temp != null) {
            s2 += temp.val;
            temp = temp.next;
        }
        reverse(head);
        if (s1.equals(s2))
            return true;
        return false;
        // or find mid and reverse only second half of the linked list and then use 2
        // pointer method;
    }

    public static void fold(ListNode head) {
        if (head == null || head.next == null || head.next.next == null)
            return;
        ListNode mid = midNode(head);
        ListNode temp1 = head;
        ListNode temp2 = reverse(mid);
        while (temp1 != null) {
            ListNode a = temp1.next;
            ListNode b = temp2.next;
            temp1.next = temp2;
            temp2.next = a;
            temp1 = a;
            temp2 = b;
        }
    }

    public static void unfold(ListNode head) {
        if (head == null || head.next == null)
            return;
        ListNode temp1 = head;
        ListNode temp2 = head.next;
        ListNode h2 = head.next;
        while (temp2 != null && temp2.next == null) {
            ListNode a = temp1.next.next;
            ListNode b = temp2.next.next;
            temp1.next = a;
            temp2.next = b;
            temp1 = a;
            temp2 = b;
        }
        h2 = reverse(h2);
        temp1.next = h2;
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
        while (temp1 != null) {
            c1.next = temp1;
            temp1 = temp1.next;
            c1 = c1.next;
        }
        while (temp2 != null) {
            c1.next = temp2;
            temp2 = temp2.next;
            c1 = c1.next;
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

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (n == 0 || head == null)
            return head;
        int size = size(head);
        if (n == size) {
            return head.next;
        }
        ListNode temp = head;
        for (int i = 0; i < size - n - 1; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        return head;
    }

    public static ListNode segregateEvenOdd(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode h1 = null;
        ListNode h2 = null;
        ListNode temp1 = null;
        ListNode temp2 = null;
        ListNode itr = head;
        while (itr != null) {
            if (itr.val % 2 == 0) {
                if (h1 == null) {
                    h1 = temp1 = itr;
                } else {
                    temp1.next = itr;
                    temp1 = itr;
                }
            } else {
                if (h2 == null) {
                    h2 = temp2 = itr;
                } else {
                    temp2.next = itr;
                    temp2 = itr;
                }
            }
            itr = itr.next;
        }
        if (temp1 == null) {
            return h2;
        }
        if (temp2 == null) {
            return h1;
        }
        temp2.next = null;
        temp1.next = h2;
        return h1;
    }

    public static ListNode segregate01(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode h1 = null;
        ListNode h2 = null;
        ListNode temp1 = null;
        ListNode temp2 = null;
        ListNode itr = head;
        while (itr != null) {
            if (itr.val == 0) {
                if (h1 == null) {
                    h1 = temp1 = itr;
                } else {
                    temp1.next = itr;
                    temp1 = itr;
                }
            } else {
                if (h2 == null) {
                    h2 = temp2 = itr;
                } else {
                    temp2.next = itr;
                    temp2 = itr;
                }
            }
            itr = itr.next;
        }
        if (temp1 == null) {
            return h2;
        }
        if (temp2 == null) {
            return h1;
        }
        temp2.next = null;
        temp1.next = h2;
        return h1;
    }

    // dummy node approach
    // best approach //dry run as much possible
    public static ListNode segregate012(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode d1 = new ListNode(-1), d2 = new ListNode(-1), d3 = new ListNode(-1), temp1 = d1, temp2 = d2,
                temp3 = d3, itr = head;
        while (itr != null) {
            if (itr.val == 0) {
                temp1.next = itr;
                temp1 = itr;
            } else if (itr.val == 1) {
                temp2.next = itr;
                temp2 = itr;
            } else {
                temp3.next = itr;
                temp3 = itr;
            }
            itr = itr.next;
        }
        // maintain this order
        temp3.next = null;
        temp2.next = d3.next;
        temp1.next = d2.next;
        return d1.next;
    }

}