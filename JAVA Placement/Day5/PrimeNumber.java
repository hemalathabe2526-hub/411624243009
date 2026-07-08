package Day5;

import java.util.Scanner;

public class PrimeNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n value: ");
        int n = sc.nextInt();
        System.out.println("Prime numbers from 1 to " + n + ":");
        for (int num = 2; num <= n; num++) {
            int count = 0;
            for (int i = 1; i <= num; i++) {
                if (num % i == 0) {
                    count++;
                }
            }
            if (count == 2) {
                System.out.print(num + " ");
            }
        }
        sc.close();
    }
}