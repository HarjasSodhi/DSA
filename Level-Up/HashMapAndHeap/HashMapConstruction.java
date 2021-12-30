import java.util.*;

public class HashMapConstruction {
    private class ll {

        private class node {
            Integer val;
            Integer key;
            node next;

            private node(Integer val, Integer key) {
                this.val = val;
                this.key = key;
            }
        }

        private node head;
        private node tail;
        private Integer llSize;

        private ll() {
            head = null;
            tail = null;
            llSize = 0;
        }

        private void addLast(node temp) {
            if (head == null) {
                head = tail = temp;
            } else {
                tail.next = temp;
                tail = temp;
            }
            llSize++;
        }

        private void addLast(Integer val, Integer key) {
            node temp = new node(val, key);
            if (head == null) {
                head = tail = temp;
            } else {
                tail.next = temp;
                tail = temp;
            }
            llSize++;
        }

        private Integer size() {
            return llSize;
        }

        private node getFirst() {
            return this.head;
        }

        private node removeFirst() {
            if (llSize == 0)
                return null;
            node temp = head;
            if (llSize == 1) {
                head = tail = null;
            } else {
                head = head.next;
            }
            llSize--;
            return temp;
        }
    }

    private ll[] arr;
    private int size = 0;

    public void assignValues(Integer size) {
        arr = new ll[size];
        for (Integer i = 0; i < size; i++) {
            arr[i] = new ll();
        }
    }

    public HashMapConstruction() {
        assignValues(10);
    }

    private void rehash() {
        ll[] backup = arr;
        assignValues(backup.length * 2);

        for (int i = 0; i < backup.length; i++) {
            ll temp = backup[i];
            int llsize = temp.size();
            while (llsize-- > 0) {
                int val = temp.getFirst().val;
                int key = temp.getFirst().key;
                put(key, val);
                temp.removeFirst();
            }
        }

    }

    public String display() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i = 0; i < arr.length; i++) {
            ll temp = arr[i];
            int listSize = temp.size();
            while (listSize-- > 0) {
                sb.append(temp.getFirst().key + ":" + temp.getFirst().val + " ");
                temp.addLast(temp.removeFirst());
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public void putIfAbsent(int key, int val) {
        if (!containskey(key)) {
            put(key, val);
        }
    }

    public void put(int key, int val) {
        ll temp = getList(key);
        if (containskey(key)) {
            temp.getFirst().val = val;
        } else {
            temp.addLast(val, key);
            this.size++;

            double lambda = (temp.size() / this.arr.length * 1.0);
            if (lambda > 0.4) {
                rehash();
            }
        }
    }

    public int size() {
        return this.size;
    }

    public ArrayList<Integer> keySet() {
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            ll temp = arr[i];
            int listSize = temp.size();
            while (listSize-- > 0) {
                ans.add(temp.getFirst().key);
                temp.addLast(temp.removeFirst());
            }
        }
        return ans;
    }

    public Integer remove(int key) {
        ll temp = getList(key);
        int size = temp.size();
        while (size-- > 0) {
            if (temp.getFirst().key == key) {
                this.size--;
                return temp.removeFirst().val;
            } else {
                temp.addLast(temp.removeFirst());
            }
        }
        return null;
    }

    public Integer getOrDefault(int key, Integer defaultVal) {
        Integer temp = get(key);
        if (temp != null)
            return temp;
        return defaultVal;
    }

    public Integer get(int key) {
        ll temp = getList(key);
        int size = temp.size();
        while (size-- > 0) {
            if (temp.getFirst().key == key) {
                return temp.getFirst().val;
            } else {
                temp.addLast(temp.removeFirst());
            }
        }
        return null;
    }

    public boolean containskey(int key) {
        ll temp = getList(key);
        int size = temp.size();
        while (size-- > 0) {
            if (temp.getFirst().key == key) {
                return true;
            } else {
                temp.addLast(temp.removeFirst());
            }
        }
        return false;
    }

    private ll getList(int key) {
        int code = hashCode(key);
        return arr[code];
    }

    private int hashCode(Integer key) {
        return key.hashCode() % arr.length;
    }

}