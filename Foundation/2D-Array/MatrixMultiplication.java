import java.util.*;
public class MatrixMultiplication {
public static Scanner scn=new Scanner(System.in);
public static void display(int[][] arr) {
for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
public static int[][] insert(int n, int m) {
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scn.nextInt();
            }
        }
        return arr;
    }

public static void MM(int[][]arr1,int[][]arr2) {
if(arr1[0].length==arr2.length){
int[][]ans=new int[arr1.length][arr2[0].length];
int i=0;
while(i<ans.length){
    int j=0;
    while(j<ans[0].length){
    int n=0;
    while(n<arr2.length){
    ans[i][j]=ans[i][j]+(arr1[i][n]*arr2[n][j]);
    n++;
    }
    j++;
}
i++;
}
display(ans);
}
else{
    System.out.println("Invalid input");
}    
}
public static void main(String[] args)    {
int n=scn.nextInt();
int m=scn.nextInt();
int[][] arr1=new int[n][m];
arr1=insert(n, m);
int i=scn.nextInt();
int j=scn.nextInt();
int[][] arr2=new int[i][j];
arr2=insert(i, j);
MM(arr1,arr2);
}
}