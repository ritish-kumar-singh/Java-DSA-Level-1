package recursion;

import java.util.Scanner;

public class L17 {
    static void main() {
        Scanner sc = new Scanner(System.in);

        String str = sc.next();

//        printPermutations(str,"");
        printEncodings(str, "");
    }


//    Print Permutations
    public static void printPermutations(String str, String ans){
        if(str.length()==0){
            System.out.println(ans);
            return;
        }

        // Assumption : Mujhe bs phli position pe character ko rakhna hai
        // baaki positions pe rest of the string se characters apne aap rakh diye jaayenge

        // Phli position pe character rakhne ke multiple options hai - harr ek character jo str me aa rha ho usme se koi bhi
        // phli position pe aa skta hai

        // Suppose str = "abc"
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);    // a, b, c
            String ros=str.substring(0,i) + str.substring(i+1); // rest of the string

            printPermutations(ros,ans+ch);
        }
    }


//    Print Encodings
    public static void printEncodings(String str, String ans){
        if(str.length()==0){
            System.out.println(ans);
            return;
        }

        if(str.charAt(0)=='0'){ // str cannot start with 0 - no encoding is available for 0
            return;
        }

        // Assumption : I need to get the encoding for the first no. only which can be of single or 2-digits (1 to 26)
        // Baaki numbers ke liye encodings apne aap aa jaayegi

        // 1st char ko String ki form me nikaalenge because baad me Integer.parseInt() me string hi use hoti hai char nahi
        String ch1=str.substring(0,1);  // String which denotes single digit no.
        String ros=str.substring(1);    // rest of the string

        printEncodings(ros,ans+(char)(Integer.parseInt(ch1) - 1 + 'a'));

        if(str.length()>=2){
            String ch2=str.substring(0,2);  // String with denotes 2-digit no.
            String ros2=str.substring(2);   // rest of the string

            if(Integer.parseInt(ch2)<=26){
                printEncodings(ros2,ans+(char)(Integer.parseInt(ch2) - 1 + 'a'));
            }
        }
    }
}
