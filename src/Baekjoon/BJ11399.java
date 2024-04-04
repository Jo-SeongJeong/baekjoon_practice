package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 11399 ATM
 *
 * 조건
 * 사람 수 N(1부터 N) : 1 ~ 1000
 * 각 사람이 인출하는 데 걸리는 시간 Pi : 1 ~ 1000
 * 모든 사람이 인출하는 데 시간 : 각각 앞 사람이 걸리는 시간 누적 + 본인 시간으로 누적 합
 *
 * 문제에서 구하고자 하는 것
 * 모든 사람이 인출하는 데 걸리는 시간의 최소값
 *
 * 문제 해결 프로세스
 * 오름차순 정렬 후 누적 시간의 합을 구하면 될 듯
 * 한 사람의 시간이 오래 걸릴 수록 계속 시간이 증가할 것
 *
 * 고려한 시간 복잡도
 * 1000
 *
 * */

public class BJ11399 { // 메모리 : 11820kb, 시간 : 88ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int time = 0;
        int sum = 0;
        int n  = Integer.parseInt(br.readLine());

        int[] people = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < n+1; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(people);

        for(int i = 1; i < n+1; i++) {
            time += people[i];
            sum += time;
        }

        System.out.println(sum);
    }
}
