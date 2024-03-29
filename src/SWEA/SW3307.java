package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 3307 최장 증가 수열
 *
 * 조건
 * 최장 증가 부분 수열의 길이 : 원본 수열의 부분 집합 중 오름차순으로 정렬된 부분 집합의 최대 길이
 * 수열의 길이 N : 1 ~ 1000
 * 원소의 크기 : 1 ~ 2^31 -1 -> int
 *
 * 문제에서 구하고자 하는 것
 * 최장 부분 증가 수열의 길이
 *
 * 문제 해결 프로세스
 * dp로 풀자
 * 원소 개수만큼 반복하며, 현재 원소에 길이를 늘릴 수 있는지 확인하면 됨
 *
 * 고려한 시간 복잡도
 * 1000* 1000 * 10 = 10000000 -> 이진 안해도 됨!
 * */

public class SW3307 { // 메모리 : 38376kb, 시간 : 145ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++) {
            int n = Integer.parseInt(br.readLine());

            int[] array = new int[n];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                array[i] = Integer.parseInt(st.nextToken());
            }

            int[] lis = new int[n];

            for(int i = 0; i < n; i++) {
                lis[i] = 1;
                for(int j = 0; j < i; j++) {
                    if(array[j] < array[i] && lis[i] < lis[j]+1) {
                        lis[i] = lis[j]+1;
                    }
                }
            }

            int max = 0;
            for(int i : lis) {
                max = Math.max(max, i);
            }

            sb.append("#").append(t+ " ").append(max + "\n");
        }
        System.out.println(sb);
    }
}