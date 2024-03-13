package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 백준 1181 단어 정렬
 *
 * 조건
 * 단어 개수 N : 1 ~ 20000
 * 단어 종류 : 알파벳 소문자로 이루어짐
 * 문자열의 길이 : < 50
 * 정렬 조건
 * 1. 길이가 짧은 것
 * 2. 길이가 같으면 사전 순
 * 3. 중복 단어는 제거
 *
 * 문제에서 구하고자 하는 것
 * 정렬 및 중복 제거한 문자열 순서
 *
 * 문제 해결 프로세스
 * 람다식을 통한 정렬
 * 중복제거
 * 출력
 *
 * 고려한 시간 복잡도
 * 20000log20000 + 20000 = 100000 정도
 * */

public class BJ1181 { // 메모리 : 33200kb, 시간 : 428ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        String[] array = new String[n];

        for(int i = 0; i < n; i++) {
            array[i] = br.readLine();
        }

        Arrays.sort(array, (a, b) -> {
            if(a.length() == b.length()) {
                return a.compareTo(b);
            }
            return a.length() - b.length();
        });

        for(int i = n-1; i >= 0; i--) {
            if(array[i].equals(".")) continue;
            for(int j = i-1; j >= 0; j--) {
                if(array[i].equals(array[j]))
                    array[j] = ".";
                else break;
            }

        }

        for(String str : array) {
            if(str.equals(".")) continue;
            sb.append(str).append("\n");
        }
        System.out.println(sb);
    }
}
