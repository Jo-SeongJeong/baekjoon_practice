package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 17136 색종이 붙이기
 *
 * 조건
 * 색종이 종류 : 5개, 1x1 ~ 5X5
 * 각 종류마다 5개씩 있음
 * 종이 크기 : 10 * 10
 * 1 : 색종이로 덮여야하는 공간
 * 0 : 색종이가 있으면 안되는 공간
 * 색종이를 붙일 때는 경계 밖으로 나가서도, 겹쳐도 안됨
 *
 * 문제에서 구하고자 하는 것
 * 1이 적힌 모든 칸을 붙이는데 필요한 색종이의 최소 개수
 *
 * 문제 해결 프로세스
 * 각 색종이 개수를 관리 할 배열을 만들어야 함
 * 1을 만나면 1부터 5까지 차례로색종이를 붙여보자
 * 불일 수 있는 조건
 *  1. 경계를 벗어나지 않아야 함
 *  2. 시작점부터 색종이 크기까지 모두 1이어야 함
 * 5x5까지 계속 붙이면서 가자
 * r == n이 되면 최소값 갱신해주면서 모든 과정 끝나면 최소값이 나올 것 -> 완탐
 * 수 세보기
 *
 * 고려한 시간 복잡도
 * 100*25*25*16*9*4*5(5개) = 180,000,000
 * */

public class BJ17136 { // 메모리 : 23768kb, 시간 : 260ms
    static int n = 10;
    static int[][] map;
    static int[] paper;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[n][n];
        paper = new int[6];
        Arrays.fill(paper, 5);

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);
        if(min == Integer.MAX_VALUE) min = -1;

        System.out.println(min);
    }
    private static void dfs(int r, int c, int count) {
        if(r == n) {
            min = Math.min(min, count);
            return;
        }

        if(map[r][c] == 1) {
            for(int i = 1; i < 6; i++) {
                if(r+i > n || c+i > n) continue;
                if(paper[i] < 1) continue;
                if(!possible(r, c, i)) continue;

                paper[i]--;
                //색종이 붙이기
                putPaper(r, c, i);
                //탐색 시작
                if(c+1 < n) dfs(r, c+1, count+1);
                else dfs(r+1, 0, count+1);
                paper[i]++;
                //색종이 떼기
                removePaper(r, c, i);
            }
        }
        else {
            if(c+1 < n) dfs(r, c+1, count);
            else dfs(r+1, 0, count);
        }
    }

    private static boolean possible(int r ,int c, int len) {
        for(int i  = r; i < r+len; i++) {
            for(int j = c; j < c+len; j++) {
                if(map[i][j] == 0) return false;
            }
        }

        return true;
    }

    private static void putPaper(int r, int c, int len) {
        for(int i  = r; i < r+len; i++) {
            for(int j = c; j < c+len; j++) {
                map[i][j] = 0;
            }
        }
    }

    private static void removePaper(int r, int c, int len) {
        for(int i  = r; i < r+len; i++) {
            for(int j = c; j < c+len; j++) {
                map[i][j] = 1;
            }
        }
    }
}