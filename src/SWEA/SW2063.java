package SWEA;

import java.util.Scanner;

public class SW2063 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] array = new int[n];

        for(int i=0; i<array.length; i++) {
            array[i] = scan.nextInt();
        }

        for(int i=0; i<array.length; i++) {
            for(int j=i+1; j<array.length; j++) {
                int temp;

                if(array[i]>array[j]) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        System.out.println(array[(n-1)/2]);
        for(int i=0; i<array.length; i++) {
            System.out.println(array[i]);
        }
    }
}