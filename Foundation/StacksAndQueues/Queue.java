public class Queue {
    private int arr[];
    private int front = -1;
    private int end = -1;
    private int size = 0;
    private int maxSize;

    protected void initialize(int size) {
        arr = new int[size];
        this.maxSize = size;
    }

    public Queue() {
        initialize(5);
    }

    public Queue(int size) {
        initialize(size);
    }

    @Override
    public String toString() {
        int i = this.front;
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        if (front > end) {
            for (; i < this.maxSize; i++) {
                sb.append(this.arr[i] + ", ");
            }
            i = 0;
        }
        for (int j = i; j <= this.end; j++) {
            if (j == this.end)
                sb.append(this.arr[j]);
            else
                sb.append(this.arr[j] + ", ");

        }

        sb.append("]");
        return sb.toString();
    }

    public int size() {
        return this.size;
    }
    
    public int maxSize(){
        return this.maxSize;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == this.maxSize;
    }

    public void queueFull() throws Exception {
        if (this.size == this.maxSize)
            throw new Exception("Queue is full at Index: " + this.end);
    }

    public void queueEmpty() throws Exception {
        if (this.size == 0)
            throw new Exception("Queue is empty at Index: " + this.front);
    }

    private void addData(int data) {
        if (this.front == -1 && this.end == -1)
            this.front++;
        this.end = (this.end + 1) % this.maxSize;
        this.arr[this.end] = data;
        this.size++;
    }

    public void add(int data) throws Exception {
        queueFull();
        addData(data);
    }

    public int peek() throws Exception {
        queueEmpty();
        return this.arr[this.front];
    }

    private int removeData() {
        int rv = this.arr[this.front];
        this.arr[this.front] = 0;
        this.front = (this.front + 1) % this.maxSize;
        this.size--;
        return rv;
    }

    public int remove() throws Exception {
        queueEmpty();
        return removeData();
    }
}