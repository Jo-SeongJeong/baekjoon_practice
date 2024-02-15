package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 4012 요리사
 *
 * 조건
 * N개의 식재료를 반씩 나누어 두개의 요리(N은 짝수)
 * N : 4 ~ 16
 * 시너지 범위 : 1 ~ 20000
 * 재료가 같은 경우(i=j), 0
 * 식재료 2개를 고르면 해당 행렬 값은 2번 다 사용해야함 맛은 2번 사용한 값의 합
 *
 * 문제에서 구하고자 하는 것
 * A음식, B음식 차이가 최소가 되는 값(절대값)
 *
 * 문제 해결 프로세스
 * 식재료 번호에 대한 nC2/n을 뽑고, 뽑히지 않은 나머지 재료를 구해 각각의 시너지를 구한다
 * 시너지 차를 구하고 최소값과 비교하여 최소 차이 구한다
 *
 * 고려한 시간 복잡도
 * 16C8 + 16(조합으로 뽑히지 않은 수들) + 8*8(시너지 구하기)*2(연산) -> 25,884 * 50(tc수) = 1,294,200 충분
 *
 * */

public class SW4012 { // 메모리 : 22560kb, 시간 : 199ms
    static int n;
    static int result;
    static int[][] synergy;
    static int[] picked;
    static boolean[] isChecked;
    static int[] unpicked;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++) {
            n = Integer.parseInt(br.readLine());
            result = Integer.MAX_VALUE;
            synergy = new int[n][n];
            picked = new int[n/2];
            unpicked = new int[n/2];
            isChecked = new boolean[n];

            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    synergy[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            combi(0, 0);
            System.out.println("#" + t + " " +result);
        }
    }

    private static void combi(int index, int r) {
        if(r == n/2) {
            int idx = 0;
            for(int i = 0; i < n; i++) {
                if(!isChecked[i]) {
                    unpicked[idx] = i;
                    idx++;
                }
            }

            int tasteA = 0;
            int tasteB = 0;
            int taste = 0;
            for(int i = 0; i < r-1; i++) {
                for(int j = i+1; j < r; j++) {
                    tasteA += synergy[picked[i]][picked[j]];
                    tasteA += synergy[picked[j]][picked[i]];

                    tasteB += synergy[unpicked[i]][unpicked[j]];
                    tasteB += synergy[unpicked[j]][unpicked[i]];
                }
            }
            taste = Math.abs(tasteA - tasteB);

            if(taste < result) result = taste;

            return;
        }

        for(int i = index; i< n; i++) {
            picked[r] = i;
            isChecked[i] = true;
            combi(i+1, r+1);
            isChecked[i] =false;

        }
    }

}
