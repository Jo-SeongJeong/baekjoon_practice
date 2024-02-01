package Baekjoon;

import java.util.Scanner;

public class BJ2750 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int []array = new int[n];

        for(int i=0; i<n; i++) {
            array[i] = scan.nextInt();
        }

        for(int i=0; i<n; i++) {
            for(int j=i+1; j<n; j++) {
                int temp;
                if(array[i]>array[j]) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }

        for(int i=0; i<n; i++) {
            System.out.println(array[i]);
        }
    }
}