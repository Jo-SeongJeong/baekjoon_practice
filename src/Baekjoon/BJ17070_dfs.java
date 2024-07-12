package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 17070 파이프옮기기1
 *
 * 조건
 * 집 크기 N * N : 3 ~ 16
 * r : 행 번호, 1부터 시작
 * c : 열 번호, 1부터 시작
 * 빈칸 : 0
 * 벽 : 1
 * 파이프 : 2개의 연속된 칸을 차지하는 크기
 * 최초 위치 : 1,1 - 1,2
 * 회전 가능(현재 방향에서 한번)
 * 가능한 방향 : 가로 2칸, 세로 2칸, 대각 2칸(5시)
 * 밀어서 이동
 * 방향 : 가로, 세로, 5시
 * 벽으로 이동 불가 대각으로 회전시 끝 위치의 우,하, 우하대각은 벽이여야 함
 *
 * 문제에서 구하고자 하는 것
 * 최초 방향에서 N,N으로 이동시키는 방법의 수
 *
 * 문제 해결 프로세스
 * dfs
 * 1, 2를 시작점으로 잡는다
 * 방향은 우, 하, 우하대각으로 설정
 * dfs로 이동 진행하면 n,n이 되면 cnt++ 후 return
 * 안되면 return
 *
 * 고려한 시간 복잡도
 * 16 * 16 * 3 = 768
 * */

public class BJ17070_dfs { // 메모리 : 297584kb, 시간 : 600ms
    static int n;
    static int[][] map;
    static int cnt;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n+1][n+1];

        for(int i = 1; i < n+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < n+1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(1, 2, 0);

        System.out.println(cnt);
    }

    private static void dfs(int r, int c , int dir) {
        if(r == n && c == n) {
            cnt++;
            return;
        }

        int[][] delta = new int[][] {{0, 1}, {1, 0}, {1, 1}};

        for(int d = 0; d < 3; d++) {

            if(dir == 0 && d == 1) continue;
            else if(dir == 1 && d == 0) continue;

            int nr = r + delta[d][0];
            int nc = c + delta[d][1];

            if(nr < 0 || nr >= n+1 || nc < 0 || nc >= n+1) continue;
            if(map[nr][nc] == 1) continue;

            if(d == 2) {
                boolean flag = true;
                for(int i = 0; i < 2; i++) {
                    int cr = r + delta[i][0];
                    int cc = c + delta[i][1];

                    if(cr < 0 || cr >= n+1 || cc < 0 || cc >= n+1) flag = false;
                    if(map[cr][cc] == 1) flag = false;
                }

                if(flag) dfs(nr, nc, d);
            }

            else dfs(nr, nc, d);
        }

    }
}
