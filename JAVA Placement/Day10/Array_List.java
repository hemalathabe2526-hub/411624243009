package Day10;

import java.util.ArrayList;

public class Array_List {
// public static void main(String[] args) {
    //     int[] arr1 = {1, 2, 3, 4, 4, 4, 5};
    //     int[] arr2 = {6, 7, 9, 10, 10, 11};
    //     ArrayList<Integer> union = new ArrayList<>();
    //     int i = 0;
    //     int j = 0;
    //     while (i < arr1.length && j < arr2.length) {
    //         if (arr1[i] <= arr2[j]) {
    //             if (union.size() == 0 || union.get(union.size() - 1) != arr1[i]) {
    //                 union.add(arr1[i]);
    //             }
    //             i++;
    //         } 
    //         else {
    //             if (union.size() == 0 || union.get(union.size() - 1) != arr2[j]) {
    //                 union.add(arr2[j]);
    //             }
    //             j++;
    //         }
    //     }
    //     while (i < arr1.length) {
    //         if (union.size() == 0 || union.get(union.size() - 1) != arr1[i]) {
    //             union.add(arr1[i]);
    //         }
    //         i++;
    //     }
    //     while (j < arr2.length) {
    //         if (union.size() == 0 || union.get(union.size() - 1) != arr2[j]) {
    //             union.add(arr2[j]);
    //         }
    //         j++;
    //     }
    //     System.out.println("Union of arrays: " + union);
    // }


    // // Program to find the intersection of two sorted arrays
    // public static void main(String[] args) {
    //     int[] arr1 = {1, 2, 3, 4, 5};
    //     int[] arr2 = {3, 4, 5, 6, 7};
    //     ArrayList<Integer> intersection = new ArrayList<>();
    //     int i = 0;
    //     int j = 0;
    //     while (i < arr1.length && j < arr2.length) {
    //         if (arr1[i] == arr2[j]) {
    //             intersection.add(arr1[i]);
    //             i++;
    //             j++;
    //         } 
    //         else if (arr1[i] < arr2[j]) {
    //             i++;
    //         } 
    //         else {
    //             j++;
    //         }
    //     }
    //     System.out.println("Intersection of arrays: " + intersection);
    // }


    // Program to find the Reverse Pairs in array
    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 3, 1};
        int n = arr.length;
        int reversePairCount = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > 2 * arr[j]) {
                    reversePairCount++;
                }
            }
        }
        System.out.println("Number of Reverse Pairs: " + reversePairCount);
    }
}