public class DynamicStack extends Stack {
    public DynamicStack() {
        super();
    }

    public DynamicStack(int val) {
        super(val);
    }

    @Override
    public void push(int val) throws Exception {
        if (super.size() == super.maxSize()) {
            int[] temp = new int[super.size()];
            int idx = super.size() - 1;
            while (idx >= 0) {
                temp[idx--] = super.pop();
            }
            super.initialise(super.maxSize() * 2);

            for (int i : temp) {
                super.push(i);
            }
        }
        super.push(val);
    }

}
