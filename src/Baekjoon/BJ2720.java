package Baekjoon;

import java.util.Scanner;

public class BJ2720 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for(int i=0; i<n; i++) {
            int a = scan.nextInt();
            int quarter = a / 25;
            int b = a % 25;

            int dime = b / 10;
            int c = b % 10;

            int nickel = c / 5;
            int d = c % 5;

            int penny = d / 1;

            System.out.print(quarter+" "+dime+" "+nickel+" "+penny);
            System.out.println();
        }
    }
}
