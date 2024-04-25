package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 1789 수들의 합
 *
 * 조건
 * 서로 다른 N개의 자연수의 합 S : 1 ~ 4,294,967,295 => long
 *
 * 문제에서 구하고자 하는 것
 * S가 되는 자연수들의 합 중 가장 많은 수가 사용된 개수
 *
 * 문제 해결 프로세스
 * 1부터 누적시켜 더한 값이 s를 넘을 때 반복 종료, 이때까지 더한 횟수도 누적
 * 횟수-1이 정답
 *
 * 고려한 시간 복잡도
 * 누적합이니 2초는 충분할듯
 * */

public class BJ1789 { // 메모리 : 11516kb, 시간 : 80ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long s = Long.parseLong(br.readLine());
        long sum = 0;

        int i = 1;
        while(sum <= s) {
            sum += i;
            i++;
        }

        System.out.println(i-2);
    }
}
