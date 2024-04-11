package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 2577 숫자의 개수
 *
 * 조건
 * 세 자연수 범위 : 100 ~ 1000 -> 곱하면 1000000000 (10억 int 가능)
 *
 * 문제에서 구하고자 하는 것
 * 세 자연수의 곱 결과의 각 자리수가 0 ~ 9까지 각각 몇번 쓰였는지
 *
 * 문제 해결 프로세스
 * 곱한 결과를 문자열로 만들자
 * 각 숫자가 몇번 쓰였는지 저장할 배열 만들자
 * 해당 숫자에 해당하는 배열 index값을 증가시킴
 * 모두 출력
 *
 * 고려한 시간 복잡도
 * 10
 * */

public class BJ2577 { // 메모리 : 11480kb, 시간 : 76ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());

        String rs = a * b * c + "";

        int[] array = new int[10];

        for(int i = 0; i < rs.length(); i++) {
            array[Integer.parseInt(rs.charAt(i)+"")]++;
        }

        for(int i : array) {
            sb.append(i + "\n");
        }

        System.out.println(sb);

    }

}
