package Day7;
import java.util.Scanner;

public class Print1toN{
    // static void printNumbers(int n) {
    //     if (n == 0) { // Base case
    //         return;
    //     }
    //     printNumbers(n - 1); // Recursive call
    //     System.out.print(n + " ");
    // }
    // public static void main(String[] args) {
    //     Scanner sc = new Scanner(System.in);
    //     System.out.print("Enter n: ");
    //     int n = sc.nextInt();
    //     printNumbers(n);
    //     sc.close();
    // }


    // // Recursive Sum Problem
    // static int sum(int n) {
    //     if (n == 0)
    //         return 0;
    //     return n + sum(n - 1);
    // }
    // static void printSeries(int n) {
    //     if (n == 1) {
    //         System.out.print(1);
    //         return;
    //     }
    //     printSeries(n - 1);
    //     System.out.print(" + " + n);
    // }
    // public static void main(String[] args) {
    //     Scanner sc = new Scanner(System.in);
    //     System.out.print("Enter n: ");
    //     int n = sc.nextInt();
    //     System.out.print("Sum = ");
    //     printSeries(n);
    //     System.out.println(" = " + sum(n));
    //     sc.close();
    // }


    // Sum Of Digit in recursion
     static int sumDigits(int n, int sum) {
        if (n == 0) {
            return sum;
        }
        return sumDigits(n / 10, sum + (n % 10));
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = sc.nextInt();
        int result = sumDigits(n, 0);
        System.out.println("Sum of digits = " + result);
        sc.close();
    }
}
