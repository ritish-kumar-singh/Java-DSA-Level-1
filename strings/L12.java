package strings;

import java.util.Scanner;

public class L12 {
    public static void main() {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();

//        palindromicSubstring(str);

        System.out.println(compression1(str));
        System.out.println(compression2(str));
    }


    //Palindromic substring
    public static void palindromicSubstring(String str) {
        for(int si=0;si<str.length();si++){
            for(int ei=si;ei<str.length();ei++){
                if(isPalindrome(str.substring(si,ei+1))){   //if substring is a palindrome
                    System.out.println(str.substring(si,ei+1)); //print
                }
            }
        }
    }

    public static boolean isPalindrome(String str) {
        int left=0;
        int right=str.length()-1;

        while(left<right){
            if(str.charAt(left)!=str.charAt(right)){
                return false;
            }
            left++;
            right--;
        }

        return true;
    }


//    String Compression
    public static String compression1(String str){
        String ans="";

        ans+=str.charAt(0);

        for(int i=1;i<str.length();i++){
            if(str.charAt(i) != ans.charAt(ans.length()-1)){
                ans+=str.charAt(i);
            }
        }
        return ans;
    }

    public static String compression2(String str){
        String ans="";

        ans+=str.charAt(0);
        int count=1;

        for(int i=1;i<str.length();i++){
            if(str.charAt(i) == ans.charAt(ans.length()-1)){
                count++;
            }
            else{
                if(count>1){
                    ans+=count;
                }
                ans+=str.charAt(i);
                count=1;
            }
        }

        if(count>1){
            ans+=count;
        }

        return ans;
    }
}
