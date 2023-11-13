package SWEA;

import java.util.Scanner;

public class SW1933 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for(int i=1; i<=n; i++) {
            if(n%i == 0) {
                System.out.print(i);
                if(i<n) {
                    System.out.print(" ");
                }
            }
        }
    }
}
