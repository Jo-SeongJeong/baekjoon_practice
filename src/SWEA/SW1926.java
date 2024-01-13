package SWEA;

import java.util.Scanner;

public class SW1926 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for(int i=1; i<=n; i++) {


            if (i >= 100) {
                int q = i/10;
                int r = i%10;
                int p = q/10;
                int s = q%10;

                if (r == 3 || r == 6 || r == 9) {
                    if (p == 3 || p == 6 || p == 9) {
                        if(s == 3 || s == 6 || s == 9) {
                            System.out.print("---");
                        }
                        else {
                            System.out.print("--");
                        }
                    }
                    else {
                        if(s == 3 || s == 6 || s == 9) {
                            System.out.print("--");
                        }
                        else {
                            System.out.print("-");
                        }
                    }
                }
                else {
                    if (p == 3 || p == 6 || p == 9) {
                        if(s == 3 || s == 6 || s == 9) {
                            System.out.print("--");
                        }
                        else {
                            System.out.print("-");
                        }
                    }
                    else {
                        if(s == 3 || s == 6 || s == 9) {
                            System.out.print("-");
                        }
                        else {
                            System.out.print(i);
                    }
                }

                }
            }
            else {
                int q = i / 10;
                int r = i % 10;
                if (r == 3 || r == 6 || r == 9) {
                    if (q == 3 || q == 6 || q == 9) {
                        System.out.print("--");
                    }
                    else {
                        System.out.print("-");
                    }
                }
                else if (q == 3 || q == 6 || q == 9) {
                    System.out.print("-");
                }
                else {
                    System.out.print(i);
                }
            }
            if (i < n) {
                System.out.print(" ");
            }
        }
    }
}