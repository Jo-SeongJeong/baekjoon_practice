package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 7576 토마토
 *
 * 조건
 * 익지 않은 토마토 : 상하좌우에 익은 토마토가 있다면 하루 뒤에 익는다
 * 토마토가 없는 칸이 있을 수 있다
 * 토마토는 혼자서 익을 수 없다
 * 상자의 크기 N x M : 각 2 ~ 1000
 * 1 : 익은 토마토
 * 0 : 익지 않은 토마토
 * -1 : 빈 칸
 *
 * 문제에서 구하고자 하는 것
 * 토마토가 모두 익는 최소 날짜, 만약 모두 익지 못 하면 -1 출력, 만약 처음부터 모두 익었다면 0
 *
 * 문제 해결 프로세스
 * 처음 저장할 때 익은 토마토 개수를 새는 과정, 안 익은 토마토의 경우 빈 칸인지 확인을 진행
 * 만약 안 익은 토마토 주변 모두가 빈칸이거나 경계 밖이라면 empty++
 * empty = 4인 경우가 생기면 -1 출력 후 종료
 * 만약 처음 익은 토마토가 n*m개이면 0 출력 후 종료
 * 나머지 경우, 탐색 진행
 * 초기 상태에서 1인 좌표값을 큐에 저장
 * 큐가 빌 때까지 반복 진행
 * 큐 poll -> 사방이 0이면 큐에 offer, 1로 바꿈, 사방이 1이면 걍 버림
 * 반복 cycle 1번 진행되면 day+1
 *
 * 고려한 시간 복잡도
 * N * M * 4 -> 4000000
 *
 * */

public class BJ7576 { // 메모리 : 119204kb, 시간 : 616ms
    static int n;
    static int m;
    static int[][] box;
    static boolean[] isChecked;
    static int[][] delta = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        box = new int[n][m];

        int all = 0;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if(box[i][j] == 0) {
                    all++;
                }
            }
        }

        if(all == 0) {
            System.out.println(0);
            return;
        }

        int day = bfs();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(box[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }
        System.out.println(day);

    }
    private static int bfs() {
        int day = 0;
        Queue<int[]> queue = new ArrayDeque<>();

        int size = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                int[] loc = new int[2];
                if(box[i][j] == 1) {
                    loc[0] = i;
                    loc[1] = j;
                    queue.offer(loc);
                    size++;
                }
            }
        }

        while(!queue.isEmpty()) {
            int len = size;
            size = 0;
            for(int i = 0; i < len; i++) {
                int[] temp = queue.poll();
                for(int d = 0; d < 4; d++) {
                    int nr = temp[0] + delta[d][0];
                    int nc = temp[1] + delta[d][1];

                    if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                    if(box[nr][nc] == 0) {
                        box[nr][nc] = 1;
                        int[] loc = new int[2];
                        loc[0] = nr;
                        loc[1] = nc;
                        queue.offer(loc);
                        size++;
                    }
                }
            }
            day++;
        }
        return day-1;
    }

}
