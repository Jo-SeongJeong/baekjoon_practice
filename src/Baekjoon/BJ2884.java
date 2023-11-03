package Baekjoon;

import java.util.Scanner;

public class BJ2884 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int H = scan.nextInt();
        int M = scan.nextInt();

        if(H==0) {
            if(M>=45 && M<=60) {
                M = M-45;
                System.out.println(H+" "+M);
            }
            else if(M>=0 && M<45){
                H = 23;
                M = 60-(45-M);
                System.out.println(H+" "+M);
            }
        }
        else if(H>0 && H<=23){
            if(M>=45 && M<=60) {
                M = M-45;
                System.out.println(H+" "+M);
            }
            else if(M>=0 && M<45){
                H = H-1;
                M = 60-(45-M);
                System.out.println(H+" "+M);
            }
        }
    }
}
