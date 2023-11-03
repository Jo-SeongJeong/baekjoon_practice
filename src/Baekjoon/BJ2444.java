package Baekjoon;

import java.util.Scanner;

public class BJ2444 {
   public static void main(String []args) {
       Scanner scan = new Scanner(System.in);
       int N = scan.nextInt();

       for(int i=0; i<N; i++) {
           for(int j=N-1; j>i; j--) {
               System.out.print(" ");
           }
           for(int k=1; k<=(2*i+1); k++) {
               System.out.print("*");
           }
           System.out.println("");
       }

       for(int i=N-1; i>0; i--) {
           for(int j=N; j>i; j--) {
               System.out.print(" ");
           }
           for(int k=(2*i-1); k>0; k--) {
               System.out.print("*");
           }
           System.out.println("");
       }
   }
}
