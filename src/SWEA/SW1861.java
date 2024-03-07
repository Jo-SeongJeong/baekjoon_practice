package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 1861 정사각형 방
 * <p>
 * 조건
 * 방의 개수: N x N (1~1000000)
 * 배열의 값은 모두 서로 다른 수
 * 방 이동 조건 : 4방향, 본인보다 +1 값인 경우만 이동 가능
 * 각 방의 값: 1 ~ 100000
 * 만약 방문 횟수가 같은 경우 더 작은 값으로 출력
 * <p>
 * 문제에서 구하고자 하는 것
 * 모든 방 중 가장 이동을 많이할 수 있는 방의 번호와 방문 횟수
 * <p>
 * 문제 해결 프로세스
 * 4방 탐색(상하좌우)
 * +1인 경우 다음으로 넘어가서 4방 탐색 반복 <- 모든 수는 서로 다르기 떄문에 한 방향으로 탐색이 가능해지면 남은 방향은 탐색할 필요 x
 * 다음으로 넘어갈 떄마다 cnt++
 * 탐색 할 방이 없으면 return해서 처음으로 돌아감
 * 해당 방향 제외 -1인 경우로 4방 탐색해서 cnt++, 있으면 시작 값도 갱신
 * -1 탐색이 끝나면 해당 길은 모두 isChecked 표시, 다음부터는 방문x
 * 최대값 갱신하며 반복해 정답 출력
 * <p>
 * 고려한 시간 복잡도
 * 1000 * 1000
 */

public class SW1861 { // 메모리 : 29200kb, 시간 : 161ms
    static int[][] rooms;
    static boolean[][] isChecked;
    static int n;
    static int count;
    static int number;
    static int maxCount;
    static int minNumber;
    static int current;
    static int[][] delta = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // 좌 우 상 하

    public static void main(String[] args) throws Exception { // 메모리 : 29268kb, 실행시간 : 161ms
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int t = 1; t <= testCase; t++) {
            n = Integer.parseInt(br.readLine());
            rooms = new int[n][n];
            isChecked = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    rooms[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            maxCount = 0;
            minNumber = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (isChecked[i][j]) continue;
                    current = rooms[i][j];
                    count = 1;
                    visit(i, j);
                    backVisit(i, j);

                    if(maxCount < count) {
                        maxCount = count;
                        minNumber = current;
                    }

                    else if(maxCount == count) {
                        if(minNumber > current) minNumber = current;
                    }
                }
            }
            System.out.println("#" + t + " " + minNumber + " " + maxCount);
        }
    }

    private static void visit(int r, int c) {
        boolean flag = false;

        for (int i = 0; i < 4; i++) {
            if(flag) continue;

            int nr = r + delta[i][0];
            int nc = c + delta[i][1];

            if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                if (rooms[nr][nc] - rooms[r][c] == 1 && isChecked[nr][nc] == false) {
                    count++;
                    isChecked[nr][nc] = true;
                    visit(nr, nc);
                    flag = true;
                }
            }
        }
        return ;
    }

    private static void backVisit(int r, int c) {
        boolean flag = false;
        number = rooms[r][c];

        for (int i = 0; i < 4; i++) {
            if(flag) continue;

            int nr = r + delta[i][0];
            int nc = c + delta[i][1];

            if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                if (rooms[nr][nc] - rooms[r][c] == -1 && isChecked[nr][nc] == false) {
                    count++;
                    isChecked[nr][nc] = true;
                    backVisit(nr, nc);
                    flag = true;
                }

            }
        }

        if(current > number) current = number;
        return ;
    }
}
