package SWEA;

import java.util.Scanner;

public class SW12712 {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        for(int i = 1; i <= t; i++) {
            int n = scan.nextInt();
            int m = scan.nextInt();
            int[][] array = new int[n][n];
            int max =0;

            for(int j = 0; j < n; j++) {
                for(int k = 0; k < n; k++) {
                    array[j][k] = scan.nextInt();
                }
            }

            int[] r_index = {-1, 1, 0, 0};
            int[] c_index = {0, 0, -1, 1};
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < n; k++) {
                    int temp = array[j][k];
                    for(int s = 1; s < m; s++) {
                        for(int d = 0; d < 4; d++) {
                            int r = j + r_index[d] * s;
                            int c = k + c_index[d] * s;
                            if(r >=0 && c >= 0 && r < n && c < n) {
                                temp += array[r][c];
                            }
                        }
                    }
                    if(temp >= max) {
                        max = temp;
                    }
                }
            }

            int[] xr_index = {-1, 1, -1 ,1};
            int[] xc_index = {-1, -1, 1, 1};
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < n; k++) {
                    int temp = array[j][k];
                    for (int s = 1; s < m; s++) {
                        for (int d = 0; d < 4; d++) {
                            int r = j + xr_index[d] * s;
                            int c = k + xc_index[d] * s;
                            if (r >= 0 && c >= 0 && r < n && c < n) {
                                temp += array[r][c];
                            }
                        }
                    }
                    if(temp >= max) {
                        max = temp;
                    }
                }
            }
            System.out.println("#" + i + " " + max);
        }
    }
}
