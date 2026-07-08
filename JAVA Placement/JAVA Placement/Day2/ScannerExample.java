package Day2;

import java.util.Scanner;

public class ScannerExample {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter your name :");
            String name = sc.nextLine();
            System.out.println("Welcome " + name);

            System.out.println("Enter your age :");
            int age = sc.nextInt();
            System.out.println("Your age is : " + age);

            System.out.println("Enter your CGPA :");
            float cgpa = sc.nextFloat();
            System.out.println("Your CGPA is : " + cgpa);

            System.out.println("Enter your gender :");
            char gender = sc.next().charAt(0);
            System.out.println("Your gender is : " + gender);

            System.out.println("Enter your Phone Number:");
            long phoneNumber = sc.nextLong();
            System.out.println("Your Phone Number is : " + phoneNumber);

            System.out.println("Enter your Hall Ticket Number:");
            double hallTicketNumber = sc.nextDouble();
            System.out.println("Your Hall Ticket Number is : " + hallTicketNumber);
        }
    }
}
