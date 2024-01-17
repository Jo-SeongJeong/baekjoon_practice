package SWEA;

import java.util.Scanner;

public class SW1976 {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        int testCase = scan.nextInt();

        for(int t = 1; t <= testCase; t++) {
            int h1 = scan.nextInt();
            int m1 = scan.nextInt();
            int h2 = scan.nextInt();
            int m2 = scan.nextInt();

            int temp = 0;

            m2 += m1;

            if(m2 > 60) {
                temp = m2 / 60;
                m2 %= 60;
            }

            h2 += h1 + temp;

            if(h2 > 12) {
                h2 -= 12;
            }

            System.out.println("#" + t + " " + h2 + " " + m2);
        }
    }
}