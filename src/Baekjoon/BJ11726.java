package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 11726 2xN 타일링
 *
 * 조건
 * 입력받은 직사각형은 2 x N
 * 직사각형을 채울 수 있는 타일은 1x2, 2x1 타일
 *
 * 문제에서 구하고자 하는 것
 * 2가지 유형의 타일을 가지고 직사각형을 채울 수 있는 경우의 수 구하기
 * 해당 경우의 수 / 10007로 나눈 수
 *
 * 문제 해결 프로세스
 * 2 x 1 = 1개
 * 2 x 2 = 2개의 경우의 수가 존재
 * 2 x 3 = 3
 * 2 x 4 = 5
 *  -> 3부터는 이전 두개를 합친 값이 경우의 수가 됨
 *  이 수는 10007로 나누었을 때와 같은 값
 *  오버플로우 방지를 위해 계산할 때 10007로 나누어 값 저장
 *
 * 고려한 시간 복잡도
 * n
 *
 * */

public class BJ11726 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[] array = new long[n];

        for(int i = 0; i < n; i++) {
            if(i == 0 || i == 1)
                array[i] = i+1;
            else
                array[i] = (array[i-2] + array[i-1]) % 10007;
        }
        System.out.println(array[n-1]);
    }

}

// 메모리 : 11532kb, 시간 : 76ms
