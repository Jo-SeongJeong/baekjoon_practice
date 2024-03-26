package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 1992 쿼드트리
 *
 * 조건
 * 흰점 : 0
 * 검은점 : 1
 * 모두 0 -> 0
 * 모두 1 -> 1
 * 그외 -> 4칸이 될때까지 분할, 4칸이면 2-1-4-3사분면 순으로 0/1 나타냄
 * 영상 크기 N : 1 ~ 64 (2의 제곱수)
 * 압축은 ()로 표현
 *
 * 문제에서 구하고자 하는 것
 * 조건의 과정을 모두 거친 결과 숫자
 *
 * 문제 해결 프로세스
 * 배열을 n/2씩 나누며 모두 같은 숫자가 되거나, 2x2가 되었을 때 압축을 진행하며 구한다
 *
 * 고려한 시간 복잡도
 * 64 * 64 * 6 -> 24576 가능
 *
 * */

public class BJ1992 { // 메모리 : 13244kb, 시간 : 88ms
    static int n;
    static int[][] array;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        n = Integer.parseInt(br.readLine());

        array = new int[n][n];

        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            for(int j = 0; j < n; j++) {
                array[i][j] = str.charAt(j) - '0';
            }
        }

        compress(n, 0, 0);

        System.out.println(sb);
    }


    private static void compress(int n, int r, int c) {


        int[][] temp = new int[n][n];
        int row = 0;
        for(int i = r; i < r+n; i++) {
            int col= 0;
            for(int j = c; j < c+n; j++) {
                temp[row][col] = array[i][j];
                col++;
            }
            row++;
        }

        int result = temp[0][0];

        if(n==1) {
            sb.append(result);
            return;
        }


        boolean flag = false;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(result == temp[i][j]) continue;
                flag = true;
            }
        }

        if(flag) {
            sb.append("(");
            compress(n/2, r, c);
            compress(n/2, r, c+n/2);
            compress(n/2, r+n/2, c);
            compress(n/2, r+n/2, c+n/2);
            sb.append(")");
        }
        else {
            sb.append(result);
        }
        return;

    }
}