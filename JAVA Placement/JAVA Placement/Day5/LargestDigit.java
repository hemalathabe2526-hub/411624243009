package Day5;

public class LargestDigit {
    public static void main(String[] args) {
        long x = 438578694354L;
        int largest = 0;
        while (x > 0) {
            int digit = (int)(x % 10);
            if (digit > largest) {
                largest = digit;
            }
            x /= 10;
        }
        System.out.println("Largest digit: " + largest);
    }
}
