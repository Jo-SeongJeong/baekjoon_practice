package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 2178 미로 탐색
 *
 * 조건
 * N x M 배열 : 각 2 ~ 100
 * 1 : 이동할 수 있는 칸
 * 0 : 이동할 수 없는 칸
 * 칸을 셀 때, 시작 위치와 도착 위치 포함
 *
 * 문제에서 구하고자 하는 것
 * 1,1에서 출발하여 n,m의 위치로 도착할 때까지 지나야 하는 최소 칸 수 구하기
 *
 * 문제 해결 프로세스
 * 1,1 위치부터 n,m까지가는 경로를 탐색한다
 * 거리 변수는 1로 초기화 한다
 * bfs로 queue를 이용해 탐색한다
 * queue에 담겨있는 정보는 map의 행, 열, 간선(경로)이다
 * 탐색한 칸 은 방문 처리한다 또한 해당 행, 열, 간선+1을 저장한다
 * 도착지를 발견한다면 간선 수를 return하여 답을 구한다
 * 혹시 뒤에 방문된 칸을 가더라도 최소 거리는 아니므로 고려하지 않아도 된다
 *
 * 고려한 시간 복잡도
 * 100 * 100 * 4 = 40000
 *
 * */
public class BJ2178 { // 메모리 : 12108kb, 시간 : 88ms
    static int n;
    static int m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];

        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            for(int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        int answer = bfs(map);
        System.out.println(answer);

    }

    private static int bfs(int[][] map) {
        int answer = 0;
        boolean[][] isChecked = new boolean[n][m];
        int[][] delta = new int[][] {{0,-1}, {0, 1}, {-1, 0}, {1, 0}};
        Queue<int[]> queue = new ArrayDeque<>();

        int[] loc = new int[3];
        loc[0] = 0;
        loc[1] = 0;
        loc[2] = 1;

        queue.offer(loc);

        isChecked[0][0] = true;

        while(!queue.isEmpty()) {
            int[] temp = queue.poll();
            int r = temp[0];
            int c = temp[1];

            if(r == n-1 && c == m-1)  {
                answer = temp[2];
                return answer;
            }

            for(int i = 0; i < 4; i++) {
                int nr = r +delta[i][0];
                int nc = c + delta[i][1];

                if(nr >= 0 && nr < n && nc >= 0 && nc < m && !isChecked[nr][nc] && map[nr][nc] == 1) {
                    int[] visit = new int[3];
                    visit[0] = nr;
                    visit[1] = nc;
                    visit[2] = temp[2]+1;
                    isChecked[nr][nc] = true;
                    queue.offer(visit);
                }

            }
        }

        return 0;

    }
}
