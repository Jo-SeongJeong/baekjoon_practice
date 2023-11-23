package SWEA;

import java.util.Scanner;

public class SW1284 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        for(int i=1; i<=t; i++) {
            int p = scan.nextInt();
            int q = scan.nextInt();
            int r = scan.nextInt();
            int s = scan.nextInt();
            int w = scan.nextInt();

            int bill1 = 0;
            int bill2 = 0;
            bill1 = w * p;

            if(w<=r) {
                bill2 = q;
            }
            else {
                bill2 = q + (w - r) * s;
            }

            if(bill1 > bill2) {
                System.out.println("#"+i+" "+bill2);
            }
            else {
                System.out.println("#"+i+" "+bill1);
            }
        }
    }
}