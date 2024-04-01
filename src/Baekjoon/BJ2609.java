package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 2609 최대공약수와 최소공배수
 *
 * 조건
 * 두개의 자연수 : 1 ~ 10000
 *
 * 문제에서 구하고자 하는 것
 * 두 수의 최대 공약수와 최소 공배수 구하기
 *
 * 문제 해결 프로세스
 * 유클리드 호제(p,q) ->(q, p%q)를 통해 최대 공약 수를 구한다
 * p%q가 0이 되면 p가 최대 공약수
 * 이를 통해 주어진 p * q / 최대공약수 = 최소공배수임을 통해 답을 구한다
 *
 * 고려한 시간 복잡도
 * 10000
 * */

public class BJ2609 { // 메모리 : 11512kb, 시간 : 80ms
    static int lcm;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int gcd = 0;

        if(a >= b) findLcm(a, b);
        else findLcm(b, a);

        gcd = a * b / lcm;

        System.out.println(lcm);
        System.out.println(gcd);
    }

    private static void findLcm(int a, int b) {
        if(b == 0) {
            lcm = a;
            return ;
        }

        findLcm(b, a%b);


    }

}
