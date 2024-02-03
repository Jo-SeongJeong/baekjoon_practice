package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 2961 도영이가 만든 맛있는 음식
 *
 * 조건
 *
 * 신맛 = 곱
 * 쓴맛 = 합
 * 재료를 섞는 경우 신맛은 곱으로 증가, 쓴맛은 합으로 증가한다
 * 재료의 개수는 1 ~ 10
 *
 * 문제에서 구하고자 하는 것
 *
 * 재료당 신맛, 쓴맛이 있는데 둘의 차이가 가장 작은 수를 출력
 *
 * 문제 해결 프로세스
 * 재료의 개수를 입력받는다
 * 신맛 쓴맛을 저장할 이차원 배열을 생성하여 입력 받는다
 * 이차원 배열의 행에 대한 부분집합을 구한다
 * 구한 부분집합에서 첫번째 열은 곱, 두번째 열은 합을 진행한 후 차를 구한다
 * 이 때 공집합은 제외 한다
 * 차가 가장 작은 값을 출력한다
 *
 * 고려한 시간 복잡도
 *
 * n*2 (입력) + 2**n -1 +
 *
 * */


public class BJ2961 {
    static int n;
    static int[][] taste;
    static int min = Integer.MAX_VALUE;
    static StringTokenizer st;
    static boolean[] isChecked; // 부분집합에 포함할 원소 선택 기준

    static int mul;
    static int sum;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 재료 개수

        taste = new int[n][2]; // 신맛, 쓴맛 저장할 배열


        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            taste[i][0] = Integer.parseInt(st.nextToken());
            taste[i][1] = Integer.parseInt(st.nextToken());
        }

        isChecked = new boolean[n];
        subSet(0, 0); // 부분집합 재귀함수 호출

        System.out.println(min);


    }

    private static void subSet(int cnt, int trueCount) {
        if(n == cnt) {
            if(trueCount == 0) { // 공집합인 경우 제외
                return;
            }

            mul = 1;
            sum = 0;
            for(int i = 0; i < n; i++) {
                if(isChecked[i]) {
                    mul *= taste[i][0];
                    sum += taste[i][1];
                }
            }

            int temp = Math.abs(mul-sum);
            min = Math.min(temp, min);
            return;
        }

        isChecked[cnt] = true; // 해당 인덱스의 원소 포함
        subSet(cnt+1, trueCount+1); // 포함하는 경우 원소 포함된다는 것 표시
        isChecked[cnt] = false; // 원소 미포함
        subSet(cnt+1, trueCount); // 포함하지 않는 tCount 증가x

    }
}

// 메모리 : 11544kb, 시간 : 76ms
