import java.util.*;

public class PriorityQueues {
    public static Scanner scn = new Scanner(System.in);

    public static void PQmin() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // by default min Priority Queue

        for (int i = 10; i >= 1; i--) {
            pq.add(i * 10);// add complexity is log(n);
        }

        while (pq.size() != 0) {
            System.out.println(pq.remove());// remove complexity in cpp is log(n) and in java O(n) but assume it log(n)
                                            // also.
            // peek function is O(1);
        }
    }

    public static void PQmax() {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });

        for (int i = 10; i >= 1; i--) {
            pq.add(i * 10);
        }

        while (pq.size() != 0) {
            System.out.println(pq.remove());
        }
    }

    public static void matrixPQ(int[][] arr) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });
        for (int i = -0; i < arr.length; i++) {
            pq.add(arr[i]);
        }
        while (pq.size() != 0) {
            int[] temp = pq.remove();
            for (int e : temp) {
                System.out.println(e + " ");
            }
        }
    }

    public static class mobilePhone {
        String Company = "";
        String Model = "";
        int Ram = 0;
        int Storage = 0;
        int BatteryBackup = 0;

        mobilePhone(String Company, String Model, int Ram, int Storage, int BatteryBackup) {
            this.Company = Company;
            this.Model = Model;
            this.Ram = Ram;
            this.Storage = Storage;
            this.BatteryBackup = BatteryBackup;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            sb.append("Company: " + this.Company + "\n");
            sb.append("Model: " + this.Model + "\n");
            sb.append("Ram: " + this.Ram + "GB\n");
            sb.append("Storage: " + this.Storage + "GB\n");
            sb.append("BatteryBackup: " + this.BatteryBackup + "mAH\n");

            return sb.toString();
        }
    }

    public static void comparePhones(int n) {
        PriorityQueue<mobilePhone> pq = new PriorityQueue<>((a, b) -> {
            if (a.Ram != b.Ram)
                return b.Ram - a.Ram;
            else if (a.Storage != b.Storage)
                return b.Storage - a.Storage;
            else
                return b.BatteryBackup - a.BatteryBackup;
        });
        for (int i = 0; i < n; i++) {
            pq.add(new mobilePhone(scn.next(), scn.next(), scn.nextInt(), scn.nextInt(), scn.nextInt()));
        }
        while (pq.size() != 0) {
            System.out.println(pq.remove());
        }
    }

    public static void main(String[] args) {
        // PQmin();
        // PQmax();
    }
}