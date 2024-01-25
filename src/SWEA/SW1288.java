package SWEA;

import java.util.Scanner;

public class SW1288 {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        int testCase = scan.nextInt();

        for(int t = 1; t <= testCase; t++) {
            int[] array = new int[10];
            int n = scan.nextInt();

            int count = 1;
            while(true) {
                int temp = count * n;

                while(temp > 0) {
                    int k = temp % 10;
                    temp /= 10;
                    array[k]++;
                }

                int check = count;
                for(int i = 0; i < array.length; i++) {
                    if(array[i] == 0) {
                        count++;
                        break;
                    }
                }
                if(check == count) {
                    n *= count;
                    break;
                }
            }
            System.out.println("#" + t + " " + n);
        }
    }
}