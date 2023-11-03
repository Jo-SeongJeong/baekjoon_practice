package Baekjoon;

import java.util.Scanner;

public class BJ2798 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int [] card = new int[n];

        for(int i=0; i<n; i++) {
            card[i] = scan.nextInt();
        }
        int sum = result(card, n, m);
        System.out.println(sum);
    }

    public static int result(int []card, int n, int m) {
        int sum = 0;
        for(int i=0; i<n-2; i++) {
            for(int j=i+1; j<n-1; j++) {
                for(int k=j+1; k<n; k++) {
                    int temp = card[i] + card[j] + card[k];
                    if(temp == m) {
                        return temp;
                    }
                    if(temp<m && sum<temp) {
                        sum = temp;
                    }
                }
            }
        }
        return sum;
    }
}
