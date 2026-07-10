package backtracking;

import java.util.Scanner;

public class L18 {
    static void main() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

//        int[] arr = new int[n];
//        for(int i=0;i<n;i++){
//            System.out.print("arr["+i+"] : ");
//            arr[i]=sc.nextInt();
//        }

        int[][] chess=new int[n][n];

//        printTargetSumSubsets(arr,0,"",0,5);
//        printQueens(chess,0,"");
        printKnightsTour(chess, 2, 0, 1);
    }


//    Target Sum Subsets

    // set = subset, sos = sum of subset, tar = target
    public static void printTargetSumSubsets(int[] arr, int idx, String set, int sos, int tar){
        if(idx == arr.length){
            if(sos==tar){
                System.out.println(set);
            }
            return;
        }

        if(sos>tar){
            return;
        }

        // Assumption : I need to explore the choices of 1st element only, i.e. it comes in the subset or it doesn't come in the it
        // Rest all other element's choices will be explored automatically
        printTargetSumSubsets(arr, idx+1, set+arr[idx], sos+arr[idx], tar); // element comes
        printTargetSumSubsets(arr, idx+1, set, sos, tar);   // element does not come
    }


//    N-Queens
    public static void printQueens(boolean[][] chess, int row, String ans){
        if(row==chess.length){
            System.out.println(ans);
            return;
        }

        // Assumption : Mujhe sirf 1st queen ko place karna hai. Baaki saari queens apne aap place ho jaayengi
       for(int col=0;col<chess[0].length;col++){    // options where queen can be placed in row 0 -> saare columns me se kisi me bhi
           if(isSafe(chess,row,col)){
               chess[row][col]=true;    // place the queen for the current solution
               printQueens(chess,row+1,ans+row+"-"+col+", ");
               chess[row][col]=false;   // unplace the queen for the next solution
           }
       }
    }

    public static boolean isSafe(boolean[][] chess, int row, int col){
        // We need to check the cells above the current position only
        // because neeche to hum pahuche hi nahi...
        // hum row wise queen place kr rhe hai to current position se neeche queens placed hongi hi nahi
        // current row ko check krne ki need bhi nahi hai because hum row me queen ko place krte hi next row pe chale jaayenge
        // => agar mai iss cell pe aaya hu then this means iss row me issey phle koi queen place nahi hui hai

        //column
        for(int i=row-1;i>=0;i--){
            if(chess[i][col]==true){
                return false;
            }
        }

        //diagonal 1
        for(int i=row-1, j=col+1;i>=0 && j<chess[0].length; i--, j++){
            if(chess[i][j]==true){
                return false;
            }
        }

        //diagonal 2
        for(int i=row-1, j=col-1;i>=0 && j>=0 ;i--, j--){
            if(chess[i][j]==true){
                return false;
            }
        }

        return true;
    }


//    Knight's Tour
    public static void printKnightsTour(int[][] chess,int r, int c, int stepNo){
        if(r<0 || r>=chess.length || c<0 || c>=chess.length || chess[r][c]>0){ // Out of bounds or cell already visited
            return;
        }

        if(stepNo==chess.length*chess.length){  // Taking the last move - Successfully reached the final cell
            chess[r][c]=stepNo; // Marking the cell as visited for the current solution
            displayBoard(chess);
            chess[r][c]=0;  //  Marking the cell as not visited for the next solution
            return;
        }


        // Assumption : I only need to make the first move (step no. 1). Rest all other moves will be done automatically.
        // There are 8 choices (directions) for making the first move. I will have to explore all of them

        chess[r][c]=stepNo; // Marking the cell as visited for the current solution

        printKnightsTour(chess, r-2,c+1,stepNo+1);
        printKnightsTour(chess, r-1,c+2,stepNo+1);
        printKnightsTour(chess, r+1,c+2,stepNo+1);
        printKnightsTour(chess, r+2,c+1,stepNo+1);
        printKnightsTour(chess, r+2,c-1,stepNo+1);
        printKnightsTour(chess, r+1,c-2,stepNo+1);
        printKnightsTour(chess, r-1,c-2,stepNo+1);
        printKnightsTour(chess, r-2,c-1,stepNo+1);

        chess[r][c]=0;  // Marking the cell as not visited for the next solution
    }

    public static void displayBoard(int[][] chess){
        for(int i=0;i<chess.length;i++){
            for(int j=0;j<chess[0].length;j++){
                System.out.print(chess[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();   // To keep a gap of 1 line between all the solutions
    }
}
