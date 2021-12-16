import java.util.*;;

public class Heap {

    private ArrayList<Integer> arr;
    private int noOfEle = 0;
    private boolean IsMaxHeap = true;

    private void initialize(boolean IsMaxHeap) {
        arr = new ArrayList<>();
        noOfEle = 0;
        this.IsMaxHeap = IsMaxHeap;
    }

    public Heap(boolean isMaxHeap) {
        initialize(IsMaxHeap);
    }

    public Heap() {
        this(true);
    }

    // O(N)
    public Heap(int[] data, boolean isMaxHeap) {
        this(isMaxHeap);
        for (int ele : data) {
            this.arr.add(ele);
        }
        this.noOfEle = data.length;

        for (int i = this.noOfEle - 1; i >= 0; i--) {// NlogN=> N
            downHeapify(i);
        }
    }

    private void UnderFlowException() throws Exception {
        if (noOfEle == 0) {
            throw new Exception("Underflow");
        }
    }

    public int size() {
        return this.noOfEle;
    }

    public boolean isEmpty() {
        return this.noOfEle == 0;
    }

    // O(1)
    public int peek() throws Exception {
        UnderFlowException();
        return this.arr.get(0);
    }

    private int compareTo(int t, int o) {
        if (IsMaxHeap)
            return this.arr.get(o) - this.arr.get(t);
        else
            return this.arr.get(t) - this.arr.get(o);
    }

    private void swap(int i, int j) {
        int a = this.arr.get(i);
        int b = this.arr.get(j);

        this.arr.add(i, b);
        this.arr.add(j, a);
    }

    // Log(n)
    private void downHeapify(int pi) {
        int lc = 2 * pi + 1;
        int rc = 2 * pi + 2;
        int mi = pi;
        if (lc < noOfEle && compareTo(mi, lc) > 0) {
            mi = lc;
        }
        if (rc < noOfEle && compareTo(mi, rc) > 0) {
            mi = rc;
        }
        if (mi != pi) {
            swap(mi, pi);
            downHeapify(mi);
        }
    }

    // log(n)
    public int remove() throws Exception {
        UnderFlowException();
        swap(0, this.noOfEle - 1);
        int rvdata = this.arr.get(this.noOfEle - 1);
        this.arr.remove(this.noOfEle - 1);
        this.noOfEle--;
        downHeapify(0);
        return rvdata;
    }

    // log(n)
    private void upHeapify(int ci) {
        int pi = (ci - 1) / 2;
        if (compareTo(pi, ci) > 0) {
            swap(pi, ci);
            upHeapify(pi);
        }
    }

    // log(n)
    public void add(int data) {
        this.arr.add(data);
        this.noOfEle++;
        upHeapify(this.noOfEle - 1);
    }

}