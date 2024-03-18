package Baekjoon;

import java.util.Scanner;

/**
 * 백준 1463 1로 만들기
 *
 * 조건
 * 1. X가 3으로 나누어 떨어지면 3으로 나눈다
 * 2. X가 2로 나누어 떨어지면 2로 나눈다
 * 3. 1을 뺀다
 * 위 연산을 적절히 조합 가능, 순서 상관 X
 * 입력 정수 N : 1 ~ 1000000
 *
 * 문제에서 구하고자 하는 것
 * n을 1로 만드는 최소 연산 횟수
 *
 * 문제 해결 프로세스
 * 4부터
 * 가능한 연산 중, 가장 작은 배열값 + 1
 *
 * 고려한 시간 복잡도
 * n+1
 * */

public class BJ1463 { // 메모리 : 17052kb, 시간 : 136ms
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] array = new int[1000001];
        array[1] = 0;
        array[2] = 1;
        array[3] = 1;


        for(int i = 4; i < n+1; i++) {
            if(i % 6 == 0) {
                array[i] = Math.min(array[i/2], Math.min(array[i/3], array[i-1])) + 1;
            }
            else if(i % 3 == 0) {
                array[i] = Math.min(array[i/3], array[i-1]) + 1;
            }
            else if(i % 2 == 0) {
                array[i] = Math.min(array[i/2], array[i-1]) + 1;
            }
            else {
                array[i] = array[i-1] + 1;
            }
        }

        System.out.println(array[n]);
    }

}
