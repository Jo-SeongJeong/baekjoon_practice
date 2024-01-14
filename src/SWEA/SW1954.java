package SWEA;

import java.util.Scanner;

public class SW1954 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        for(int i=1; i<=t; i++) {
            int n = scan.nextInt();
            int[][] array = new int[n][n];
            int row = 0;
            int col = 0;
            int num = 1;
            int move = n-1;
            array[0][0] = 1;


            while(true) {
                if(num==1) {
                    for (int j = 0; j < move; j++) {
                        num++;
                        col++;
                        array[row][col] = num;
                    }
                }
                for(int j=0; j<move; j++) {
                    num++;
                    row++;
                    array[row][col] = num;
                }
                for(int j=0; j<move; j++) {
                    num++;
                    col--;
                    array[row][col] = num;
                }
                move--;
                for(int j=0; j<move; j++) {
                    num++;
                    row--;
                    array[row][col] = num;
                }
                for(int j=0; j<move; j++) {
                    num++;
                    col++;
                    array[row][col] = num;
                }
                move--;

                if(num == n*n) {
                    break;
                }
            }
            System.out.println("#"+i);
            for(int j=0; j<n; j++) {
                for(int k=0; k<n; k++) {
                    System.out.print(array[j][k]);
                    if(k<n-1) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
    }
}