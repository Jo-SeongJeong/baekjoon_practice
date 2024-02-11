package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SWEA5215 햄버거 다이어트
 *
 * 조건
 *
 * 재료의 수 (1 ~ 20)
 * 제한 칼로리 (1 ~ 10000)
 * 각 재료 점수 (1 ~ 1000)
 * 각 재료 칼로리(1 ~ 1000)
 *
 * 문제에서 고려해야할 것
 *
 * 제한한 칼로리를 넘지 않으면서 만족도가 가장 높은 점수를 구해 출력
 * 재료 개수는 제한하지 않음 -> 부분 집합
 *
 * 문제 해결 프로세스
 * 재료의 수와 제한 칼로리를 입력받는다
 * 이차원 배열을 생성하여 각 재료에 대한 정보를 입력 받아 저장한다(0: 재료 점수, 1: 재료 칼로리)
 * 칼로리에 대해 오름차순으로 정렬한다
 * 부분 집합을 생성하여 칼로리가 넘지 않는 최대 점수 조합을 출력한다
 * isChecked를 활용해 원소 사용 기준으로 재귀를 호출하여 재료의 수까지 호출됐을 때 맛에 대한 점수와 칼로리 합을 구한다
 * 만약 재료의 수까지 호출되지 않아도, 제한 칼로리를 넘으면 해당 부분은 넘어간다
 * 칼로리 합이 제한보다 낮을 때 최대값으로 갱신한다
 * 최대값을 출력한다
 *
 * 고려한 시간 복잡도
 *
 * 2*2(재료 배열 입력) + 2^20(재료 부분집합에 대한 연산)
 *
 * */

public class SW5215 {
    static StringTokenizer st;
    static int n;
    static int l;
    static int[][] recipe;
    static boolean[] isChecked;
    static int max;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());
            max = 0;
            recipe = new int[n][2];
            isChecked = new boolean[n];

            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                recipe[i][0] = Integer.parseInt(st.nextToken());
                recipe[i][1] = Integer.parseInt(st.nextToken());
            }

            subSet(0, 0, 0);

            System.out.println("#" + t + " " + max);
        }

    }

    private static void subSet(int index, int tasteSum, int calSum) {

        if(calSum > l) return;

        if(index == n) {
            max = Math.max(tasteSum, max);
            return;
        }

        isChecked[index] =true;
        subSet(index+1, tasteSum+recipe[index][0], calSum+recipe[index][1]);
        isChecked[index] = false;
        subSet(index+1, tasteSum, calSum);
    }
}

// 메모리 : 27276kb, 시간 : 270ms