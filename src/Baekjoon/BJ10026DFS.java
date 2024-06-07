package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 10026 적록색약
 *
 * 조건
 * R, G, B
 * 적록 색약 : 빨간색 = 초록색 (R = G)
 * 그리드 N x N : 1 ~ 100
 * 구역은 같은 색으로 이루어짐
 * 같은 구역 : 같은 색상이 상하좌우로 인접
 *
 * 문제에서 구하고자 하는 것
 * 적록 색약이 아닌 사람 기준, 적록 색약 기준 구역의 개수 각각 출력
 *
 * 문제 해결 프로세스
 * DFS
 * 0,0부터 탐색
 * 한 색이 나오면 해당 색 기준으로 4방탐색
 * 만약 현재 색과 같은 색이 4방 중 있다면 해당 영역 _로 바꾸고 재귀로 넘어감
 * 만약 모두 다르다면 해당 색 cnt 후 return
 * 적록색약 유무에 따라 2번 진행
 *
 * 고려한 시간 복잡도
 * 100 * 100 * 4 * 2 = 80000 충분
 * */

public class BJ10026DFS { // 메모리 : 12316kb, 시간 : 88ms
    static int n;
    static char[][] grid;
    static char[][] redGreen;
    static int[][] delta = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static boolean flag;
    static boolean RGFlag;
    static int count;
    static int RGCount;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        grid = new char[n][n];
        redGreen = new char[n][n];

        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            for(int j = 0; j < n; j++) {
                grid[i][j] = str.charAt(j);
                if(grid[i][j] == 'B')
                    redGreen[i][j] = grid[i][j];
                else
                    redGreen[i][j] = 'R';

            }
        }
        count = 0;
        RGCount = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j< n; j++) {
                if(grid[i][j] != '_') {
                    flag = false;
                    char current = grid[i][j];
                    grid[i][j] = '_';
                    dfs(i, j, current);
                }

                if(redGreen[i][j] != '_') {
                    RGFlag = false;
                    char current = redGreen[i][j];
                    redGreen[i][j] = '_';
                    RGdfs(i, j, current);
                }
            }
        }
        System.out.println(count + " " + RGCount);
    }

    private static void dfs(int row, int col, char current) {

        for(int i = 0; i < 4; i++) {
            int nr = row + delta[i][0];
            int nc = col + delta[i][1];

            if(nr >= 0 && nr < n && nc >= 0 && nc < n) {
                if(grid[nr][nc] == current) {
                    grid[nr][nc] = '_';
                    dfs(nr, nc, current);
                }
            }
        }

        if(flag) return;

        flag = true;
        count += 1;
    }

    private static void RGdfs(int row, int col, char current) {

        for(int i = 0; i < 4; i++) {
            int nr = row + delta[i][0];
            int nc = col + delta[i][1];

            if(nr >= 0 && nr < n && nc >= 0 && nc < n) {
                if(redGreen[nr][nc] == current) {
                    redGreen[nr][nc] = '_';
                    RGdfs(nr, nc, current);
                }
            }
        }

        if(RGFlag) return;

        RGFlag = true;
        RGCount += 1;
    }

}
