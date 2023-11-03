package Baekjoon;

import java.util.Scanner;

public class BJ11021 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();

        for(int i=1; i<=T; i++) {
            int sum = 0;
            int A = scan.nextInt();
            int B = scan.nextInt();
            sum = A + B;
            System.out.println("Case #"+i+": "+sum);
        }
    }
}
