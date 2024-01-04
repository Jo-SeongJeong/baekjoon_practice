package SWEA;

import java.util.Scanner;

public class SW1974 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        int row = 9;
        int col = 9;
        int[][] array = new int[row][col];

        for (int i = 1; i <= t; i++) {
            int result = 1;

            for (int j = 0; j < row; j++) {
                for (int k = 0; k < col; k++) {
                    array[j][k] = scan.nextInt();
                }
            }

            for (int j = 0; j < row; j++) {
                int r_sum = 0;
                int r_mul = 1;
                int c_sum = 0;
                int c_mul = 1;
                for (int k = 0; k < col; k++) {
                    r_sum += array[j][k];
                    r_mul *= array[j][k];
                    c_sum += array[k][j];
                    c_mul *= array[k][j];
                }

                if (!(r_sum == 45 && r_mul == 362880 && c_sum == 45 && c_mul == 362880)) {
                    result = 0;
                    break;
                }
            }

            for(int j = 0; j < row; j+=3) {
                int s_sum = 0;
                int s_mul = 1;
                for(int k = 0; k < 3; k++) {
                    for(int l = 0; l < 3; l++) {
                        s_sum += array[j+k][j+l];
                        s_mul *= array[j+k][j+l];
                    }
                }

                if(!(s_sum == 45 && s_mul == 362880)) {
                    result = 0;
                    break;
                }
            }
            System.out.println("#" + i + " " + result);
        }
    }
}