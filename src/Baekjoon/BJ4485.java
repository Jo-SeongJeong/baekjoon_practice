package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 4485 녹색 옷 입은 애가 젤다지?
 *
 * 조건
 * 화폐 단위 : 루피
 * 도둑루피 : 소지한 루피가 감소됨
 * 동굴의 크기 N*N : 2 ~ 125
 * 배열 안의 수 : 도둑루피 금액 -> 잃는 금액
 * 시작 지점 : 0, 0
 * 도착 지점 : n-1, n-1
 * 이동 조건 : 한 번에 상하좌우 인접한 곳으로 1칸씩 이동 가능
 * 0을 누르면 입력 종료
 *
 * 문제에서 구하고자 하는 것
 * 잃을 수밖에 없는 최소 금액
 *
 * 문제 해결 프로세스
 * 레벨별 bfs로 풀자
 * 이 때, 해당 칸에 대해 누적된 최소값을 갱신해서 만약 현재 온 값이 최소값보다 크면 방문처리처럼 진행(방문 배열 응용)
 *
 * 고려한 시간 복잡도
 * 125*125*4*125 = 7,812,500
 * */

public class BJ4485 { // 메모리 : 273492kb, 시간 : 796ms
    static int n;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = 1;
        while(true) {
            n = Integer.parseInt(br.readLine());
            if(n == 0) break;

            sb.append("Problem " + testCase + ": ");

            map = new int[n][n];

            for(int i = 0; i< n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int money = bfs(0, 0);
            sb.append(money + "\n");
            testCase++;
        }
        System.out.println(sb);
    }

    private static int bfs(int sr, int sc) {
        int money = Integer.MAX_VALUE;
        int[][] delta = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        Queue<int[]> queue = new ArrayDeque<>();
        int[][] min = new int[n][n];

        for(int i = 0; i < n; i++) {
            Arrays.fill(min[i], Integer.MAX_VALUE);
        }

        min[sr][sc] = map[sr][sc];
        queue.offer(new int[] {sr, sc, map[sr][sc]});

        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int i = 0; i < size; i++) {
                int[] loc = queue.poll();
                int r = loc[0];
                int c = loc[1];
                int temp = loc[2];

                if(r == n-1 && c == n-1) {
                    money = Math.min(money, temp);
                    break;
                }

                for(int d = 0; d < 4; d++) {
                    int nr = r + delta[d][0];
                    int nc = c + delta[d][1];
                    if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                    if(min[nr][nc] <= temp + map[nr][nc]) continue;

                    min[nr][nc] = temp + map[nr][nc];
                    queue.offer(new int[] {nr, nc, min[nr][nc]});
                }
            }
        }
        return money;
    }
}
