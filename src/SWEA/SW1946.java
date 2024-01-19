package SWEA;

import java.util.Scanner;

public class SW1946 {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        int testCase = scan.nextInt();

        for (int t = 1; t <= testCase; t++) {
            int n = scan.nextInt();
            char[] ch = new char[n];
            int[] num = new int[n];
            System.out.println("#" + t);

            for (int i = 0; i < n; i++) {
                ch[i] = scan.next().charAt(0);
                num[i] = scan.nextInt();
            }

            int count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < num[i]; j++) {
                    System.out.print(ch[i]);
                    count++;
                    if (count % 10 == 0) {
                        System.out.println();
                    }
                }
            }
            System.out.println();
        }
    }
}