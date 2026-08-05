package Day10;

import java.util.*;

public class AggressiveCows {

    // // Aggressive Cows Problem
    // static boolean isPossible(int[] stalls, int k, int minDist) {
    //     int cowsPlaced = 1;
    //     int lastPos = stalls[0];
    //     for (int i = 1; i < stalls.length; i++) {
    //         if (stalls[i] - lastPos >= minDist) {
    //             cowsPlaced++;
    //             lastPos = stalls[i];
    //             if (cowsPlaced == k)
    //                 return true;
    //         }
    //     }
    //     return false;
    // }
    // static int aggressiveCows(int[] stalls, int k) {
    //     Arrays.sort(stalls);
    //     int low = 1;
    //     int high = stalls[stalls.length - 1] - stalls[0];
    //     int ans = 0;
    //     while (low <= high) {
    //         int mid = low + (high - low) / 2;
    //         if (isPossible(stalls, k, mid)) {
    //             ans = mid;
    //             low = mid + 1;
    //         } else {
    //             high = mid - 1;
    //         }
    //     }
    //     return ans;
    // }
    // static void printArrangement(int[] stalls, int k, int minDist) {
    //     int lastPos = stalls[0];
    //     int cowsPlaced = 1;
    //     System.out.print("Cow positions: " + lastPos);
    //     for (int i = 1; i < stalls.length && cowsPlaced < k; i++) {
    //         if (stalls[i] - lastPos >= minDist) {
    //             System.out.print(" " + stalls[i]);
    //             lastPos = stalls[i];
    //             cowsPlaced++;
    //         }
    //     }
    //     System.out.println();
    // }
    // public static void main(String[] args) {
    //     int[] stalls = {1, 2, 4, 8, 9};
    //     int k = 3;
    //     int maxDistance = aggressiveCows(stalls, k);
    //     System.out.println("Maximum Possible Minimum Distance = " + maxDistance);
    //     printArrangement(stalls, k, maxDistance);
    // }


    // KoKo Eating Bananas Problem
    static int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = Arrays.stream(piles).max().getAsInt();
        int ans = high;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canEatAll(piles, h, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
    static boolean canEatAll(int[] piles, int h, int speed) {
        int hoursSpent = 0;
        for (int pile : piles) {
            hoursSpent += Math.ceil((double) pile / speed);
            if (hoursSpent > h) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        int[] piles = {3, 6, 7, 11};
        int h = 8;
        int minSpeed = minEatingSpeed(piles, h);
        System.out.println("Minimum Eating Speed = " + minSpeed);
    }
}