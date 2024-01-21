package SWEA;

import java.util.Scanner;

public class SW1945 {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        int testCase = scan.nextInt();
        int[] num = new int[] {2, 3, 5, 7, 11};

        for(int t = 1; t <= testCase; t++) {
            int n = scan.nextInt();
            int[] array= new int[5];

            for(int i = 0; i < num.length; i++) {
                while(n % num[i] == 0) {
                    n /= num[i];
                    array[i]++;
                }
            }

            System.out.print("#" + t);

            for(int i = 0; i < array.length; i++) {
                System.out.print(" " + array[i]);
            }
            System.out.println();
        }
    }
}