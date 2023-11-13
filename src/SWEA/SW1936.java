package SWEA;

import java.util.Scanner;

public class SW1936 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();

        int c = a-b;

        if(c == -1 || c == 2) {
            System.out.println("B");
        }
        else if(c == -2 || c == 1) {
            System.out.println("A");
        }
    }
}
