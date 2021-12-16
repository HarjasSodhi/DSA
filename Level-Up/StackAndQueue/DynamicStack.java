public class DynamicStack extends Stack {
    DynamicStack(int size) {
        super(size);
    }

    DynamicStack() {
        super();
    }

    @Override
    public void push(int n) throws Exception {
        if (super.capacity() == super.size()) {
            int[] arr = new int[super.size()];
            int idx = super.size() - 1;
            while (idx >= 0) {
                arr[idx] = super.pop();
                idx--;
            }
            super.initialise(super.size() * 2);
            for (int i = 0; i < arr.length; i++) {
                super.push(arr[i]);
            }
        }
        super.push(n);
    }
}
