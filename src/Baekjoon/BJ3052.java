package Baekjoon;

import java.util.Scanner;

public class BJ3052 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] array = new int[10];
        int[] rest = new int[10];
        int count = 0;

        for (int i = 0; i < 10; i++) {
            array[i] = scan.nextInt();
            rest[i] = array[i] % 42;
        }

        for (int i = 0; i < 10; i++) {
            for (int j = i + 1; j < 10; j++) {
                if (rest[i] == rest[j])
                    rest[j] = -1;
            }
        }

        for(int i=0; i<10; i++)
            if(rest[i] == -1)
                count++;
        System.out.println(10-count);
    }
}
