package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 16236 아기 상어
 *
 * 조건
 * 공간 크기 N * N : 2 ~ 20
 * 물고기 수 M
 * 아기 상어 = 1마리
 * 한 칸에는 물고기가 최대 1마리 존재
 * 아기 상어와 물고기는 모두 크기를 가지고 있음(자연수)
 * 0 : 빈칸
 * 1 ~ 6 : 물고기의 크기
 * 9 : 아기상어 위치
 * 최초 상어의 크기 : 2
 * 아기 상어는 1번 이동에 상하좌우로 이동 가능
 * 아기 상어는 자신의 크기보다 큰 물고기가 있는 칸은 지날 수 없음
 * 크기가 같은 물고기는 먹을 수 없지만, 칸을 지나갈 수는 있음
 * 상어 이동 프로세스
 * 1. 더 이상 먹을 수 있는 물고기가 공간에 없다면 엄마 상어에게 도움 요청
 * 2. 먹을 수 있는 물고기가 1마리면, 그 물고기를 먹으러 감
 * 3. 먹을 수 있는 물고기가 1마리 이상이면, 거리가 가장 가까운 물고기를 먹으러 감
 *     거리 = 아기 상어 칸에서 물고기 칸으로 이동할 때 지나야 하는 칸의 개수(최소값)
 *  거리가 가까운 물고기가 많은 경우, 가장 위->가장 왼쪽 우선순위
 * 물고기를 먹는데 걸리는 시간은 고려x
 * 물고기를 먹으면 해당 칸은 빈칸
 * 아기상어 크기 증가 조건 => 현재 본인의 크기만큼 물고기를 먹으면 1 증가
 *
 * 문제에서 구하고자 하는 것
 * 엄마 상어에게 도움을 요청하는 시간
 *
 * 문제 해결 프로세스
 * level별 bfs로, level이 거리
 * 먹을 수 있는 물고기 찾기
 * map[nr][nc] > 상어 크기 -> 불가
 * map[nr][nc] == 상어 크기 -> 큐에 담기
 * map[nr][nc] < 상어크기 -> 먹을 수 있는 물고기 후보, 큐 담기, 다음레벨로 못가게 막는다
 * 먹었던 후보 중 제일 우선순위가 높은 물고기 선택(우선순위 큐로 하자)
 * 먹으면 상어 위치 옮기고, 다시 반복 만약 다 방문할 때까지 큐를 빠져나오지 못했다면, 종료
 *
 * 고려한 시간 복잡도
 * 20 * 20 * 4 * 20 * 20 = 640,000
 *
 * 최적화에 대한 고민
 * 상어의 위치를 맵에 그려줄 필요는 없음
 * 좌표값만 기억하고 있으면 됨
 * */

public class BJ16236_최적화 { // 메모리 : 18628kb, 시간 : 228ms
    static int n;
    static int sr;
    static int sc;
    static int time;
    static int[][] map;
    static Queue<int[]> queue = new ArrayDeque<>();


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int count = 0;
        int shark = 2;
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        queue = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 9) {
                    sr = i;
                    sc = j;
                }
            }
        }

        while(findFish(sr, sc, shark)) {
            map[sr][sc] = 0;
            eatFish(shark);
            count++;

            if(count == shark) {
                shark++;
                count = 0;
            }
        }

        System.out.println(time);
    }

    private static boolean findFish(int sr, int sc, int shark) {
        int level = 0;
        boolean flag = false;
        boolean[][] visited = new boolean[n][n];
        int[][] delta = new int[][] {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

        visited[sr][sc] = true;
        queue.offer(new int[] {sr, sc});

        while(!queue.isEmpty()) {
            int size = queue.size();
            if(flag) {
                time += level;
                break;
            }

            for(int i = 0; i < size; i++) {
                int[] loc = queue.poll();

                for(int d = 0; d < 4; d++) {
                    int nr = loc[0] + delta[d][0];
                    int nc = loc[1] + delta[d][1];

                    if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                    if(visited[nr][nc]) continue;

                    if(shark < map[nr][nc]) continue;

                    visited[nr][nc] = true;
                    queue.offer(new int[] {nr, nc});

                    if(map[nr][nc] > 0 && map[nr][nc] < shark) flag = true;
                }
            }
            level++;
        }
        if(!flag) return false;

        return true;
    }

    private static void eatFish(int shark) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if(a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });

        while(!queue.isEmpty()) {
            int[] loc = queue.poll();

            int r = loc[0];
            int c = loc[1];

            if(map[r][c] < 1 || map[r][c] >= shark) continue;

            pq.offer(new int[] {r, c});

        }

        int[] temp = pq.poll();
        sr = temp[0];
        sc = temp[1];
    }
}
