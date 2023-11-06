package SWEA;

import java.util.Scanner;

public class SW2068 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int []array = new int[10];
        int t = scan.nextInt();

        for(int i=1; i<=t; i++) {
            for(int j=0; j< array.length; j++) {
                array[j] = scan.nextInt();
            }

            int max = 0;
            for(int j=0; j<array.length; j++) {
                if(array[j]>max) {
                    max = array[j];
                }
            }
            System.out.println("#"+i+" "+max);
        }
    }
}
