package dynamicProgramming;

import java.util.Scanner;

public class L24 {
    static void main() {
        Scanner sc = new Scanner(System.in);
//        int target = sc.nextInt();
        int[] arr={2,9,1,8,7,3};

//        int cap=sc.nextInt();
//        int[] prices={15,14,10,45,30};
//        int[] weights={2,5,1,3,4};

//        Boolean[][] dp=new Boolean[arr.length][target+1];

//        System.out.println(targetSumSubsets(arr,target,0,dp));
//        System.out.println(targetSumSubsetsTabulation(arr,target));

//        System.out.println(zeroOneKnapsack(prices, weights, cap));

//        int[] arr={5,7,1,-3,-4,-6,2,1,5,7,-2};
        int n=sc.nextInt();
        int[] dp=new int[n+1];
//        int[] dp0=new int[arr.length];
//        int[] dp1=new int[arr.length];

        System.out.println(countBinaryStrings(n));
        System.out.println(countBinaryStringsMemoization(n,dp));
        System.out.println(countBinaryStringsTabulation(n));

        //        System.out.println(arrangeBuildings(n));

//        System.out.println(maxSumNonAdjacentElements(arr,0));
//        System.out.println(maxSumNonAdjacentElementsMemoization(arr, 0, dp));
//        System.out.println(maxSumNonAdjacentElementsTabulation(arr));
    }


//    Target Sum Subsets
    // Memoization
    public static boolean targetSumSubsets(int[] arr, int target, int idx, Boolean[][] dp) {
        if(target == 0){    // sum of elements of the subset = target
            return true;
        }

        if(idx >= arr.length || target < 0){
            return false;
        }

        if(dp[idx][target] != null){    //  true or false => ans already calculated, null=> ans not yet calculated
            return dp[idx][target];
        }

        boolean notIncludedAns = targetSumSubsets(arr, target, idx+1, dp);  // current element not included in subset
        boolean includedAns = targetSumSubsets(arr, target-arr[idx], idx+1, dp);  // current element included in subset

        boolean ans = notIncludedAns || includedAns;
        dp[idx][target] = ans;  // we have taken a 2D array for DP because with each call 2 things are varying - index and target
        return ans;
    }

//    Tabulation
    public static boolean targetSumSubsetsTabulation(int[] arr, int target){
        boolean[][] dp=new boolean[arr.length+1][target+1];   // dp[no. of elements considered][target]

        for(int i=0;i<dp.length;i++){
            dp[i][0]=true;              // target 0 is achievable for all indices
        }

        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                dp[i][j]=dp[i-1][j];    // current element ko exclude krke kya target j achieve ho paa raha hai

                // if target already achieve nhi hua hai & current element ko include krke updated target ka idx exist krta hai
                if(dp[i][j]==false && (j-arr[i-1])>=0){
                    dp[i][j]=dp[i-1][j-arr[i-1]];   // arr[i-1] because jab i ki value 2 hai tab ussey arr ka idx 1 mapped hai
                }
            }
        }
        // bottom right cell tells whether pura array consider krke target sum subset achieve ho paaya ya nahi
        return dp[dp.length-1][target];
    }


//    Zero One Knapsack
    public static int zeroOneKnapsack(int[] prices, int[] weights, int cap){
        int maxProfit = Integer.MIN_VALUE;

        int[][] dp=new int[prices.length+1][cap+1];

        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                dp[i][j]=dp[i-1][j];    // if current weight doesn't get included

                if(j-weights[i-1]>=0){
                    dp[i][j]=Math.max(dp[i][j], prices[i-1] + dp[i-1][j-weights[i-1]]); // if current weight gets included
                }
            }
        }
        return dp[dp.length-1][cap];    // max profit considering all the weights will be stored at the bottom-right cell
    }


