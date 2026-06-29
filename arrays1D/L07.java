package arrays;

import java.util.*;

public class L07 {
    public static void main(String[] args) {
//        int[] arr=new int[2];
//        arr[0]=10;
//        arr[1]=20;

//        System.out.println(arr[0] + " " + arr[1]);
//        swap(arr);
//        System.out.println(arr[0] + " " + arr[1]);

        Scanner sc=new Scanner(System.in);

        System.out.println("Enter the size of the array: ");
        int n=sc.nextInt();

        int[] arr=new int[n];

        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }

        System.out.println(spanOfArray(arr));
    }

    public static void swap(int[] arr){
        int left=0;
        int right=arr.length-1;

        while(left<right){
            int temp=arr[left];
            arr[left]=arr[right];
            arr[right]=temp;

            left++;
            right--;
        }
    }

    public static int spanOfArray(int[] arr){
        int max=arr[0];
        int min=arr[0];

        for(int i=1;i<arr.length;i++){
            if(arr[i]>max){
                max=arr[i];
            }
            if(arr[i]<min){
                min=arr[i];
            }
        }

        int span=max-min;

        return span;
    }
}