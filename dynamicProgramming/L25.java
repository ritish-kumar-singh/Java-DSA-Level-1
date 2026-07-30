package dynamicProgramming;

import java.util.Scanner;

public class L25 {
    static void main() {
        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int m = sc.nextInt();

//        int[] dp = new int[n+1];
//        int k = sc.nextInt();
//

        String str=sc.next();

//        System.out.println(paintFence(n, k));
//        System.out.println(paintFenceRecursive(n, k));
//        System.out.println(paintFenceTabulation(n, k));

//        System.out.println(tiling21(n));
//        System.out.println(tiling21Memoization(n,dp));
//        System.out.println(tiling21Tabulation(n));

//        System.out.println(tilingM1(n,m));
//        System.out.println(tilingM1Memoization(n,m,dp));
//        System.out.println(tilingM1Tabulation(n,m));

        System.out.println(countEncodings(str, 0));
        System.out.println(countEncodings1(str, 0));

    }


//    Paint Fence
    public static int paintFence(int n, int k){
        int[] dp1 =  new int[n+1];  // no. of color combinations possible with last 2 fences painted with same colors
        int[] dp2 = new int[n+1];   // no. of color combinations possible with last 2 fences painted with different colors

     // dp1[1] = 0;
        dp2[1] = k;

        for(int i=2;i<=n;i++){
            dp1[i]=dp2[i-1];

            dp2[i] = (dp1[i-1] + dp2[i-1]) * (k-1);
        }

        int ans=dp1[n]+dp2[n];  // Total number of color combinations possible
        return ans;
    }

    // Recursive
    public static int paintFenceRecursive(int n, int k){
        if(n==1){   // total combinations possible when no. of fences = 1
            return k;
        }
        if(n==2){   // total combinations possible when no. of fences = 2
            return k*k;
        }

        // total combinations = last 2 fences painted with different color + last 2 fences painted with same color
        //  int ans = paintFenceRecursive(n-1, k) * (k-1) + paintFenceRecursive(n-2, k) * (k-1);
        int ans = (paintFenceRecursive(n-1, k) + paintFenceRecursive(n-2, k)) * (k-1);
        return ans;
    }

    // Recursive to Tabulation
    public static int paintFenceTabulation(int n, int k){
        int[] dp = new int[n+1];
        dp[1] = k;   // total combinations possible when no. of fences = 1
        dp[2] = k*k;   // total combinations possible when no. of fences = 2

        for(int i=3;i<=n;i++){
            dp[i] = (dp[i-1] + dp[i-2]) * (k-1);    // ( different color + same color ) * (total colors - 1)
        }

        return dp[n];   // total combinations possible when no. of fences = n
    }

//    Tiling with 2*1 Tiles
    // Recursive
    public static int tiling21(int n){
        if(n==1 || n==2){
            return n;
        }

        if(n<=0){
            return 0;
        }

        int verticalAns=tiling21(n-1);  // placing the tiles vertically
        int horizontalAns=tiling21(n-2);  // placing the tiles horizontally

        int ans=verticalAns+horizontalAns;

        return ans;
    }

    // Memoization
    public static int tiling21Memoization(int n, int[] dp){
        if(n==1 || n==2){
            return n;
        }

        if(n<=0){
            return 0;
        }

        if(dp[n]!=0){
            return dp[n];
        }

        int verticalAns=tiling21Memoization(n-1, dp);  // placing the tiles vertically
        int horizontalAns=tiling21Memoization(n-2, dp);  // placing the tiles horizontally

        int ans=verticalAns+horizontalAns;
        dp[n]=ans;

        return ans;
    }

    // Tabulation
    public static int tiling21Tabulation(int n){
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;

        for(int i=3;i<=n;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }


//    Tiling with m*1 Tiles
// Recursive
    public static int tilingM1(int n, int m){
        if(n==1 || n==2){
            return n;
        }

        if(n<=0){
            return 0;
        }

        int verticalAns=tilingM1(n-1, m);  // placing the tiles vertically
        int horizontalAns=tilingM1(n-m, m);  // placing the tiles horizontally

        int ans=verticalAns+horizontalAns;

        return ans;
    }

    // Memoization
    public static int tilingM1Memoization(int n, int m, int[] dp){
        if(n==1 || n==2){
            return n;
        }

        if(n<=0){
            return 0;
        }

        if(dp[n]!=0){
            return dp[n];
        }

        int verticalAns=tilingM1Memoization(n-1, m, dp);  // placing the tiles vertically
        int horizontalAns=tilingM1Memoization(n-m, m, dp);  // placing the tiles horizontally

        int ans=verticalAns+horizontalAns;
        dp[n]=ans;

        return ans;
    }

    // Tabulation
    public static int tilingM1Tabulation(int n, int m){
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;

        for(int i=3;i<=n;i++){
            dp[i] = dp[i-1];

            if(i-m>0){
                dp[i] += dp[i-m];
            }
        }

        return dp[n];
    }


//    Count Encodings
    // Recursive - 1
    public static int countEncodings(String str, int idx){
        if(idx == str.length()){
            return 1;
        }

        if(idx > str.length()){
            return 0;
        }

        if(str.charAt(idx) == '0'){   // string starts from 0
            return 0;
        }

        int singleDigitAns=countEncodings(str, idx+1);  // single digit code -> idx+1 se aage ki string ki encoding aa jaayegi
        int doubleDigitAns=0;

        // if index does not cross str.length() when we call for countEncodings(str, idx+2) && double digit code <= 26
        if(idx+2 <= str.length() && Integer.parseInt(str.substring(idx, idx+2)) <= 26){
            doubleDigitAns=countEncodings(str, idx+2);  // double digit code -> idx+2 se aage ki string ki encoding aa jaayegi
        }

        int ans=singleDigitAns+doubleDigitAns;
        return ans;
    }

    // Recursive - 2
    public static int countEncodings1(String str, int idx){
        if(idx == str.length()){
            return 1;
        }

        if(idx > str.length()){
            return 0;
        }

        if(str.charAt(idx) == '0'){   // string starts from 0
            return 0;
        }

        int singleDigitAns=countEncodings1(str, idx+1);  // single digit code -> idx+1 se aage ki string ki encoding aa jaayegi
        int doubleDigitAns=0;

        if(idx+2 <= str.length()){  // if index does not cross str.length() when we call for countEncodings(str, idx+2)
            int charAtidx=str.charAt(idx)-'0';  // the number at idx in integer form
            int charAtidxPlus1=str.charAt(idx+1)-'0';     // the number at idx+1 in integer form

            if( (charAtidx*10) + charAtidxPlus1 <= 26){  // double digit code <= 26
                doubleDigitAns=countEncodings1(str, idx+2); // double digit code -> idx+2 se aage ki string ki encoding aa jaayegi
            }
        }

        int ans=singleDigitAns+doubleDigitAns;
        return ans;
    }
}
