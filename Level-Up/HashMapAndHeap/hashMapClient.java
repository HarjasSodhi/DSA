public class hashMapClient {
    public static void main(String[] args) {
        HashMapConstruction hm = new HashMapConstruction();
        hm.put(10, 100);
        hm.put(20, 200);
        hm.put(30, 300);
        hm.put(40, 400);
        hm.putIfAbsent(80, 700);
        System.out.println(hm.display());
        System.out.println(hm.get(90));
        System.out.println(hm.keySet());
    }
}