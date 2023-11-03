package Baekjoon;

import java.util.Scanner;

public class BJ2525 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int A = scan.nextInt();
        int B = scan.nextInt();
        int C = scan.nextInt();

        if(A==23) {
            if(B+C<60) {
                B = B+C;
                System.out.println(A+" "+B);
            }
            else if(B+C>=60){
                A = A+((B+C)/60)-24;
                B = (B+C)%60;
                System.out.println(A+" "+B);
            }
        }
        else if(A>=0 && A<23){
            if(B+C<60) {
                B = B+C;
                System.out.println(A+" "+B);
            }
            else if(B+C>=60){
                A = A + (B+C)/60;
                B = (B+C)%60;
                if(A<=23)
                    System.out.println(A + " " + B);
                else {
                    A = A-24;
                    System.out.println(A+" "+B);
                }
            }
        }
    }
}
