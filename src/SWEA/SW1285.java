// 다 풀고 보니, 자바 지원이 안됨;

package SWEA;

import java.util.Scanner;

public class SW1285 {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        int testCase = scan.nextInt();

        for(int t = 1; t <= testCase; t++) {
            int n = scan.nextInt();
            int[] array = new int[n];

            for(int i = 0; i < n; i++) {
                array[i] = scan.nextInt();
            }

            int min = 100000;
            int count = 0;
            for(int i = 0; i < n; i++) {
                if(array[i] < 0) {
                    array[i] = array[i] - 2 * array[i];
                }

                if(min > array[i]) {
                    count = 1;
                    min = array[i];
                }

                else if (min == array[i]) {
                    count++;
                }
            }

            System.out.println("#" + t + " " + min + " " + count);
        }
    }
}