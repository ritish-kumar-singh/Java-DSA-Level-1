package algorithms;

import java.util.Scanner;

public class L09 {
    public static void main() {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter the size of the array1 : ");
        int n=sc.nextInt();

        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            System.out.println("Enter the element "+(i+1)+" : ");
            arr[i]=sc.nextInt();
        }

        System.out.println("Enter the data to be searched : ");
        int data=sc.nextInt();

        System.out.println(binarySearch(arr,data));
    }

//    Binary Search
    public static int binarySearch(int[] arr, int data) {
        int left = 0;
        int right = arr.length - 1;

        while(left<=right){
            int mid = left + (right-left)/2;

            if(arr[mid] == data){
                return mid;
            }
            else if(arr[mid] < data){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }
        return -1;  // signifies that the element is not present in the array
    }
}
