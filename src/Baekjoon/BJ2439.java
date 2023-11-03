package Baekjoon;

import java.util.Scanner;

public class BJ2439 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();

        for(int i=1; i<=N; i++) {
            for(int j=N; j>i; j--) {
                System.out.print(" ");
            }
            for(int k=0; k<i; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
