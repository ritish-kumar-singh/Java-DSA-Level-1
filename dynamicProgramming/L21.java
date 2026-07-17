package dynamicProgramming;

import java.util.Scanner;

public class L21 {
    static void main() {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();

        int[] dp=new int[n+1];  // storage/array

//        System.out.println(fib(n,dp));

        System.out.println(fibTab(n));
    }


//    Fibonacci - Memoization
    public static int fib(int n, int[] dp){
        if(n==0 || n==1){
            return n;
        }

        if(dp[n]!=0){   // if ans is already calculated & stored, then use it instead of calculating it again
            return dp[n];
        }

        int fnm1=fib(n-1, dp);  // Fibonacci of n-1
        int fnm2=fib(n-2, dp);  // Fibonacci of n-2

        int fn = fnm1 + fnm2;  // Fibonacci of n

        dp[n]=fn;   // ans return krne se phle store karenge

        return fn;
    }

//    Fibonacci - Tabulation
    public static int fibTab(int n){
        int[] dp=new int[n+1];  // storage

        // storing the initially known values
        dp[0]=0;
        dp[1]=1;

        // iteration
        for(int i=2;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];  // fibonacci calculation formula - used for filling the storage
        }

        return dp[n];   // fib(n) is stored at dp[n]
    }
}
