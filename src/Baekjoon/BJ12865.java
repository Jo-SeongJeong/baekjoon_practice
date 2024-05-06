package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 12865 평범한 배낭
 *
 * 조건
 * 물건의 개수 N : 1 ~ 100
 * 배낭의 최대 무게 K : 1 ~ 100000
 * 각 물건의 무게 W : 1 ~ 100000
 * 각 물건의 가치 V : 1 ~ 1000
 *
 * 문제에서 구하고자 하는 것
 * 배낭에 넣을 수 있는 물건들의 가치합에 대한 최대값
 *
 * 문제 해결 프로세스
 * dp로 풀자
 * 행렬의 각 열은 배낭의 무게(0 ~ 최대까지)
 * 행은 각 물건의 무게 <- 해당 idx는 물건 배열의 값으로 쓰자
 * 배낭 채우는 과정
 * dp 배열의 j가 stuff[i][0]보다 작다 -> dp[i-1][j]
 * dp 배열의 j가 stuff[i][0]와 크거나 같다 -> max(dp[i-1][j], dp[i-1][j-stuff[i][0]] + stuff[i][1])
 *
 * 고려한 시간 복잡도
 * k * n = 10000000
 * */

public class BJ12865 { // 메모리 : 51288kb, 시간 : 148ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] stuff = new int[n+1][2];
        int[][] bag = new int[n+1][k+1];

        for(int i = 1; i < n+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 2; j++) {
                stuff[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i < n+1; i++) {
            for(int j = 0; j < k+1; j++) {
                if(j < stuff[i][0])
                    bag[i][j] = bag[i-1][j];
                else
                    bag[i][j] = Math.max(bag[i-1][j], bag[i-1][j-stuff[i][0]] + stuff[i][1]);
            }
        }

        System.out.println(bag[n][k]);
    }
}
