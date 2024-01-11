package SWEA;

import java.util.Scanner;

public class SW1984 {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        for(int i = 1; i <= t; i++) {
            int[] array = new int[10];

            for(int j = 0; j < 10; j++) {
                array[j] = scan.nextInt();
            }

            for(int j = 0; j < 9; j++) {
                int temp;
                for(int k = j+1; k < 10; k++) {
                    if(array[j] >= array[k]) {
                        temp = array[j];
                        array[j] = array[k];
                        array[k] = temp;
                    }
                }
            }

            int sum = 0;
            for(int j = 1; j < 9; j++) {
                sum+=array[j];
            }

            double avg = sum / 8.0;

            System.out.println("#" + i + " " + Math.round(avg)); // Math.round(변수명) -> 소수점 첫번째 자리에서 반올림!
        }
    }
}