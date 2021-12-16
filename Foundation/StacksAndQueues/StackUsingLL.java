import java.util.*;
public class StackUsingLL {
    
    public static class Stack{
        private LinkedList <Integer> ll = new LinkedList<>();
        public int size(){
            return this.ll.size();
        }

        public boolean isEmpty(){
            return this.ll.isEmpty();
        }
        
        public void push(int val){
            this.ll.addFirst(val);
        }

        public int top(){
            return this.ll.getFirst();
        }

        public int pop(){
            return this.ll.removeFirst();
        }

    }
    
    public static void main(String[] args) {
        Stack ll=new Stack();
        ll.push(6);
        System.out.println(ll.top());
    }
}