package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1987 알파벳
 *
 * 조건
 * 세로 R, 가로 C, 각 1 ~ 20
 * 좌측 상단 칸 : 말
 * 나머지 : 대문자 알파벳
 * 말 : 상하좌우 한 칸 중 이동 가능 <- 지금까지 나온 알파벳과는 달라야 함
 *
 * 문제에서 구하고자 하는 것
 * 말이 지날 수 있는 최대 칸 수 출력
 *
 * 문제 해결 프로세스
 * 알파벳 대문자 check 배열 생성 (해당 문자 - 65)
 * 현재 칸 -> true
 * DFS -> 재귀로 cnt 보내면서 최대값 갱신 및 저장
 * 모두 true이거나 경계 밖이면 return
 *
 * 고려한 시간 복잡도
 * 4 * 20 * 20 = 1600 가능
 * */

public class BJ1987 { // 메모리 : 12300kb, 시간 : 916ms

    static int r;
    static int c;
    static char[][] map;
    static int max;
    static boolean[] isChecked;
    static int[][] delta = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        isChecked = new boolean[26];
        for(int i = 0; i < r; i++) {
            String str = br.readLine();
            for(int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        isChecked[map[0][0] - 65] = true;
        dfs(0, 0, 1);

        System.out.println(max);
    }

    private static void dfs(int row, int col, int count) {

        for(int i = 0; i < 4; i++) {
            int nr = row + delta[i][0];
            int nc = col + delta[i][1];

            if(nr >= 0 && nr < r && nc >= 0 && nc < c) {
                char next = map[nr][nc];
                if(!isChecked[next - 65]) {
                    isChecked[next - 65] = true;
                    dfs(nr, nc, count+1);
                    isChecked[next - 65] = false;
                }
            }
        }

        if(count > max) {
            max = count;
        }

        return;
    }
}
