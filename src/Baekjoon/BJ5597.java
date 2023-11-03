package Baekjoon;

import java.util.Scanner;

public class BJ5597 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int []student = new int[31];

        for(int i=1; i<29; i++) {
            int submit = scan.nextInt();
            student[submit] = 1;
        }

        for(int i=1; i<=30; i++) {
            if(student[i] != 1) {
                System.out.println(i);
            }
        }
    }
}
