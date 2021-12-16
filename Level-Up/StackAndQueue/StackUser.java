public class StackUser {
    // as we are in same folder as class,the protected variables will be present
    // otherwise they are not present
    public static void main(String[] args) throws Exception {
        Stack st = new Stack();
        st.push(10);
        System.out.println(st.peek());
    }
}
