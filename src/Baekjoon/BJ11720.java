package Baekjoon;

import java.util.Scanner;

public class BJ11720 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        String num = scan.next();
        int sum = 0;

        for(int i=0; i<N; i++) {
            sum += num.charAt(i) - '0';
        }

        System.out.println(sum);
    }
}
