package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백분 11660 구간 합 구하기5
 * 조건
 * N = 2차원 배열 크기
 * M = 합을 구하는 횟수
 * x1, y1 = 합을 구할 시작 인덱스 값 (x는 행, y는 열)
 * x2, y2 = 합을 구할 끝 인덱스 값
 *
 * 문제에서 구하고자 하는 것
 * 입력된 범위 내의 누적 합을 출력
 *
 * 문제 해결 프로세스
 * N과 M을 입력 받는다
 * 누적 합이 적용된 수를 담을 배열 (N * N)을 생성한다
 * N*N 만큼 수를 입력 받아 행의 누적합을 적용해 배열 인덱스에 저장한다
 * (반복 시작)
 * 시작 끝 인덱스값을 입력 받아 해당 범위 합 구하기
 * -> (x2, y2 - x2, y1-1) +... +(x1, y2 - x1, y1-1) -> ...은 x1 ~ x2까지 반복
 * 출력(M번 반복)
 *
 * 고려한 시간 복잡도
 * 1024 * 1024 + 100000 + 100000
 * */

public class BJ11660 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] cummul = new int[n][n+1];
        for(int  i = 0; i < n; i++) {
            cummul[i][0] = 0;
        }

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < n+1; j++) {
                cummul[i][j] = cummul[i][j-1] + Integer.parseInt(st.nextToken());
            }
        }

        int[] sum = new int[m];

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken());

            for(int j = x1; j <= x2; j++) {
                sum[i] += cummul[j][y2] - cummul[j][y1-1];
            }
        }

        for(int i = 0; i < m; i++) {
            System.out.println(sum[i]);
        }

    }

}

// 메모리 : 138280kb, 시간 : 2572ms
