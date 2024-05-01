package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 2115 벌꿀채취
 *
 * 조건
 * 벌통 크키 N * N : 3 ~ 10
 * 각 칸의 숫자 : 꿀의 양 (1 ~ 9)
 * 채취할 수 있는 벌통의 수 M : 1 ~ 5
 * 채취할 수 있는 꿀의 최대 양 C :10 ~ 30
 * 일꾼의 수 : 2
 * 벌꿀 채취 과정
 * 1. 가로로 연속되도록 M개의 벌통을 선택해 채취 가능, 겹치면 안됨
 * 2. 각 벌통에서 채취한 꿀이 C를 넘지 않아야 함(칸은 all/nothing 임)
 * 3. 수익 : 각 칸에 대한 제곱의 합
 *
 * 문제에서 구하고자 하는 것
 * 수익의 최대값
 *
 * 문제 해결 프로세스
 * 1. 벌통 고르기 -> 조합을 통해, 2차원 배열이므로 for문 조건 잘 걸기
 * 2. 벌통 안에서 채취할 꿀 고르기 + 가격 구하기-> 부분집합을 통해
 * 최대값 갱신
 *
 * 고려한 시간 복잡도
 * 100C2 * 2^6 = 4950*64 =316,800
 * */

public class SW2115 { // 메모리 : 37008kb, 시간 : 155ms
    static int n;
    static int m;
    static int limit;
    static int price;
    static int max;
    static int[][] honey;
    static int[][] picked;
    static int[] amount;
    static boolean[] isChecked;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++) {
            sb.append("#"+t+ " ");

            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            limit = Integer.parseInt(st.nextToken());
            max = 0;

            honey = new int[n][n];
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    honey[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            picked = new int[2][2];
            combi(0, 0, 0);

            sb.append(max+"\n");
        }

        System.out.println(sb);


    }

    private static void combi(int r, int c, int depth) {

        if(depth == 2) {
            int sum = 0;

            for(int[] loc : picked) {
                amount = new int[m];
                int row = loc[0];
                int col = loc[1];

                for(int i = 0; i < m; i++) {
                    amount[i] = honey[row][col+i];
                }

                isChecked = new boolean[m];
                price = 0;
                subset(0);

                sum += price;

            }

            max = Math.max(max, sum);

            return ;
        }

        for(int i = r; i < n; i++) {
            for(int j = (i == r)? c : 0; j < n; j++) {
                if(j+m > n) continue;

                picked[depth][0] = i;
                picked[depth][1] = j;

                combi(i, j+m, depth+1);
            }
        }

    }

    private static void subset(int index) {
        if(index == m) {

            int temp = limit;
            int pr = 0;
            for(int i = 0; i < m; i++) {
                if(!isChecked[i]) continue;

                temp -= amount[i];
                if(temp < 0)  return;

                pr += Math.pow(amount[i], 2);

            }

            price = Math.max(price, pr);
            return ;
        }

        isChecked[index] = true;
        subset(index+1);
        isChecked[index] = false;
        subset(index+1);
    }
}