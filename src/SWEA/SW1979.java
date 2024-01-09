package SWEA;

import java.util.Scanner;

public class SW1979 {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        for (int i = 1; i <= t; i++) {
            int n = scan.nextInt();
            int k = scan.nextInt();
            int[][] array = new int[n][n];

            for (int j = 0; j < n; j++) {
                for (int l = 0; l < n; l++) {
                    array[j][l] = scan.nextInt();
                }
            }

            int count = 0;
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < n; l++) {
                    int c_temp = 0;
                    if (array[j][l] == 1 && c_temp == 0) {
                        for (int c = l; c < n; c++) {
                            l = c;
                            if (array[j][c] == 0) {
                                break;
                            }
                            c_temp++;
                        }
                        if (k == c_temp) {
                            count++;
                        }
                    }
                }
            }

            for(int l = 0; l < n; l++) {
                for(int j = 0; j < n; j++) {
                    int r_temp = 0;
                    if(array[j][l] == 1 && r_temp == 0) {
                        for(int r = j; r < n; r++) {
                            j = r;
                            if(array[r][l] == 0) {
                                break;
                            }
                            r_temp++;
                        }
                        if(k == r_temp) {
                            count++;
                        }
                    }
                }
            }
            System.out.println("#" + i + " " + count);
        }
    }
}