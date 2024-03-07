package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 1247 최적 경로
 *
 * 조건
 * 위치 좌표 x, y(0 ~ 100)
 * 두 위치 사이의 거리 : |x1-x2| + |y1 - y2|
 * 주어진 좌표를 모두 방문해야 함
 * 고객 수 n : 2~10
 *  -> 총 좌표 수 최대 -> n+2
 *
 * 문제에서 구하고자 하는 것
 * 회사 좌표(시작) ->(고객 N) -> 집 좌표(도착)의 최단 거리 구하기
 *
 * 문제 해결 프로세스
 * 시작 좌표, 끝좌표 고정
 * 고객 좌표 순열로 구하기
 * 시작 좌표와 끝 좌표를 양 사이드에 넣고 거리 계산해서 최단 거리 출력
 *
 * 고려한 시간 복잡도
 * N! + (N+2)*N/2 -> 10! + 12*6 -> 3628800 + 72 -> 많이 잡아도 4백만 -> 1초연산도 안됨
 *
 * */

public class SW1247 { // 메모리 : 21204kb, 시간 : 1999ms
    static StringTokenizer st;
    static int n;
    static boolean[] isChecked;
    static int[][] input;
    static int[][] picked;
    static int min;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        for(int t = 1; t <= testCase; t++) {
            n = Integer.parseInt(br.readLine()) + 2;
            input = new int[n][2];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < 2; j++) {
                    input[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            picked = new int[n][2];
            isChecked = new boolean[n];
            min = Integer.MAX_VALUE;
            permu(1);

            System.out.println("#" + t + " " + min);
        }
    }

    private static void permu(int index) {
        if(index == n-1) {
            picked[0][0] = input[0][0];
            picked[0][1] = input[0][1];
            picked[n-1][0] = input[1][0];
            picked[n-1][1] = input[1][1];

            int distance = 0;
            for(int i = 0; i < n-1; i++) {
                distance += Math.abs((picked[i][0] - picked[i+1][0])) + Math.abs((picked[i][1] - picked[i+1][1]));
            }
            min = Math.min(min, distance);
            return ;
        }

        for(int i = 2; i < n; i++) {
            if(isChecked[i]) continue;

            picked[index][0] = input[i][0];
            picked[index][1] = input[i][1];
            isChecked[i] = true;

            permu(index+1);

            isChecked[i] = false;
        }
    }
}
