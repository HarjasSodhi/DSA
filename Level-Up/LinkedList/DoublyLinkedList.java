public class DoublyLinkedList {

    private class Node {
        int data = 0;
        Node prev = null;
        Node next = null;

        Node(int data) {
            this.data = data;
        }

    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    public void addFirst(int val) {
        Node temp = new Node(val);
        if (head == null) {
            head = tail = temp;
        } else {
            temp.next = head;
            head.prev = temp;
            head = temp;
        }
    }

    public void addLast(int val) {
        Node temp = new Node(val);
        if (head == null) {
            head = tail = temp;
        } else {
            tail.next = temp;
            temp.prev = tail;
            tail = temp;
        }
    }

    private Node removeFirstNode() {
        Node node = this.head;
        if (this.size == 1)
            this.head = this.tail = null;
        else {
            Node nextNode = this.head.next;
            nextNode.prev = null;
            node.next = null;

            this.head = nextNode;
        }

        this.size--;
        return node;
    }

    private boolean ListIsEmptyException() {
        if (this.size == 0) {
            System.out.print("ListIsEmpty: ");
            return true;
        }
        return false;
    }

    public int removeFirst() {
        if (ListIsEmptyException())
            return -1;
        Node node = removeFirstNode();
        return node.data;
    }

    private Node removeLastNode() {
        Node node = this.tail;
        if (this.size == 1)
            this.head = this.tail = null;
        else {
            Node prevNode = this.tail.prev;
            prevNode.next = null;
            node.prev = null;

            this.tail = prevNode;
        }

        this.size--;
        return node;
    }

    public int removeLast() {
        if (ListIsEmptyException())
            return -1;
        Node node = removeLastNode();
        return node.data;
    }

    public int getFirst() {
        return head.data;
    }

    public int getLast() {
        return tail.data;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int getAt(int index) {
        if (index < 1 || index > size)
            return -1;
        int i = 0;
        Node temp = head;
        while (i < index) {
            temp = temp.next;
            i++;
        }
        return temp.data;
    }

    public void displayForw() {
        Node temp = head;
        System.out.print("[");
        while (temp != null) {
            if (temp.next == null) {
                System.out.print(temp.data);
            } else {
                System.out.print(temp.data + ", ");
            }
            temp = temp.next;
        }
        System.out.println("]");
    }

    public void displayBack() {
        Node temp = tail;
        System.out.print("[");
        while (temp != null) {
            if (temp.prev == null) {
                System.out.print(temp.data);
            } else {
                System.out.print(temp.data + ", ");
            }
            temp = temp.prev;
        }
        System.out.println("]");
    }
    
}