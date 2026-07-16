package timeAndSpaceComplexity;

public class L21 {
    static void main() {
        int[] arr={5,7,4,4,8,3,5,2,4,7};
//        countSort(arr,2,8);
        radixSort(arr);
        for(int i:arr){
            System.out.print(i+" ");
        }
    }


//    Count Sort
    public static void countSort(int[] arr, int min, int max) {
        // 1. create frequency array
        int[] farr=new int[max-min+1];

        for (int i=0;i<arr.length;i++) {
            farr[arr[i]-min] += 1;  // arr[i] is represented as arr[i]-min
        }

        // 2. convert frequency array into prefix sum array
        for(int i=1;i<farr.length;i++) {
            farr[i]=farr[i]+farr[i-1];
        }

        // 3. travel from right to left in original array & fill ans array
        int[] ans=new int[arr.length];

        for(int i=arr.length-1;i>=0;i--) {
            int pos=farr[arr[i]-min];   // array sort hone ke baad ans array pe number ki position
            int idx=pos-1;  // conversion of position to index
            ans[idx]=arr[i];    // placing the no. in ans array
            farr[arr[i]-min]--; // position update for next time
        }

        // copy ans[i] to arr[i]
        for(int i=0;i<ans.length;i++) {
            arr[i]=ans[i];
        }
    }


//    Radix Sort
    public static void radixSort(int[] arr){
        int max=0;
        for(int i=0;i<arr.length;i++){
            max=Math.max(max,arr[i]);   // find max element
        }

        int counter=0;
        while(max!=0){  // loop will run how many times->no. of digits in max element/number => countSort will be done this many times
            max=max/10;
            countSort1(arr,(int)Math.pow(10,counter));   // sorting hogi phle one's ke basis pe, then ten's ke basis pe & so on...
            counter++;
        }
    }

    // Will stable sort on the basis of one's, ten's, hundred's & so on
    public static void countSort1(int[] arr,int exp){
        // frequency array
        int[]  farr=new int[10];    // size = 10 because single digit ki range 0-9 tak hi hoti hai
        for (int i=0;i<arr.length;i++){
            farr[(arr[i]/exp) % 10]++;
        }

        // conversion of frequency array to prefix sum array
        for(int i=1;i<farr.length;i++){
            farr[i]=farr[i]+farr[i-1];
        }

        // ans array
        int[] ans=new int[arr.length];
        for(int i=arr.length-1;i>=0;i--){
            int pos=farr[(arr[i]/exp) % 10];    // array sort hone ke baad ans array me no. ki correct position
            ans[pos-1]=arr[i];  // ans array me no. place kr diya
            farr[(arr[i]/exp) % 10]--;  // agli baar ke liye position update
        }

        // copy ans array to original arr
        for(int i=0;i<ans.length;i++){
            arr[i]=ans[i];
        }
    }
}
