package Baekjoon;

import java.util.Scanner;

public class BJ1152 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine().trim();
        String[] ch = s.split(" ");

        if(ch.length == 1 && ch[0].equals(""))
            System.out.println(0);
        else
            System.out.println(ch.length);
    }
}
