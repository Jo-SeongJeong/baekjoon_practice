package Baekjoon;

import java.util.Scanner;

public class BJ27866 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String S = scan.next();
        int i = scan.nextInt();

        System.out.println(S.charAt(i-1));
    }
}
