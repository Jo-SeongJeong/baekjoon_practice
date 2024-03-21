package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 1003 피보나치 함수
 *
 * 조건
 * 피보나치 수 N : 1 ~ 40
 *
 * 문제에서 구하고자 하는 것
 * f(n)이 만들어질 때 f(0)이 출력되는 횟수와 f(1)이 출력되는 횟수
 *
 * 문제 해결 프로세스
 * dp로 횟수 누적시켜서 풀자
 * f(n) = f(n-2) + f(n-1)이므로, dp[i][0] 에는 0이 불리는 횟수, dp[i][1]에는 1이 불리는 횟수 저장하기
 *
 * 고려한 시간 복잡도
 * 40
 * */

public class BJ1003 { // 메모리 : 11408kb, 시간 : 80ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());

        for(int t = 0; t < testCase; t++) {
            int n = Integer.parseInt(br.readLine());
            int[][] dp = new int[n+1][2];
            dp[0][0] = 1;

            for(int i = 1; i < n+1; i++) {
                if(i == 1) {
                    dp[i][1] = 1;
                    continue;
                }

                dp[i][0] = dp[i-1][0] + dp[i-2][0];
                dp[i][1] = dp[i-1][1] + dp[i-2][1];
            }
            sb.append(dp[n][0]).append(" ").append(dp[n][1]).append("\n");
        }
        System.out.println(sb);
    }
}
