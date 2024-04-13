package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 백준 14425 문자열 집합
 *
 * 조건
 * 집합 S의 크기 N : 1 ~ 10000
 * 검사할 문자열 M : 1 ~ 10000
 * 문자열의 길이 : 1 ~ 499
 * 집합 S의 문자는 중복되지 않음
 *
 * 문제에서 구하고자 하는 것
 * M개의 문자열 중 집합 S에 포함되어 있는 문자열의 개수
 *
 * 문제 해결 프로세스
 * set에 집합 s 만들기
 * 중복된 문자열이 있다면 cnt
 *
 * 고려한 시간 복잡도
 * 만약 일일이 다 검사하게 된다면 10000 * 10000 * 500 = 50,000,000,000
 * set으로 풀자
 * 20000
 * */

public class BJ14425 { // 메모리 : 38984kb, 시간 : 324ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int count = 0;
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<>();

        for(int i = 0; i < n; i++) {
            set.add(br.readLine());
        }

        for(int i = 0; i < m; i++) {
            if(set.contains(br.readLine())) count++;
        }

        System.out.println(count);
    }
}
