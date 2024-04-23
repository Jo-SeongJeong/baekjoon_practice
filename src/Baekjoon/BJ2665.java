package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 백준 2665 미로만들기
 *
 * 조건
 * 맵 n * n : 1 ~ 50 => 250
 * 흰방(1) : 지나갈 수 있음
 * 검은 방(0) : 지나갈 수 없음
 * 시작 위치 : 1, 1
 * 끝 위치 : n, n
 * 검은 방을 흰방으로 바꿔 움직일 수 있음
 *
 * 문제에서 구하고자 하는 것
 * 끝 위치까지 가는 데 필요한 검은 방 변경의 최소 횟수
 *
 * 문제 해결 프로세스
 * bfs
 * 4방탐색을 하자
 * 레벨별 bfs
 * 방문 배열을 3차원으로 만들자
 * 끝방으로 갔을 때 방문배열의 차원이 바꾼 횟수
 * 끝방으로 가면 최솟값 갱신
 * 현재 차원이 최솟값보다 크면 큐에 안담음
 *
 * 고려한 시간 복잡도
 * 50 * 50 * 4 * 250 = 2,500,000
 * */

public class BJ2665 { // 메모리 : 25612kb, 시간 : 180ms
    static int n;
    static int count = Integer.MAX_VALUE;
    static int[][] map;
    static boolean[][][] visited;
    static int[][] delta = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n+1][n+1];
        visited = new boolean[n+1][n+1][n*n+1];

        for(int i = 1; i < n+1; i++) {
            String str = br.readLine();
            for(int j = 1; j < n+1; j++) {
                map[i][j] = Integer.parseInt(str.charAt(j-1) +"");
            }
        }

        bfs(1);
        System.out.println(count);
    }

    private static void bfs(int start) {
        Queue<int[]> queue = new ArrayDeque<>();
        visited[start][start][0] = true;
        queue.offer(new int[] {start, start, 0});

        while (!queue.isEmpty()) {
            int size = queue.size();

            for(int i = 0; i < size; i++) {
                int[] loc = queue.poll();
                int r = loc[0];
                int c = loc[1];
                int change = loc[2];

                if(count <= change) continue;

                if(r == n && c == n) {
                    count = change;
                    continue;
                }

                for(int d = 0; d < 4; d++) {
                    int nr = r + delta[d][0];
                    int nc = c + delta[d][1];

                    if(nr < 1 || nr > n || nc < 1 || nc > n) continue;
                    if(visited[nr][nc][change]) continue;

                    if(map[nr][nc] == 0) {
                        if(visited[nr][nc][change+1]) continue;
                        if(count <= change+1) continue;

                        visited[nr][nc][change+1] = true;
                        queue.offer(new int[]{nr, nc, change+1});
                    }
                    else {
                        visited[nr][nc][change] = true;
                        queue.offer(new int[]{nr, nc, change});
                    }
                }
            }
        }
    }
}
