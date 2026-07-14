package timeAndSpaceComplexity;

public class L20 {
    static void main() {
        int[] arr1={1,2,3,4,5};
        int[] arr2={6,7,8,9,10};

//        int[] ans=merge(arr1,arr2);

//        int[] arr={10,30,1,4,90,7,9};
//        int[] ans=mergeSort(arr,0,arr.length-1);

//        int[] arr={0,0,2,1,1,2,0,0,1,0};
//        sort012(arr);

        int[] arr={13,6,4,8,17,16,9,7,-2};
//        partition(arr,7);

        quickSort(arr,0,arr.length-1);
        for(int i:arr){
            System.out.print(i+" ");
        }
    }


//    Merge Two Sorted Arrays
    public static int[] merge(int[] arr1, int[] arr2){
        int n = arr1.length;
        int m = arr2.length;

        int[] ans=new int[n+m];

        int i=0,j=0,k=0;

        while(i<arr1.length && j<arr2.length){  // compare and add elements to ans
            if(arr1[i]<arr2[j]){
                ans[k++]=arr1[i++];
            }
            else{
                ans[k++]=arr2[j++];
            }
        }

        while(i<arr1.length){   // add remaining elements
            ans[k++]=arr1[i++];
        }
        while(j<arr2.length){   // add remaining elements
            ans[k++]=arr2[j++];
        }

        return ans;
    }


//    Merge Sort
    public static int[] mergeSort(int[] arr, int lo, int hi){
        if(lo==hi){
            int[] bans=new int[1];
            bans[0]=arr[lo];
            return bans;
        }

        // Assumption : Mujhe bs original array ko 1 baar halves me split kr dena hai. Baad me mujhe apne aap sorted
        // halves mil jaayenge. Fir Mujhe bs unko merge krna hoga

        int mid=lo+(hi-lo)/2;   // mid idx to split the array
        int[] firstHalf=mergeSort(arr,lo,mid);  // 1st half sorted half
        int[] secondHalf=mergeSort(arr,mid+1,hi); // 2nd sorted half
        int[] ans=merge(firstHalf,secondHalf);  // merge 1st sorted half and 2nd sorted half

        return ans;
    }



//    Sort 01
    public static void sort01(int[] arr){
        int i=0, j=0;

        while(i<arr.length){
            if(arr[i]==0){
                swap(arr,i,j);
                i++;
                j++;
            }
            else{   // arr[i] = 1
                i++;
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


//    Sort 012
    public static void sort012(int[] arr){
        int i=0,j=0,k=arr.length-1;

        while(i<=k){
            if(arr[i]==2){
                swap(arr,i,k);
                k--;    // only k-- and not i++
            }
            else if(arr[i]==0){
                swap(arr,i,j);
                i++;
                j++;
            }
            else{   // arr[i] = 1
                i++;
            }
        }
    }


//    Partition an Array
    public static void partition(int[] arr, int pivot){
        int i=0,j=0;

        // Similar to sort01() -> usme left pe 0 right me 1
        // <= pivot wale elements array me ek trh aayenge or > pivot wale element ek trh
        // or kisi bhi order me ho skta hain..jaruri nhi ki ascending order ho
        // or jaruri nahi ki pivot element beech me hi aaye
        while(i<arr.length){
            if(arr[i]<=pivot){  //  Similar to arr[i] == 0
                swap(arr,i,j);
                i++;
                j++;
            }
            else{   //  Similar to arr[i] = 1
                i++;
            }
        }
    }


//    Quick Sort
    public static void quickSort(int[] arr, int lo, int hi){
        if(lo>hi){
            return;
        }

        int pivotIndex=partition(arr, arr[hi], lo, hi); //  Sorts the pivot and partitions the array
        quickSort(arr, lo, pivotIndex-1);   // Sort element on the left of pivot
        quickSort(arr, pivotIndex+1, hi);   // Sort elements on the right of pivot
    }

    public static int partition(int[] arr, int pivot,int lo, int hi) {
        int i = lo, j = lo;
        while (i < arr.length) {
            if (arr[i] <= pivot) {
                swap(arr, i, j);
                i++;
                j++;
            } else {
                i++;
            }
        }
        System.out.println("pivot index -> " + (j - 1));
        return (j - 1); // return pivot index
    }


//    Quick Select
    public static int quickSelect(int[] arr, int lo, int hi, int k){
        int pivotIdx=partition(arr, arr[hi], lo, hi);   // pivot idx nikaal liya

        if(pivotIdx==k-1){  // kth largest element mil gaya
            return pivotIdx;
        }
        else if(pivotIdx>k){    // pivot index is xth largest element  & x>k => left me dhundhna pdega
            hi=pivotIdx-1;
        }
        else{    // pivot index is xth largest element  & x<=k => right me dhundhna pdega
            lo=pivotIdx;
        }

        int ans=quickSelect(arr, lo, hi, k);    // search range kam krke function call kr diya
        return ans;
    }
}
