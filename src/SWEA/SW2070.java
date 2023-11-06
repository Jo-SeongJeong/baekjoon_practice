package SWEA;

import java.util.Scanner;

public class SW2070 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        for(int i=1; i<=t; i++) {
            int a = scan.nextInt();
            int b = scan.nextInt();

            if(a > b) {
                System.out.println("#"+i+" >");
            }
            else if(a == b) {
                System.out.println("#"+i+" =");
            }
            else {
                System.out.println("#"+i+" <");
            }
        }
    }
}
