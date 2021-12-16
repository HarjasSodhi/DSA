public class BoilerPlate {

    private class Node {
        int data = 0;
        Node next = null;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node node = this.head;
        while (node != null) {
            if (node.next == null)
                sb.append(node.data);
            else
                sb.append(node.data + ", ");
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }

    // ******************************************************************************

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    // **********************************************************

    private void addFirstNode(Node node) {
        if (size == 0) {
            this.head = this.tail = node;
        } else {
            node.next = this.head;
            this.head = node;
        }
        this.size++;
    }

    public void addFirst(int data) {
        Node node = new Node(data);
        addFirstNode(node);
    }

    private void addLastNode(Node node) {
        if (size == 0) {
            this.head = this.tail = node;
        } else {
            this.tail.next = node;
            this.tail = node;
        }
        this.size++;
    }

    public void addLast(int data) {
        Node node = new Node(data);
        addLastNode(node);
    }

    private void AddNodeAt(int data, int idx) {
        Node Beforeidx = getNodeAt(idx - 1);
        Node nodeAt = new Node(data);
        Node Afteridx = Beforeidx.next;
        nodeAt.next = Afteridx;
        Beforeidx.next = nodeAt;
        this.size++;
    }

    public void AddAt(int data, int idx) {
        if (idx < 0 || idx >= size) {
            return;
        } else if (idx == 0) {
            addFirst(data);
        } else if (idx == size) {
            addLast(data);
        } else
            AddNodeAt(data, idx);
    }

    // *******************************************************************************************
    private Node RemoveFirstNode() {
        Node node = this.head;
        if (size == 1) {
            this.head = this.tail = null;
        } else {
            this.head = this.head.next;
            node.next = null;
        }
        this.size--;
        return node;
    }

    public int RemoveFirst() {
        if (this.size == 0)
            return -1;
        Node node = RemoveFirstNode();
        return node.data;
    }

    private Node RemoveNodeAt(int idx) {
        Node Beforeidx = getNodeAt(idx - 1);
        Node node = Beforeidx.next;
        Beforeidx.next = node.next;
        node.next = null;
        this.size--;
        return node;
    }

    public int RemoveAt(int idx) {
        if (this.size == 0)
            return -1;
        else if (idx < 0 || idx >= size) {
            return -1;
        } else if (idx == 0) {
            return RemoveFirst();
        } else if (idx == size - 1) {
            return RemoveLast();
        } else
            return RemoveNodeAt(idx).data;
    }

    private Node RemoveLastNode() {
        Node temp = this.head;
        if (this.size == 1) {
            this.head = this.tail = null;
        } else {
            Node node = getNodeAt(size - 2);
            temp = node.next;
            node.next = null;
            this.tail = node;
        }
        this.size--;
        return temp;
    }

    public int RemoveLast() {
        if (this.size == 0)
            return -1;
        Node node = RemoveLastNode();
        return node.data;
    }

    // *******************************************************************************************
    private Node getFirstNode() {
        return this.head;
    }

    public int getFirst() {
        if (size == 0)
            return -1;
        return getFirstNode().data;
    }

    private Node getLastNode() {
        return this.tail;
    }

    public int getLast() {
        if (size == 0)
            return -1;
        return getLastNode().data;
    }

    private Node getNodeAt(int idx) {
        int i = 0;
        Node temp = head;
        while (i <= idx) {
            temp = temp.next;
            i++;
        }
        return temp;
    }

    public int getAt(int idx) {
        if (idx < 0 || idx >= this.size)
            return -1;
        else if (idx == 0)
            return getFirst();
        else if (idx == this.size)
            return getLast();
        else
            return getNodeAt(idx).data;
    }

    /*****************************************************************************************************/
    // questions//

    private Node returnMidNode() {
        Node front = this.head;
        Node back = this.head;
        boolean checker = true;
        while (front != tail) {
            if (checker) {
                front = front.next;
                checker = false;
            } else {
                front = front.next;
                back = back.next;
                checker = true;
            }
        }
        return back;
    }

    private Node returnMidNode(Node start, Node end) {
        Node front = start;
        Node back = start;
        boolean checker = true;
        while (front != end) {
            if (checker) {
                front = front.next;
                checker = false;
            } else {
                front = front.next;
                back = back.next;
                checker = true;
            }
        }
        return back;
    }

    public int returnMid() {
        if (this.size == 0)
            return -1;
        else if (this.size == 1)
            return getFirst();
        else
            return returnMidNode().data;
    }

    private Node returnKthNodeFromLast(int idx) {
        Node front = this.head;
        Node back = this.head;
        int i = 1;
        while (i <= idx) {
            front = front.next;
            i++;
        }
        while (front != tail) {
            front = front.next;
            back = back.next;
        }
        return back;
    }

    public int returnKthFromLast(int idx) {
        if (idx < 0 || idx >= size || this.size == 0)
            return -1;
        else if (idx == 0)
            return getLast();
        else if (idx == size - 1)
            return getFirst();
        else
            return returnKthNodeFromLast(idx).data;
    }

    public void OddEvenLinkedList() {
        if (this.head == null || this.head.next == null)
            return;
        Node oddHead = null;
        Node oddTail = null;
        Node evenHead = null;
        Node evenTail = null;
        Node curr = this.head;
        while (curr != null) {
            if (curr.data % 2 != 0) {
                if (oddHead == null) {
                    oddHead = oddTail = curr;
                } else {
                    oddTail.next = curr;
                    oddTail = curr;
                }
            } else {
                if (evenHead == null) {
                    evenHead = evenTail = curr;
                } else {
                    evenTail.next = curr;
                    evenTail = curr;
                }
            }
            curr = curr.next;
        }
        if (oddHead != null && evenHead != null) {
            oddTail.next = evenHead;
            evenTail.next = null;
            this.head = oddHead;
            this.tail = evenTail;
        } else if (oddHead == null) {
            this.head = evenHead;
            this.tail = evenTail;
        } else if (evenHead == null) {
            this.head = oddHead;
            this.tail = oddTail;
        }
    }

    public void removeDuplicates() {
        if (head == null || head.next == null)
            return;
        Node itr = this.head.next;
        Node beforeitr = this.head;
        while (itr != null) {
            if (beforeitr.data == itr.data) {
                itr = itr.next;
                if (itr == null)
                    beforeitr.next = null;
            } else {
                beforeitr.next = itr;
                beforeitr = itr;
                itr = itr.next;
            }
        }
    }

    public void ReverseListPointerVoid() {
        if (this.head == null || this.head.next == null)
            return;
        Node prev = this.head;
        Node mid = this.head.next;
        Node next = mid.next;

        prev.next = null;

        while (mid != null) {
            mid.next = prev;
            prev = mid;
            mid = next;
            if (next != null)
                next = next.next;
        }
        Node temp = this.head;
        this.head = this.tail;
        this.tail = temp;
    }

    /* add two linked Lists question -pepcoding */

    public Node ReverseListPointerNode(BoilerPlate L1) {
        if (L1.head == null || L1.head.next == null)
            return L1.head;
        Node prev = L1.head;
        Node mid = L1.head.next;
        Node next = mid.next;

        prev.next = null;

        while (mid != null) {
            mid.next = prev;
            prev = mid;
            mid = next;
            if (next != null)
                next = next.next;
        }
        return prev;
    }

    // public Node addTwoNumbers(Node H1, Node H2) {

    // Node c1 = ReverseListPointerNode(H1);
    // Node c2 = ReverseListPointerNode(H2);
    // int carry = 0;
    // Node ans = new Node(-1);
    // Node itr = ans;
    // while (c1 != null || c2 != null || carry != 0) {
    // int sum = (c1 != null ? c1.data : 0) + (c2 != null ? c2.data : 0) + carry;
    // carry = sum / 10;
    // sum = sum % 10;
    // Node node = new Node(sum);
    // itr.next = node;
    // itr = node;
    // if (c1 != null)
    // c1 = c1.next;
    // if (c2 != null)
    // c2 = c2.next;
    // }
    // return ReverseListPointer(ans.next);
    // }

    Node fleft;

    private void FoldHelper(Node frigth, int floor) {
        if (frigth == null) {
            return;
        }
        FoldHelper(frigth.next, floor + 1);

        if (floor > size / 2) {
            Node temp = fleft.next;
            fleft.next = frigth;
            frigth.next = temp;

            fleft = temp;
        } else if (floor == size / 2) {
            tail = frigth;
            tail.next = null;
        }
    }

    public void FoldList() {
        fleft = head;
        FoldHelper(fleft, 0);
        /*
         * another way to fold is to reverse second half of the linked list and then
         * make connections accordingly
         */
    }

    private void displayReverseHelper(Node node) {
        if (node == null) {
            return;
        }
        displayReverseHelper(node.next);
        System.out.print(node.data + " ");
    }

    public void displayReverseRecusrsion() {
        displayReverseHelper(head);
        System.out.println();
    }

    private void reversePRHelper(Node node) {
        if (node == tail) {
            return;
        }
        reversePRHelper(node.next);
        node.next.next = node;
    }

    public void reverseListRecursion() {
        reversePRHelper(head);
        Node temp = head;
        head = tail;
        tail = temp;
        tail.next = null;
    }

    public BoilerPlate addTwoListsRecursion(BoilerPlate one, BoilerPlate two) {
        int carry = 0;
        BoilerPlate ans = new BoilerPlate();
        carry = addtwohelper(one.head, one.size, two.head, two.size, ans);
        if (carry != 0) {
            ans.addFirst(carry);
        }
        return ans;
    }

    private int addtwohelper(Node h1, int size1, Node h2, int size2, BoilerPlate ans) {
        if (h1 == null && h2 == null)
            return 0;
        int carry;
        if (size1 > size2) {
            carry = addtwohelper(h1.next, size1 - 1, h2, size2, ans);
            int sum = h1.data + carry;
            int newcarry = sum / 10;
            sum = sum % 10;
            ans.addFirst(sum);
            return newcarry;
        } else if (size2 > size1) {
            carry = addtwohelper(h1, size1, h2.next, size2 - 1, ans);
            int sum = h2.data + carry;
            int newcarry = sum / 10;
            sum = sum % 10;
            ans.addFirst(sum);
            return newcarry;
        } else {
            carry = addtwohelper(h1.next, size1 - 1, h2.next, size2 - 1, ans);
            int sum = h1.data + h2.data + carry;
            int newcarry = sum / 10;
            sum = sum % 10;
            ans.addFirst(sum);
            return newcarry;
        }
    }

    private void Merge2listsHelper(Node head1, Node head2, BoilerPlate ans) {
        Node c1 = head1;
        Node c2 = head2;
        while (c1 != null && c2 != null) {
            if (c1.data < c2.data) {
                ans.addLast(c1.data);
                c1 = c1.next;
            } else {
                ans.addLast(c2.data);
                c2 = c2.next;
            }
        }
        while (c1 != null) {
            ans.addLast(c1.data);
            c1 = c1.next;
        }
        while (c2 != null) {
            ans.addLast(c2.data);
            c2 = c2.next;
        }
        c1 = null;
        c2 = null;
    }

    public BoilerPlate MergeTwoSortedLists(BoilerPlate L1, BoilerPlate L2) {
        if (L1.head == null || L2.head == null)
            return L1.head == null ? L2 : L1;
        BoilerPlate ans = new BoilerPlate();
        Merge2listsHelper(L1.head, L2.head, ans);
        return ans;
        // can also use dummy pointer approach because it does not use any extra memory
        // but destroyes original lists.
    }

    private BoilerPlate MergeSortHelper(Node start, Node end) {
        if (start == end) {
            BoilerPlate base = new BoilerPlate();
            base.addLast(start.data);
            return base;
        }
        Node mid = returnMidNode(start, end);
        BoilerPlate ans1 = MergeSortHelper(start, mid);
        BoilerPlate ans2 = MergeSortHelper(mid.next, end);

        return MergeTwoSortedLists(ans1, ans2);
    }

    public BoilerPlate MergeSort(BoilerPlate L1) {
        if (L1.head == null || L1.head.next == null) {
            return L1;
        }
        return MergeSortHelper(L1.head, L1.tail);
        // if you want to do without tail pointer then always get the mid node and then
        // put a pointer on mid.next and then put mid.next=null so that the link is
        // broken and then
        // pass it to merge lists function
        // https://leetcode.com/problems/sort-list/discuss/1243713/Java-or-sorting-by-merge-sort-or-O(nlogn)
    }

    Node fleft2;

    private boolean PalindromeHelper(Node fright) {
        if (fright == null)
            return true;
        boolean ans;
        ans = PalindromeHelper(fright.next);
        if (ans == false)
            return false;
        if (ans == true) {
            if (fleft2.data == fright.data) {
                fleft2 = fleft2.next;
                ans = true;
            } else
                ans = false;
        }
        return ans;
    }

    public boolean IsPalindromeRecursive() {
        fleft2 = head;
        return PalindromeHelper(fleft2);
    }

    public boolean Ispalindrome1() {
        if (head == null || head.next == null)
            return true;
        String str1 = "";
        String str2 = "";
        Node ptr = head;
        while (ptr != null) {
            str1 = str1 + ptr.data;
            ptr = ptr.next;
        }
        ReverseListPointerVoid();
        ptr = head;
        while (ptr != null) {
            str2 = str2 + ptr.data;
            ptr = ptr.next;
        }
        ReverseListPointerVoid();
        if (str1.equals(str2))
            return true;
        else
            return false;
        // you can also modify the reverse function to return a string of data as
        // well as reverse the string and run the same function two times and compare
        // the two strings similarly
        // then comp-2n
        // can also use recursion similar to fold array method
        //
        // or break string from middle and start traversing both halves and if any
        // pointer reaches null then palindrome is true
    }

    public int FindIntersection(BoilerPlate one, BoilerPlate two) {
        int s1 = one.size();
        int s2 = two.size();
        Node greater = s1 > s2 ? one.head : two.head;
        Node smaller = s1 > s2 ? two.head : one.head;
        int diff = Math.abs(s1 - s2);
        while (diff > 0) {
            greater = greater.next;
            diff--;
        }
        while (greater != smaller) {
            greater = greater.next;
            smaller = smaller.next;
            if (greater == null || smaller == null)
                return -1;
        }
        return greater.data;
    }

}