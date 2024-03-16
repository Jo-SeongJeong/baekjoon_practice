package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 18870 좌표 압축
 *
 * 조건
 * 배열 길이 N :1 ~ 1000000
 * 각 배열 값 범위 x: -1000000000 ~ 1000000000 -> int
 *
 * 문제에서 구하고자 하는 것
 * 배열을 오름차순으로 정렬했을 때 제일 작은 값 -> 0부터 제일 큰값까지 압축한 결과
 * 입력 순으로 출력
 *
 * 문제 해결 프로세스
 * 2차원 배열로 풀어보자
 * i,0 = 입력 순서, i, 1 입력 값
 * i, 1로 오름차순 정렬
 * i, 2에 압축한 값 넣기
 * i, 0로 다시 정렬
 * i, 3값 출력
 *
 * 고려한 시간 복잡도
 * 1000000log1000000 * 2 + 1000000 * 3 = 13000000 * 2 + 3000000 = 30000000 -> 2초 충분
 * */

public class BJ18870 { // 메모리 : 273196kb, 시간 : 3448ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[][] array = new int[n][3];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            array[i][0] = i;
            array[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(array, (a, b) -> a[1]-b[1]);

        int compress = 0;
        int curr = array[0][1];
        for(int i = 1; i < n; i++) {
            if(curr < array[i][1]) compress++;

            array[i][2] = compress;
            curr = array[i][1];
        }

        Arrays.sort(array, (a, b) -> a[0]- b[0]);

        for(int i = 0; i  <n; i++) {
            sb.append(array[i][2]).append(" ");
        }

        System.out.println(sb);
    }
}
