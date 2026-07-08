package Day5;

public class CountEvenOdd {
    public static void main(String[] args) {
        int number = 232934;
        int count = 0;
        while (number != 0) {
            number /= 10; // Remove the last digit
            count++; // Increment the count
        }
        System.out.println("Number of digits: " + count);
    }
}