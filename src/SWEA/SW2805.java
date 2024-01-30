package SWEA;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SW2805 {
    static int n;
    static int[][] array;
    static boolean[][] isChecked; // 중복 체크
    static int[][] delta = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static int startRow;
    static int startCol;
    static int sum;
    static int index;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase; t++) {
            n = Integer.parseInt(br.readLine());
            array = new int[n][n];
            isChecked = new boolean[n][n];
            sum = 0;

            for (int i = 0; i < n; i++) {
                String str = br.readLine();
                for (int j = 0; j < n; j++) {
                    array[i][j] = str.charAt(j) - '0';
                }
            }

            startRow = (n - 1) / 2; // 중간 좌표 행 값
            startCol = (n - 1) / 2; // 중간 좌표 열 값
            isChecked[startRow][startCol] = true;
            sum += array[startRow][startCol];
            index = startRow;

            recursion(startRow, startCol, index);


            System.out.println("#" + t + " " + sum);
        }
    }

    public static void recursion(int r, int c, int idx) {
        if (idx == 0) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            int row = r + delta[i][0];
            int col = c + delta[i][1];

            if(c < startCol) {
                if(i == 1) {
                    continue;
                }
            }
            else if(c > startCol) {
                if(i == 0) {
                    continue;
                }
            }

            if (row >= 0 && row < n && col >= 0 && col < n) {
                if (isChecked[row][col] == true) {
                    continue;
                }
                sum += array[row][col];
                isChecked[row][col] = true;
                recursion(row, col, idx - 1);
            }
        }
    }
}
