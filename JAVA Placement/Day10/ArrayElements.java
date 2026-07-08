package Day10;

import java.util.Map;
import java.util.HashMap;

// Java program to print the elements of an array
public class ArrayElements {
    // public static void main(String[] args) {
    //     int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    //     int N = arr.length;
    //     int temp = arr[0];
    //     for(int i = 0; i < N - 1; i++) {
    //         System.out.println(arr[i]);
    //         arr[i] = arr[i + 1];
    //     }
    //     arr[arr.length - 1] = temp;
    //     System.out.println(arr[N - 1]);
    // }


    // // Java program to rotate an array by k positions
    // public static void main(String[] args) {
    //     int[] arr = {1, 2, 3, 4, 5, 6, 7};
    //     int N = arr.length;
    //     int k = 3; // Number of positions to rotate
    //     k = k % N;
    //     for(int j = 0; j < k; j++) {
    //         int temp = arr[0];
    //         for(int i = 0; i < N - 1; i++) {
    //             arr[i] = arr[i + 1];
    //         }
    //         arr[N - 1] = temp;
    //     }
    //     for(int i : arr) {
    //         System.out.print(i + " ");
    //     }
    // }

    // // Java program to move all zeros to the end of an array
    // public static void main(String[] args) {
    //     int[] arr = new int[] {1, 2, 3, 0, 0, 4, 5, 0, 6, 7};
    //     for(int i : arr) {
    //         if(i != 0) {
    //             System.out.print(i + " ");
    //         }
    //     }
    //     for(int i : arr) {
    //         if(i == 0) {
    //             System.out.print(i + " ");
    //         }
    //     }
    // }

    // // Java program to print missing numbers in an array
    // public static void main(String[] args) {
    //     int[] arr = {2, 3, 5, 6, 7, 1};
    //     int sum = 0;
    //     for(int i : arr) {
    //         sum += i;
    //     }
    //     int n = arr.length + 1; 
    //     int totalSum = n * (n + 1) / 2; 
    //     int missingNumber = totalSum - sum; 
    //     System.out.println("The missing number is: " + missingNumber);
    // }


    // // Java program to find the maximum water content problem using array
    // public static void main(String[] args) {
    //     int[] height = {1, 3, 6, 2, 5, 4, 8, 9, 7}; 
    //     int maxWater = 0;
    //     for(int i = 0; i < height.length; i++) {
    //         for(int j = i + 1; j < height.length; j++) {
    //             int water = Math.min(height[i], height[j]) * (j - i);
    //             maxWater = Math.max(maxWater, water);
    //         }
    //     }
    //     System.out.println("Maximum water content: " + maxWater);
    // }

    
    // Java program to count the frequency of elements in an array
    public static void main(String[] args) {
        String[] str = new String[] {"Hemant", "Rohit", "Hemant", "Rohit", "Hemant", "Rohit",
         "Hemant", "Rohit", "Sheela", "Rohit", "Sujith", "Monish"};
        Map<String, Integer> map = new HashMap<>();
        for(String s : str) {
            if(map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }
        System.out.println(map);
    }
}