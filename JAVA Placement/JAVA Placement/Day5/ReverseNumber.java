package Day5;

public class ReverseNumber {
    public static void main(String[] args) {
        // Reverse Number
        System.out.println("Reversing Of Number: ");
        int n = 582;
        int count = 0;
        int rev = 0;
        System.out.println("Number for Reversing: " + n);
        while (n > 0) {
            rev = rev * 10 + n % 10;
            n /= 10;
            count++;
        }
        System.out.println("Reversed Number: " + rev);
        System.out.println("Number of digits: " + count);

        // Palindrome Number
        System.out.println("\nPalindrome Program: ");
        long num = 1234654563421L;
        long original = num;
        long reverse = 0;
        while (num > 0) {
            reverse = reverse * 10 + num % 10;
            num /= 10;
        }
        if (original == reverse) {
            System.out.println(original + " is a Palindrome");
        } else {
            System.out.println(original + " is Not a Palindrome");
        }
    }
}