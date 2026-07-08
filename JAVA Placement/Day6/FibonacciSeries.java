package Day6;

public class FibonacciSeries {
    // public static void main(String[] args) {
    //     int n = 10, firstTerm = 0, secondTerm = 1;
    //     System.out.println("Fibonacci Series up to " + n + ":");
    //     for (int i = 1; i <= n; ++i) {
    //         System.out.print(firstTerm + " ");
    //         int nextTerm = firstTerm + secondTerm;
    //         firstTerm = secondTerm;
    //         secondTerm = nextTerm;
    //     }
    // }

    // Using recursion
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    public static void main(String[] args) {
        int n = 10;
        System.out.println("Fibonacci Series up to " + n + ":");
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }
}