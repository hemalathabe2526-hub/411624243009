package Day9;

import java.util.HashMap;

public class Hashing {
    public static void main(String[] args) {
        int[] arr = {5, 7, 5, 10, 7, 5, 10, 15};
        int[] freq = new int[16]; // Assuming the numbers are in the range 0-15
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }
        for(int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for(int num : map.keySet()) {
            System.out.println(num + " occurs " + map.get(num) + " times");
        }

        // Get the value of a specific key
        System.out.println("\nUsing get() method:");
        System.out.println("Value for key 5: " + map.get(5));
        System.out.println("Value for key 10: " + map.get(10));

        // Contains key check
        System.out.println("\nUsing containsKey() method:");  
        System.out.println("Does key 7 exist? " + map.containsKey(7));
        System.out.println("Does key 17 exist? " + map.containsKey(17));

        // getOrDefault() method
        System.out.println("\nUsing getOrDefault() method:");
        System.out.println("Value for key 5: " + map.getOrDefault(5, 0));
        System.out.println("Value for key 17: " + map.getOrDefault(17, 3));

        // Remove a key-value pair
        System.out.println("\nUsing remove() method:");
        map.remove(10);
        map.remove(15);
        System.out.println("Does key 10 exist after removal? " + map.containsKey(10));
        System.out.println("Value for key 10 after removal: " + map.get(10));

        // Put a new key-value pair
        System.out.println("\nUsing put() method:");
        map.put(20, 1);
        map.put(25, 2);
        System.out.println("Does key 20 exist after adding? " + map.containsKey(20));

        // Highest frequency element
        int maxFreq = 0;
        int maxFreqElement = -1;
        for (int num : map.keySet()) {
            if (map.get(num) > maxFreq) {
                maxFreq = map.get(num);
                maxFreqElement = num;
            }
        }
        System.out.println("\nHighest frequency element: " + maxFreqElement + " with frequency: " + maxFreq);
    }
}