package recursion;

import java.util.Scanner;

public class L14 {
    static void main() {
        Scanner sc=new Scanner(System.in);
        int size=sc.nextInt();

        int[] arr=new int[size];

        for(int i=0;i<size;i++) {
            arr[i]=sc.nextInt();
        }

//        printArray(arr,0);
//        printArrayReverse(arr,0);

//        System.out.println(maxOfArray(arr,0));

//        System.out.println(firstIndex(arr,10,0));
//        System.out.println(lastIndex(arr,10,0));

        int[] ans=allIndices(arr,10,0,0);
        for(int i=0;i<ans.length;i++) {
            System.out.print(ans[i]+" ");
        }
    }


//    Display Array
    public static void printArray(int[] arr, int idx){
        if(idx==arr.length) {
            return;
        }
        System.out.println(arr[idx]);   // Mujhe bs 0th idx ke liye print krna hai
        printArray(arr,idx+1);  // Assumption : idx 1 se le ke last idx tak print ho jaayega
    }


//    Display Array in Reverse
    public static void printArrayReverse(int[] arr,int idx){
        if(idx==arr.length) {
            return;
        }

        printArrayReverse(arr,idx+1);
        System.out.println(arr[idx]);     // Ye wali line Recursion tree me upar se wapas neeche aate huye print hogi
    }


//    Max of an Array
    public static int maxOfArray(int[] arr, int idx){
        if(idx==arr.length-1) {
            return arr[idx];
        }

        int max=maxOfArray(arr,idx+1);  // Assumption : idx 1 se le ke last idx tak ka max already mil jaayega

        return Math.max(arr[idx],max);  // Bs uss max or current element me se jo bada hai wahi array ka max element hai
    }


//    First Index
    public static int firstIndex(int[] arr, int x,int idx){
        if(idx==arr.length) {   // incase arr me x nahi mila
            return -1;
        }

        if(arr[idx]==x){    // current idx pe agar x hai to vo idx return kr diya
            return idx;
        }

        return firstIndex(arr,x,idx+1); // Assumption : idx+1 se le kr last idx tak x ki first occurrence ka idx check kr liya
    }


    //    Last Index
    public static int lastIndex(int[] arr, int x,int idx){
        if(idx==arr.length) {   // incase arr me x nahi mila
            return -1;
        }

        int ans=lastIndex(arr,x,idx+1); // Assumption : idx+1 se le kr last idx tak x ki last occurrence ka idx check kr liya

        if(ans==-1){    // last idx ab tak nhi mila hai
            if(arr[idx]==x){    // current idx hi last idx hai
                return idx;
            }
            else{   // current idx x ke equal nahi hai
                return -1;
            }
        }
        else{   // last idx already mil chuka hai
            return ans;
        }
    }


//    All Indices of Array
    public static int[] allIndices(int[] arr, int x, int idx, int fsf){
        if(idx==arr.length) {
            int[] ans=new int[fsf]; // ans array is formed
            return ans;
        }

        // recursion tree me upar jaate time no. of occurrences ko count kr liya ... or found so far ko update kr denge
        if(arr[idx]==x){
            fsf++;  // no. of occurrence of x
        }

        int[] ans=allIndices(arr,x,idx+1, fsf); // Assumption : indices of x from idx+1 till last idx
                                                    // have been filled in the ans array

        // recursion tree me neeche aate time ans array ko fill kr diya
        if(arr[idx]==x){    // if x is present at current idx
            ans[fsf-1]=idx; // insert it in the ans array
        }

        return ans;
    }
}
