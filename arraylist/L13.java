package arraylist;

import java.util.ArrayList;

public class L13 {
    static void main() {
        ArrayList<Integer> al=new ArrayList<>();
//        al.add(10);
//        al.add(20);
//        al.add(30);
//        al.add(40);
//        al.add(50);
//
//        System.out.println(al);
//        System.out.println(al.size());
//        System.out.println(al.get(2));
//
//        al.set(3,200);
//        System.out.println(al);
//
//        al.remove(3);
//        System.out.println(al);
//
//        al.add(3,40);
//        System.out.println(al);

        al.add(1);
        al.add(2);
        al.add(3);
        al.add(4);
        al.add(5);
        al.add(6);
        al.add(7);
        al.add(8);
        al.add(9);
        al.add(10);

        removePrimes(al);
        System.out.println(al);
    }


//    Remove Primes
    public static void removePrimes(ArrayList<Integer> al){
        for(int i=0;i<al.size();i++){
            if(isPrime(al.get(i))){
                al.remove(i);
                i--;    //Shifting of idx happens on removal - next element would be missed if i-- is not done
            }
        }
    }

    public static boolean isPrime(int n){
        for(int div=2;div*div<=n;div++){
            if(n%div==0){
                return false;
            }
        }
        return true;
    }

}
