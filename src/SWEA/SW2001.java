package SWEA;

import java.util.Scanner;

public class SW2001 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        for(int i=1; i<=t; i++) {
            int n = scan.nextInt();
            int m = scan.nextInt();
            int[][] array = new int[n][n];

            for(int j=0; j<n; j++) {
                for(int k=0; k<n; k++) {
                    array[j][k] = scan.nextInt();
                }
            }

            int max = 0;

            for(int j=0; j<=n-m; j++) {
                for(int k=0; k<=n-m; k++) {
                    int sum = 0;
                    for(int x=0; x<m; x++) {
                        for(int y=0; y<m; y++) {
                            sum += array[j+x][k+y];
                        }
                    }
                    if(sum>max) {
                        max = sum;
                    }
                }
            }

            System.out.println("#"+i+" "+max);
        }
    }
}