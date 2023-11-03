package Baekjoon;

import java.util.Scanner;

public class BJ2869 {
    public static void main(String []args) {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();
        int v = scan.nextInt();
        int day = 0;

        if((v-b)%(a-b) == 0) {
            day = (v-b)/(a-b);
        }
        else {
            day = (v-b)/(a-b)+1;
        }

        System.out.println(day);
    }
}
