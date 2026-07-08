public class SumAllElements {
    // public static void main(String[] args) {
    //     int[] array = {1, 2, 3, 4, 5};
    //     int sum = 0;
    //     for (int element : array) {
    //         sum += element;
    //     }
    //     System.out.println("Sum of all elements in the array: " + sum);
    // }
    
    //Dynamic user input version
    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int size = sc.nextInt();
        int[] array = new int[size];
        System.out.println("Enter " + size + " integers:");
        for (int i = 0; i < size; i++) {
            array[i] = sc.nextInt();
        }
        int sum = 0;
        for (int element : array) {
            sum += element;
        }
        System.out.println("Sum of all elements in the array: " + sum);
        sc.close();
    }
}
