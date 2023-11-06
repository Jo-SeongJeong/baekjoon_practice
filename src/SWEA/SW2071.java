package SWEA;

import java.util.Scanner;

public class SW2071 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        for(int i=1; i<=t; i++) {
            int sum = 0;
            int avg = 0;
            double temp = 0;

            for(int j=0; j<10; j++) {
                int n = scan.nextInt();
                sum += n;
            }

            temp = sum/10.0;

            if(temp - (sum/10) < 0.5) {
                avg = sum/10;
            }
            else {
                avg = sum/10+1;
            }
            System.out.println("#"+i+" "+avg);
        }
    }
}
