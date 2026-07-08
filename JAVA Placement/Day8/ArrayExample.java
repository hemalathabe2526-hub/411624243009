package Day8;

import java.util.Arrays;

/*public class DigitExtractor {
    public static void main(String[] args) {
        String num = "3855320917";
        for (int i = num.length() - 1; i >= 0; i--) {
            int digit = num.charAt(i) - '0';
            System.out.println(digit);
        }
    }
}*/

public class ArrayExample {
    public static void main(String[] args){
        // Anagram Program
        String s1 = "listen";
        String s2 = "silent";
        char[] a = s1.toCharArray();
        char[] b = s2.toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);
        if(Arrays.equals(a, b)){
            System.out.println("Anagram");
        }
        else{
            System.out.println("Not a Anagram");
        }
    }
}