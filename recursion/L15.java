package recursion;

import java.util.ArrayList;
import java.util.Scanner;

public class L15 {
    static void main() {
        Scanner sc = new Scanner(System.in);

//        String str = sc.next();
//        int n = sc.nextInt();

//        ArrayList<String> al = getSubsequence(str);

//        ArrayList<String> al = getKPC(str);

//        ArrayList<String> al = getStairPaths(n);

        ArrayList<String> al=getMazePaths(0,0,2,2);

        System.out.println(al);
    }


//    Get Subsequence
    public static ArrayList<String> getSubsequence(String str) {
        if(str.length() == 0) {
            ArrayList<String> bans = new ArrayList<>();
            bans.add("");
            return bans;
        }

        char ch=str.charAt(0);
        String ros=str.substring(1);    // rest of the string

        ArrayList<String> smallAns=getSubsequence(ros);    // Assumption : idx 1 se le ke last idx tak ka subsequence aa jaayega
        // Suppose abc ke liye chalaana tha to hum maan ke chl rhe hain ki bc ke liye pehle se chalta hai
        // or uska ans smallAns me aa jaayega

        //Now I only have to get the subsequence which includes the char at idx 0.
        //It will have 2 choices : Either it will come or it will not come in the subsequence

        ArrayList<String> myAns=new ArrayList<>(); // Arraylist for storing the subsequence which includes the char at idx 0

        for(int i=0;i<smallAns.size();i++) {
            myAns.add(smallAns.get(i)); // char does not come
        }

        for(int i=0;i<smallAns.size();i++) {
            myAns.add(ch + smallAns.get(i));  // char comes
        }

        return myAns;
    }


//    Get KPC
    static String[] arr={".;","abc","def","ghi","jkl","mno","pqrs","tu","vwx","yz"};
//                        0     1     2     3     4     5      6     7    8     9

    public static ArrayList<String> getKPC(String str) {    // str = "479"
        if(str.length() == 0) {
            ArrayList<String> bans = new ArrayList<>();
            bans.add("");
            return bans;
        }

        char ch=str.charAt(0); // 4
        String ros=str.substring(1); // 79

        ArrayList<String> smallAns=getKPC(ros);  // Assumption : idx 1 se le ke last idx tak KPC aa jaayega
        // Suppose number 479 hai...to 79 ke liye KPC aa jaayega..mujhe bs saath me 4 ke liye KPC laana hai

        ArrayList<String> myAns=new ArrayList<>();

        String num=arr[ch - '0'];   // num = arr[4] = "jkl"  ,  [ch-'0'] will give ['4'-'0'] = [4] as integer

        for(int i=0;i<num.length();i++) {
            char c=num.charAt(i);   // j,k,l
            for(int j=0;j<smallAns.size();j++) {
                myAns.add(c + smallAns.get(j));
            }
        }

        return myAns;
    }


//    Get Stair Paths
    public static ArrayList<String> getStairPaths(int n){
        if(n<0){    // Path not possible - Destination crossed
            return new ArrayList<>();
        }

        if(n==0){   // reached destination
            ArrayList<String> bans=new ArrayList<>();
            bans.add("");
            return bans;
        }

        // Assumption : 2nd jump se le ke saari stairs cover krne tak ka path aa jaayega
        // mujhe bs first jump krni hai - jisko krne ke mere paas 3 options hai - 1 stair, 2 stairs, 3 stairs
        ArrayList<String> ans1=getStairPaths(n-1);  // Assumption : n-1 stairs cover krne ka path aa jaayega
                                                       // mujhe bs first jump me starting ka 1 step cover krna hai
        ArrayList<String> ans2=getStairPaths(n-2);  // Assumption : n-2 stairs cover krne ka path aa jaayega
                                                       // mujhe bs  first jump me starting ke 2 steps cover krna hai
        ArrayList<String> ans3=getStairPaths(n-3);  // Assumption : n-3 stairs cover krne ka path aa jaayega
                                                       // mujhe bs first jump me starting ke 3 steps cover krna hai

//        Now, I have 3 options : Cover the 1st step, cover 2 steps, cover 3 steps - in first jump
        ArrayList<String> myAns=new ArrayList<>();

        // covering 1 step in first jump
        for(int i=0;i<ans1.size();i++) {
            myAns.add('1'+ans1.get(i));
        }

        // covering 2 steps in first jump
        for(int i=0;i<ans2.size();i++) {
            myAns.add('2'+ans2.get(i));
        }

        // covering 3 steps in first jump
        for(int i=0;i<ans3.size();i++) {
            myAns.add('3'+ans3.get(i));
        }

        return myAns;
    }


//    Get Maze Paths
//    sr : source row, sc : source column, dr : destination row, dc : destination column
    public static ArrayList<String> getMazePaths(int sr, int sc, int dr, int dc){
        if(sr>dr || sc>dc){  // Crossed Maze boundary - Path not possible
            return new ArrayList<>();
        }

        if(sr==dr && sc==dc){   // reached destination
            ArrayList<String> bans=new ArrayList<>();
            bans.add("");
            return bans;
        }

        // Assumption: I will get the path from 2nd move onwards to reach the destination
        ArrayList<String> ansH=getMazePaths(sr, sc+1, dr, dc);  // when the first move is horizontal
        ArrayList<String> ansV=getMazePaths(sr+1, sc, dr, dc);  // when the first move is vertical


//        I only need to make the first move - which can be vertical or horizontal
        ArrayList<String> myAns=new ArrayList<>();

        // First Move - Horizontal
        for(int i=0;i<ansH.size();i++) {
            myAns.add('H'+ansH.get(i));
        }

        // First Move - Vertical
        for(int i=0;i<ansV.size();i++) {
            myAns.add('V'+ansV.get(i));
        }

        return myAns;
    }

}
