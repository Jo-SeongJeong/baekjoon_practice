package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 2931 가스관
 *
 * 조건
 * 맵의 크기 R * C : 각 1 ~ 25
 * . : 빈칸
 * M : 모스크바
 * Z : 자그레브
 * 가스관의 모양
 * | : 수직
 * - : 수평
 * + : 사방향
 * 1 : ┌
 * 2 : ㄴ
 * 3 : ┘
 * 4 : ㄱ
 * 가스는 블록을 통해 양방향으로 흐름
 * +는 수직 수평으로 흘러야 함
 * 모스크바와 자그레브는 각각 하나의 블록과 인접해 있음(입력)
 *
 * 문제에서 구하고자 하는 것
 * 빈칸이 원래 어떤 칸이었는지  -> 어떤 블록을 넣어야 가스가 흐르는지
 *
 * 문제 해결 프로세스
 * 시작점에 대해 연결된 파이프로 방향을 정하자
 * 1, 2, 3, 4번 파이프를 만나면 방향을 바꿔주자
 * 빈칸을 만나면 해당 위치 기준 사방탐색으로 연결되어야 할 배관을 맞추자
 * 이 때 위치와 연결된 배관을 결정
 * 종료
 *
 * 고려한 시간 복잡도
 * 25*25*2 = 1250
 * */

public class BJ2931 { // 메모리 : 11496kb, 시간 : 80ms
    static int n;
    static int m;
    static char[][] map;
    static int[][] delta = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상 하 좌 우
    static int[] dir = new int[2];
    static int rsR;
    static int rsC;
    static char block;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];

        int sr = 0;
        int sc = 0;

        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            for(int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);

                if(map[i][j] == 'M') {
                    sr = i;
                    sc = j;
                }
            }
        }
        for(int d = 0; d < 4; d++) {
            int nr = sr+ delta[d][0];
            int nc = sc+ delta[d][1];
            if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
            if(map[nr][nc] == '.') continue;
            if(map[nr][nc] == 'Z') continue;

            // 현재 나의 방향 설정
            dir[0] = delta[d][0];
            dir[1] = delta[d][1];
            break;
        }

        findZ(sr, sc);

        System.out.println(rsR + " " + rsC + " " + block);
    }

    private static void findZ(int sr, int sc) {
        boolean flag = false;
        int nr = sr + dir[0];
        int nc = sc + dir[1];

        if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
            switch (map[nr][nc]) {
                case '1':
                    if (dir[0] == 0 && dir[1] == -1) {
                        dir[0] = 1;
                        dir[1] = 0;
                    } else {
                        dir[0] = 0;
                        dir[1] = 1;
                    }

                    break;
                case '2':
                    if (dir[0] == 1 && dir[1] == 0) {
                        dir[0] = 0;
                        dir[1] = 1;
                    } else {
                        dir[0] = -1;
                        dir[1] = 0;
                    }
                    break;
                case '3':
                    if (dir[0] == 0 && dir[1] == 1) {
                        dir[0] = -1;
                        dir[1] = 0;
                    } else {
                        dir[0] = 0;
                        dir[1] = -1;
                    }
                    break;
                case '4':
                    if (dir[0] == -1 && dir[1] == 0) {
                        dir[0] = 0;
                        dir[1] = -1;
                    } else {
                        dir[0] = 1;
                        dir[1] = 0;
                    }
                    break;
                case '.':
                    rsR = nr + 1;
                    rsC = nc + 1;
                    pipe(nr, nc);
                    map[nr][nc] = block;
                    flag = true;
                    break;
                case 'Z':
                    return;
            }

            if (flag) findZ(sr, sc);
            else findZ(nr, nc);
        }

    }

    private static void pipe(int r, int c) {
        boolean[] around = new boolean[4];

        for (int d = 0; d < 4; d++) {
            int nr = r + delta[d][0];
            int nc = c + delta[d][1];

            if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;

            switch (map[nr][nc]) {
                case '+':
                    around[d] = true;
                    break;
                case '|':
                    if(d== 0 || d == 1) around[d] = true;
                    break;
                case '-':
                    if(d== 2 || d == 3) around[d] = true;
                    break;
                case '1':
                    if(d == 0 || d == 2) around[d] = true;
                    break;
                case '2':
                    if (d == 1 || d == 2) around[d] = true;

                    break;
                case '3':
                    if (d == 1 || d == 3) around[d] = true;
                    break;
                case '4':
                    if (d == 0 || d == 3) around[d] = true;
                    break;
            }
        }

        if (around[0] && around[1] && around[2] && around[3]) block = '+';
        else if (around[0] && around[1]) block = '|';
        else if (around[2] && around[3]) block = '-';
        else if (around[1] && around[3]) block = '1';
        else if (around[0] && around[3]) block = '2';
        else if (around[0] && around[2]) block = '3';
        else if (around[1] && around[2]) block = '4';
    }
}