package Baekjoon;

import java.util.Scanner;

public class BJ1316 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int count = 0;
        int n = scan.nextInt();

        for(int i=0; i<n; i++) {
            String str = scan.next();
            boolean check[] = new boolean[26];
            boolean seq = true;

            for(int j=0; j<str.length(); j++) {
                int num = str.charAt(j) - 'a';

                if(check[num]) {
                    if (str.charAt(j) != str.charAt(j-1)) {
                        seq = false;
                        break;

                    }
                }
                else {
                    check[num] = true;
                }
            }
            if(seq)
                count++;
        }
        System.out.println(count);
    }
}
