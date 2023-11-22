package SWEA;

import java.util.Scanner;

public class SW1204 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        for(int i=1; i<=t; i++) {
            int n = scan.nextInt();
            int []array = new int[1000];

            for(int j=0; j<1000; j++) {
                array[j] = scan.nextInt();
            }

            int max = 0;
            int freq = 0;

            for(int k=0; k<1000; k++) {
                int count = 0;
                for(int l=0; l<1000; l++) {
                    if(array[k] == array[l]) {
                        count++;
                    }
                }
                if(count >= max) {
                    max = count;
                    freq = array[k];
                }
            }
            System.out.println("#"+i+" "+freq);
        }
    }
}
