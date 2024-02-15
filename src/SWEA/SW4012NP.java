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

public class SW4012NP { // 메모리 : 26820kb, 시간 : 238ms
    static int n;
    static int result;
    static int[][] synergy;
    static int[] picked;
    static boolean[] isChecked;
    static int[] unpicked;

    static int[] npCheck;

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
            npCheck = new int[n];

            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    synergy[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = n-1; i >= n/2; i--) {
                npCheck[i] = 1;
            }

            do {
                int pIdx = 0;
                int upIdx = 0;
                for(int i = 0; i < n; i++) {
                    if(npCheck[i] == 1) {
                        picked[pIdx] = i;
                        pIdx++;
                    }
                    else {
                        unpicked[upIdx] = i;
                        upIdx++;
                    }
                }
                int tasteA = 0;
                int tasteB = 0;
                int taste = 0;
                for(int i = 0; i < n/2; i++) {
                    for(int j = i+1; j < n/2; j++) {
                        tasteA += synergy[picked[i]][picked[j]];
                        tasteA += synergy[picked[j]][picked[i]];

                        tasteB += synergy[unpicked[i]][unpicked[j]];
                        tasteB += synergy[unpicked[j]][unpicked[i]];
                    }
                }
                taste = Math.abs(tasteA - tasteB);

                if(taste < result) result = taste;

            }while(np());

            System.out.println("#" + t + " " +result);
        }
    }

    private static boolean np() {
        int i = n - 1;
        while(i>0 && npCheck[i] <= npCheck[i-1])i--;

        if(i == 0) return false;

        int j = n-1;
        while(npCheck[j] <= npCheck[i-1])j--;


        swap(npCheck, i-1, j);

        int k = n-1;
        while(i < k) swap(npCheck, i++, k--);

        return true;

    }

    private static void swap(int array[], int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


}
