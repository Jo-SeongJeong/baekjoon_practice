package SWEA;

import java.util.Scanner;

public class SW1948 {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        int testCase = scan.nextInt();
        int [] day = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        for(int t = 1; t <= testCase; t++) {
            int m1 = scan.nextInt();
            int d1 = scan.nextInt();
            int m2 = scan.nextInt();
            int d2 = scan.nextInt();

            int result = 1;
            result += day[m1-1] -d1;
            if(m2 - m1 > 0) {
                for (int i = m1; i < m2-1; i++) {
                    result += day[i];
                }
                result += d2;
            }
            System.out.println("#" + t + " " + result);
        }
    }
}
