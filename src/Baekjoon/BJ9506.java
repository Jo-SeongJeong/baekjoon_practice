package Baekjoon;

import java.util.Scanner;

public class BJ9506 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        while(true) {
            int sum = 0;
            int n = scan.nextInt();
            if(n == -1) {
                break;
            }
            int []array = new int[n];
            int index = 0;

            for(int i=1; i<n; i++) {
                if(n%i == 0) {
                    array[index] = i;
                    sum += i;
                    index++;
                }
            }

            if(n == sum) {
                System.out.print(n+" = ");
                for(int i=0; i<index; i++) {
                    if (i == index - 1) {
                        System.out.println(array[i]);
                    } else {

                        System.out.print(array[i] + " + ");
                    }
                }
            }
            else {
                System.out.println(n+" is NOT perfect.");
            }
        }
    }
}
