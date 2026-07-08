package Day7;
import java.util.Scanner;

public class Method {
    // // Method to calculate product
    // public static int product(int a, int b) {
    //     return a * b;
    // }
    // public static void main(String[] args) {
    //     int result = product(10, 5);
    //     System.out.println("Product = " + result);
    // }

    // // Method to Calculate the Sum
    // public static int sum(int a, int b) {
    //     return a + b;
    // }
    // public static void main(String[] args) {
    //     Scanner sc = new Scanner(System.in);
    //     System.out.print("Enter first number: ");
    //     int num1 = sc.nextInt();
    //     System.out.print("Enter second number: ");
    //     int num2 = sc.nextInt();
    //     int result = sum(num1, num2);
    //     System.out.println("Sum = " + result);
    //     sc.close();
    // }


    // Products Of Digits in recursion
    static int productDigits(int n, int product) {
        if (n == 0) {
            return product;
        }
        return productDigits(n / 10, product * (n % 10));
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = sc.nextInt();
        int result = productDigits(n, 1);
        System.out.println("Product of digits = " + result);
        sc.close();
    }
}