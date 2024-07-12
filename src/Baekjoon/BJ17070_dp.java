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
 * dp로 풀어서 현재 칸은 이전칸 방향에서 올 수 있는 방향을 기준으로 누적시켜 더함
 * 가로 : dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];
 * 세로 : dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];
 * 대각 : dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2]; -> 대각을 구할 땐 해당칸의 상, 좌가 벽이 아닌지 확인해봐야함
 * 만약 현재 구하는 칸이 1이면 무조건 0
 *
 * 고려한 시간 복잡도
 * 16 * 16 * 3 = 768
 * */

public class BJ17070_dp {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n+1][n+1];

        for(int i = 1; i < n+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < n+1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] dp = new int[n+1][n+1][3]; // 마지막 0-가로, 1-세로, 2-대각

        dp[1][2][0] = 1;

        for(int i = 1; i < n+1; i++) {
            for(int j =(i==1 ? 3 : 1); j <n+1; j++) {
                if(map[i][j] == 1) {
                    for(int k = 0; k < 3; k++) {
                        dp[i][j][k] = 0;
                    }
                }
                else {
                    dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];
                    dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];
                    if(map[i][j-1] == 1 || map[i-1][j] == 1) continue;
                    dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
                }
            }
        }

        int rs = 0;
        for(int i = 0; i < 3; i++) {
            rs += dp[n][n][i];
        }
        System.out.println(rs);
    }
}