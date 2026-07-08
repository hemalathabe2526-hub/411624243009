package Day5;

import java.util.Scanner;
public class PerfectNumber {
    public static void main(String[] args) {
        // Perfect Number Program
        System.out.println("Finding Perfect Number ");
        int n = 28;
        int sum = 0;
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                sum += i;
            }
        }
        System.out.println(sum == n ? "Perfect Number" : "Not a Perfect Number");

        // Factorial Program
        System.out.println("\nFactorial");
        int num = 5;
        long fact = 1;
        for (int i = 1; i <= num; i++) {
            fact *= i;
        }
        System.out.println("Factorial of " + num + " = " + fact);

        // Finding Of Prime Number
        System.out.println("\nFinding of Prime Number Program ");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num1 = sc.nextInt();
        boolean isPrime = true;
        if (num1 <= 1) {
            isPrime = false;
        } else {
            for (int i = 2; i <= Math.sqrt(num1); i++) {
                if (num1 % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }
        if (isPrime) {
            System.out.println(num1 + " is a Prime Number");
        } else {
            System.out.println(num1 + " is Not a Prime Number");
        }
        sc.close();
    }
}