package Baekjoon;

import java.util.Scanner;

public class BJ24313 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        int n = scan.nextInt();

        int result = 0;

        for(int i=n; i<=100; i++) {
            if (a * i + b > c * i) {
                result = 0;
                break;
            }
            else {
                result = 1;
            }
        }

        System.out.println(result);
    }
}
