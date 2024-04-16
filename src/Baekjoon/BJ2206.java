package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 2206 벽 부수고 이동하기
 *
 * 조건 맵 N x M : 각 1 ~ 1000 0 : 이동 가능 지역 1 : 벽(이동 불가) 최단 경로 : 맵에서 가장 적은 개수의 칸을
 * 지나는 경로 (시작, 끝칸 포함) 벽은 한 개까지 부술 수 있음 이동 경로 : 상하좌우 한칸
 *
 * 문제에서 구하고자 하는 것 최단 경로의 칸 수 구하기 (불가능 한 경우 -1)
 *
 * 문제 해결 프로세스 BFS로 푼다 시작점에서 4방탐색을 진행, 다음 칸의 위치와 벽 부순 여부(행, 열, 부셨으면 1, 아니면 0)를 담을
 * 배열을 큐에 넣자 큐에서 값을 꺼낼 때, 벽을 부신 적이 있다면 벽이 아닌 경우만 담아 반복, 벽을 부순적 없는 경우는 벽인 경우도 담을
 * 수 있도록 한다
 *
 * 고려한 시간 복잡도 1000 x 1000 x 4 => 4000000
 */

public class BJ2206 { // 메모리 : 119052kb, 시간 : 696ms
    static int n;
    static int m;
    static int[][] map;
    static Queue<int[]> queue;
    static int count;
    static int[][] delta = new int[][] { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };
    static boolean[][][] isChecked;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        isChecked = new boolean[n][m][2];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        bfs(0, 0, 0);

    }

    private static void bfs(int row, int col, int crush) {
        count = 1;
        queue = new ArrayDeque<>();

        queue.offer(new int[] { row, col, crush });
        isChecked[row][col][0] = true;
        int size = 1;
        while (!queue.isEmpty()) {
            int len  = size;
            size = 0;


            for (int l = 0; l < len; l++) {
                int[] current = new int[3];
                current = queue.poll();
                int r = current[0];
                int c = current[1];
                int cr = current[2];

                if (r == n - 1 && c == m - 1) {
                    System.out.println(count);
                    return;
                }

                for (int i = 0; i < 4; i++) {
                    int nr = r + delta[i][0];
                    int nc = c + delta[i][1];

                    if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
                        if (cr == 0  && !isChecked[nr][nc][0]) {
                            if (map[nr][nc] == 0) {
                                queue.offer(new int[] { nr, nc, cr });
                            }
                            else {
                                queue.offer(new int[] { nr, nc, cr + 1 });
                            }
                            isChecked[nr][nc][0] = true;
                            size++;
                        }
                        else if(cr == 1 && !isChecked[nr][nc][1]){
                            if (map[nr][nc] == 0) {
                                queue.offer(new int[] { nr, nc, cr });
                                isChecked[nr][nc][1] = true;
                                size++;
                            }
                        }
                    }
                }
            }
            count++;
        }

        System.out.println(-1);
        return;
    }
}
