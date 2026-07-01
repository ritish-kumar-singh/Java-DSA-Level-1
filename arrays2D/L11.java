package arrays2D;

import java.util.Scanner;

public class L11 {
    public static void main() {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter the number of rows : ");
        int n=sc.nextInt();
        System.out.println("Enter the number of columns : ");
        int m=sc.nextInt();

        int[][] arr=new int[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.print("Enter element ["+(i)+"]["+(j)+"] : ");
                arr[i][j]=sc.nextInt();
            }
        }

//        rotateBy90(arr);
//        display(arr);

//        stateOfWakanda2(arr);

        System.out.println(saddlePrice(arr));

    }

    public static void display(int[][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

//    Rotate by 90 degree
    public static void rotateBy90(int[][] arr){
        // 1. Take transpose
        for(int i=0;i<arr.length-1;i++){
            for(int j=i+1;j<arr[0].length;j++){
                int temp=arr[i][j];
                arr[i][j]=arr[j][i];
                arr[j][i]=temp;
            }
        }

        // 2. Arrange columns
        int left=0;
        int right=arr[0].length-1;

        while(left<=right){
            for(int row=0;row<arr.length;row++){
                int temp=arr[row][left];
                arr[row][left]=arr[row][right];
                arr[row][right]=temp;
            }
            left++;
            right--;
        }
    }


//    The State of Wakanda 2
    public static void stateOfWakanda2(int[][] arr){
        int n=arr.length;

       for(int diagonal=0;diagonal<n;diagonal++){
           int i=0;
           int j=diagonal;

           while(j<n){
               System.out.print(arr[i][j]+" ");
               i++;
               j++;
           }
       }
    }


//    Saddle Price
    public static int saddlePrice(int[][] arr){
        int n=arr.length;

        for(int i=0;i<arr.length;i++){

            //1. find min element of every row along with its col number
            int min=arr[i][0];
            int colNo=0;

            for(int col=1;col<n;col++){
                if(arr[i][col]<min){
                    min=arr[i][col];
                    colNo=col;
                }
            }

            //2. verify if it's the max element of its col
            boolean ans=true;
            for(int row=0;row<n;row++){
                if(arr[row][colNo]>min){
                    ans=false;
                    break;
                }
            }

            if(ans){
                return min;     // saddle price
            }
        }
        System.out.println("Invalid input");
        return -1;  //invalid input
    }

}
