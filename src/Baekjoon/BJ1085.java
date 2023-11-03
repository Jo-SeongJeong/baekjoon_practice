package Baekjoon;

import java.util.Scanner;

public class BJ1085 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();
        int y = scan.nextInt();
        int w = scan.nextInt();
        int h = scan.nextInt();
        int x_count = 0;
        int y_count = 0;

        if(w-x < x) {
            x_count = w-x;
        }
        else {
            x_count = x;
        }

        if(h-y < y) {
            y_count = h-y;
        }
        else {
            y_count = y;
        }

        if(x_count < y_count) {
            System.out.println(x_count);
        }
        else {
            System.out.println(y_count);
        }
    }
}
