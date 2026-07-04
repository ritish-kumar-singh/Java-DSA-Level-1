package recursion;

import java.util.Scanner;

public class L13 {
    static void main() {
        Scanner sc = new Scanner(System.in);
//        int x = sc.nextInt();
//        int n = sc.nextInt();

//        printDecreasing(n);
//        printIncreasing(n);
//        printDecreasingIncreasing(n);
//        System.out.println(factorial(n));
//        System.out.println(powerLinear(x, n));
//        System.out.println(powerLogarithmic(x,n));

        towerOfHanoi(3,10,11,12);
    }


//    Print Decreasing
    public static void printDecreasing(int n){
        if(n==0){
            return;
        }

        System.out.println(n);
        printDecreasing(n-1);   // Assumption - Function already works for lower values
    }


//    Print Increasing
    public static void printIncreasing(int n){
        if(n==0){
            return;
        }

        printIncreasing(n-1);
        System.out.println(n);
    }


//    Print Decreasing-Increasing
    public static void printDecreasingIncreasing(int n){
        if(n==0){
            return;
        }

        System.out.println(n);
        printDecreasingIncreasing(n-1);
        System.out.println(n);
    }


//    Factorial
    public static int factorial(int n){
        if(n==0){
            return 1;
        }

        return n * factorial(n-1);
    }


//    Power Linear
    public static int powerLinear(int x,int n){
        if(n==0){
            return 1;
        }

        return x * powerLinear(x,n-1);
    }


//    Power Logarithmic
    public static int powerLogarithmic(int x,int n){
        if(n==0){
            return 1;
        }

        int xpnby2=powerLogarithmic(x,n/2);     // x raised to the power n/2

        int xpn=xpnby2 * xpnby2;    // x raised to the power n

        if(n%2!=0){     // when n is odd
            xpn*=x;     // xpn = xpn * x
        }

        return xpn;
    }


//    Tower of Hanoi
    public static void towerOfHanoi(int n, int t1, int t2, int t3){
        if(n==0){
            return;
        }

        towerOfHanoi(n-1,t1,t3,t2); //faith 1 : moving n-1 disks from t1 to t3 using t2

        System.out.println(n+"["+t1+" -> "+t2+"]"); // disk no which is moved, source tower, destination tower

        towerOfHanoi(n-1,t3,t2,t1); // faith 2 : moving n-1 disks from t3 to t2 using t1
    }
}

