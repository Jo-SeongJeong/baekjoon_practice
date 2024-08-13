package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SWEA 5656 벽돌 깨기
 *
 * 조건
 * 구슬을 쏠 수 있는 횟수 N: 1 ~ 4
 * 맵의 크기 W * H : 각 2 ~ 12 , 2 ~ 15
 * 벽돌의 정보
 * 0 : 빈 공간
 * 1 ~ 9 : 벽돌
 * 게임 규칙
 * 1. 구슬은 좌-우로만 움직임 -> 맨 위의 벽돌만 깰 수 있음
 * 2. 구슬이 명중한 벽돌은 상하좌우로 (벽돌이 적힌 숫자 -1)칸 만큼 함께 제거
 * 3. 제거되는 범위 내에 있는 벽돌은 동시 제거
 *
 * 문제에서 구하고자 하는 것
 * 구슬 게임을 한 뒤, 남은 벽돌 개수의 최소값
 *
 * 문제 해결 프로세스
 * 1. 열의 크기에 대해 N번 뽑는 중복순열을 구하자 -> 중복순열이 구슬을 쏘는 위치 및 순서
 * 2. 구슬 쏘기
 * 해당 행을 탐색해 제일 처음 만나는 숫자를 확인
 * 해당 행의 숫자가 2 이상이면 큐에 좌표와 수를 담자
 * 해당 숫자는 이제 0
 * 해당 좌표 기준 숫자-1 한 사방향을 탐색
 * 탐색하면서 해당 숫자를 0으로 만들자, 만약 2이상이면 큐에 담고 이 과정을 반복
 * 큐가 비면 벽 부수기 끝
 * 3. 벽돌 당기기
 * 행 끝부터 0까지 탐색
 * 0인 곳을 발견하면 다음 좌표가 0이 아닌 것까지 가서 둘이 스왑
 * 2, 3 과정을 중복순열의 길이까지 반복
 * 4. 최소값 갱신
 * 해당 맵에 대해 좌표를 탐색하며 벽돌 개수 세기
 * 현재 최소값과 비교하여 갱신
 *
 * 고려한 시간 복잡도
 * 12^4 * 4*4*9 * 12*15 = 537,477,120
 * */

public class SW5656 { // 메모리 : 105388kb, 시간 : 1282ms
    static int n;
    static int w;
    static int h;
    static int[][] map;
    static int[] picked;
    static int[][] delta = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static int min;

    public static void main(String[] args) throws Exception { // 메모리 : 105388kb, 시간 : 1282ms
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++) {
            sb.append("#" + t + " ");
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            min = Integer.MAX_VALUE;

            map = new int[h][w];

            for(int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            picked = new int[n];
            dupPermu(0);

            sb.append(min + "\n");
        }
        System.out.println(sb);
    }

    private static void dupPermu(int idx) {
        if(idx == n) {

            int[][] temp = new int[h][w];
            for(int i = 0; i < h; i++) {
                temp[i] = map[i].clone();
            }

            for(int i = 0; i < n; i++) {
                shoot(i, temp);
            }

            int cnt = 0;
            for(int i = 0; i <h; i++) {
                for(int j = 0; j < w; j++) {
                    if(temp[i][j] == 0) continue;
                    cnt++;
                }
            }
            min = Math.min(min, cnt);

            return;
        }

        for(int i = 0; i < w; i++) {
            picked[idx] = i;
            dupPermu(idx+1);
        }


    }

    private static void shoot(int idx, int[][] temp) {
        Queue<int[]> queue = new ArrayDeque<>();
        for(int i = 0; i < h; i++) {
            if(temp[i][picked[idx]] == 0) continue;

            queue.offer(new int[] {i, picked[idx], temp[i][picked[idx]]});
            temp[i][picked[idx]] = 0;
            break;
        }

        if(queue.isEmpty()) return;

        if(queue.peek()[2] == 1) {
            queue.poll();
            return;
        }


        while(!queue.isEmpty()) {
            int[] loc = queue.poll();
            int cr = loc[0];
            int cc = loc[1];
            int size = loc[2] - 1;

            for(int i = 1; i <= size; i++) {
                for(int d = 0; d < 4; d++) {
                    int nr = cr + i*delta[d][0];
                    int nc = cc + i*delta[d][1];

                    if(nr < 0 || nr >= h || nc < 0 || nc >= w) continue;

                    if(temp[nr][nc] > 1) queue.offer(new int[] {nr, nc, temp[nr][nc]});
                    temp[nr][nc] = 0;
                }
            }
        }

        setMap(temp);

    }

    private static void setMap(int[][] temp) {
        for(int j = 0; j < w; j++) {
            for(int i = h-1; i >= 0; i--) {
                if(temp[i][j] != 0) continue;

                for(int k = i-1; k >= 0; k--) {
                    if(temp[k][j] == 0) continue;

                    temp[i][j] = temp[k][j];
                    temp[k][j] = 0;
                    break;
                }
            }
        }
    }
}
