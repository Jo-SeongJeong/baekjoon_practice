package SWEA;

import java.util.Scanner;

public class SW2072 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        for(int i = 1; i <= t; i++) {
            int sum = 0;
            for(int j = 0; j < 10; j++) {
                int n = scan.nextInt();

                if(n % 2 != 0) {
                    sum += n;
                }
            }
            System.out.println("#"+i+" "+sum);
        }
    }
}
