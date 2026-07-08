public class ForEachLoop {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        System.out.println("Array elements using for-each loop:");
        for (int element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
        java.util.Scanner sc = new java.util.Scanner(System.in);
        int[] marks = new int[5];
        System.out.println("Enter 5 marks:");
        for (int i = 0; i < 5; i++) {
            marks[i] = sc.nextInt();
        }
        System.out.println("Marks entered:");
        for (int mark : marks) {
            System.out.print(mark + " ");
        }
        System.out.println();
        sc.close();
    }
}
