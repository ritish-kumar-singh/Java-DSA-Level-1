package recursion;

import java.util.ArrayList;
import java.util.Scanner;

public class L16 {
    static void main() {
        Scanner sc=new Scanner(System.in);
//        String str=sc.next();
//        int n=sc.nextInt();

//        ArrayList<String> al=getMazePathWithJumps(0,0,3,4);
//        System.out.println(al.size());

//        printSubsequence(str,"");
//        printKPC(str,"");
//        printStairPaths(n,"");
//        printMazePaths(0,0,3,3,"");
        printMazePathWithJumps(0,0,3,4,"");
    }


//    Get Maze Path With Jumps
    public static ArrayList<String> getMazePathWithJumps(int sr, int sc, int dr, int dc){
        if(sr==dr && sc==dc){
            ArrayList<String> bans=new ArrayList<>();
            bans.add("");
            return bans;
        }

        ArrayList<String> myAns=new ArrayList<>();

        for(int jump=1;jump<=dc-sc;jump++){ // jumps possible when moving horizontally
            ArrayList<String> ansH=getMazePathWithJumps(sr,sc+jump,dr, dc); // Assumption: Getting the path from 2nd to last step
            // Taking the first step - Horizontally
            for(int i=0;i<ansH.size();i++){
                myAns.add("H" + jump + ansH.get(i));
            }
        }

        for(int jump=1;jump<=dr-sr;jump++){ // jumps possible when moving vertically
            ArrayList<String> ansV=getMazePathWithJumps(sr+jump,sc,dr, dc); // Assumption: Getting the path from 2nd to last step
            // Taking the first step - Vertically
            for(int i=0;i<ansV.size();i++){
                myAns.add("V" + jump + ansV.get(i));
            }
        }

        for(int jump=1;jump<=dc-sc && jump<=dr-sr;jump++){ // jumps possible when moving diagonally
            ArrayList<String> ansD=getMazePathWithJumps(sr+jump,sc+jump,dr, dc); // Assumption: Getting the path from 2nd to last step
            // Taking the first step - Diagonally
            for(int i=0;i<ansD.size();i++){
                myAns.add("D" + jump + ansD.get(i));
            }
        }

        return myAns;
    }


//    RECURSION -> BOTTOM TO TOP APPROACH

//    Print Subsequence
    public static void printSubsequence(String str, String ans){
        if(str.length()==0){    // subsequence prepared
            System.out.println(ans);    // print subsequence
            return;
        }

        // Assumption : Baaki sab character apne aap subsequence bann jaayega
        // mujhe bs first character ke options explore krne hain
        // jab vo ans me add hoga tab ans kaisa hoga or jab nhi add hoga tab ans kaisa hoga
        // & keep in mind ki starting me ans "" hoga. So first character ko agar add hona hoga to isme baad me hi add hoga ("" + ch)

        char ch=str.charAt(0); // first character
        String ros=str.substring(1);    // rest of the string

        // Each character hai 2 choices - ya to ans me add hoga, ya fir nahi hoga
        printSubsequence(ros, ans+ch);  // character included in the ans
        printSubsequence(ros, ans); // character not included in the ans
    }


//    Print KPC
    static String[] arr={".;","abc","def","ghi","jkl","mno","pqrs","tu","vwx","yz"};
    //                    0     1     2     3     4     5      6     7    8     9

    public static void printKPC(String str, String ans){
        if(str.length()==0){
            System.out.println(ans);
            return;
        }

//        Suppose str = "479"
//        Assumption : i need to get the ans for 4. Ans for 79 will be handled automatically

        char ch=str.charAt(0);  // 4
        String ros=str.substring(1);    // 79

        String num=arr[ch - '0'];   // arr[4] = "jkl"

        for(int i=0;i<num.length();i++){
            char c=num.charAt(i);   // j, k, l
            printKPC(ros, ans+c);   // initially ans was "". That's why we add c at end
                                    // (Keep in mind we are making the ans while going up and not while coming down)
                                    // There is no printKPC(ros, ans) because a number is pressed always => some char will always
                                    // => there is no option that a key is pressed and nothing comes in the ans
                                    // yahan pe we only have a choice ki character aayega to kya bann ke aayega (eg - j,k,l)
        }
    }


//    Print Stair Paths
    public static void printStairPaths(int n, String ans){
        if(n<0){    // crossed destination
            return;
        }

        if(n==0){   // reached destination
            System.out.println(ans);
            return;
        }

        //Assumption : Mujhe bs 1st step move krna hai. rest all the steps will be taken automatically.
        //1st step move krne ke 3 options hain - climb 1 step, 2 steps, 3 steps
        printStairPaths(n-1, ans+"1");  // Climb 1 step
        printStairPaths(n-2, ans+"2");  // Climb 2 steps
        printStairPaths(n-3, ans+"3");  // Climb 3 steps
    }


//    Print Maze Paths
    public static void printMazePaths(int sr, int sc, int dr, int dc, String ans){
        if(sr>dr || sc>dc){ // crossed maze boundary
            return;
        }

        if(sr==dr && sc==dc){   // reached destination
            System.out.println(ans);
            return;
        }

        // Assumption : i need to get the ans for first movement only. Rest all movements will be done automatically.

        // For 1st movement I have two options : Either Move Horizontally or Vertically
        printMazePaths(sr, sc+1, dr, dc, ans+"H");  // Horizontal Movement
        printMazePaths(sr+1, sc, dr, dc, ans+"V");  // Vertical Movement
    }


    //    Print Maze Path With Jumps
    public static void printMazePathWithJumps(int sr, int sc, int dr, int dc, String ans){
        if(sr==dr && sc==dc){   // reached destination
            System.out.println(ans);
            return;
        }

        // Assumption : i need to get the ans for first jump only. Rest all movements/jumps will be done automatically.

        for(int jump=1;jump<=dc-sc;jump++){ // jumps possible for horizontal movement
            printMazePaths(sr, sc+jump, dr, dc, ans+"H"+jump);   // Horizonal Movement
        }

        for(int jump=1;jump<=dr-sr;jump++){ // jumps possible for vertical movement
            printMazePaths(sr+jump, sc, dr, dc, ans+"V"+jump);   // Vertical Movement
        }

        for(int jump=1;jump<=dc-sc && jump<=dr-sr;jump++){ // jumps possible for diagonal movement
            printMazePaths(sr+jump, sc+jump, dr, dc, ans+"D"+jump);   // Diagonal Movement
        }
    }
}

