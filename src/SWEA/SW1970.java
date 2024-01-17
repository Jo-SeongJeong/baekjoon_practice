package SWEA;

import java.util.Scanner;

public class SW1970 {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        int testCase = scan.nextInt();

        for(int t = 1; t <= testCase; t++) {
            int n = scan.nextInt();
            int[] array = new int[8];
            int[] money = new int[]{50000, 10000, 5000, 1000, 500, 100, 50, 10};

            for(int i = 0; i < 8; i++) {
                int temp = n / money[i];
                array[i] = temp;
                n %= money[i];
            }

            System.out.println("#" + t);
            for(int i = 0; i < array.length; i++) {
                System.out.print(array[i]+ " ");
            }
            System.out.println();
        }
    }
}