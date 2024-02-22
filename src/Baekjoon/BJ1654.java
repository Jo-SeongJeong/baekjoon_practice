package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1654 랜선 자르기
 *
 * 조건 K개의 랜선을 일정한 길이의 N개의 랜선으로 자르기 이미 자른 랜선은 붙일 수 없다 N개보다 많이 만든 것도 N개를 만드는 것에 포함
 * K : 1 ~ 10,000 / K <= N N : 1 ~ 1,000,000 각 랜선의 길이 최대 : 2^31 - 1 자연수 --> int
 *
 * 문제에서 구하고자 하는 것 N개를 만들 수 있는 랜선의 최대 길이 출력
 *
 * 문제 해결 프로세스 가장 긴 길이의 랜선을 기준으로 binary search mid가 구해지면 해당 길이로 랜선이 몇개 만들어지는 구해
 * 더해보기 만약 개수가 N보다 적으면 더 작게 자르기 만약 개수가 N보다 많으면 더 크게 자르기 반복해서 나오는 최대 길이 구하기
 *
 * 고려한 시간 복잡도 log(2^31-1)
 *
 */

public class BJ1654 { // 메모리 : 15296kb, 시간 : 136ms
    static int k;
    static int n;
    static int[] lan;
    static long mid;
    static long result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        lan = new int[k];

        for (int i = 0; i < k; i++) {
            lan[i] = Integer.parseInt(br.readLine());
        }

        search(lan, 0, Integer.MAX_VALUE);

        System.out.println(result);

    }

    private static void search(int array[], long start, long end) {
        while (start <= end) {
            mid = (start + end) / 2;
            int count = 0;

            for (int i = 0; i < lan.length; i++) {
                count += lan[i] / mid;
            }

            if (count < n) {
                end = mid - 1;
            }

            else {
                result = mid;
                start = mid + 1;
            }
        }
    }
}