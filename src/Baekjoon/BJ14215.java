package Baekjoon;

import java.util.Scanner;

public class BJ14215 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();

        int max = Math.max(Math.max(a,b),c);

        if(max==a) {
            if(max>=b+c) {
                max = b+c-1;
                System.out.println(max+b+c);
            }
            else {
                System.out.println(max+b+c);
            }
        }
        else if(max==b) {
            if(max>=a+c) {
                max = a+c-1;
                System.out.println(max+a+c);
            }
            else {
                System.out.println(max+a+c);
            }
        }
        else {
            if(max>=a+b) {
                max = a+b-1;
                System.out.println(max+a+b);
            }
            else {
                System.out.println(max+a+b);
            }
        }
    }
}
