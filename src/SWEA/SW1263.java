package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 1263 사람 네트워크2
 *
 * 조건
 * 노드 수 N : 1 ~ 1000
 * CC(i) : 노드 i로부터 노드 j까지의 최단 거리, 노드 i 기준 다른 모든 경로까지의 최단거리 합
 *
 * 문제에서 구하고자 하는 것
 * CC의 최소 값인 CC(i)
 *
 * 문제 해결 프로세스
 * 플로이드 워샬 알고리즘으로 풀기
 * 인접행렬에서 경유지를 하나씩 추가하며 최단거리를 인접행렬에 저장하기
 *
 * 고려한 시간 복잡도
 * n^3 => 최악 10억, 10개 합쳐 20초
 * */

public class SW1263 { // 메모리 : 106876kb, 시간 : 3755ms

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++) {
            sb.append("#").append(t + " ");

            st = new StringTokenizer(br.readLine());
            int min = Integer.MAX_VALUE;

            int n = Integer.parseInt(st.nextToken());

            int[][] adjMatrix = new int[n][n];

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    adjMatrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] dp = new int[n][n];
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(adjMatrix[i][j] == 0) dp[i][j] = Integer.MAX_VALUE;
                    else dp[i][j] = adjMatrix[i][j];

                    if(i == j) dp[i][j] = 0;
                }
            }

            for(int k = 0; k < n; k++) { // 경유할 수 있는 노드 수
                for(int i = 0; i < n; i++) { // 출발 노드(기준, 행)
                    if(i == k) continue;
                    for(int j = 0; j < n; j++) { // 도착 노드(순회, 열)
                        if(j == k || i == j) continue;
                        if(dp[i][k] == Integer.MAX_VALUE || dp[k][j] == Integer.MAX_VALUE) continue;
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                    }
                }
            }

            for(int i = 0; i < n; i++) {
                int sum = 0;
                boolean overFlow = false;
                for(int j = 0; j < n; j++) {
                    if(dp[i][j] == Integer.MAX_VALUE) {
                        overFlow = true;
                        continue;
                    }
                    sum += dp[i][j];
                }

                if(overFlow) continue;
                min = Math.min(min, sum);
            }

            sb.append(min+"\n");

        }
        System.out.println(sb);
    }

}
