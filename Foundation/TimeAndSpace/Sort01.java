import java.util.*;

public class Sort01 {
    public static Scanner scn = new Scanner(System.in);

    public static void S01(int[] arr) {
        int p = -1, itr = 0;
        while (itr < arr.length) {
            if (arr[itr] == 0) {
                p++;
                System.out.println("Swapping index " + p + " and index " + itr);
                int temp = arr[p];
                arr[p] = arr[itr];
                arr[itr] = temp;
            }
            itr++;
        }
    }
    public static void S011(int []arr) {
        int i=0;
        int j=arr.length-1;
        while(i<j){
            if(arr[i]==1){
                if(arr[j]==0){
                    int temp=arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;
                    j--;
                    i++;
                }
                if(arr[j]==1){
                    j--;
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        S01(arr);
        for (int i = 0; i < n; i++) {
            System.out.println(arr[i]);
        }
    }
}