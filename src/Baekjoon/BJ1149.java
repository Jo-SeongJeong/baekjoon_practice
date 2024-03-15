package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1149 RGB 거리
 *
 * 조건
 * 집의 개수 N(1번 부터) : 2 ~ 1000
 * 집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야 함
 * 각 집을 색칠하는 비용 : 1 ~ 1000
 * 1번 집의 색은 2번 집의 색과 같지 않아야 함
 * N번 집의 색은 N-1번 집의 색과 같지 않아야 함
 * i번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 함
 *
 * 문제에서 구하고자 하는 것
 * 모든 집을 조건에 맞게 색칠했을 때의 최소 비용
 *
 * 문제 해결 프로세스
 * 입력을 저장할 수 있는 배열(각 집을 칠하는 비용)을 생성
 * dp 배열 생성
 * N == 1인 경우
 * dp[1] 은 입력[1]
 * dp[2]부터 입력[2][] +dp[1][입력번호 제외]
 *
 * */

public class BJ1149 { // 메모리 : 12112kb, 시간 : 88ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] input = new int[n+1][3];

        int[][] dp = new int[n+1][3];

        for(int i = 1; i < n+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i < n+1; i++) {
            for(int j = 0; j < 3; j++) {
                if(i == 1) {
                    dp[i][j] = input[i][j];
                    continue;
                }

                if(j == 0)
                    dp[i][j] = Math.min(input[i][j] + dp[i-1][1], input[i][j] + dp[i-1][2]);
                else if(j == 1)
                    dp[i][j] = Math.min(input[i][j] + dp[i-1][0], input[i][j] + dp[i-1][2]);
                else
                    dp[i][j] = Math.min(input[i][j] + dp[i-1][0], input[i][j] + dp[i-1][1]);

            }
        }

        System.out.println(Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2])));
    }
}
