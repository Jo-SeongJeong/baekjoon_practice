package Baekjoon;

import java.util.Scanner;

public class BJ2563 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int [][]array = new int[100][100];
        int count = 0;
        int n = scan.nextInt();

        for(int i=0; i<n; i++) {
            int x = scan.nextInt();
            int y = scan.nextInt();

            for(int w=x; w<x+10; w++) {
                for(int h=y; h<y+10; h++) {
                    if(array[w][h] != 1) {
                        array[w][h] = 1;
                    }
                }
            }
        }

        for(int i=0; i<100; i++) {
            for(int j=0; j<100; j++) {
                if(array[i][j] == 1) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}
