package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SWEA 9229 한빈이와 Spot Mart
 *
 * 조건
 *
 * 과자는 2봉지를 사야한다
 * 2 <= N <= 1000  주어진 과자 봉지의 개수
 * 2 <= M <= 2000000 과자 두봉지 합의 값
 * 과자 2봉지의 합은 최대 M까지 이다
 *
 * 문제에서 구하고자 하는 것
 *
 * 과자 2봉지의 무게 합이 M을 초과하지 않는 최대값 구하기
 *
 * 문제 해결 프로세스
 *
 * N 과 M을 입력받는다
 * N개의 과자 무게를 각각 입력받을 배열을 생성하여 값을 저장한다
 * 과자 배열을 오름차순으로 정렬한다
 * 재귀를 이용하여 과자하나를 선택한 경우에 나머지 하나를 선택한 경우를 합하여 최대값을 구한다
 * 하나를 선택했을 때 나머지 하나가 한번 M을 초과한다면 그 뒤 값은 보지 않고 다음과정으로 넘어간다
 * 이 때 한 번 선택된 과자는 다음에 선택될 수 없다
 * 합이 M에 초과가 되지 않으면서, M에 가장 가까운 값을 출력한다
 *
 * 고려한 시간 복잡도
 *
 * N + (1000 * 1000log1000) * 2 * 273
 * **/

public class SW9229 {
    static StringTokenizer st;
    static int n;
    static int m;
    static int[] snack;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            max = -1;
            snack = new int[n];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                snack[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(snack);

            combi(0, 0, 0);

            System.out.println("#" + t + " " + max);
        }

    }

    private static void combi(int count, int index, int sum) {
        if(sum > m) {
            return;
        }

        if(index == 2) {
            max = Math.max(sum, max);
            return;
        }

        for(int i = count; i < n; i++) {
            combi(i+1, index+1, sum + snack[i]);
        }
    }
}

// 메모리 : 27292kb, 시간 : 174ms
