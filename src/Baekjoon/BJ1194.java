package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 1194 달이 차오른다, 가자.
 *
 * 조건
 * 미로 크기 N * M : 1 ~ 50
 * 미로의 구성
 * 빈칸 '.' : 이동 가능
 * 벽 '#' : 이동 불가
 * 열쇠 'a' ~ 'f' : 이동 가능, 처음 들어가면 열쇠 획득
 * 문 'A' ~ 'F' : 문과 대응되는 열쇠가 있을 때만 이동 가능
 * 현재 위치 '0' : 빈칸
 * 출구 : '1' : 도착하면 미로 탈출
 *
 * 한번의 움직임은 수평 / 수직으로 한 칸 이동 가능
 * 문에 대응하는 열쇠가 없을 수도 있음
 * 같은 타입의 열쇠가 여러개일 수 있음
 *
 * 문제에서 구하고자 하는 것
 * 미로 탈출에 걸리는 이동 횟수의 최소값 구하기
 *
 * 문제 해결 프로세스
 * 레벨 별 bfs
 * 열쇠 획득에 따라 방문 배열을 다르게
 *     -> 총 6가지 종류, 가능한 열쇠의 조합 = 2^6, 64개
 * 비트마스킹으로 열쇠관리
 *  -> 000001을 몇 비트 밀건지 (열쇠 종류에 따라 고정), | 연산으로 누적
 *  -> 문 열 때, 해당 문과 열쇠가 모두 1인지, & 연산 결과가 0이면 없는 것
 *
 * 고려한 시간 복잡도
 * 250 * 6 = 1500
 * */

public class BJ1194 { // 메모리 : 13472kb, 시간 : 96ms
    static int n;
    static int m;
    static char[][] map;
    static boolean[][][] isChecked;
    static Queue<int[]> queue;
    static int[][] delta = new int[][] {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    static int count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        isChecked = new boolean[n][m][64];
        queue = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            for(int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == '0') {
                    isChecked[i][j][0] = true;
                    queue.offer(new int[] {i, j, 0});
                }
            }
        }

        bfs();

        System.out.println(count);

    }

    private static void bfs() {

        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int i = 0; i < size; i++) {
                int[] loc = queue.poll();
                int r = loc[0];
                int c = loc[1];
                int z = loc[2];

                if(map[r][c] == '1') return;

                for(int d = 0; d < 4; d++) {
                    int nr = r + delta[d][0];
                    int nc = c + delta[d][1];

                    // 경계 벗어나는 경우
                    if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                    // 벽인 경우
                    if(map[nr][nc]== '#') continue;


                    if(isChecked[nr][nc][z]) continue;

                    // 열쇠의 경우
                    if(map[nr][nc] - 97 >= 0 && map[nr][nc] - 97 < 6) {
                        // 새로운 방문 배열
                        int keyZ = (z |1 << (map[nr][nc]-97));

                        isChecked[nr][nc][keyZ] = true;
                        queue.offer(new int[] {nr, nc, keyZ});
                    }

                    // 문의 경우
                    else if(map[nr][nc] - 65 >= 0 && map[nr][nc] - 65 < 6) {
                        // 열쇠가 있는지 확인, 현재 방과 열쇠가 둘 다 1인지?
                        int doorZ = (z & (1 << (map[nr][nc] - 65)));

                        if (doorZ == 0) continue;

                        isChecked[nr][nc][z] = true;
                        queue.offer(new int[] {nr, nc, z});
                    }

                    // 나머지
                    else {
                        isChecked[nr][nc][z] = true;
                        queue.offer(new int[] {nr, nc , z});
                    }
                }
            }
            count++;
        }

        count = -1;
        return;
    }
}
