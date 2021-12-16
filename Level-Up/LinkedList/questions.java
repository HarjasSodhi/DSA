public class questions {

    public static class ListNode {
        int val = 0;
        ListNode next = null;
        ListNode random = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode getNode(ListNode head, int k) {
        if (k == 0) {// modified a little for add/subtract/multiply lists function
            ListNode dp = new ListNode(0);
            dp.next = head;
            return dp;
        }
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

    public static int size(ListNode head) {
        int size = 0;
        ListNode temp = head;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        return size;
    }

    public static ListNode removeDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode itr = head;
        ListNode curr = head;
        while (itr != null) {
            if (itr.val != curr.val) {
                curr.next = itr;
                curr = itr;
            }
            itr = itr.next;
        }
        curr.next = null;
        return head;
    }

    // new Method.using temporary head and tail
    // remove first add first node algo to reverse LL
    private static ListNode th = null, tt = null;

    public static void addFirst(ListNode node) {
        if (th == null) {
            th = tt = node;
        } else {
            node.next = th;
            th = node;
        }
    }

    public static ListNode reverseInKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1)
            return head;
        int size = size(head);
        // original head and original tail
        ListNode oh = null, ot = null;
        while (size >= k) {
            int tempK = k;
            while (tempK-- > 0) {
                ListNode temp = head;
                head = head.next;
                temp.next = null;
                addFirst(temp);
            }
            if (oh == null) {
                oh = th;
                ot = tt;
            } else {
                ot.next = th;
                ot = tt;
            }
            tt = th = null;
            size -= k;
        }
        ot.next = head;
        return oh;
    }

    public static ListNode reverseInRange(ListNode head, int n, int m) {
        if (head == null || head.next == null)
            return head;
        ListNode prev = null, curr = head, forward = head.next;
        int i = 1;
        while (i <= m) {
            if (i >= n && i <= m) {
                ListNode temp = curr;
                temp.next = null;
                addFirst(temp);
            } else {
                prev = curr;
            }
            curr = forward;
            forward = forward.next;
            i++;
        }
        if (prev != null)
            prev.next = th;
        else
            head = th;
        tt.next = curr;
        return head;
    }

    public static ListNode removeAllDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dp = new ListNode(-1);
        ListNode dpc = dp;
        ListNode prev = null, curr = head, forward = head.next;
        while (curr != null) {
            if ((prev == null || prev.val != curr.val) && (forward == null || forward.val != curr.val)) {
                dpc.next = curr;
                dpc = curr;
            }
            prev = curr;
            curr = forward;
            if (forward != null)
                forward = forward.next;
        }
        dpc.next = null;
        return dp.next;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null | l2 == null)
            return l1 == null ? l2 : l1;
        int a = size(l1);
        int b = size(l2);
        ListNode temp1, temp2;
        temp1 = a > b ? l1 : l2;
        temp2 = a < b ? l1 : l2;
        ListNode carryNode = getNode(temp1, Math.abs(a - b));
        int carry = addTwoNumbersHelper(carryNode.next, temp2);
        if (carry != 0) {
            carryNode.val += carry;
            if (Math.abs(a - b) == 0)
                return carryNode;
        }
        return temp1;
    }

    public static int addTwoNumbersHelper(ListNode l1, ListNode l2) {
        if (l1 == null)
            return 0;
        int carry = addTwoNumbersHelper(l1.next, l2.next);
        int ans = l1.val + l2.val + carry;
        carry = ans / 10;
        l1.val = ans % 10;
        return carry;
    }

    public static ListNode subtractTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null | l2 == null)
            return l1 == null ? l2 : l1;
        int a = size(l1);
        int b = size(l2);
        ListNode dn = new ListNode(-1), dnp = dn, temp1, temp2;
        if (a == b) {
            temp1 = l1.val >= l2.val ? l1 : l2;
            temp2 = l1.val < l2.val ? l1 : l2;
        } else {
            temp1 = a > b ? l1 : l2;
            temp2 = a < b ? l1 : l2;
        }
        temp1 = reverse(temp1);
        temp2 = reverse(temp2);
        int borrow = 0;
        while (temp1 != null) {
            int ans = (temp1 != null ? temp1.val : 0) - (temp2 != null ? temp2.val : 0) - borrow;
            if (ans < 0) {
                ans += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            dnp.next = new ListNode(ans);
            dnp = dnp.next;
            temp1 = temp1.next;
            if (temp2 != null)
                temp2 = temp2.next;
        }
        ListNode ansList = reverse(dn.next);
        ListNode itr = ansList;
        while (itr != null && itr.val == 0) {
            itr = itr.next;
            ansList = itr;
        }
        if (itr == null)
            return new ListNode(0);
        return ansList;
    }

    public static void addListsForMulti(ListNode prev, ListNode list) {
        int carry = 0;
        while (list != null || carry != 0) {
            int ans = carry + (list != null ? list.val : 0) + (prev.next != null ? prev.next.val : 0);
            carry = ans / 10;
            ans = ans % 10;
            if (prev.next != null) {
                prev.next.val = ans;
            } else {
                prev.next = new ListNode(ans);
            }
            prev = prev.next;
            if (list != null)
                list = list.next;
        }
    }

    public static ListNode multiplyDigit(ListNode list, int d) {
        ListNode dp = new ListNode(-1), dpn = dp, itr = list;
        int carry = 0;
        while (itr != null || carry != 0) {
            int ans = carry + ((itr != null ? itr.val : 0) * d);
            carry = ans / 10;
            ans = ans % 10;
            dpn.next = new ListNode(ans);
            dpn = dpn.next;
            if (itr != null)
                itr = itr.next;
        }
        return dp.next;
    }

    public static ListNode multiplyTwoLL(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            return new ListNode(0);
        l1 = reverse(l1);
        l2 = reverse(l2);
        ListNode ans = new ListNode(-1), prev = ans;
        while (l2 != null) {
            ListNode list = multiplyDigit(l1, l2.val);
            addListsForMulti(prev, list);
            prev = prev.next;
            l2 = l2.next;
        }
        return reverse(ans.next);
    }

    public static void copyListNodes(ListNode head) {
        ListNode curr = head, forward = head.next;
        while (curr != null) {
            ListNode node = new ListNode(curr.val);
            curr.next = node;
            node.next = forward;
            curr = forward;
            if (forward != null)
                forward = forward.next;
        }
    }

    public static void copyRandomNodes(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            if (curr.random != null)
                curr.next.random = curr.random.next;
            curr = curr.next.next;
        }
    }

    public static ListNode extractList(ListNode head) {
        ListNode curr = head, dn = new ListNode(-1), prev = dn;
        while (curr != null) {
            ListNode forward = curr.next.next;

            prev.next = curr.next;
            curr.next = forward;
            curr = forward;
            prev = prev.next;
        }
        return dn.next;
    }

    public static ListNode copyRandomList(ListNode head) {
        if (head == null)
            return null;
        if (head.next == null)
            return new ListNode(head.val);
        copyListNodes(head);
        copyRandomNodes(head);
        return extractList(head);
    }

}