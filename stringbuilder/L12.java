package stringbuilder;

import java.util.Scanner;

public class L12 {
    public static void main() {
//        StringBuilder sb=new StringBuilder("hello");
//        sb.append('e');
//
//        System.out.println(sb);
//
//        sb.deleteCharAt(2);
//        System.out.println(sb);
//        System.out.println(sb.length());
//
//        sb.setCharAt(0,'i');
//        System.out.println(sb);
//
//        sb.insert(0,'h');
//        System.out.println(sb);

        Scanner sc=new Scanner(System.in);
        String str=sc.next();

//        System.out.println(toggleCase(str));

        printPermutations(str);

    }


//    Toggle Case
    public static String toggleCase(String str) {
        StringBuilder sb=new StringBuilder();

        for(int i=0;i<str.length();i++) {
            char ch=str.charAt(i);

            if(ch>='a' && ch<='z'){
                sb.append((char)(ch-'a'+'A'));
            }
            else{
                sb.append((char)(ch-'A'+'a'));
            }
        }

        return sb.toString();
    }


//    Permutations of a String
    public static void printPermutations(String str) {
        int n=str.length();
        int totalPermutations=1;

        for(int i=1;i<=n;i++) {
            totalPermutations*=i;   // Calculate total Np. of permutations
        }

        for(int i=0;i<totalPermutations;i++) {
            int dividend=i;
            int divisor=n;

            StringBuilder sb=new StringBuilder(str);
            StringBuilder ans=new StringBuilder();

            while(divisor>0) {
                int rem=dividend%divisor;
                dividend/=divisor;
                divisor--;

                ans.append(sb.charAt(rem));
                sb.deleteCharAt(rem);
            }
            System.out.println(ans);    // Print each permutation
        }
    }

}