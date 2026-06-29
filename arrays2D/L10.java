package arrays2D;

import java.util.Scanner;

public class L10 {
    public static void main() {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter the number of rows : ");
        int n=sc.nextInt();
        System.out.println("Enter the number of columns : ");
        int m=sc.nextInt();

        int[][] arr=new int[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                arr[i][j]=sc.nextInt();
            }
        }
    }
}
