package Baekjoon;

import java.util.Scanner;

public class BJ2839 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int count = 0;

        while(true) {
            if(n%5 == 0) {
                count += n/5;
                System.out.println(count);
                break;
            }
            else if(n<0){
                System.out.println(-1);
                break;
            }
            n = n-3;
            count++;
        }
    }
}
