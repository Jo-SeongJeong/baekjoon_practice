package SWEA;

import java.util.Scanner;

public class SW1966 {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        int testCase = scan.nextInt();

        for(int t = 1; t <= testCase; t++) {
            int n = scan.nextInt();
            int[] array = new int[n];

            for(int i = 0; i < array.length; i++) {
                array[i] = scan.nextInt();
            }

            for(int i = 0; i < array.length-1; i++) {
                int temp = 0;
                for(int j = i + 1; j < array.length; j++) {
                    if(array[i] >= array[j]) {
                        temp = array[i];
                        array[i] = array[j];
                        array[j] = temp;
                    }
                }
            }

            System.out.print("#"+t+ " ");

            for(int i = 0; i < array.length; i++) {
                System.out.print(array[i] +" ");
            }
            System.out.println();
        }
    }
}