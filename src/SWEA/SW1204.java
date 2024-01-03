package SWEA;

import java.util.Scanner;

public class SW1204 {
    public static void main(String args[]) throws Exception {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        for (int i = 1; i <= t; i++) {
            int n = scan.nextInt();
            int[] array = new int[101];
            int max = 0;
            int result = 0;

            for (int j = 0; j < 1000; j++) {
                int score = scan.nextInt();
                array[score]++;
            }

            for (int j = 0; j < array.length; j++) {
                if (array[j] >= max) {
                    max = array[j];
                    result = j;
                }
            }
            System.out.println("#" + n + " " + result);
        }
    }
}
