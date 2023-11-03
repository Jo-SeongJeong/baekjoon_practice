package Baekjoon;

import java.util.Scanner;

public class BJ10807 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int []array = new int[N];
        int count = 0;

        for(int i=0; i<N; i++)
            array[i] = scan.nextInt();

        int v = scan.nextInt();

        for(int i=0; i<N; i++) {
            if(array[i] == v)
                count++;
        }
        System.out.println(count);
    }
}
