public class Stack {
    private int[] arr;
    private int tos;
    private int size;
    private int maxSize;

    Stack(int size) {
        initialise(size);
    }

    Stack() {
        this(10);// this is constructor chaining.this function calls the above function which
                 // calls the function below
        // this line has to be on top else it will give a error
    }

    protected void initialise(int size) {
        this.maxSize = size;
        this.tos = -1;
        this.size = 0;
        this.arr = new int[this.maxSize];
    }

    public void display() {
        for (int i = this.tos; i >= 0; i--) {
            System.out.print(this.arr[i] + " ");
        }
        System.out.println();
    }

    public int capacity() {
        return this.maxSize;
    }

    public int size() {
        return this.size;
    }

    private void underFlow() throws Exception {
        if (this.size == 0) {
            throw new Exception("Stack Empty");
        }
    }

    private void overFlow() throws Exception {
        if (this.size == this.maxSize) {
            throw new Exception("Stack Full");
        }
    }

    public void push(int n) throws Exception {
        overFlow();
        this.arr[++this.tos] = n;
        this.size++;
    }

    public int peek() throws Exception {
        underFlow();
        return this.arr[this.tos];
    }

    public int pop() throws Exception {
        underFlow();
        int n = this.arr[this.tos];
        this.arr[this.tos--] = 0;
        this.size--;
        return n;
    }

}