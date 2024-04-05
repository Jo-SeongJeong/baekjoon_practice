package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 11047 동전0
 *
 * 조건
 * 동전의 종류 N : 1 ~ 10
 * 목표 가치 K : 1 ~ 100000000
 * 각 동전의 가치 A : 1 ~ 100000000
 * 각 동전은 매우 많다
 *
 * 문제에서 구하고자 하는 것
 * 동전을 사용해 K원 만드는데 필요한 동전의 최소값
 *
 * 문제 해결 프로세스
 * 그리디
 * 동전을 배열로 받고, 배열 끝에서부터 가능한 금액이 나오면 차감하면서 ++하자
 *
 * 고려한 시간 복잡도
 * 100000000
 * */

public class BJ11047 { // 메모리 : 12020kb, 시간 : 124ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coin = new int[n];

        for(int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        int count = 0;
        while(k > 0) {
            for(int i = n-1; i >= 0; i--) {
                if(k >= coin[i]) {
                    k -= coin[i];
                    count++;
                    break;
                }
            }
        }

        System.out.println(count);
    }
}
