package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1018 체스판 다시 칠하기
 *
 * 조건
 * 보드 크기 N x M : 각 1 ~ 50
 * 각 칸은 검정색, 흰색으로만 칠해져 있음
 * 보드를 8 x 8로 자르기
 * 각 칸의 색은 번갈아가며 잘라야함
 * 기준은 왼쪽 위
 * 문제에서 구하고자 하는 것
 * 보드에서 8x8을 골랐을 때 바꿔야 하는 칸의 색에 대한 최소값
 *
 * 문제 해결 프로세스
 * 시작점 기준 체스판을 2개 만든다
 * 시작점부터 끝까지 for문을 돌린다
 * 만약 자른 판의 길이가 8칸이 안되면 아닌 것
 * 8 x 8이 된다면 각 체스판이랑 비교해보자
 * 다른 값이 있으면 해당 chess판 cnt
 * cnt 중 작은 게 바꿀 칸의 개수
 * 최소값 갱신
 *
 * 고려한 시간 복잡도
 *
 * 50 * 50 * 8 * 8 = 160,000
 * */

public class BJ1018 {
    static int n;
    static int m;
    static char[][] map;
    static char[][] wChess;
    static char[][] bChess;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];

        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            for(int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        wChess = new char[8][8];
        bChess = new char[8][8];

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(i % 2 == 0) {
                    if(j % 2 == 0) {
                        wChess[i][j] = 'W';
                        bChess[i][j] = 'B';
                    }
                    else {
                        wChess[i][j] = 'B';
                        bChess[i][j] = 'W';
                    }
                }
                else {
                    if(j % 2 == 0) {
                        wChess[i][j] = 'B';
                        bChess[i][j] = 'W';
                    }
                    else {
                        wChess[i][j] = 'W';
                        bChess[i][j] = 'B';
                    }
                }
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                chess(i, j);
            }
        }

        System.out.println(min);
    }

    private static void chess(int row, int col) {
        if(n - row < 8 || m - col < 8) return;

        int wCount = 0;
        int bCount = 0;

        int rIdx = 0;
        for(int i = row; i < row + 8; i++) {
            int cIdx = 0;
            for(int j = col; j < col + 8; j++) {
                if(map[i][j] != wChess[rIdx][cIdx]) wCount++;
                if(map[i][j] != bChess[rIdx][cIdx]) bCount++;
                cIdx++;
            }
            rIdx++;
        }

        int temp = Math.min(wCount, bCount);
        min = Math.min(min, temp);
    }
}
