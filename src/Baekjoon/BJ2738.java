package Baekjoon;

import java.util.Scanner;

public class BJ2738 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int [][]A = new int[n][m];
        int [][]B = new int [n][m];

        for(int i=0; i<n; i++) {
            for (int j = 0; j < m; j++) {
                A[i][j] = scan.nextInt();
            }
        }
        for(int i=0; i<n; i++) {
            for (int j = 0; j < m; j++) {
                B[i][j] = scan.nextInt();
            }
        }
        for(int i=0; i<n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(A[i][j]+B[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
    }
}
