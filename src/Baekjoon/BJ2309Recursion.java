package Baekjoon;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 백준 2309 일곱 난쟁이
 * 조건
 * 7개의 키 합 = 100
 *
 * 문제에서 구하고자 하는 것
 * 9개의 키 중 7개의 키 합이 100인 7개의 입력 값을 오름차순으로 출력
 *
 * 문제 해결 프로세스
 * 9개의 입력 값을 저장 할 배열 생성
 * 배열에 입력 값을 저장하면서 해당 값을 더함
 * 9개의 입력 값 중 2개를 뽑는 조합을 재귀로 구현
 * 합 - 2개 = 100 이면 해당 값 0으로 만들고 종료
 * 배열을 오름차순으로 정렬
 * 인덱스 2번 부터 끝까지 출력
 *
 * 고려한 시간 복잡도
 * 9log9 + 9 + 7 + 9*9
 *
 * */


public class BJ2309Recursion {
    static int[] array = new int[9];
    static int sum;
    static int[] pick = new int[2];
    static int a;
    static int b;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < 9; i++) {
            array[i] = sc.nextInt();
            sum += array[i];
        }

        combi(0, 0);

        for(int i = 0; i < 9; i++) {
            if(array[i] == a || array[i] == b) array[i] = 0;
        }

        Arrays.sort(array);

        for(int i = 2; i < 9; i++) {
            System.out.println(array[i]);
        }
    }

    private static void combi(int r, int index) {
        if(index == 2) {
            if(sum-(pick[0] +pick[1]) == 100) {
                a = pick[0];
                b = pick[1];
            }
            return;
        }
        for(int i = r; i < 9; i++) {
            pick[index] = array[i];
            combi(i+1, index+1);
        }
    }

}

// 메모리 : 12860kb , 시간 : 108ms
