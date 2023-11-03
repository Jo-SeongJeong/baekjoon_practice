package Baekjoon;

import java.util.Scanner;

public class BJ2501 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        int a = 0;
        int count = 0;
        for(int i=1; i<=n; i++) {
            if(n%i == 0) {
                count++;
                if (count == k)
                    a = i;
            }
        }

        if(count >= k)
            System.out.println(a);
        else
            System.out.println(0);
    }
}
