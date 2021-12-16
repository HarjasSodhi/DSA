import java.util.*;
public class ReturnLast {
    public static Scanner scn=new Scanner(System.in);
    public static int [] insert(int n){
    int []arr=new int[n];
    for (int i = 0; i < n; i++) {
        arr[i]=scn.nextInt();
    }
    return arr;
    } 
    public static int RL(int []arr,int i,int data) {
        if(i==arr.length) return -1;
        int li=RL(arr,i+1,data);
        if(li==-1){
            if(arr[i]==data)return i;
            else return -1;
        }
        else return li;
        
    }
    public static void main(String[] args) {
    int n=scn.nextInt();
    int []arr=new int[n];
    arr=insert(n);
    int i=0;
    int data=scn.nextInt();
    int LI=RL(arr, i,data);
    System.out.println(LI);
    }
}