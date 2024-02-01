package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 백준 15650 N과 M 2
 * 조건
 * 1부터 N까지 자연수 중에서 중복 없이 M개 고르기
 * 수열은 오름차순
 *
 * 문제에서 구하고자 하는 것
 * 중복되지 않은 수열을 오름차순으로 출력한다
 *
 * 문제해결 프로세스
 * 최대 숫자 N과 구성될 숫자 길이 M을 입력 받는다
 * M 길이로 구성된 배열을 만든다
 * 재귀함수를 만든다
 * 재귀 함수 매개변수는 수열을 담을 배열의 인덱스와 가능한 시작 숫자로 한다
 * 인덱스가 최대 번호이면 해당 수열을 출력하고 리턴한다
 * 가능한 시작 숫자부터 N까지 반복한다
 * 반복문 안에는 수열 배열의 인덱스 값에 숫자를 넣는다
 * 재귀함수를 호출한다
 * 호출하는 경우 대입된 숫자의 다음 숫자, 1 증가된 인덱스값으로 호출한다
 *
 * 고려한 시간 복잡도
 * 8*7* ... * 1
 *
 * */

public class BJ15650 {
    static int[] pick;
    static int n;
    static int m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        pick = new int[m];

        combi(1, 0);
    }

    private static void combi(int r, int index) {
        if(index == m) {
            for(int i = 0; i < m; i++) {
                System.out.print(pick[i] + " ");
            }
            System.out.println();
            return;
        }

        for(int i = r; i <= n; i++) {
            pick[index] = i;
            combi(i+1, index+1);
        }
    }
}

// 메모리 : 11572kb, 시간 : 84ms