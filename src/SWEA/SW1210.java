package SWEA;

import java.util.Scanner;

public class SW1210 {
    static int[][] array = new int[100][100]; // 사다리 판
    static int[][] delta = new int[][] { { 0, -1 }, { 0, 1 }, { -1, 0 } }; // 좌, 우, 상 델타
    static int startRow; // 도착점의 행
    static int startCol; // 도착점의 열
    static boolean[][] isChecked; // 이동 체크

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int t = 1; t <= 10; t++) {
            isChecked = new boolean[100][100];
            int testCase = sc.nextInt();

            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    array[i][j] = sc.nextInt();
                    if (array[i][j] == 2) {
                        startRow = i;
                        startCol = j;
                    }
                }
            }

            isChecked[startRow][startCol] = true;
            recursion(startRow, startCol);
            System.out.println("#" + testCase + " " +startCol);

        }
    }

    public static void recursion(int r, int c) {
        if(r == 0) {
            startCol = c;
            return ;
        }

        for (int i = 0; i < 3; i++) {
            int row = r + delta[i][0];
            int col = c + delta[i][1];

            if (row >= 0 && row < 100 && col >= 0 && col < 100) {
                if (array[row][col] == 0 || isChecked[row][col] == true) {
                    continue;
                }

                else {
                    isChecked[row][col] = true;
                    recursion(row, col);
                    break;
                }
            }
        }
    }
}
