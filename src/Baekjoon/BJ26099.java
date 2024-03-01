package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 26099 설탕배달2
 *
 * 조건
 * 봉지 종류 : 3, 5kg
 * N : 3 ~ 10^18 -> long타입으로 풀자
 * 정확한 Nkg이 되지 않으면 -1 출력
 *
 * 문제에서 구하고자 하는 것
 * Nkg의 설탕을 배달할 때, 봉지의 최소 개수 구하기
 *
 * 문제 해결 프로세스
 * n % 5 = 0이 되면 해당 몫만큼 min을 더하고 종료
 * 아니라면 n-3을 해주고 개수를 하나 더함
 * 위 과정 반복하다가 n이 음수가 되면 정확한 kg이 되지 않으므로 -1로 변경 후 종료
 * 출력
 *
 * 고려한 시간 복잡도
 * 10^18/3 = 333,333,333,333,333,333
 *
 * */

public class BJ26099 { // 메모리 : 11512kb , 시간 : 76ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());

        long min = 0;

        while(true) {
            if(n % 5 == 0) {
                min += n/5;
                break;
            }

            n -= 3;
            min++;

            if(n < 0) {
                min = -1;
                break;
            }
        }

        System.out.println(min);
    }

}
