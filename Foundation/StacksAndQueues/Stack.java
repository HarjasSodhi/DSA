public class Stack {
    private int arr[];
    private int tos = -1;
    private int size = 0;
    private int maxSize;

    protected void initialise(int size) {
        arr = new int[size];
        this.maxSize = size;
    }

    public Stack() {
        initialise(5);
    }

    public Stack(int size) {
        initialise(size);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = this.tos; i >= 0; i--) {
            if (i == 0)
                sb.append(this.arr[i]);
            else
                sb.append(this.arr[i] + ", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private void StackOverflow() throws Exception {
        if (this.size == this.maxSize)
            throw new Exception("Stack is full at Index: " + this.tos);
    }

    private void StackUnderflow() throws Exception {
        if (this.size == 0)
            throw new Exception("Stack is empty at Index : " + this.tos);
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

    private void pushData(int data) {
        this.size++;
        this.arr[++this.tos] = data;
    }

    public void push(int data) throws Exception {
        StackOverflow();
        pushData(data);
    }

    public int top() throws Exception {
        StackUnderflow();
        return this.arr[this.tos];
    }

    private int popData() {
        int rv = this.arr[this.tos];
        this.arr[this.tos--] = 0;
        this.size--;
        return rv;
    }

    public int pop() throws Exception {
        StackUnderflow();
        return popData();
    }

}