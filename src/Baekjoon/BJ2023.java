package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 2023 신기한 소수
 *
 * 조건
 * 수의 자리수 N : 1 ~ 8
 *
 * 문제에서 구하고자 하는 것
 * 신기한 소수 : N자리수 소수 중 번호 인덱스 0 ~ N-1번까지의 숫자까지 모두 소수인 경우
 *
 * 문제 해결 프로세스
 * 첫번째의 자리 기준 소수의 가능성 = 2, 3, 5, 7
 * 나머지 자리 기준  = 1, 3, 5, 7, 9
 *
 * 첫번째 자리 뽑고 순열 picked에 저장
 * 나머지를 자리에 대한 순열을 저장
 * 이 때, 각 자리수 * 10^i승 한 값에 대해 소수 체크
 * 소수체크 true면, picked에 담자
 * 만약 n자리수가 완성 되면 해당 소수는 신기한 소수
 * 만약 소수체크가 false면 return, 해당 자리부터 다시 채우며 반복
 *
 * 소수체크는 해당 수를 뽑았을 때, 3부터 root(해당 수)까지 +2 시키면서 해당수와 나누어봄
 * 나머지 나눗셈 결과 모두 0이 나오지 않으면 소수
 *
 *
 * 고려한 시간 복잡도
 * 4 * 5^7 * 10000(무조건 미만) 가능할 듯
 *
 * */

public class BJ2023 { // 메모리 : 11576kb, 시간 : 820ms
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int[] firstPrime = new int[]{2, 3, 5, 7};
    static int[] remainPrime = new int[] {1, 3, 5, 7, 9};
    static int[] picked;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        picked = new int[n];

        int index = 0;
        for(int i = 0; i < 4; i++) {
            picked[index] = firstPrime[i];
            permu(index+1);
        }

        System.out.println(sb);
    }

    private static void permu(int index) {

        if(index == n) {
            for(int i = 0; i < index; i++)
                sb.append(picked[i]);
            sb.append("\n");
            return;
        }

        for(int i = 0; i < 5; i++) {
            int temp = 0;
            int len = index;
            for(int j = 0; j < index; j++) {
                temp += picked[j] * Math.pow(10, len);
                len--;
            }
            temp += remainPrime[i];

            if(isPrime(temp)) {
                picked[index] = remainPrime[i];
                permu(index+1);
            }
        }
    }

    private static boolean isPrime(int num) {
        for(int i = 2; i < num; i++) {
            if(num % i == 0)
                return false;
        }
        return true;
    }

}
