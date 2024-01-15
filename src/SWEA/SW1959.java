package SWEA;

import java.util.Scanner;

public class SW1959 {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        int testCase = scan.nextInt();

        for(int t = 1; t <= testCase; t++) {
            int n = scan.nextInt();
            int m = scan.nextInt();
            int[] a = new int[n];
            int[] b = new int[m];

            for(int i = 0; i < n; i++) {
                a[i] = scan.nextInt();
            }
            for(int i = 0; i < m; i++) {
                b[i] = scan.nextInt();
            }
            int max = 0;
            if(n < m) {
                for(int i = 0; i < m-n+1; i++) {
                    int sum = 0;
                    for (int j = 0; j < n; j++) {
                        sum += a[j] * b[j+i];
                    }
                    if(sum >= max) {
                        max = sum;
                    }
                }
            }

            else {
                for(int i = 0; i < n-m+1; i++) {
                    int sum = 0;
                    for (int j = 0; j < m; j++) {
                        sum += a[j+i] * b[j];
                    }
                    if(sum >= max) {
                        max = sum;
                    }
                }
            }
            System.out.println("#" + t + " " + max);
        }
    }
}