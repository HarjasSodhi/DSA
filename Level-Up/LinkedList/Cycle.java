public class Cycle {

    // class for maths of this concept
    // https://classroom.pepcoding.com/the-placement-program-pitampura-mar-14-2021/116/classvideos/4737/#
    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    // leetcode 141
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;
        if (head.next == head)
            return true;
        ListNode temp1 = head;
        ListNode temp2 = head;
        while (temp2 != null && temp2.next != null) {
            temp1 = temp1.next;
            if (temp2.next != null)
                temp2 = temp2.next.next;
            if (temp1 == temp2)
                return true;
        }
        return false;
    }

    // leetcode 142
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow)
                break;
        }
        if (slow != fast)
            return null;
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    // leetcode 160
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        ListNode tail = headA;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = headB;
        ListNode ans = detectCycle(headA);
        tail.next = null;
        return ans;
    }

    // All Variable
    public int getCycleLen(ListNode mp) {
        int cycleLen = 1;
        ListNode curr = mp.next;

        while (curr != mp) {
            curr = curr.next;
            cycleLen++;
        }

        return cycleLen;
    }

    public ListNode cycleVariable(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow)
                break;
        }

        if (slow != fast)
            return null;

        slow = head;
        ListNode mp = fast; // meetingPoint
        int cycleCount = 0;
        int A = 0;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;

            if (mp == fast)
                cycleCount++;
            A++;
        }

        int cycleLen = getCycleLen(mp);
        int m = 0, C = 0, B = 0;
        if (A != 0 && A % cycleLen == 0) {
            m = cycleCount - 1;
            B = cycleLen;
        } else {
            m = cycleCount + 1;
            C = A - cycleCount * cycleLen;
            B = cycleLen - C;
        }
        System.out.println(m + " " + B);
        return slow;
    }

}