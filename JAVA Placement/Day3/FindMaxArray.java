import java.util.Scanner;

public class FindMaxArray {
    // public static void main(String[] args) {
    //     java.util.Scanner sc = new java.util.Scanner(System.in);
    //     System.out.print("Enter the size of the array: ");
    //     int size = sc.nextInt();
    //     int[] array = new int[size];
    //     System.out.println("Enter " + size + " integers:");
    //     for (int i = 0; i < size; i++) {
    //         array[i] = sc.nextInt();
    //     }
    //     int max = array[0];
    //     for (int element : array) {
    //         if (element > max) {
    //             max = element;
    //         }
    //     }
    //     int min = array[0];
    //     for (int element : array) {
    //         if (element < min) {
    //             min = element;
    //         }
    //     }
    //     System.out.println("Minimum element in the array: " + min);
    //     System.out.println("Maximum element in the array: " + max);
    //     sc.close();
    // }

    // Finds Second Maximum number from the array
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of array: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter array elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (arr[i] > max) {
                secondMax = max;
                max = arr[i];
            } else if (arr[i] > secondMax && arr[i] != max) {
                secondMax = arr[i];
            }
        }
        if (secondMax == Integer.MIN_VALUE) {
            System.out.println("Second maximum does not exist.");
        } else {
            System.out.println("Second maximum is: " + secondMax);
        }
        sc.close();
    }
}
