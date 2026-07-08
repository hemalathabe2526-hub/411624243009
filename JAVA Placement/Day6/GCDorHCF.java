package Day6;

public class GCDorHCF {
    // // Using Eclidean algorithm Method to find GCD
    // public static int gcd(int a, int b) {
    //     if (b == 0) {
    //         return a;
    //     }
    //     return gcd(b, a % b);
    // }
    // public static void main(String[] args) {
    //     int num1 = 48, num2 = 18;
    //     System.out.println("GCD of " + num1 + " and " + num2 + " is: " + gcd(num1, num2));
    // }

    // Using brute force method to find GCD
    public static int gcd(int a, int b) {
        int gcd = 1;
        for (int i = 1; i <= a && i <= b; i++) {
            if (a % i == 0 && b % i == 0) {
                gcd = i;
            }
        }
        return gcd;
    }
    public static void main(String[] args) {
        int num1 = 48, num2 = 18;
        System.out.println("GCD of " + num1 + " and " + num2 + " is: " + gcd(num1, num2));
    }
}