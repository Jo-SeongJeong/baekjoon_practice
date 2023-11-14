package SWEA;

import java.util.Scanner;

public class SW1545 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for(int i=n; i>=0; i--) {
            System.out.print(i);
            if(i==0) {
                break;
            }
            System.out.print(" ");
        }
    }
}
