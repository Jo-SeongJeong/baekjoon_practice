package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 1600 말이 되고픈 원숭이
 *
 * 조건
 * 원숭이가 말로 움직일 수 있는 횟수 k : 0 ~ 30, 장애물 뛰어넘기는 가능
 * 맵 H x W : 각각 1 ~ 200
 * 0 : 평지
 * 1 : 장애물
 * 장애물이 있는 곳으로는 이동 불가
 * 시작점, 도착점은 항상 평지
 * k 소진 시, 상하좌우로만 이동 가능
 *
 * 문제에서 구하고자 하는 것
 * 원숭이의 동작수의 최솟값
 *
 * 문제 해결 프로세스
 * 방문 배열을 2개 만들어
 * 뛰어넘은 경우, 방문한 것과 그렇지 않은 경우에 따라 bfs 진행
 * 어쨋든 먼저 도착점에 오면 최솟값일 것
 *
 * 고려한 시간 복잡도
 * 200 * 200 = 40000
 * */

public class BJ1600 { // 메모리 : 59200kb, 시간 : 508ms
    static int k;
    static int w;
    static int h;
    static int[][] map;
    static boolean[][][] isChecked;
    static int[][] delta = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static int[][] jump = new int[][] {{-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {-2, -1}, {-2, 1}, {2, -1}, {2, 1}};
    static Queue<int[]> queue;
    static int min = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        k = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int[h][w];
        isChecked = new boolean[h][w][k+1];

        for(int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if(w == 1 && h == 1) {
            System.out.println(min);
            return;
        }

        bfs(0, 0, 0);

        System.out.println(min);
    }

    private static void bfs(int r, int c, int horse) {
        boolean flag = false;
        queue = new ArrayDeque<>();
        queue.offer(new int[] {r, c, horse});
        isChecked[r][c][0] = true;

        A :while(!queue.isEmpty()) {
            int size = queue.size();

            for(int l = 0; l < size; l++) {
                int[] loc = queue.poll();

                if(loc[0] == h-1 && loc[1] == w-1) {
                    flag = true;
                    break A;
                }

                for(int i = 0; i < 4; i++) {
                    int nr = loc[0] + delta[i][0];
                    int nc = loc[1] + delta[i][1];

                    if(nr < 0 || nr >= h || nc < 0 || nc >= w) continue;
                    if(isChecked[nr][nc][loc[2]]) continue;
                    if(map[nr][nc] == 1) continue;

                    queue.offer(new int[] {nr, nc, loc[2]});
                    isChecked[nr][nc][loc[2]] = true;

                }
                if(loc[2] < k) {
                    for(int i = 0; i < 8; i++) {
                        int nr = loc[0] + jump[i][0];
                        int nc = loc[1] + jump[i][1];

                        if(nr < 0 || nr >= h || nc < 0 || nc >= w) continue;
                        if(isChecked[nr][nc][loc[2]+1]) continue;
                        if(map[nr][nc] == 1) continue;

                        queue.offer(new int[] {nr, nc, loc[2]+1});
                        isChecked[nr][nc][loc[2]+1] = true;
                    }
                }
            }
            min++;
        }

        if(!flag)
            min = -1;

    }

}
