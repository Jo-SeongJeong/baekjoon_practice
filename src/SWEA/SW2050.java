package SWEA;

import java.util.Scanner;

public class SW2050 {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        String n = scan.next();
        String array[] = new String[200];
        array = n.split("");

        for(int i=0; i<n.length(); i++) {
            int ch = array[i].charAt(0)-64;
            System.out.print(ch);
            if(i<n.length()-1) {
                System.out.print(" ");
            }
        }
    }
}
