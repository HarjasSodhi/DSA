import java.util.*;

public class QueueUsingLL {

    public static class Queue {
        LinkedList<Integer> ll = new LinkedList<>();

        public int size() {
            return this.ll.size();
        }

        public boolean isEmpty() {
            return this.ll.isEmpty();
        }

        public int peek() {
            return this.ll.getFirst();
        }

        public void add(int val) {
            this.ll.addLast(val);
        }

        public int remove() {
            return this.ll.removeFirst();
        }

    }

    public static void main(String[] args) {
        Queue ll=new Queue();
        ll.add(8);
        System.out.println(ll.peek());
    }
}