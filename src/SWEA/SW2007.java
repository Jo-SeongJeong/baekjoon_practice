package SWEA;

import java.util.Scanner;

public class SW2007 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        for(int i=1; i<=t; i++) {
            String str = scan.next();
            int count = 0;

            for(int j=1; j<str.length(); j++) {
                String sub = str.substring(0, j);
                if(sub.equals(str.substring(j, 2*j))) {
                    count = sub.length();
                    break;
                }
            }
            System.out.println("#"+i+" "+count);
        }
    }
}