package SWEA;

import java.util.Scanner;

public class SW2005 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        for(int i=1; i<=t; i++) {
            int n = scan.nextInt();
            int [][] array = new int[n][n];
            array[0][0] = 1;
            for(int j=1; j<n; j++) {
                for(int k=0; k<=j; k++) {
                    if (k == 0) {
                        array[j][k] = 1;
                    }
                    else {
                        array[j][k] = array[j - 1][k - 1] + array[j - 1][k];
                    }
                }
            }

            System.out.println("#"+i);

            for(int j=0; j<n; j++) {
                for(int k=0; k<=j; k++) {
                    System.out.print(array[j][k]);
                    if(k<j) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
    }
}