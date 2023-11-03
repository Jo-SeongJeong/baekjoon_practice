package Baekjoon;

import java.util.Scanner;

public class BJ11653 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        if(n != 1) {
            for(int i=2; i<=n; i++) {
                while(n % i == 0) {
                    System.out.println(i);
                    n = n/i;
                }
            }
        }
    }
}
