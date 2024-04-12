package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 1932 정수삼각형
 * 
 * 조건
 * 삼각형 크기 : 1 ~ 500 => 2차원 : 500 * 500
 * 각 수의 범위 : 0 ~ 999
 * 
 * 문제에서 구하고자 하는 것
 * 제일 위에서 아래까지 내려올 때 누적되는 합의 최대값
 * 
 * 문제 해결 프로세스
 * dp
 * 위에서 밑으로 내려오면서 해당 칸의 합을 더하자
 * 이동 가능 경로는 왼쪽 대각선, 오른쪽 대각선의 아래 => 행렬로 보면 1, 0 / 1, 1로 가자
 * dp[i][j] = 현재값 + dp[i-1][j-1] or dp[i-1][j] 최대값
 * 제일 마지막 행 보면서 최대값 구하자
 * 
 * 고려한 시간 복잡도
 * 500 * 500 / 2 = 125,000
 * */

public class BJ1932 { // 메모리 : 25200kb, 시간 : 228ms
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[][] triangle = new int[n+1][n+1];
		int[][] dp = new int[n+1][n+1];
		
		for(int i = 1; i < n+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= i; j++) {
				triangle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[1][1] = triangle[1][1];
		
		for(int i = 2; i < n+1; i++) {
			for(int j = 1; j <= i; j++) {
				dp[i][j] = triangle[i][j] + Math.max(dp[i-1][j], dp[i-1][j-1]);
			}
		}

		
		int max = 0;
		for(int i = 1; i < n+1; i++) {
			max = Math.max(max, dp[n][i]);
		}
		
		System.out.println(max);
//		
//		for(int[] row : triangle) {
//			System.out.println(Arrays.toString(row));
//		}
//		System.out.println();
//		for(int[] row : dp) {
//			System.out.println(Arrays.toString(row));
//		}
		
	}
}
