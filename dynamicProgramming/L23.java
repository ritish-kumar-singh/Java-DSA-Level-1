package dynamicProgramming;

public class L23 {
    static void main() {
        int[][] arr={
                {2,3,0,1},
                {2,2,2,3},
                {4,3,2,1},
                {0,2,5,3},
                {5,6,7,4}
        };

//        int[][] dp=new int[arr.length][arr[0].length];

//        System.out.println(minCostInMazeTraversalTabulation(arr,0,0));
//        System.out.println(goldmine(arr));

        int[] denominations={2,3,5,7};
        System.out.println(coinChangeCombination(denominations, 10));
        System.out.println(coinChangePermutation(denominations, 10));
    }


//    Minimum Cost in Maze Traversal
//    Destination is the Bottom-Right cell, i.e. arr[n-1][m-1]
//   Can move at max 1 cell in either vertical or horizontal direction
    // Tabulation
    public static int minCostInMazeTraversalTabulation(int[][] arr, int sr, int sc){
        int[][]  dp=new int[arr.length][arr[0].length]; // because destination is at [n-1][m-1]

        for(int i=dp.length-1;i>=0;i--){
            for(int j=dp[0].length-1;j>=0;j--){
                if(i==dp.length-1 && j==dp[0].length-1){    // destination
                    dp[i][j]=arr[i][j]; // destination cell cost
                }
                else if(i==dp.length-1){ // last row => vertical move not possible
                    dp[i][j]+=dp[i][j+1] + arr[i][j];   // horizontal move cost + current cell cost
                }
                else if(j==dp[0].length-1){ // last col => horizontal move not possible
                    dp[i][j]+=dp[i+1][j] + arr[i][j];   // vertical move cost + current cell cost
                }
                else{   // Min(Vertical move cost, Horizontal move cost) + current cell cost
                    dp[i][j]+=Math.min(dp[i+1][j],dp[i][j+1]) + arr[i][j];
                }
            }
        }

        return dp[0][0];    // min cost to reach destination from (0,0)
    }


//    Goldmine
    public static int goldmine(int[][] arr){
        int n=arr.length;
        int m=arr[0].length;
        int[][] dp=new int[n][m];

        for(int j=m-1;j>=0;j--){    // right to left column-wise
            for(int i=0;i<n;i++){   // for all rows
                if(j==m-1){ // last column
                    dp[i][j] = arr[i][j];
                }
                else if(i==0){   // row 0
                    dp[i][j] = Math.max(dp[i][j+1], dp[i+1][j+1]) + arr[i][j];
                }
                else if(i==n-1){ // last row
                    dp[i][j] = Math.max(dp[i][j+1], dp[i-1][j+1]) + arr[i][j];
                }
                else{
                    dp[i][j] = Math.max(dp[i][j+1],Math.max(dp[i+1][j+1], dp[i-1][j+1])) + arr[i][j];
                }
            }
        }

        // get the max amount of gold collected
        int ans=0;
        for(int i=0;i<n;i++){
            ans = Math.max(ans, dp[i][0]);
        }
        return ans;
    }


//    Gold Change Combination
    public static int coinChangeCombination(int[] denominations, int amount){
        int[] dp=new int[amount+1];
        dp[0]=1;    // amount = 0 ko pay krne ka 1 way hota hai, i.e. kuch bhi mat do

        // traversal will be like - ek denomination purey array me
        // ek denomination different amounts pe
        for(int i=0;i<denominations.length;i++){
            int coin=denominations[i];  // current denomination
            for(int j=coin;j<dp.length;j++){ // j=coin -> so that dp[j]-coin is always >=0
                dp[j]+=dp[j-coin];  // no. of ways possible to make the current amount (dp[j]) using the given denominations
            }
        }

        return dp[amount];  // no. of ways possible to make the given amount using the given denominations
    }


//    Gold Change Permutation
    public static int coinChangePermutation(int[] denominations, int amount){
        int[] dp=new int[amount+1];
        dp[0]=1;    // amount = 0 ko pay krne ka 1 way hota hai, i.e. kuch bhi mat do

        // traversal will be like - ek amount pe different denominations
        for(int i=0;i<dp.length;i++){
            for(int j=0;j<denominations.length;j++){    // because for an amount, denominations will vary
                int coin=denominations[j];

                if(i-coin>=0){  // current amount(i) - coin value >= 0
                    dp[i]+=dp[i-coin];
                }
            }
        }
        return dp[amount];
    }

}
