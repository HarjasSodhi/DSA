import java.util.*;

class LRUCache {

    private class Node {
        Node next = null;
        Node prev = null;
        int val;
        int key;

        Node(int key, int val) {
            this.val = val;
            this.key = key;
        }
    }

    private Node head, tail;
    private int size;
    private int maxSize;
    private HashMap<Integer, Node> map;

    public LRUCache(int capacity) {
        this.maxSize = capacity;
        this.size = 0;
        this.head = null;
        this.tail = null;
        this.map = new HashMap<>();
    }

    private void bringFront(Node node) {
        if (size == 1 || node == head)
            return;
        if (node == tail)
            tail = node.prev;
        Node next = node.next;
        Node prev = node.prev;
        node.next = null;
        node.prev = null;
        if (prev != null) {
            prev.next = next;
        }
        if (next != null) {
            next.prev = prev;
        }
        addFirst(node);
    }

    private void addFirst(Node node) {
        if (size == 0) {
            head = tail = node;
        } else {
            head.prev = node;
            node.next = head;
            head = node;
        }
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        Node node = map.get(key);
        bringFront(node);
        return node.val;
    }

    private void remove() {
        map.remove(tail.key);
        if (size == 1) {
            head = tail = null;
        } else if (size > 1) {
            Node node = tail;
            tail = tail.prev;
            node.prev = null;
            tail.next = null;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            bringFront(node);
        } else {
            if (size == maxSize) {
                remove();
                size--;
            }
            Node node = new Node(key, value);
            map.put(key, node);
            addFirst(node);
            size++;
        }
    }
}