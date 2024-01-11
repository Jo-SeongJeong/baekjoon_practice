package SWEA;

import java.util.Scanner;

public class SW1986 {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        for(int i = 1; i <= t; i++) {
            int n = scan.nextInt();

            int result = 0;
            for(int j = 1; j <= n; j++) {
                result += j % 2 == 1 ? j : -j;
            }
            System.out.println("#" + i + " " + result);
        }
    }
}