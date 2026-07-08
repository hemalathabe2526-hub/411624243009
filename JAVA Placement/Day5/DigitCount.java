package Day5;

public class DigitCount {
    public static void main(String[] args) {
        int[] arr = {12, 45, 7, 89, 23, 10, 8};
        int evenCount = 0;
        int oddCount = 0;
        for (int num : arr) {
            if (num % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }
        System.out.println("Even numbers count: " + evenCount);
        System.out.println("Odd numbers count: " + oddCount);
    }
}