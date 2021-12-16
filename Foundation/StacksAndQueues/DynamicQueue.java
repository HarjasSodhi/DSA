public class DynamicQueue extends Queue {
    public DynamicQueue() {
        super();
    }

    public DynamicQueue(int val) {
        super(val);
    }

    @Override
    public void add(int data) throws Exception {
        if (super.size() == super.maxSize()) {
            int[] temp = new int[super.size()];
            int idx = 0;

            while (super.size() != 0) {
                temp[idx++] = super.remove();
            }

            super.initialize(super.maxSize() * 2);

            for (int i : temp) {
                super.add(i);
            }
        }
        super.add(data);
    }
}
