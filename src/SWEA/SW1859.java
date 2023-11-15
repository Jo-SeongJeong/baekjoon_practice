package SWEA;

import java.util.Scanner;

public class SW1859 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        for(int i=1; i<=t; i++) {
            long result = 0;
            int n = scan.nextInt();
            int []array = new int[n];
            for(int j=0; j<n; j++) {
                array[j] = scan.nextInt();
            }
            int max = array[n-1];
            for(int k=n-2; k>=0; k--) {
                if(max>array[k]) {
                    result += max-array[k];
                }
                else {
                    max = array[k];
                }
            }
            System.out.println("#"+i+" "+result);
        }
    }
}