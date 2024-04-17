package SWEA;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

/**
 * SWEA 1949 등산로 조성
 *
 * 조건
 * 부지 크기 N * N : 3 ~ 8
 * 배열 안 값 = 지형의 높이 : 1 ~ 20
 * 등산로를 만드는 규칙
 * 1. 등산로는 가장 높은 봉우리에서 시작해야 함 -> 가장 높은 봉우리는 최대 5개
 * 2. 등산로는 산으로 올라갈 수 있도록, 반드시 높은 지형에서 낮은지형으로 가로 또는 세로 방향으로 연결되어야 함
 *  -> 높이가 같은 곳, 낮은 지형, 대각선 방향은 연결 불가
 * 3. 긴 등산로를 만들기 위해 딱 한 곳을 정해서 최대 k깊이 만큼 지형을 깎을 수 있음
 * 최대 공사가능 깊이 K : 1 ~ 5
 * 지형은 정수 단위로만 깎을 수 있음
 * 필요한 경우 지형을 1보다 작게 만들 수도 있음
 *
 * 문제에서 구하고자 하는 것
 * 최대 길이의 등산로
 *
 * 문제 해결 프로세스
 * 입력 시, 최대 높이 봉우리인 좌표를 list로 담는다 -> 가능한 시작점
 * 해당 시작점 기준으로 등산로를 만들어보자
 * 등산로를 만들 때, 0 ~ k까지 한 곳을 깎으면서 가보자
 * 깎는 조건 : 어차피 낮으면 깎을 이유가 없기 때문에, 높은 곳을 깎아보며 가보자
 * 이 때 해당 봉우리 기준 제일 긴 등산로로 갱신
 * 최대값과 갱신된 값 중 최대값으로 갱신
 *
 * 고려한 시간 복잡도
 * 64 * 4 * 5 = 1280
 *
 * */

public class SW1949 { // 메모리 : 21088kb, 시간 : 110ms
    static int n;
    static int k;
    static int[][] map;
    static boolean[][] isChecked;
    static List<int[]> list;
    static int load;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++) {
            sb.append("#" + t + " ");

            int max = 0;
            int result = 0;
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            map = new int[n][n];
            list = new ArrayList<>();

            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());

                    if(max < map[i][j]) {
                        max = map[i][j];
                        list = new ArrayList<>();
                        list.add(new int[] {i, j});
                    }
                    else if(max == map[i][j]) list.add(new int[] {i, j});
                }
            }


            for(int[] idx : list) {
                load = 1;
                isChecked = new boolean[n][n];
                isChecked[idx[0]][idx[1]] = true;
                dfs(idx[0], idx[1], 1, 0);
                isChecked[idx[0]][idx[1]] = false;

                if(result < load) result = load;
            }

            sb.append(result + "\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int sr, int sc, int len, int count) {
        if(load < len) load = len;

        int[][] delta = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        for(int d = 0; d < 4; d++) {
            int nr = sr +delta[d][0];
            int nc = sc + delta[d][1];

            if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
            if(isChecked[nr][nc]) continue;

            if(map[sr][sc] > map[nr][nc]) {
                isChecked[nr][nc] = true;

                dfs(nr, nc , len+1, count);

                isChecked[nr][nc] = false;
            }

            else {
                if(count != 0) continue;

                for(int i = 1; i <= k; i++) {
                    if(map[sr][sc] > map[nr][nc] - i) {
                        map[nr][nc] -= i;
                        isChecked[nr][nc] = true;

                        dfs(nr, nc, len+1, count+1);

                        map[nr][nc] += i;
                        isChecked[nr][nc] = false;

                        break;
                    }
                }
            }
        }


    }
}
