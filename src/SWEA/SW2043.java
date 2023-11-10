package SWEA;

import java.util.Scanner;

public class SW2043 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int p = scan.nextInt();
        int t = scan.nextInt();
        int count = 1;

        while(p!=t) {
            if(p==t) {
                break;
            }
            else {
                count++;
            }
            t++;
        }
        System.out.println(count);
    }
}
