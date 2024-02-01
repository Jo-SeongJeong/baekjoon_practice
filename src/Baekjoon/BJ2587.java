package Baekjoon;

import java.util.Scanner;

public class BJ2587 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int sum = 0;
        int avg = 0;
        int[] array = new int[5];

        for(int i=0; i<5; i++) {
            array[i] = scan.nextInt();
            sum += array[i];
        }

        for(int i=0; i<5; i++) {
            for(int j=i+1; j<5; j++) {
                int temp;
                if(array[i] > array[j]) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }

        System.out.println(sum/5);
        System.out.println(array[2]);
    }
}