//    Count Binary Strings
    // Recursive
    public static int countBinaryStrings(int n){
        if(n==1){
            return 2;   // no. of binary strings with non-consecutive 0's possible
        }
        if(n==2){
            return 3;   // no. of binary strings with non-consecutive 0's possible
        }

        int ans0=countBinaryStrings(n-1);   // no. of binary strings with last digit 0
        int ans1=countBinaryStrings(n-2);   // no. of binary strings with last digit 1

        int ans=ans0+ans1;
        return ans;
    }

    // Memoization
    public static int countBinaryStringsMemoization(int n, int[] dp){
        if(n==1){
            return 2;   // no. of binary strings with non-consecutive 0's possible
        }
        if(n==2){
            return 3;   // no. of binary strings with non-consecutive 0's possible
        }

        if(dp[n]!=0){
            return dp[n];
        }

        int ans0=countBinaryStringsMemoization(n-1, dp);   // no. of binary strings with last digit 0
        int ans1=countBinaryStringsMemoization(n-2, dp);   // no. of binary strings with last digit 1

        int ans=ans0+ans1;

        dp[n]=ans;

        return ans;
    }

    // Tabulation
    public static int countBinaryStringsTabulation(int n){
        int[] dp0 =  new int[n+1];  // ending at 0
        int[] dp1 = new int[n+1];   // ending at 1

        dp0[1]=1;
        dp1[1]=1;

        for(int i=2;i<=n;i++){
            dp0[i]=dp1[i-1];    // no. of strings possible with last digit 0
            dp1[i]=dp0[i-1]+dp1[i-1];   // no. of strings possible with last digit 1
        }

        int ans=dp0[n]+dp1[n];  // total number of binary strings possible of length n with non-consecutive 0's
        return ans;
    }


//    Arrange Buildings
    public static int arrangeBuildings(int n){
        int[] dp1=new int[n+1]; //ending at 0 -> Building
        int[] dp2=new int[n+1]; //ending at 1 -> Space

        dp1[1]=1;
        dp2[1]=1;

        for(int i=2;i<dp1.length;i++){
            dp1[i]=dp2[i-1];
            dp2[i]=dp1[i-1] + dp2[i-1];
        }

        int ans=dp1[n]+dp2[n];

        // no. of configurations possible for road of length = n -> n*n (because road has 2 sides for placing the buildings
        return ans*ans;
    }


//    Max Sum Non Adjacent Elements
    // Recursive
    public static int maxSumNonAdjacentElements(int[] arr, int idx){
        if(idx>=arr.length){
            return 0;       // max sum when idx out of range
        }

        //max sum possible if current element is included - if current element is included then next will definitely not be included
        int includeAns=arr[idx] + maxSumNonAdjacentElements(arr, idx+2);
        // max sum possible if current element is excluded
        int excludeAns=maxSumNonAdjacentElements(arr, idx+1);

        int ans=Math.max(includeAns,excludeAns);    // max sum possible till current idx

        return ans;
    }

    // Memoization
    public static int maxSumNonAdjacentElementsMemoization(int[] arr, int idx, int[] dp){
        if(idx>=arr.length){
            return 0;       // max sum when idx out of range
        }

        if(dp[idx]!=0 ){
            return dp[idx];    // Max of sum after including & excluding the current element
        }

        //max sum possible if current element is included - if current element is included then next will definitely not be included
        int includeAns=arr[idx] + maxSumNonAdjacentElementsMemoization(arr, idx+2, dp);
        // max sum possible if current element is excluded
        int excludeAns=maxSumNonAdjacentElementsMemoization(arr, idx+1, dp);

        int ans=Math.max(excludeAns,includeAns);    // max sum possible till current idx

        dp[idx]=ans;

        return ans;
    }

    // Tabulation
    public static int maxSumNonAdjacentElementsTabulation(int[] arr){
        int[] dp1=new int[arr.length];    // max sum when current element is included
        int[] dp2=new int[arr.length];    // max sum when current element is excluded

        dp1[0]=arr[0];
        dp2[0]=0;

        for(int i=1;i<dp1.length;i++){
            dp1[i]=dp2[i-1]+arr[i]; // when current element is included -> max sum when last element was excluded + current element
                                    // if current element is included then last element should be excluded to get non-adjacent sum
            dp2[i]=Math.max(dp1[i-1],dp2[i-1]);    // when current element is excluded -> max sum possible till last element
        }

        int ans=Math.max(dp1[dp1.length-1],dp2[dp2.length-1]);  // Max sum possible
        return ans;
    }
}
