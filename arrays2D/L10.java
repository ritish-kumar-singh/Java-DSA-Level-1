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
                System.out.print("Enter element ["+(i)+"]["+(j)+"] : ");
                arr[i][j]=sc.nextInt();
            }
        }

//        stateOfWakanda1(arr);
//        spiralDisplay(arr);
        ringRotate(arr, 2, 3);

//        Display Array
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }




//    State of Wakanda - 1
    public static void stateOfWakanda1(int[][] arr){
        int n=arr.length;
        int m=arr[0].length;

        for(int j=0;j<m;j++){
            if(j%2==0){     // column is even
                for(int i=0;i<n;i++){   // row varies 0 to last
                    System.out.println(arr[i][j] + " ");
                }
            }
            else{     // column is odd
                for(int i=n-1;i>=0;i--){   // row varies last to 0
                    System.out.println(arr[i][j] + " ");
                }
            }
        }
    }


//    Spiral Display
    public static void spiralDisplay(int[][] arr){
        int n=arr.length;
        int m=arr[0].length;
        int noOfElements=0;

        int rmin=0,rmax=n-1,cmin=0,cmax=m-1;

        while(noOfElements < n*m) {
            //left boundary
            for (int row = rmin; row <= rmax && noOfElements < n*m; row++) {
                System.out.print(arr[row][cmin] + " ");
                noOfElements++;
            }
            cmin++;

            //bottom boundary
            for (int col = cmin; col <= cmax && noOfElements < n*m; col++) {
                System.out.print(arr[rmax][col] + " ");
                noOfElements++;
            }
            rmax--;

            //right boundary
            for (int row = rmax; row >= rmin && noOfElements < n*m; row--) {
                System.out.print(arr[row][cmax] + " ");
                noOfElements++;
            }
            cmax--;

            //top boundary
            for (int col = cmax; col >= cmin && noOfElements < n*m; col--) {
                System.out.print(arr[rmin][col] + " ");
                noOfElements++;
            }
            rmin++;
        }
    }



//    Ring Rotate
    public static void ringRotate(int[][] arr, int sno, int r){
        int[] arr1=new int[8];

        arr1=fill1Dfrom2D(arr,sno);
        rotateArr(arr1,r);
        fill2Dfrom1D(arr,arr1,sno);
    }

    public static int[] fill1Dfrom2D(int[][] arr, int sno){
        int n=arr.length;
        int m=arr[0].length;

        int rmin=sno-1,rmax=n-sno,cmin=sno-1,cmax=m-sno;

        int size=2*(rmax-rmin+cmax-cmin);
        int[] arr1D=new int[size];
        int i=0;

        while(i<size){
            //left boundary
            for(int row=rmin;row<=rmax && i<size;row++){
                arr1D[i++]=arr[row][cmin];
            }
            cmin++;

            //bottom boundary
            for(int col=cmin;col<=cmax && i<size;col++){
                arr1D[i++]=arr[rmax][col];
            }
            rmax--;

            //right boundary
            for(int row=rmax;row>=rmin && i<size;row--){
                arr1D[i++]=arr[row][cmax];
            }
            cmax--;

            //top boundary
            for(int col=cmax;col>=cmin && i<size;col--){
                arr1D[i++]=arr[rmin][col];
            }
            rmin--;
        }
        return arr1D;
    }

    public static void rotateArr(int[] arr, int r){
        int n=arr.length;

        r=r%arr.length;
        if(r<0){
            r=r+arr.length;
        }

        reverseArr(arr,0,n-1);
        reverseArr(arr,0,r-1);
        reverseArr(arr,r,n-1);
    }

    public static void reverseArr(int[] arr,int start,int end){
        while(start<end){
            int temp=arr[start];
            arr[start]=arr[end];
            arr[end]=temp;
            start++;
            end--;
        }
    }

    public static void fill2Dfrom1D(int[][] arr, int[] arr1D, int sno){
        int n=arr.length;
        int m=arr[0].length;

        int rmin=sno-1,rmax=n-sno,cmin=sno-1,cmax=m-sno;
        int i=0;
        int totalElements=arr1D.length;

        while(i<totalElements){
            //left boundary
            for(int row=rmin; row<=rmax && i<totalElements;row++){
                arr[row][cmin]=arr1D[i++];
            }
            cmin++;

            //bottom boundary
            for(int col=cmin; col<=cmax && i<totalElements;col++){
                arr[rmax][col]=arr1D[i++];
            }
            rmax--;

            //right boundary
            for(int row=rmax;row>=rmin && i<totalElements;row--){
                arr[row][cmax]=arr1D[i++];
            }
            cmax--;

            //top boundary
            for(int col=cmax;col>=cmin && i<totalElements;col--){
                arr[rmin][col]=arr1D[i++];
            }
            rmin--;
        }
    }
}
