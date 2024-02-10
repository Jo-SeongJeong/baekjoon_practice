package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SWEA 6808 규영이와 인영이의 카드 게임
 *
 * 조건
 * 카드 18장을 9장씩 나누어 진행 (1 ~ 18), 서로다른 수
 * 9라운드 진행
 * 1라운드에 1장씩 제출, 이긴 사람이 적힌 숫자만큼 점수 합산해 가져감
 * 9라운드 종료 후, 총점이 높은 사람이 게임 승리
 * 총점이 같으면 무승부
 *
 * 문제에서 구하고자 하는 것
 * 규영이가 내는 카드 순서(입력)에 따른 규영이의 승, 패에 대한 경우의 수
 *
 * 문제 해결 프로세스
 * 규영이의 카드 종류를 입력받는다
 * 인영이의 카드를 구한다
 * 인영이의 카드 9장에 대한 순열을 구한다 <- 인영이가 카드를 내는 9!의 경우의 수
 * 순열이 완성되면 규영이와 인영이의 인덱스에 맞게 게임 진행, 점수 구하기
 * 총 점수에 따른 규영이의 게임 승 패 경우의 수 cnt
 * 경우의 수 출력
 * 9장에 대한 순열을 구할 때, Next Permutation을 활용한다
 *
 * 고려한 시간 복잡도
 * 9! * 9 (순열이 구해진 경우 9라운드 카드게임 진행) -> 362880 * 9 = 3,265,920 0.001초?
 *
 * */

public class SW6808NP { // 메모리: 25364kb, 시간: 1183ms
    static final int n = 9;
    static int[] gCards;
    static int[] iCards;
    static boolean[] isCheckd;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++) {
            st = new StringTokenizer(br.readLine());
            gCards = new int[n];

            isCheckd = new boolean[19];
            for(int i = 0; i < n; i++) {
                gCards[i] = Integer.parseInt(st.nextToken());
                isCheckd[gCards[i]] = true;
            }

            iCards = new int[n];
            int index = 0;
            for(int i = 1; i < 2*n+1; i++) {
                if(!isCheckd[i]) {
                    iCards[index] = i;
                    index++;
                }
            }

            Arrays.sort(iCards);
            int wCnt = 0;
            int lCnt = 0;

            do {
                int gScore = 0;
                int iScore = 0;
                for(int i = 0; i < n; i++) {
                    if(gCards[i] > iCards[i]) gScore += gCards[i] + iCards[i];
                    else iScore += iCards[i] + gCards[i];
                }

                if(gScore > iScore) wCnt++;
                else if(gScore < iScore) lCnt++;

            } while (nextPermu(iCards));

            System.out.println("#" + t + " " + wCnt + " " + lCnt);
        }
    }

    private static boolean nextPermu(int[] array) {
        // 꼭대기 찾기, 현재보다 다음이 작아지는 순간 구하기, i-1이 교환 대상
        int i = n-1;
        while(i > 0 && array[i] <= array[i-1]) {
            i--;
        }

        if(i == 0) return false;

        // 교환대상과 교환할 j 찾기, i-1보다 큰 값 중 최소값
        int j = n-1;
        while(array[j] <= array[i-1]) j--;

        // swap
        swap(array, i-1, j);

        // i부터 끝까지 오름차순 정렬
        int k = n-1;
        while(i < k) swap(array, i++, k--);
        return true;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
