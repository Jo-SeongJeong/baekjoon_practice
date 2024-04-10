package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 3055 탈출
 *
 * 조건
 * 지도 R x C : 각 1 ~ 50
 * . : 비어있는 곳
 * * : 물이 차 있는 지역
 * X : 돌
 * D : 비버의 굴
 * S : 고슴도치 위치
 * 매 분마다 고슴도치는 현재 있는 칸과 인접한 네칸 중 하나로 이동(상, 하, 좌, 우)
 * 물도 비어있는 칸으로 확장(상, 하, 좌, 우)
 * 물이 있는 칸과 인접해있는 비어있는 칸은 물이 차게 됨
 * 물과 고슴도치는 돌을 통과할 수 없다
 * 고슴도치는 물로 차 있는 구역으로 이동 불가
 * 물은 비버의 소굴로 이동 불가
 * 고슴도시는 물이 찰 예정인 칸으로 이동할 수 없다(다음 시간에 물이 차는 경우)
 *
 * 문제에서 구하고자 하는 것
 * 고슴도치가 비버의 굴로 이동할 수 있는 가장 빠른 시간
 * 만약 이동 불가하다면 KAKTUS 출력
 *
 * 문제 해결 프로세스
 * bfs -> 먼저 물이 찰 위치를 체크하고 고슴도치 이동 bfs 진행
 *
 * 고려한 시간 복잡도
 * 50 * 50 * 4 = 10000
 * */

public class BJ3055 { // 메모리 : 11772kb, 시간 : 84ms
    static int r;
    static int c;
    static char[][] map;
    static boolean[][] isChecked;
    static boolean[][] visited;
    static Queue<int[]> queue;
    static Queue<int[]> water;
    static int[][] delta = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static int day;
    static boolean flag;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        isChecked = new boolean[r][c];
        visited = new boolean[r][c];
        queue = new ArrayDeque<>();
        water = new ArrayDeque<>();

        for(int i = 0; i < r; i++) {
            String str = br.readLine();
            for(int j = 0; j< c; j++) {
                map[i][j] = str.charAt(j);

                if(map[i][j] == 'S') {
                    visited[i][j] = true;
                    queue.offer(new int[] {i, j});
                }
                if(map[i][j] == '*') {
                    isChecked[i][j] = true;
                    water.offer(new int[] {i, j});
                }
            }
        }

        bfs();

        if(flag)
            System.out.println(day);
        else
            System.out.println("KAKTUS");
    }

    private static void bfs() {
        A : while(!queue.isEmpty()) {
            int size = water.size();

            for(int i = 0; i < size; i++) {
                int[] loc = water.poll();

                for(int d = 0; d < 4; d++) {
                    int nr = loc[0] + delta[d][0];
                    int nc = loc[1] + delta[d][1];

                    if(nr < 0 || nr >= r || nc < 0 || nc >= c) continue;
                    if(map[nr][nc] == 'X') continue;
                    if(map[nr][nc] == 'D') continue;
                    if(isChecked[nr][nc]) continue;

                    map[nr][nc] = '*';
                    isChecked[nr][nc] = true;
                    water.offer(new int[] {nr, nc});
                }
            }

            size= queue.size();

            for(int i = 0; i < size; i++) {
                int[] loc = queue.poll();

                if(map[loc[0]][loc[1]] == 'D') {
                    flag = true;
                    break A;
                }

                for(int d = 0; d < 4; d++) {
                    int nr = loc[0] + delta[d][0];
                    int nc = loc[1] + delta[d][1];

                    if(nr < 0 || nr >= r || nc < 0 || nc >= c) continue;
                    if(map[nr][nc] == 'X') continue;
                    if(map[nr][nc] == '*') continue;
                    if(visited[nr][nc]) continue;

                    visited[nr][nc] = true;
                    queue.offer(new int[] {nr, nc});
                }
            }
            day++;
        }

    }

}
