package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * SWEA 2806 N-Queen
 *
 * 조건
 * 체스판 크기 : N x N (1 ~ 100)
 * 퀸의 개수 : N (1 ~ 10)
 * 퀸은 본인 위치에서 좌, 우, 대각을 모두 갈 수 있음
 *
 * 문제에서 구하고자 하는 것
 * 체스 보드에 입력된 개수의 퀸을 서로 공격하지 못하게 하는 경우의 수 구하기
 *
 * 문제 해결 프로세스
 * 0행부터 n-1행까지 탐색 한다
 * 행에서는 0열부터 n-1열까지 탐색한다
 * 퀸을 놓으면 해당 열의 check를 cnt 처리
 * 양 대각, 해당 열을 모두 탐색해서 cnt 처리
 * 다음 행에서 열이 0인 경우에만 퀸 두며 반복
 * 끝까지 가면 경우의 수 추가 후 return
 * 중간에 막히면 해당 좌표 + 다음까지 cnt-- 처리 후 return
 *
 * 고려한 시간 복잡도
 * 10^5 = 1000000 충분
 * */

public class SW2806_Check {
    static int n;
    static int[][] chess;
    static int[][] isChecked;
    static int count;
    static int[] delta = new int[] {-1,0,1};

    public static void main(String[] args) throws Exception { // 메모리 : 2,469kb, 시간 : 133ms
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++) {
            n = Integer.parseInt(br.readLine());
            chess = new int[n][n];
            isChecked = new int[n][n];
            count = 0;

            check(0);

            System.out.println("#" + t + " " + count);
        }
    }

    private static void check(int row) {
        if(row == n) {
            count++;
            return;
        }

        for(int i = 0; i < n; i++) {
            if(chess[row][i] == 0 && isChecked[row][i] == 0) {
                isChecked[row][i]++;
                chess[row][i] = 1;
                for (int d = 0; d < 3; d++) {
                    for (int j = 1; j < n; j++) {
                        int nr = row + j;
                        int nc = i + delta[d] * j;

                        if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                        isChecked[nr][nc]++;
                    }
                }

                check(row+1);

                isChecked[row][i]--;
                chess[row][i] = 0;
                for (int d = 0; d < 3; d++) {
                    for (int j = 1; j < n; j++) {
                        int nr = row + j;
                        int nc = i + delta[d] * j;

                        if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                        isChecked[nr][nc]--;
                    }
                }
            }
        }
        return;
    }
}