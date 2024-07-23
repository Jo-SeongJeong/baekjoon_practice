package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 17406 배열 돌리기 4
 *
 * 조건
 * 배열 크기 : N x M
 * 회전 연산 : r, c, s
 *     -> 배열의 가장 윗 칸(r-s, c-s)
 *     -> 배열의 가장 아래 칸 (r+s, c+s)
 * 시계방향으로 한칸씩 회전
 * 회전 연산은 2개 이상 나올 수 있는데, 이 경우 연산 순서 존재
 * 배열의 값 : 각 행에 있는 모든 수의 합 중 최솟값
 * N, M : 3 ~ 50
 * 연산 횟수 K : 1 ~ 6
 *
 * 문제에서 구하고자 하는 것
 * 서로 다른 순서로 배열돌리기를 했을 때의 최종 배열의 값 중 최솟값
 *
 * 문제 해결 프로세스
 * 시계방향으로 배열을 한칸씩 돌리는 함수를 짠다
 * 배열 연산 순서를 NP로 뽑아본다
 * NP로 뽑은 배열의 순서에 따라 함수를 호출해 수행한다
 * 연산 수행이 끝나면 각 행의 합을 더해 배열의 값을 구한다
 * 만약 현재 최솟값보다 배열의 값이 작으면 갱신한다
 *
 * 고려한 시간 복잡도
 * 6! * (2500 + 2500)*6 + 2500(행 연산) = 21,602,500 1초 가능
 *
 * */

public class BJ17406 { // 메모리 : 32360kb, 시간 : 228ms
    static int n;
    static int m;
    static int k;
    static int[][] array;
    static List<int[]> list;
    static boolean[] isChecked;
    static int[][] picked;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        array = new int[n][m];
        list = new ArrayList<int[]>();

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int[] cal = new int[3];

            for(int j = 0; j < 3; j++) {
                cal[j] = Integer.parseInt(st.nextToken())-1;
            }
            cal[2] += 1;
            list.add(cal);
        }

        isChecked = new boolean[list.size()];
        picked = new int[list.size()][3];

        permu(0);

        System.out.println(min);

    }

    private static void permu(int index) {
        if(index == list.size()) {
            int[][] temp = new int[n][m];

            for(int i = 0; i < n; i++) {
                temp[i] = array[i].clone();
            }

            for(int i = 0; i < list.size(); i++) {
                calculation(temp, i);
            }

            int arrValue = Integer.MAX_VALUE;
            for(int i = 0; i < n; i++) {
                int sum = 0;
                for(int j = 0; j< m; j++) {
                    sum += temp[i][j];
                }
                if(arrValue > sum) arrValue = sum;
            }

            min = Math.min(min, arrValue);
//            for(int[] row : temp)
//                System.out.println(Arrays.toString(row));
//            System.out.println();
            return;
        }

        for(int i = 0; i < list.size(); i++) {
            if(isChecked[i]) continue;
            picked[index] = list.get(i);
            isChecked[i] = true;
            permu(index+1);
            isChecked[i] = false;
        }
    }


    private static void calculation(int[][] temp, int num) {
        int s = picked[num][2];
        for(int i = 0; i < s; i++) {
            int startR = picked[num][0] - s + i;
            int startC = picked[num][1] - s + i;
            int endR = picked[num][0] + s -i;
            int endC = picked[num][1] + s - i;

            int first = temp[startR][startC];

            for(int j = startR+1; j <= endR; j++) {
                temp[j-1][startC] = temp[j][startC];
            }

            for(int j = startC+1; j <= endC; j++) {
                temp[endR][j-1] = temp[endR][j];
            }

            for(int j = endR; j > startR; j--) {
                temp[j][endC] = temp[j-1][endC];
            }

            for(int j = endC; j > startC; j--) {
                temp[startR][j] = temp[startR][j-1];
            }

            temp[startR][startC+1] = first;

        }
    }
}
