package SWEA;

import java.util.Scanner;

public class SW2019 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int p = 1;
        for(int i=0; i<=n; i++) {
            if(i == 0) {
                System.out.print(1);
            }
            else {
                p *= 2;
                System.out.print(p);
            }
            if(i<n) {
                System.out.print(" ");
            }
        }
    }
}
