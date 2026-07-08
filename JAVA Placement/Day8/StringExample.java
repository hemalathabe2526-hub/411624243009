package Day8;

public class StringExample {
    public static void main(String[] args) {
        // String s1 = "Lakshman Sir";
        // String s2 = "Lakshman Sir's Wife";
        // String d = new String("Sunil");
        // StringBuffer sb = new StringBuffer();
        // StringBuilder sr = new StringBuilder();
        // sb.append("Lakshman Sir Son");
        // sr.append("Lakshman Sir Daughter");

        // System.out.println(s1);
        // System.out.println(s2);
        // System.out.println(sb.toString());
        // System.out.println(sr.toString());


        // // Program to find the strings present in another string
        // String s1 = "SAIVIMAL";
        // String s2 = "VIMALINI";
        // if(s1.length() == s2.length() && (s1 + s2).contains(s2)){
        //     System.out.println("Rotation");
        // }
        // else{
        //     System.out.println("Not a Rotation");
        // }


        // Program of LargestOddSubstring
        String num = "3855320917268";
        for(int i = num.length()-1; i >= 0; i--){
            int digit = num.charAt(i) - '0';
            if(digit % 2!= 0){
                System.out.println(num.substring(0, i + 1));
                return;
            }
        }
        System.out.println("");
    }
}