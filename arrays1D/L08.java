package arrays;

import java.util.*;

public class L08 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter the size of the array1 : ");
        int n1=sc.nextInt();
        int[] arr1=new int[n1];
        for(int i=0;i<n1;i++){
            arr1[i]=sc.nextInt();
        }

//        System.out.println("Enter the size of the array2 : ");
//        int n2=sc.nextInt();
//        int[] arr2=new int[n2];
//        for(int i=0;i<n2;i++){
//            arr2[i]=sc.nextInt();
//        }
//
//        int size=Math.max(n1,n2);
//        int[] ans=new int[size];

//        ans=sumOfTwoArrays(arr1,arr2);

//        ans=differenceOfTwoArrays(arr1,arr2);
//        int i=0;
//        while(ans[i]==0){
//            i++;
//        }
//        for(;i<size;i++){   //don't print initial 0's
//            System.out.print(ans[i]+" ");
//        }

//        reverseArray(arr1);

//        reverse(arr1,0,arr1.length-1);

//        for(int i=0;i<n1;i++){
//            System.out.print(arr1[i]+" ");
//        }
//
//        rotateArray(arr1,2);
        int[] ans=inverseOfArray(arr1);
        for(int i=0;i< ans.length;i++){
            System.out.print(ans[i]+" ");
        }
    }

    public static int[] sumOfTwoArrays(int[] arr1, int[] arr2){
        int n1=arr1.length;
        int n2=arr2.length;

        int size=Math.max(n1,n2)+1;
        int[] ans=new int[size];

        int i=n1-1;
        int j=n2-1;
        int k=size-1;

        int carry=0;

        while(k>=0){
//          int sum=arr1[i]+arr2[j]+carry;  //cannot write it directly because i & j need to be valid

            int sum=carry;

            if(i>=0){
                sum+=arr1[i];
            }
            if(j>=0){
                sum+=arr2[j];
            }

            if(sum>=10){
                carry=1;
                sum-=10;
            }
            else{
                carry=0;
            }
            ans[k]=sum;
            i--;
            j--;
            k--;
        }

        return ans;
    }

    public static int[] differenceOfTwoArrays(int[] arr1, int[] arr2){  //no. represented by arr2 > arr1
        int n1=arr1.length;
        int n2=arr2.length;

        int size=Math.max(n1,n2);
        int[] ans=new int[size];

        int borrow=0;

        int i=n1-1;
        int j=n2-1;
        int k=size-1;

        while(k>0){
            int diff=arr2[j]-borrow;

            if(i>=0){
                diff-=arr1[i];
            }

            if(diff<0){
                borrow=1;
                diff+=10;
            }
            else{
                borrow=0;
            }

            ans[k]=diff;
            i--;
            j--;
            k--;
        }
        return ans;
    }

    public static void reverseArray(int[] arr){ // whole array will be reversed
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

    //Array ke left argument se le ke right argument tak ke hisse ko reverse kr dega
    //helps in reversing a portion of array
    public static void reverse(int[] arr, int left, int right){
        while(left<right){
            int temp=arr[left];
            arr[left]=arr[right];
            arr[right]=temp;
            left++;
            right--;
        }
    }

    public static void rotateArray(int[] arr, int k){
        k=k%arr.length;
        if(k<0){
            k+=arr.length;
        }

        reverse(arr, 0, arr.length-1);
        reverse(arr,0,k-1);
        reverse(arr,k,arr.length-1);
    }

    public static int[] inverseOfArray(int[] arr){
        int[] inv=new int[arr.length];

        for(int i=0;i<arr.length;i++){
            int pos=arr[i];
            inv[pos]=i;
        }

        return inv;
    }
}
