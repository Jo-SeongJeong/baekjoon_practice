package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 6808 규영이와 인영이의 카드게임
 *
 * 조건
 *
 * 숫자는 1 ~ 18
 * 경기는 9번
 * 높은 숫자를 낸 사람이 낮은 숫자 + 높은 숫자로 점수를 얻는다
 *
 * 문제에서 구하고자 하는 것
 *
 * 한 명의 카드 제출 순서가 정해져 있을 때 나머지 한 명이 게임을 이기는 경우의 수와 지는 경우의 수를 구한다
 *
 * 문제 해결 프로세스
 *
 * 고정된 카드 번호를 입력 받고 배열에 저장한다
 * 카드 번호가 입력이 되지 않은 나머지 9장의 카드를 계산하여 배열에 저장한다
 * 이기는 경우, 지는 경우의 변수를 생성해서 고정된 카드와 나머지 카드를 비교하면서 재귀를 돌려 승패를 비교해 구한다
 *
 * */

public class SW6808 { // 메모리 : 20820kb, 시간 : 3500ms
    static int[] input; // 입력 받은 카드 값
    static StringTokenizer st;
    static boolean[] isChecked; // 1. 입력 받지 않은 카드 값 확인 2. 대결한 카드 여부 확인
    static int[] cards; // 입력 받지 않은 나머지 카드 값
    static int[] picked; // 대결한 카드 값
    static int winCnt; // 입력 받은 카드 값으로 이긴 경우
    static int loseCnt; // 진경우

    static int inputScore; // 한 판 기준, 입력 받은 카드 값에 누적되는 점수
    static int cardScore; // 나머지 카드가 이겼을 때 누적되는 점수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++) {
            input = new int[9];
            isChecked = new boolean[18];
            cards = new int[9];
            picked = new int[9];

            inputScore = 0;
            cardScore = 0;

            winCnt = 0;
            loseCnt = 0;


            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < 9; i++) {
                input[i] = Integer.parseInt(st.nextToken());

                isChecked[input[i]-1] = true;
            }

            // 입력받지 않은 카드값 구하기
            int idx = 0;
            for(int i = 0; i < 18; i++) {
                if(!isChecked[i]) {
                    cards[idx] = i+1;
                    idx++;
                }
            }
            isChecked = new boolean[9];

            recursion( 0);

            System.out.println("#"+t+" "+winCnt +" "+loseCnt);
        }
    }

    private static void recursion(int index) {
        if(index == 9) {
            for(int i = 0; i < 9; i++) {
                if(input[i] < picked[i]) {
                    cardScore += input[i] + picked[i];
                }

                else {
                    inputScore += input[i] + picked[i];
                }
            }
            if(inputScore < cardScore) {
                loseCnt++;
            }
            else {
                winCnt++;
            }
            inputScore = 0;
            cardScore = 0;
            return;
        }

        for(int i = 0; i < 9; i++) {
            if(isChecked[i]) continue;

            picked[index] = cards[i];
            isChecked[i] = true;
            recursion(index+1);
            isChecked[i] = false;
        }
    }
}