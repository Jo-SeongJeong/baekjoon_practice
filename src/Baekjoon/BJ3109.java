package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 3109 빵집
 *
 * 조건
 * 빵집 위치 : RxC (R : 1 ~ 10,000 / C : 5 ~ 500)
 * 첫번째 열 : 근처 빵집
 * 마지막 열 : 원웅 빵집
 * 둘을 연결하는 파이프는 첫번째 열 시작, 마지막 열 끝나야함
 * 각 칸은 오른쪽, 오른쪽 위 대각, 오른쪽 아래 대각으로 연결 가능
 * 건물이 있는 경우 파이프를 놓지 못 함
 * . : 빈칸
 * x : 건물
 *
 * 문제에서 구하고자 하는 것
 * 빵집 간 연결된 파이프가 놓일 수 있는 최대 개수
 *
 * 문제 해결 프로세스
 * 열 우선 탐색을 진행한다
 * 델타를 하나 만든다 우, 우상, 우하
 * 인덱스 번호 1부터 n-2까지 탐색을 진행
 * 연결 가능하면 끝까지 연결
 * 탐색 진행해서 완성 시키고 끝까지 탐색
 *
 * 고려한 시간 복잡도
 * 10000 * 498 * 3
 * */

public class BJ3109 { // 메모리 : 41916kb, 시간 : 336ms
    static int r;
    static int c;
    static int count;
    static boolean installPipe;
    static boolean[][] isChecked;
    static char[][] map;
    static int[] delta = {-1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        isChecked = new boolean[r][c];
        map = new char[r][c];

        for(int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'x') isChecked[i][j] = true;
            }
        }

        for(int i = 0; i < r; i++) {
            installPipe = false;
            pipe(i ,0);
        }

        System.out.println(count);
    }

    private static void pipe(int row, int col) {
        if(col == c-1) {
            count++;
            installPipe = true;
            return;
        }

        for(int i = 0; i < 3; i++) {
            int nr = row + delta[i];
            int nc = col + 1;

            if(installPipe) return;

            if(nr >= 0 && nr < r && !isChecked[nr][nc]) {
                isChecked[nr][nc] = true;
                pipe(nr, nc);

            }
        }
    }
}