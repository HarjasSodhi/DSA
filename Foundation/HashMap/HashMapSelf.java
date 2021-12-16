import java.util.*;

public class HashMapSelf {

    private class Node {
        Integer key = null;
        Integer value = null;

        public Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Node>[] buckets;
    private Integer totalNoOfNode = 0;
    private Integer bucketLen = 0;

    private void intialise(Integer size) {
        this.buckets = new LinkedList[size];
        this.bucketLen = size;
        for (int i = 0; i < size; i++) {
            this.buckets[i] = new LinkedList<>();
        }
        this.totalNoOfNode = 0;
    }

    public HashMapSelf() {
        intialise(10);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int tempSize = this.totalNoOfNode;
        for (int i = 0; i < this.bucketLen; i++) {
            LinkedList<Node> group = this.buckets[i];
            int size = group.size();
            while (size-- > 0) {

                Node node = group.removeFirst();
                sb.append(node.key + "=" + node.value);
                group.addLast(node);

                if (--tempSize != 0)
                    sb.append(",");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public Integer size() {
        return this.totalNoOfNode;
    }

    public boolean isEmpty() {
        return this.totalNoOfNode == 0;
    }

    public ArrayList<Integer> keySet() {
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < bucketLen; i++) {
            Integer gs = buckets[i].size();
            while (gs-- > 0) {
                ans.add(buckets[i].getFirst().key);
                buckets[i].addLast(buckets[i].removeFirst());
            }
        }
        return ans;
    }

    private Integer getHashCode(Integer key) {
        return Math.abs(key.hashCode()) % bucketLen;
    }

    private LinkedList<Node> getGroup(Integer key) {
        LinkedList<Node> group = buckets[getHashCode(key)];
        return group;
    }

    private void rehash() {
        LinkedList<Node>[] temp = this.buckets;
        intialise(this.bucketLen * 2);
        bucketLen *= 2;
        for (int i = 0; i < temp.length; i++) {
            LinkedList<Node> group = temp[i];
            int gs = group.size();
            while (gs-- > 0) {
                Node node = group.removeFirst();
                put(node.key, node.value);
            }
        }
    }

    public void put(Integer key, Integer value) {
        boolean res = containsKey(key);
        LinkedList<Node> group = getGroup(key);
        if (res) {
            group.getFirst().value = value;
        } else {
            Node node = new Node(key, value);
            group.addLast(node);
            this.totalNoOfNode++;

            double lambda = group.size() / (1.0 * this.bucketLen);
            if (lambda > 0.4) {
                rehash();
            }
        }
    }

    public void putIfAbsent(Integer key, Integer DefaultValue) {
        if (!containsKey(key)) {
            LinkedList<Node> group = getGroup(key);
            Node node = new Node(key, DefaultValue);
            group.addLast(node);
            this.totalNoOfNode++;
            double lambda = group.size() / (1.0 * this.bucketLen);
            if (lambda > 0.4) {
                rehash();
            }
        }
    }

    public boolean containsKey(Integer key) {
        LinkedList<Node> group = getGroup(key);
        Integer gs = group.size();
        while (gs-- > 0) {
            if (group.getFirst().key.equals(key)) {
                return true;
            }
            group.addLast(group.removeFirst());
        }
        return false;
    }

    public Integer get(Integer key) {
        if (containsKey(key)) {
            LinkedList<Node> group = getGroup(key);
            return group.getFirst().value;
        }
        return null;
    }

    public Integer remove(Integer key) {
        if (containsKey(key)) {
            LinkedList<Node> group = getGroup(key);
            this.totalNoOfNode--;
            return group.removeFirst().value;
        }
        return null;
    }

    public Integer getOrDefault(Integer key, Integer defaultVal) {
        Integer res = get(key);
        return res != null ? res : defaultVal;
    }

}