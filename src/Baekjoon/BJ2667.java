package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 2667 단지 번호 붙이기
 *
 * 조건
 * 지도의 크키 N * N : 5 ~ 25 (25 ~ 625)
 * 단지 : 상하좌우에 연결된 집들의 집합
 * 0 : 집이 없는 곳
 * 1 : 집이 있는 곳
 *
 * 문제에서 구하고자 하는 것
 * 총 단지 수와 각 단지내의 집 수(오름차순으로)
 *
 * 문제 해결 프로세스
 * bfs
 * 0,0부터 1인 곳을 찾아 bfs
 * 방문될 때마다 집의 수 cnt
 * 모든 탐색을 끝내면 해당 집들은 1개의 단지
 * 집의 수 리스트에 넣자
 * n-1, n-1 끝나면, 오름차순 정렬 후 정답 출력
 *
 * 고려한 시간복잡도
 * 625 * 4 = 2500
 * */

public class BJ2667 { // 메모리 : 12016kb, 시간 : 88ms
    static int n;
    static int[][] map;
    static boolean[][] isChecked;
    static int[][] delta = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static List<Integer> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        isChecked = new boolean[n][n];
        list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(str.charAt(j)+"");
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(map[i][j] == 0) continue;
                if(isChecked[i][j]) continue;

                bfs(i, j);
            }
        }
        Collections.sort(list);

        sb.append(list.size() + "\n");
        for(Integer i : list) {
            sb.append(i + "\n");
        }

        System.out.println(sb);
    }

    private static void bfs(int r, int c) {
        int cnt = 1;
        Queue<int[]> queue = new ArrayDeque<>();

        isChecked[r][c] = true;
        queue.offer(new int[] {r, c});

        while(!queue.isEmpty()) {
            int[] loc = queue.poll();

            for(int d = 0; d < 4; d++) {
                int nr = loc[0] + delta[d][0];
                int nc = loc[1] + delta[d][1];

                if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                if(map[nr][nc] == 0) continue;
                if(isChecked[nr][nc]) continue;

                isChecked[nr][nc] = true;
                queue.offer(new int[]{nr, nc});
                cnt++;
            }
        }

        list.add(cnt);
    }
}
