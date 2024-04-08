package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 11053 가장 긴 증가하는 부분 수열
 *
 * 조건
 * 수열의 크기 N : 1 ~ 1000
 * 수열을 이루고 있는 수의 범위 : 1 ~ 1000
 *
 * 문제에서 구하고자 하는 것
 * 가장 긴 증가하는 부분 수열의 길이
 *
 * 문제 해결 프로세스
 * dp
 * dp의 인덱스 번호는 수열의 숫자 -> i
 * i보다 큰 원소를 찾자
 * aj < ai인 aj 찾기
 * => 현재 최장 길이 보다 길다면 갱신
 *
 * 고려한 시간 복잡도
 * 1000000
 * */

public class BJ11053 { // 메모리 : 11900kb, 시간 : 96ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int max = 0;
        int[] array = new int[n+1];
        int[] lis = new int[n+1];

        st  = new StringTokenizer(br.readLine());

        for(int i = 1; i < n+1; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i < n+1; i++) {
            lis[i] = 1;
            for(int j = 1; j < i; j++) {
                if(array[j] < array[i] && lis[i] < lis[j]+1) lis[i] = lis[j]+1;
            }

            max = Math.max(max, lis[i]);
        }

        System.out.println(max);
    }
}
