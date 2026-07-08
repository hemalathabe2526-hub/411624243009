package Day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class FloydsTriangle {
    // // Floyd Triangles
    // static void printFloydTriangle(int n) {
    //     int num = 1;
    //     for (int i = 1; i <= n; i++) {
    //         for (int j = 1; j <= i; j++) {
    //             System.out.print(num);
    //             if (j < i) {
    //                 System.out.print(" ");
    //             }
    //             num++;
    //         }
    //         System.out.println();
    //     }
    // }
    // public static void main(String[] args) {
    //     Scanner sc = new Scanner(System.in);
    //     int n = sc.nextInt();
    //     printFloydTriangle(n);
    //     sc.close();
    // }


    // //  program to implement Spiral Matrix
    // static void SpiralMatrix(int n) {
    //     int[][] matrix = new int[n][n];
    //     int top = 0, bottom = n - 1;
    //     int left = 0, right = n - 1;
    //     int num = 1;
    //     while (top <= bottom && left <= right) {
    //         // Left -> Right
    //         for (int i = left; i <= right; i++) {
    //             matrix[top][i] = num++;
    //         }
    //         top++;
    //         // Top -> Bottom
    //         for (int i = top; i <= bottom; i++) {
    //             matrix[i][right] = num++;
    //         }
    //         right--;
    //         // Right -> Left
    //         for (int i = right; i >= left && top <= bottom; i--) {
    //             matrix[bottom][i] = num++;
    //         }
    //         bottom--;
    //         // Bottom -> Top
    //         for (int i = bottom; i >= top && left <= right; i--) {
    //             matrix[i][left] = num++;
    //         }
    //         left++;
    //     }
    //     for (int i = 0; i < n; i++) {
    //         for (int j = 0; j < n; j++) {
    //             System.out.printf("%4d", matrix[i][j]);
    //         }
    //         System.out.println();
    //     }
    // }
    // public static void main(String[] args) {
    //     SpiralMatrix(4);
    // }


    public static List<Integer> sprialOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int t = 0;
        int b = matrix.length-1;
        int l = 0;
        int r = matrix[0].length-1;
        while(l <= r && t <= b){
            for(int i = l; i <= r; i++){
                ans.add(matrix[t][i]);
            }
            t++;
            for(int i = t; i <= b; i++){
                ans.add(matrix[i][r]);
            }
            r--;
            for(int i = r; i >= l; i--){
                ans.add(matrix[b][i]);
            }
            b--;
            for(int i = b ; i >= t; i--){
                ans.add(matrix[i][l]);
            }
            l++;
        }
        return ans;
    }
    public static void main(String[] args){
        int[][] a = {
            {1, 2, 3, 4, 5, 6},
            {7, 8, 9, 0, 1, 2},
            {3, 4, 5, 6, 7, 8}
            
        };
        System.out.print(sprialOrder(a));
    }
}