package timeAndSpaceComplexity;

import java.util.Scanner;

public class L19 {
    static void main() {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] arr=new int[n];

        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }

//        bubbleSort(arr);
//        selectionSort(arr);
        insertSort(arr);

        for(int j=0;j<n;j++){
            System.out.print(arr[j]+" ");
        }
    }


//    Bubble Sort
    public static void bubbleSort(int[] arr) {
        for(int itr = 0; itr < arr.length; itr++) {
            for(int i=0;i<=arr.length-1-itr;i++) {
                if(isSmaller(arr,i+1,i)){
                    swap(arr,i+1,i);
                }
            }
        }
    }

    // swaps ith and jth elements of array
    public static void swap(int[] arr, int i, int j) {
        System.out.println("Swapping "+arr[i]+" and "+arr[j]);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // return true if ith element is smaller than jth element
    public static boolean isSmaller(int[] arr, int i, int j) {
        System.out.println("Comparing "+arr[i]+" and "+arr[j]);
        if(arr[i] < arr[j]){
            return true;
        }
        return false;
    }


//    Selection Sort
    public static void selectionSort(int[] arr) {
        for(int i=0;i<arr.length-1;i++){    // last idx will automatically be sorted
            int minidx=i;
            for(int j=i+1;j<arr.length;j++){
                if(isSmaller(arr,j,minidx)){    // if(arr[j] < arr[minidx])
                    minidx=j;
                }
            }
            swap(arr,i,minidx);
        }
    }


//    Insertion Sort
    public static void insertSort(int[] arr) {
        for(int i=1;i<arr.length;i++){
            for(int j=i;j>0;j--){  // left element se compare karte jaayenge, agar vo bada hai to swap
                if(isGreater(arr,j-1,j)){   // if (arr[j-1] > arr[j])
                    swap(arr,j-1,j);
                }
                else{   // element has been positioned correctly
                    break;
                }
            }
        }
    }

    // return true if ith element is greater than jth element
    public static boolean isGreater(int[] arr, int i, int j){
        System.out.println("Comparing "+arr[i]+" and "+arr[j]);
        if(arr[i] > arr[j]){
            return true;
        }
        return false;
    }
}
