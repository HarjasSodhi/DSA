import java.util.*;

//testcase- [10,20,30,-2,-3,-4,5,6,7,8,9,22,11,13,14];
public class Heap {
    private ArrayList<Integer> arr;
    private int size = 0;
    private boolean isMaxHeap;

    Heap() {
        this.isMaxHeap = true;
        this.arr = new ArrayList<>();
    }

    Heap(int[] ele, boolean isMaxHeap) {
        this.isMaxHeap = isMaxHeap;
        this.size = ele.length;
        for (int i = 0; i < ele.length; i++) {
            this.arr.add(ele[i]);
        }
        for (int i = this.size - 1; i >= 0; i--) {
            downHeapify(i);
        }
    }

    public int size() {
        return this.size;
    }

    private boolean compareTo(int x, int y) {
        if (isMaxHeap) {
            return this.arr.get(x) > this.arr.get(y);
        }
        return this.arr.get(y) > this.arr.get(x);
    }

    private void swap(int x, int y) {
        int a = this.arr.get(x);
        int b = this.arr.get(y);

        this.arr.set(x, b);
        this.arr.set(y, a);
    }

    private void downHeapify(int pi) {
        int mi = pi;
        int lci = pi * 2 + 1;
        int rci = pi * 2 + 2;

        if (lci < this.size && compareTo(lci, mi)) {
            mi = lci;
        }

        if (rci < this.size && compareTo(rci, mi)) {
            mi = rci;
        }

        if (pi != mi) {
            swap(mi, pi);
            downHeapify(mi);
        }
    }

    private void upHeapify(int ci) {
        int pi = (ci - 1) / 2;
        if (pi >= 0 && compareTo(ci, pi)) {
            swap(pi, ci);
            upHeapify(pi);
        }
    }

    public int remove() {
        int re = this.arr.get(0);
        swap(0, this.size - 1);
        this.arr.remove(this.size - 1);
        downHeapify(0);
        this.size--;
        return re;
    }

    public void add(int num) {
        this.arr.add(num);
        this.size++;
        upHeapify(this.size - 1);
    }

    public int peek() {
        return this.arr.get(0);
    }

}