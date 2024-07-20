package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 17144 미세먼지 안녕!
 *
 * 조건
 * 집 크기 R * C : 각 6 ~ 50
 * 지난 시간 T : 1 ~ 1000
 * 공기청정기 설치 열 : 1열
 * 공기청정기 크기 : 두 행 차지
 * 공기청정기가 설치되어 있지 않은 칸에는 미세먼지가 있음
 * 미세먼지 양 : (r, c)의 배열 값
 * 1초 후 일어나는 일
 * 1. 미세먼지 확산(확산은 동시에 일어남)
 *     미세먼지가 있는 칸의 상하좌우로 퍼짐
 *     인접한 방향에 공기청정기가 있거나 칸이 없으면 확산x
 *     학산되는 양 = 해당 칸/5 (소수점 무시)
 *     해당 칸의 미세먼지 양 = 해당 칸 - 확산되는양 * 확산된 방향의 개수
 *     만약 확산되는 칸이 중복이라면 다 더함
 * 2. 공기청정기 작동
 *     위쪽 공기청정기의 바람은 반시계방향으로 순환
 *     아래쪽 공기청정기의 바람은 시계방향으로 순환
 *     바람이 불면 미세먼지는 바람의 방향대로 한칸씩 이동
 *     공기청정기에서 부는 바람은 미세먼지가 없는 바람, 공기청정기로 들어간 미세먼지는 없어짐
 * 공기청정기의 두 행 중 위쪽 행이 반시계 방향, 아래쪽이 시계방향
 * 바람은 해당 행렬 기준으로 가장 크게 돎
 * 공기청정기의 설치 위치는 -1로 표시
 * 이는 가장 윗행, 아랫행과 두 칸 이상 떨어져 있음
 *
 * 문제에서 구하고자 하는 것
 * t초 후 남아있는 미세먼지의 양
 *
 * 문제 해결 프로세스
 * 1. 입력을 받을 때, 공기 청정기 행렬값을 저장 -> 여기서 위 아래를 나누자
 * 2. 입력을 받을 때, 양수인 행, 열, 값을 큐에 저장하자(bfs 처럼)
 * 3. 미세먼지 확산 로직 queue가 4방탐색 1번씩하며 확산(queue 빌때까지), 확산 1번하고, 현재 값 갱신
 * 4. 공기청정기 가동
 *     위쪽은 반시계, 아래쪽은 시계방향으로 제일 크게 배열돌리기 1번씩
 * 5. 다음 확산 위해 큐채우기
 * t까지 입력 제외 2, 3, 5번 반복
 * 
 * 5. 최종 방의 미세먼지의 합 구하기(큐 값 poll해서 더하자) -> 다음을 위해 채워놨을 것, 이 값을 이용
 *
 * 고려한 시간 복잡도
 * 1000 * 4 * 50 * 50 = 10,000,000
 * */

public class BJ17144 { // 메모리 : 91004kb, 시간 : 300ms
    static int r;
    static int c;
    static int t;
    static int[][] map;
    static int[][] delta = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static Queue<int[]> queue;
    static boolean[][] visited;
    static int upR;
    static int downR;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        map = new int[r][c];
        queue = new ArrayDeque<>();
        int first = 0;
        for(int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] > 0) queue.offer(new int[] {i, j, map[i][j]});

                if(first == 0 && map[i][j] == -1) first = i;
            }
        }

        upR = first;
        downR = first+1;

        for(int i = 0; i < t; i++) {
            dust();
        }
        int sum = 0;
        while(!queue.isEmpty()) {
            sum += queue.poll()[2];
        }
        System.out.println(sum);
    }

    private static void dust() {
        while(!queue.isEmpty()) {
            int[] loc = queue.poll();

            int count = 0;
            for(int d = 0; d < 4; d++) {
                int nr = loc[0] + delta[d][0];
                int nc = loc[1] + delta[d][1];

                if(nr < 0 || nr >= r || nc < 0 || nc >= c) continue;
                if(map[nr][nc] == -1) continue;

                map[nr][nc] += loc[2]/5;
                count++;
            }

            map[loc[0]][loc[1]] -= loc[2]/5 * count;
        }


        upAir();
        downAir();
//        for(int[] row : map) {
//            System.out.println(Arrays.toString(row));
//        }

        next();
    }
    private static void upAir() {
        int row = upR-1;
        int col = 0;
        int[][] delta = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상 우 하 좌
        int d = 0;
        while(d < 4) {
            int nr = row + delta[d][0];
            int nc = col + delta[d][1];

            if(nr < 0 || nr >= upR+1 || nc < 0 || nc >= c) d++;
            else {
                map[row][col] = map[nr][nc];
                row = nr;
                col = nc;
            }
        }
        map[upR][1] = 0;


    }

    private static void downAir() {
        int row = downR+1;
        int col = 0;
        int[][] delta = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // 하 우 상 좌
        int d = 0;
        while(d < 4) {
            int nr = row + delta[d][0];
            int nc = col + delta[d][1];

            if(nr < downR || nr >= r || nc < 0 || nc >= c) d++;
            else {
                map[row][col] = map[nr][nc];
                row = nr;
                col = nc;
            }
        }
        map[downR][1] = 0;
    }

    private static void next() {
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(map[i][j] > 0) queue.offer(new int[] {i, j, map[i][j]});
            }
        }
    }
}
