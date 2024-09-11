package Baekjoon;

import java.util.Scanner;

public class BJ27433 { // 메모리 : 12820kb, 시간 : 112ms
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] array = new long[n+1];
        array[0] = 1;
        for(int i = 1; i < n+1; i++) {
            array[i] = array[i-1] * i;
        }

        System.out.println(array[n]);

    }
}
