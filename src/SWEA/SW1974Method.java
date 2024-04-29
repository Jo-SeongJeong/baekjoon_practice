package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 1974 스도쿠 검증
 *
 * 조건
 * 9*9 배열 -> 스도쿠
 * 가로, 세로, 3*3 에서 1 ~ 9까지 숫자가 겹치지 않아야 함
 *  -> 합 = 45, 곱 = 362880 인 것으로 검증
 *
 * 문제에서 구하고자 하는 것
 * 스도쿠가 유효하면 1(검증 되면), 겹치는 숫자가 있는 경우 0 출력
 *
 * 문제 해결 프로세스
 * 9*9 배열 입력 받는다
 * 가로, 세로, 3*3 격자 검증을 한다
 * 검증 결과에 따라 출력한다
 *
 * 고려한 시간 복잡도
 * 9 * 9 * 9 -> 완탐~!
 * */

public class SW1974Method { // 메모리 : 19532kb, 시간 : 106ms
    static int [][] sudoku;
    static int answer;
    static final int sum = 45;
    static final int mul = 362880;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++) {
            sudoku = new int[9][9];

            for(int i = 0; i < 9; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 9; j++) {
                    sudoku[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            answer = 1;

            row();

            if(answer == 1) col();

            if(answer == 1) mini();

            System.out.println("#" + t + " " + answer);
        }
    }

    private static void row() {
        for(int i = 0; i < 9; i++) {
            int tempSum = 0;
            int tempMul = 1;

            for(int j = 0; j < 9; j++) {
                tempSum += sudoku[i][j];
                tempMul *= sudoku[i][j];
            }

            if(tempSum != sum || tempMul != mul) {
                answer = 0;
                return;
            }
        }
    }

    private static void col() {
        for(int i = 0; i < 9; i++) {
            int tempSum = 0;
            int tempMul = 1;

            for(int j = 0; j < 9; j++) {
                tempSum += sudoku[j][i];
                tempMul *= sudoku[j][i];
            }

            if(tempSum != sum || tempMul != mul) {
                answer = 0;
                return;
            }
        }
    }

    private static void mini() {

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {

                if(!(i % 3 == 0 && j % 3 == 0)) continue;

                int tempSum = 0;
                int tempMul = 1;
                for(int k = i; k < i+3; k++) {
                    for(int l = j; l < j+3; l++) {
                        tempSum += sudoku[k][l];
                        tempMul *= sudoku[k][l];
                    }
                }
                if(tempSum != sum || tempMul != mul) {
                    answer = 0;
                    return;
                }

            }
        }
    }
}
