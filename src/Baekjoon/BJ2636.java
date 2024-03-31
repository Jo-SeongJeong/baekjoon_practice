package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 2636 치즈
 *
 * 조건
 * 판의 크기 : 최대 100 x 100
 * 판의 가장자리에는 치즈가 놓여 있지 않음
 * 치즈에는 하나 이상의 구멍이 있을 수 있음
 * 구멍 제외 공기와 닿는 치즈는 녹음
 * 치즈 칸 : 1
 * 없는 칸 : 0
 *
 * 문제에서 구하고자 하는 것
 * 치즈가 모두 녹아서 없어지는 데 걸리는 시간, 녹기 한 시간 전 남아 있는 치즈 조각의 칸 개수
 *
 * 문제 해결 프로세스
 * 처음 치즈가 있는 칸수를 입력 받을 때 cnt
 * 가장자리는 무조건 공기 -> 0,0부터 공기칸만 bfs 
 * 4방탐색으로 진행, 만약 탐색 중 치즈를 만나면 치즈 좌표 저장 -> 저장된 치즈 좌표는 녹는 것
 * cnt - 저장된 치즈 좌표 개수
 * 만약 0이되면 저장된 치즈 좌표 개수 = 녹기 한 시간 전 치즈 조각의 칸 개수이므로 저장
 * 저장된 치즈 좌표 -> 0 변경, 시간++다 녹을 때까지 반복
 * cnt 0 될때까지 반복
 *
 * 고려한 시간 복잡도
 * 100 * 100 * 4 = 40000
 * */

public class BJ2636 { // 메모리 : 13804kb, 시간 : 116ms
    static int n;
    static int m;
    static int[][] cheese;
    static boolean[][] isChecked;
    static boolean[][] meltCheck;
    static int count;
    static int day;
    static int meltCount;
    static Queue<int[]> queue;
    static Queue<int[]> melt;
    static int[][] delta = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public static void main(String[] args) throws  Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cheese = new int[n][m];
        count = 0;
        day = 0;

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());
                if(cheese[i][j] == 1) count++;
            }
        }

        while(count > 0) {
            bfs(0, 0);
        }

        System.out.println(day);
        System.out.println(meltCount);
    }

    static void bfs(int row, int col) {
        meltCount = 0;
        queue = new ArrayDeque<>();
        melt = new ArrayDeque<>();
        isChecked = new boolean[n][m];
        meltCheck = new boolean[n][m];

        queue.offer(new int[]{row, col});

        while (!queue.isEmpty()) {
            int[] loc = queue.poll();
            int r = loc[0];
            int c = loc[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + delta[i][0];
                int nc = c + delta[i][1];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (isChecked[nr][nc]) continue;

                if (cheese[nr][nc] == 0) {
                    isChecked[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
                else {
                    if (meltCheck[nr][nc]) continue;
                    meltCheck[nr][nc] = true;
                    melt.offer(new int[]{nr, nc});
                    meltCount++;
                }
            }
        }

        while(!melt.isEmpty()) {
            int[] loc = melt.poll();
            cheese[loc[0]][loc[1]] = 0;
        }

        day++;
        count -= meltCount;
    }
}
