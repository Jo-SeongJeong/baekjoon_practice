package SWEA;

import java.util.Scanner;

public class SW1961 {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        for(int i = 1; i <= t; i++) {
            int n = scan.nextInt();
            int[][] array = new int[n][n];

            for(int j = 0; j < n; j++) {
                for(int k = 0; k < n; k++) {
                    array[j][k] = scan.nextInt();
                }
            }
            System.out.println("#" + i);
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < n; k++) {
                    System.out.print(array[n-k-1][j]);
                }
                System.out.print(" ");
                for(int k = 0; k < n; k++) {
                    System.out.print(array[n-j-1][n-k-1]);
                }
                System.out.print(" ");
                for(int k = 0; k < n; k++) {
                    System.out.print(array[k][n-j-1]);
                }
                System.out.println();
            }
        }
    }
}