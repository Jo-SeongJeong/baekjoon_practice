package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백분 11659 구간 합 구하기 4
 * 조건
 * N = 수의 개수
 * M = 구간 설정 개수
 * i = 입력된 수 중, 합을 구할 시작 숫자 위치
 * j = 입력된 수 중, 합을 구할  끝 숫자 위치
 * 수 <= 1000 인 자연수
 * 1 ≤ N ≤ 100,000
 * 1 ≤ M ≤ 100,000
 * 1 ≤ i ≤ j ≤ N
 *
 * 문제에서 구하고자 하는 것
 * 둘째 줄에 입력된 수에서 i번째 수부터 j번째 수까지의 합 (M번 반복)
 *
 * 문제 해결 프로세스
 * N , M 입력
 * 수를 담을 배열 생성(N 크기)
 * i, j를 M 번 입력을 받을 for 문 생성
 * 수를 더할 sum 생성 및 초기화
 * 해당 i, j의 범위만큼 숫자 더하기
 * 합 출력
 * M번 반복
 *
 * 고려한 시간 복잡도
 * 100000 (M최대) * 100000(i, j의 최대 범위) => 100억 * 100000(배열 최대 범위)
 * -> 기각
 * */

/**
 * 문제 해결 프로세스
 * N, M 입력
 * 입력된 수를 누적합을 하는 배열 생성(N+1 크기) 0번째는 아무것도 합하지 않은 0
 * i, j를 입력 받으면, j번째 배열 값 - i-1번째 배열 값으로 출력
 * */
public class BJ11659 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] cumul = new int[n+1]; // 누적 합을 저장할 배열 생성
        cumul[0] = 0; // 입력될 i, j는 1부터 시작하기 때문에 아무것도 합하지 않은 0번째를 0으로 초기화

        st = new StringTokenizer(br.readLine());

        // 1번째부터 입력된 값에 대해 누적합 저장
        for(int k = 1; k < n+1; k++) {
            cumul[k] = cumul[k-1] + Integer.parseInt(st.nextToken());
        }

        int [] sum = new int[m];     // 정답을 출력할 배열 생성

        // i, j 번째의 누적 합은 j -(i-1)의 값과 동일
        // ex 2, 4인 경우 2 ~ 4까지의 합 구하기 -> j= 1~4까지의 합, i = 1~2까지의 합(i-1은 1의 합)
        for(int k = 0; k < m; k++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken()) - 1;
            int j = Integer.parseInt(st.nextToken());
            sum[k] = cumul[j] - cumul[i];
        }

        // 출력
        for(int k = 0; k < m; k++) {
            System.out.println(sum[k]);
        }
    }

}
