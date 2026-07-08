package Day10;

import java.util.ArrayList;

public class Algorithm {

    // // Boyes Moore's Voting Algorithm implementation in Java
    // public static int majorityElement(int[] nums) {
    //     int count = 0;
    //     Integer candidate = null;
    //     for (int num : nums) {
    //         if (count == 0) {
    //             candidate = num;
    //         }
    //         count += (num == candidate) ? 1 : -1;
    //     }
    //     return candidate;
    // }
    // public static void main(String[] args) {
    //     int[] nums = {3, 2, 3, 4, 5, 5, 5};
    //     System.out.println("Majority Element: " + majorityElement(nums)); 
    //     int[] nums2 = {2, 2, 1, 1, 1, 2, 2};
    //     System.out.println("Majority Element: " + majorityElement(nums2));
    // }


    // // Program to find the combination of positive and negative numbers in an array
    // public static void main(String[] args) {
    //     int[] arr = {1, -2, 3, -4, 5, 8, -9, 2, -1, 0};
    //     int[] positiveArr = new int[arr.length];
    //     int[] negativeArr = new int[arr.length];
    //     int positiveCount = 0;
    //     int negativeCount = 0;
    //     for (int i = 0; i < arr.length; i++) {
    //         if (arr[i] >= 0) {
    //             positiveArr[positiveCount++] = arr[i];
    //         } else {
    //             negativeArr[negativeCount++] = arr[i];
    //         }
    //     }
    //     System.out.println("Number of positive numbers: " + positiveCount);
    //     System.out.println("Number of negative numbers: " + negativeCount);
    // }


    // //Program to find the Leaders in an array
    // public static void main(String[] args) {
    //     int[] arr = {16, 17, 4, 3, 5, 2};
    //     int maxFromRight = arr[arr.length - 1];
    //     System.out.print("Leaders in the array: " + maxFromRight + " ");
    //     for (int i = arr.length - 2; i >= 0; i--) {
    //         if (arr[i] > maxFromRight) {
    //             maxFromRight = arr[i];
    //             System.out.print(maxFromRight + " ");
    //         }
    //     }
    // }


    // // Program to re-arrange the numbers based on signs
    // public static void main(String[] args) {
    //     int[] arr = {1, -2, 3, -4, -1, 4, 5, 7, 0, -9};
    //     int[] neg = new int[arr.length];
    //     int[] pos = new int[arr.length];
    //     int negCount = 0, posCount = 0;
    //     // Separate negative and positive numbers
    //     for (int num : arr) {
    //         if (num < 0) {
    //             neg[negCount++] = num;
    //         } else {
    //             pos[posCount++] = num;
    //         }
    //     }
    //     for (int i = 0; i < negCount - 1; i++) {
    //         for (int j = i + 1; j < negCount; j++) {
    //             if (neg[i] > neg[j]) {
    //                 int temp = neg[i];
    //                 neg[i] = neg[j];
    //                 neg[j] = temp;
    //             }
    //         }
    //     }
    //     for (int i = 0; i < posCount - 1; i++) {
    //         for (int j = i + 1; j < posCount; j++) {
    //             if (pos[i] > pos[j]) {
    //                 int temp = pos[i];
    //                 pos[i] = pos[j];
    //                 pos[j] = temp;
    //             }
    //         }
    //     }
    //     // Merge back into original 
    //     int k = 0;
    //     for (int i = 0; i < negCount; i++) {
    //         arr[k++] = neg[i];
    //     }
    //     for (int i = 0; i < posCount; i++) {
    //         arr[k++] = pos[i];
    //     }
    //     System.out.print("Output: ");
    //     for (int num : arr) {
    //         System.out.print(num + " ");
    //     }
    // }


    // // Rearrange positive and negative numbers in positive and negative order program
    // public static void main(String[] args) {
    //     int[] arr = {3, 1, -6, -7, -8, -2, 0, 9};
    //     int[] ans = new int[arr.length * 2];
    //     int even = 0;
    //     int odd = 1;
    //     for(int i = 0; i < arr.length; i++){
    //         if(arr[i] > 0){
    //             ans[even] = arr[i];
    //             even += 2;
    //         }
    //         else{
    //             ans[odd] = arr[i];
    //             odd += 2;
    //         }
    //     }
    //     for(int num : ans){
    //         System.out.print(num+" ");
    //     }
    // }


    
    // // rearrange in another method
    // public static void main(String[] args) {
    //     int[] arr = {3, 1, 2, 4, -3, 5, -6, 9, 7};
    //     ArrayList<Integer> pos = new ArrayList<>();
    //     ArrayList<Integer> neg = new ArrayList<>();
    //      for(int num : arr){
    //         if(num >= 0)
    //             pos.add(num);
    //         else
    //             neg.add(num);
    //     }
    //     ArrayList<Integer> ans = new ArrayList<>();
    //     int p = 0;
    //     int n = 0;
    //     while(p < pos.size() && n < neg.size()){
    //         ans.add(pos.get(p++));
    //         ans.add(neg.get(n++));
    //     }
    //     // The below Two while loop is to print also the remaining array
    //     while(p < pos.size()){
    //         ans.add(pos.get(p++));
    //     }
    //     while(n < neg.size()){
    //         ans.add(neg.get(n++));
    //     }
    //     System.out.println(ans);
    // }

    // // Program to fins the majority element two 
    // public static void main(String[] args) {
    //     int[] arr = {3, 2, 3, 4, 5, 5, 5};
    //     int n = arr.length;
    //     int majorityCount = n / 2;
    //     int count = 0;
    //     int candidate = -1;
    //     for (int i = 0; i < n; i++) {
    //         count = 0;
    //         for (int j = 0; j < n; j++) {
    //             if (arr[i] == arr[j]) {
    //                 count++;
    //             }
    //         }
    //         if (count > majorityCount) {
    //             candidate = arr[i];
    //             break;
    //         }
    //     }
    //     if (candidate != -1) {
    //         System.out.println("Majority Element: " + candidate);
    //     } else {
    //         System.out.println("No Majority Element");
    //     }
    // }

    // Program to find the inversion count in an array
    public static void main(String[] args) {
        int[] arr = {1, 20, 6, 4, 5};
        int n = arr.length;
        int inversionCount = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    inversionCount++;
                }
            }
        }
        System.out.println("Inversion Count: " + inversionCount);
    }
}