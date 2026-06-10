package Day2;

import java.util.Scanner;
public class DifferentPatterns {
    public static void main(String[] args) {
        // Pattern 1: Square pattern
        int n = 5; // Number of rows
        System.out.println("Pattern 1: Square pattern");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print("# ");
            }
            System.out.println();
        }

        // Pattern 2: Right-angled triangle pattern
        System.out.println("Pattern 2: Right-angled triangle pattern");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }

        // Pattern 3: Inverted right-angled triangle pattern
        System.out.println("Pattern 3: Inverted right-angled triangle pattern");
        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("$ ");
            }
            System.out.println();
        }

        // Pattern 4: Diamond pattern
        System.out.println("Pattern 4: Diamond pattern");
        // Upper half
        for (int i = 1; i <= n; i++) {
            // Spaces
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            // Stars
            for (int j = 1; j <= (2 * i - 1); j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        // Lower half
        for (int i = n - 1; i >= 1; i--) {
            // Spaces
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            // Stars
            for (int j = 1; j <= (2 * i - 1); j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        // Pattern 5: Hollow square pattern
        System.out.println("Pattern 5: Hollow square pattern");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 || i == n || j == 1 || j == n) {
                    System.out.print("% ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }

        // Pattern 6: Pyramid pattern
        System.out.println("Pattern 6: Pyramid pattern");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n - i; j++) {
                System.out.print("  ");
            }
            for (int k = 1; k <= 2 * i - 1; k++) {
                System.out.print("& ");
            }
            System.out.println();
        }

        // Pattern 7: Inverted pyramid pattern
        System.out.println("Pattern 7: Inverted pyramid pattern");
        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= n - i; j++) {
                System.out.print("  ");
            }
            for (int k = 1; k <= 2 * i - 1; k++) {
                System.out.print("^ ");
            }
            System.out.println();
        }

        // Pattern 8: Diamond pattern with spaces
        System.out.println("Pattern 8: Diamond pattern with spaces");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n - i; j++) {
                System.out.print("  ");
            }
            for (int k = 1; k <= 2 * i - 1; k++) {
                if (k == 1 || k == 2 * i - 1) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }

        // Pattern 9: Hollow pyramid pattern
        System.out.println("Pattern 9: Hollow pyramid pattern");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n - i; j++) {
                System.out.print("  ");
            }
            for (int k = 1; k <= 2 * i - 1; k++) {
                if (k == 1 || k == 2 * i - 1 || i == n) {
                    System.out.print("# ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }   

        // Pattern 10: Hollow diamond pattern
        System.out.println("Pattern 10: Hollow diamond pattern");
        for (int i = 1; i <= n; i++) {  
            for (int j = 1; j <= n - i; j++) {
                System.out.print("  ");
            }
            for (int k = 1; k <= 2 * i - 1; k++) {
                if (k == 1 || k == 2 * i - 1) {
                    System.out.print("@ ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }

        // Pattern 11: Hollow inverted pyramid pattern
        System.out.println("Pattern 11: Hollow inverted pyramid pattern");
        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= n - i; j++) {
                System.out.print("  ");
            }
            for (int k = 1; k <= 2 * i - 1; k++) {
                if (k == 1 || k == 2 * i - 1 || i == n) {
                    System.out.print("$ ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }

        // Pattern 12: Floyd's triangle pattern
        System.out.println("Pattern 12: Floyd's triangle pattern");
        int num = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(num + " ");
                num++;
            }
            System.out.println();
        }

        // Pattern 13: Butterfly pattern
        System.out.println("Pattern 13: Butterfly pattern");
         // Upper half
        for (int i = 1; i <= n; i++) {
            // Left stars
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            // Spaces
            for (int j = 1; j <= 2 * (n - i); j++) {
                System.out.print(" ");
            }
            // Right stars
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        // Lower half
        for (int i = n - 1; i >= 1; i--) {
            // Left stars
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            // Spaces
            for (int j = 1; j <= 2 * (n - i); j++) {
                System.out.print(" ");
            }
            // Right stars
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        // Pattern 14: right-angled triangle pattern
        System.out.println("Pattern 14: right-angled triangle pattern");
        for(int i = 1; i <= 6; i++)
        {
            for(int j = 1; j <= i; j++)
            {
                System.out.print("* ");
            }
            System.out.println();
        }

        // Pattern 15: Inverted right-angled triangle pattern
        System.out.println("Pattern 15: Inverted right-angled triangle pattern");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of rows: ");
        int rows = sc.nextInt();
        for(int i = rows; i >= 1; i--)
        {
            for(int j = 1; j <= i; j++)
            {
                System.out.print("* ");
            }
            System.out.println();
        }

        // Pattern 16: Right angle-Triangle pattern with even number
        System.out.println("Pattern 16: Right angle-Triangle pattern with even number");
        System.out.print("Enter the number of rows: ");
        int rows2 = sc.nextInt();
        int evenNum = 0;
        for(int i = 1; i <= rows2; i++)
        {
            for(int j = 1; j <= i; j++)
            {
                System.out.print(evenNum + " ");
                evenNum += 2;
            }
            System.out.println();
        }

        // Pattern 17: Triangle pattern with Alphabets
        System.out.println("Pattern 17: Triangle pattern with Alphabets");
        char alphabet = 'A';
        System.out.print("Enter the number of rows: ");
        int rows3 = sc.nextInt();
        for(int i = 1; i <= rows3; i++)
        {
            for(int j = 1; j <= i; j++)
            {
                System.out.print(alphabet + " ");
            }
            alphabet++;
            System.out.println();
        }

        // Pattern 18: Binary number pattern
        System.out.println("Pattern 18: Binary number pattern");
        System.out.print("Enter the number of rows: ");
        int rows4 = sc.nextInt();
        for(int i = 1; i <= rows4; i++)
        {
            for(int j = 1; j <= i; j++)
            {
                int binaryNum = (i + j) % 2;
                System.out.print(binaryNum + " ");
            }
            System.out.println();
        }
        
        // Pattern 19: Palindromic pattern
        System.out.println("Pattern 19: Palindromic pattern");
        System.out.print("Enter the number of rows: ");
        int rows5 = sc.nextInt();
        for(int i = 1; i <= rows5; i++)
        {
            // Print spaces
            for(int j = 1; j <= rows5 - i; j++)
            {
                System.out.print("  ");
            }
            // Print decreasing numbers
            for(int j = i; j >= 1; j--)
            {
                System.out.print(j + " ");
            }
            // Print increasing numbers
            for(int j = 2; j <= i; j++)
            {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        
        // Pattern 20: Full pyramid pattern with alphabets
        System.out.println("Pattern 20: Full pyramid pattern with alphabets");
        System.out.print("Enter the number of rows: ");
        int rows6 = sc.nextInt();
        for (int i = 1; i <= rows6; i++) {
            // spaces
            for (int j = 1; j <= rows - i; j++) {
                System.out.print("  ");
            }
            // ascending letters
            for (int j = 0; j < i; j++) {
                System.out.print((char)('A' + j) + " ");
            }
            // descending letters
            for (int j = i - 2; j >= 0; j--) {
                System.out.print((char)('A' + j) + " ");
            }
            System.out.println();
        }
        sc.close();
    }
}