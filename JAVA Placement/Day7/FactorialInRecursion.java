package Day7;

import java.util.Scanner;

public class FactorialInRecursion {
    static int factorial(int i, int n, int fact) {
        if (i > n) {
            return fact;
        }
        return factorial(i + 1, n, fact * i);
    }
    static void printSeries(int i, int n) {
        if (i > n) {
            return;
        }
        System.out.print(i);
        if (i < n) {
            System.out.print(" * ");
        }
        printSeries(i + 1, n);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = sc.nextInt();
        System.out.print(n + "! = ");
        printSeries(1, n);
        int s = factorial(1, n, 1);
        System.out.println(" = " + s);
        sc.close();
    }
}