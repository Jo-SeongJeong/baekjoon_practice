package SWEA;

import java.util.Scanner;

public class SW2029 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        for(int i=1; i<=t; i++) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            int q = a / b;
            int r = a % b;
            System.out.println("#"+i+" "+q+" "+r);
        }
    }
}
