package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * SWEA 1767 프로세서 연결하기
 *
 * 조건
 * 맵(cell) N * N : 각 7 ~ 12
 * 코어의 개수 : 1 ~ 12
 * 전원이 연결되지 않는 core가 존재 가능
 * 1개의 cell에는 1개의 core나 전선 올 수 있음
 * 맵 밖에는 전원 흐르는 중
 * 전선(core - 전원): 직선으로만 설치 가능
 * 전선은 교차될 수 없음
 * 가장자리 위치한 코어는 이미 전원이 연결된 것으로 간주
 * 전원에 연결된 코어의 수가 최대인 것이 여러개이면, 전선 길이 합이 최소가 되는 값임
 * 0 : 빈 cell
 * 1 : core
 *
 * 문제에서 구하고자 하는 것
 * 전원에 연결된 코어의 수가 최대인 경우의 전선 길이의 합
 *
 * 문제 해결 프로세스
 * 입력받을 때, core인 좌표를 리스트에 저장
 * 리스트의 길이만큼, 4방향에 대한 중복 순열 생성
 * 생성되면 전선을 다 연결해봄
 * 연결이 된 core 개수 세기
 * 최대인 경우 전선 길이 비교해 낮은 전선 길이로 갱신
 *
 * 고려한 시간 복잡도
 * 4^12 * 4*12 = 805,306,368..? 가지 쳐보자 <- 최초 1번 전선 그려보고, 다음꺼 방향 바꾼다 했을 때 이미 최대값이 안된다면 그릴 이유 X
 * */

public class SW1767 { // 메모리 : 87632kb, 시간 : 538ms
    static int n;
    static int[][] map;
    static int[] picked;
    static List<int[]> core;
    static int count;
    static int link;
    static int max;
    static int len;
    static int answer;
    static int[][] delta = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static boolean[][] isChecked;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++) {
            count = 0;
            max = 0;
            answer = Integer.MAX_VALUE;

            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            core = new ArrayList<>();

            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());

                    if(map[i][j] == 1) {
                        if(i == 0 || i == n-1 || j == 0 || j == n-1) count++;
                        else core.add(new int[] {i, j});
                    }
                }
            }

            picked = new int[core.size()];

            dupPermu(0);

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    private static void dupPermu(int index) {
        if(index == core.size()) {
            isChecked = new boolean[n][n];
            link = count;
            len = 0;
            for(int i = 0; i < index; i++) {
                int[] idx = core.get(i);
                power(idx[0], idx[1], picked[i]);
            }

            if(max < link) {
                max = link;
                answer = len;
            }
            else if(max == link) {
                answer = Math.min(answer, len);
            }
            return;
        }

        for(int i = 0; i < 4; i++) {
            picked[index] = i;
            dupPermu(index+1);
            if(link + core.size() - index < max) {
                return;
            }
        }
    }

    private static void power(int r, int c, int d) {
        for(int i = 1; i <= n; i++) {
            int nr = r + delta[d][0] * i;
            int nc = c + delta[d][1] * i;

            if(map[nr][nc] == 1) break;
            if(isChecked[nr][nc]) break;

            isChecked[nr][nc] = true;

            if(nr == 0 || nr == n-1 || nc == 0 || nc == n-1) {
                link++;
                len += Math.max(Math.abs(nr - r), Math.abs(nc - c));
                break;
            }
        }
    }
}