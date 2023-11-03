package Baekjoon;

import java.util.Scanner;

public class BJ1546 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        double []array = new double[N];
        double avg = 0;

        for(int i=0; i<N; i++)
            array[i] = scan.nextInt();
        double M = 0;

        for(int i=0; i<N; i++) {
            if(M<array[i])
                M = array[i];
        }
        for(int i=0; i<N; i++) {
            array[i] = array[i]/M*100;
            avg += array[i];
        }
        System.out.println(avg/N);
    }
}
