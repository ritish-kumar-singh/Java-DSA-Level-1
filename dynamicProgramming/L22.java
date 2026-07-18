package dynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;

public class L22 {
    static void main() {
        Scanner sc=new Scanner(System.in);
//        int n=sc.nextInt();


//        int[] arr={2,1,2,4,2,0,0};
//        int[] dp=new int[arr.length+1];
//        Arrays.fill(dp,-1);

//        System.out.println(climbStairs(n,dp));
//        System.out.println(climbStairsTab(n));

//        System.out.println(climbStairsWithVariableJumps(arr,0,dp));
//        System.out.println(climbStairsWithVariableJumpsTabulation(arr,0));

        int[][] arr={
                    {2,3,0,1},
                    {2,2,2,3},
                    {4,3,2,1},
                    {0,2,5,3},
                    {5,6,7,4}
                    };
        int[][] dp=new int[arr.length][arr[0].length];
        System.out.println(minCostInMazeTraversal(arr, 0, 0, dp));
    }


    //    Climb Stairs - No. of ways possible to climb the stairs - At max 3 stairs can be climbed at once
    // Memoization
    public static int climbStairs(int n, int[] dp){
        if(n==0){   // reached destination => no. of stairs left to climb = 0
            return 1;   // destination pe pahuchne ka 1 hi way hai, i.e. same place par khade raho
        }

        if(n<0){    // destination crossed
            return 0;   // no. of ways to reach the destination = 0
        }

        if(dp[n]!=0){
            return dp[n];
        }

        int f1=climbStairs(n-1, dp);    // climbing 1 stair
        int f2=climbStairs(n-2, dp);    // climbing 2 stairs
        int f3=climbStairs(n-3, dp);    // climbing 3 stairs

        int ans=f1+f2+f3;

        dp[n]=ans;  //storing the ans

        return ans;
    }

    // Tabulation
    public static int climbStairsTab(int n){
        int[] dp=new int[n+1]; // storage

        // storing known value
        dp[0]=1;    // jab 0 stairs bache hain tab destination pe pahuchne ke no. of ways=1, i.e. wahi pe khade raho

//        ans = dp[1] + dp[2] + dp[3] -> ans = no. of ways of climbing 1 stair, 2 stairs & 3 stairs

        for(int i=1;i<=n;i++){
            dp[i]=dp[i-1];
            if(i-2>=0){     //  ensuring dp[i-2] exists
                dp[i]+=dp[i-2];
            }
            if(i-3>=0){     //  ensuring dp[i-3] exists
                dp[i]+=dp[i-3];
            }
        }

        return dp[n];   // climbStairs(n)
    }


//    Climb Stairs With Variable Jumps
    // Memoization
    public static int climbStairsWithVariableJumps(int[] arr, int idx, int[] dp){
        if(idx==arr.length){    // already at destination => no. of ways to reach destination = 1, i.e. just stay there
            return 1;
        }

        if(idx>arr.length){ // crossed destination
            return 0;
        }

        if(dp[idx]!=0){     // directly get the ans if it is pre-calculated
            return dp[idx];
        }

        int ans=0;
        for(int jump=1;jump<=arr[idx];jump++){  // jumps possible
            ans+=climbStairsWithVariableJumps(arr,idx+jump,dp); // no. of ways to climb the stairs
        }

        dp[idx]=ans;    // store the ans

        return ans;
    }

    //Tabulation
    public static int climbStairsWithVariableJumpsTabulation(int[] arr, int idx){
        int n=arr.length;
        int[] dp=new int[n+1];
        dp[n]=1;    // already at destination => no. of ways to reach destination = 1, i.e. just stay there

        for(int i=n-1;i>=0;i--){
            for(int jump=1;jump<=arr[i];jump++){    // possible jumps from current index
                if((i+jump)<=n){    // current idx se jump lene par agar at max destination tak hi jaa rha hu to hi jump lunga
                    dp[i]+=dp[i+jump];
                }
            }
        }

        return dp[0];   // total no. of ways possible from 0th idx to reach the destination
    }


    //Climb Stairs With Minimum Moves
    public static int climbStairsWithMinimumMoves(int[] arr, int idx){
        int n=arr.length;
        int[] dp=new int[n];   // because destination is at arr.length-1 (=> 0 to n-1 = n elements)
        dp[n-1]=0;  // destination => no moves required

        for(int i=n-2;i>=0;i--){
            int min=Integer.MAX_VALUE-1;    // Because in java, infinity+1 = -infinity
            for(int jump=1;jump<=arr[i];jump++){    // possible jumps from current index
                if((i+jump)<dp.length){ // take a jump till destination & not beyond that
                    min=Math.min(min,dp[i+jump]);   // min moves required to reach the destination
                }
            }
            // making a move from the current idx to the idx from where it takes the min moves to reach the destination
            dp[i]=min+1;    // that's why +1
        }
        return dp[0];   //  0 se destination tak jaane ke liye min moves
    }


//    Minimum Cost in Maze Traversal
//    Destination is the Bottom-Right cell, i.e. arr[n-1][m-1]
//   Can move at max 1 cell in either vertical or horizontal direction
    // Memoization
    public static int minCostInMazeTraversal(int[][] arr, int sr, int sc, int[][] dp){
        if(sr>= arr.length || sc>=arr[0].length){  // crossed maze boundary
            return Integer.MAX_VALUE;
        }

        if(sr==arr.length-1 && sc==arr[0].length-1){    // reached destination
            return arr[sr][sc]; // cost of destination cell
        }

        if(dp[sr][sc]!=0){  // if cost is pre-calculated, just get it directly
            return dp[sr][sc];
        }

        int horizontalCost=minCostInMazeTraversal(arr, sr,sc+1,dp); // min cost if I take jump in horizontal direction
        int verticalCost=minCostInMazeTraversal(arr,sr+1,sc,dp);    // min cost if I take jump in vertical direction

        int minCost=Math.min(horizontalCost,verticalCost) + arr[sr][sc];    // min cost after taking a jump + current cell cost

        dp[sr][sc]=minCost; //store the cost

        return minCost;
    }
}
