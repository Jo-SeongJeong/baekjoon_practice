package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 11727 2 x n 타일링 2
 *
 * 조건
 * 직사각형 크기 2 X n : 1 ~ 1000
 * 타일 종류
 * 1 X 2, 2 X 1, 2 X 2
 *
 * 문제에서 구하고자 하는 것
 * 세가지 타일을 통해 직사각형을 채울 수 있는 경우의 수
 *
 * 문제 해결 프로세스
 * dp
 * dp[0] = 1
 * dp[1] = 1
 * dp[2] = 3
 * dp[3] = 5
 * dp[4] = 11
 * dp[i] = dp[i-2]*2 + dp[i-1]
 *
 * 고려한 시간 복잡도
 * */

public class BJ11727 { // 메모리 : 11540kb, 시간 : 76ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i < n+1; i++) {
            dp[i] = (dp[i-2] * 2 + dp[i-1]) % 10007;
        }

        System.out.println(dp[n]);
    }
}
