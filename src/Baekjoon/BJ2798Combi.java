package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 2798 블랙잭
 *
 * 조건
 * N개의 카드 중 3장을 뽑음
 * 3장의 합이 <= M 중 가장 큰 값인 것 출력
 * N : 3 ~ 100, M : 10 ~ 300000
 * 각 카드 값 : 1 ~ 100000
 *
 * 문제에서 구하고자 하는 것
 * N개의 카드 중 3장을 뽑아 해당 카드의 숫자를 합했을 때 M이 넘지 않으며, 가장 큰 값을 출력
 *
 * 문제 해결 프로세스
 * N, M 입력
 * 3장을 뽑는 조합을 통해 최대값 갱신하기(M을 넘지 않는 경우)
 *
 * 시간 복잡도
 * N * (N-1) * (N-2) -> 100 * 99 * 98 대충 1000000 < 시간제한 1초
 * */

public class BJ2798Combi { // 메모리 : 11520kb, 시간 : 80ms
    static int N;
    static int M;
    static int max;
    static int[] array;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        array = new int[N];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        max= 0;
        combi(0, 0, 0);
        System.out.println(max);
    }

    private static void combi(int index, int start, int sum) {
        if(sum > M)
            return;

        if(index == 3) {
            max = Math.max(max, sum);
            return;
        }

        for(int i = start; i < N; i++) {
            combi(index+1, i+1, sum+array[i]);
        }
    }
}
