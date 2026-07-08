package Day6;

public class Factorization {
    // public static void main(String[] args) {
    //     int num = 60;
    //     System.out.println("Factors of " + num + ":");
    //     for (int i = 1; i <= num; i++) {
    //         if (num % i == 0) {
    //             System.out.print(i + " ");
    //         }
    //     }
    // }


    // Using prime factorization
    public static void main(String[] args) {
        int num = 60;
        System.out.println("Prime factors of " + num + ":");
        for (int i = 2; i <= num; i++) {
            while (num % i == 0) {
                System.out.print(i + " ");
                num /= i;
            }
        }
    }
}