package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 16435 스네이크버드
 *
 * 조건
 * 과일을 1개 먹으면 스네이크버드의 길이가 1 늘어남
 * 자신의 길이보다 더 높이 위치한 과일은 없을 수 없음
 * 과일 개수 N : 1 ~ 1000
 * 스네이크버드의 초기 길이 L : 1 ~ 10000
 * 각 과일의 높이 h :  1 ~ 10000
 *
 * 문제에서 구하고자 하는 것
 * 입력받은 과일을 먹은 결과로 스네이크버드의 최대 길이
 *
 * 문제 해결 프로세스
 * 배열을 통해 과일을 오름차순으로 정렬
 * 제일 길이가 작은 것부터 한개씩 스네이크버드의 길이와 비교
 * 작거나 같으면 길이를 1늘리고 다음 과일과 비교
 * 먹지 못하는 순간이오면 종료
 *
 * 고려한 시간복잡도
 * 1000(입력) + 1000(과일 비교) + 1000log1000(정렬)
 *
 * */

public class BJ16435 { // 메모리 : 11844kb, 시간 : 84ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[] fruit = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            fruit[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(fruit);

        for(int i = 0;i < n; i++) {
            if(fruit[i] <= l) l++;
            else break;
        }

        System.out.println(l);
    }
}