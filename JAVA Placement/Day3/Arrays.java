import java.util.Scanner;

public class Arrays {
    public static void main(String[] args) {    
        int[] array = new int[10]; 
        Scanner sc = new Scanner(System.in); 
        for (int i = 0; i < array.length; i++) {  
            array[i] = i + 1;  
        }
        System.out.println("Array elements:");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("\nEnter an index to access the array element:");
        int index = sc.nextInt();  
        if (index >= 0 && index < array.length) {
            System.out.println("Element at index " + index + ": " + array[index]);
        } else {
            System.out.println("Index out of bounds. Please enter a valid index between 0 and " + (array.length - 1));
        }
        sc.close();
    }
}