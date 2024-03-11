package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SWEA 2117 홈 방범 서비스
 *
 * 조건
 * 도시 크기 N x N : 5 ~ 20
 * 하나의 집이 지불할 수 있는 비용 M : 1 ~ 10
 * 집이 있는 위치 : 1
 * 없는 위치 0, 도시에는 최소 1개 이상의 집이 존재
 * 홈방범 서비스는 마름모 모양 영역에서만 제공
 * 운영 비용 필요
 * 서비스 영역의 크기 K : 1이상의 정수
 * 운영비용 = K * K + (k-1) + (K-1)
 * 서비스 영역 면적 = 운영비용
 * K가 커질수록 운영 비용 증가
 * 영역은 도시를 벗어날 수 있지만, 운영비용은 변경x
 *
 * 문제에서 구하고자 하는 것
 * 서비스 영역크기가 주어져있을 때, 손해를 보지 않으면서 도시에서 가장 많은 서비스를 받을 수 있는 집의 개수
 *
 * 문제 해결 프로세스
 * 시작 점 0, 0부터 n-1, n-1까지 4방탐색하는 과정을 (n-1)/2번 반복, 집의 개수 cnt
 * 탐색을 한번 했을 때 운영비용보다 수금 비용이 작은 경우, 집의 개수 비교해 최대값으로 갱신
 *
 * 고려한 시간 복잡도
 * 20 * 20 * 4 * 10 = 16000
 *
 * */

public class SW2117 { // 메모리 : 88072kb, 시간 : 383ms
    static int n;
    static int m;
    static int[][] city;
    static int cost;
    static int max;
    static Queue<int[]> queue;
    static boolean[][] isChecked;
    static int[][] delta = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static final int maxK = 40;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            city = new int[n][n];

            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    city[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            max = 0;
            cost = 0;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    bfs(i, j);

                }
            }
            sb.append("#" + t +" ");
            sb.append(max);
            sb.append("\n");
        }

        System.out.println(sb);

    }

    private static void bfs(int r, int c) {
        int count = 0;
        int k = 1;

        isChecked = new boolean[n][n];
        isChecked[r][c] = true;

        queue = new ArrayDeque<>();
        queue.offer(new int[] {r, c});

        if(city[r][c] == 1) count++;

        while(!queue.isEmpty()) {
            cost = k * k + (k-1) * (k-1);

            if(count * m >= cost) {
                max = Math.max(count, max);
            }

            int size = queue.size();

            for(int i = 0; i < size; i++) {
                int[] loc = queue.poll();
                for(int d = 0; d < 4; d++) {
                    int nr = loc[0] + delta[d][0];
                    int nc = loc[1] + delta[d][1];

                    if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                    if(isChecked[nr][nc]) continue;

                    if(city[nr][nc] == 1) count++;
                    isChecked[nr][nc] = true;
                    queue.offer(new int[] {nr, nc});

                }
            }
            k++;

            if(k == maxK) break;

        }
    }
}
