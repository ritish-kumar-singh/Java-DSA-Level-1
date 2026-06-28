package arrays;

import java.util.Scanner;

public class L09 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter the size of the array1 : ");
        int n=sc.nextInt();

        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            System.out.println("Enter the element "+(i+1)+" : ");
            arr[i]=sc.nextInt();
        }

//        printSubarray(arr);
//        printSubset(arr);

        System.out.println("Enter the value of the element : ");
        int val=sc.nextInt();

        brokenEconomy(arr,val);
    }


//    Print subarray
    public static void printSubarray(int[] arr){
        for(int si=0;si<arr.length;si++){
            for(int ei=si;ei<arr.length;ei++){
                for(int i=si;i<=ei;i++){
                    System.out.print(arr[i] + " ");
                }
                System.out.println();
            }
        }
    }


//    Print subset
    public static void printSubset(int[] arr){
        int n=arr.length;  //total no. of elements

        int totalSubsets=(int)Math.pow(2,n);

         for(int i=0;i<totalSubsets;i++){
            int binaryNo=decimalToBinary(i);

            int divisor=(int) Math.pow(10,n-1); // for traversing left to right on binary number

            for(int j=0;j<n;j++){
                int q=binaryNo/divisor; // gives the leftmost digit
                int r=binaryNo%divisor; // gives the number after removing leftmost digit

                if(q==1){
                    System.out.print(arr[j]+" ");
                }
                else{
                    System.out.print("_ ");
                }
                binaryNo=r;
                divisor/=10;
            }
            System.out.println();
         }
    }

    public static int decimalToBinary(int n){
        int ans=0;
        int power=1;
        while(n!=0){
            int r=n%2;
            n/=2;
            ans+=(r*power);
            power*=10;
        }
        return ans;
    }


//    Broken Economy
    public static void brokenEconomy(int[] arr, int data){
        int ceil=-1;
        int floor=-1;

        int left=0;
        int right=arr.length-1;

        while(left<=right){
            int mid=left+(right-left)/2;

            if(arr[mid]==data){
                ceil=data;
                floor=data;
                break;
            }
            else if(arr[mid]>data){
                right=mid-1;
                ceil=arr[mid];
            }
            else{
                left=mid+1;
                floor=arr[mid];
            }
        }
        System.out.println("ceil="+ceil+" floor="+floor);
    }


}
