package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 6987 월드컵
 * <p>
 * 조건
 * 월드컵 조별 예선 : 6개국, 같은 조에 소속된 국가들과 한번씩 총 5버 경기 치름
 * 승, 무, 패의 수 : 0 ~ 5
 * <p>
 * 문제에서 구하고자 하는 것
 * 6개 국의 승 무 패가 가능한 결과인지 출력(총 4번 결과가 나옴) 가능하면 1, 불가면 0
 * <p>
 * 문제 해결 프로세스
 * 게임이 일어날 수 있는 매치업에 대해 배열로 만들기
 * 입력에 대해 6 *3 배열을 만들자
 * 각각의 매치업에 대해 가능한 경우를 모두 구해보고, 안 되면 back
 * 15번의 매치업이 가능한 경우의 수가 나온다면 가능한 결과
 * 안되면 불가능한 결과
 *
 * <p>
 * 고려한 시간 복잡도
 * 6*3*4 = 72
 */

public class BJ6987 { // 메모리 : 11592kb, 시간 : 80ms
    static boolean flag;
    static int[][] table;
    static int[][] game = new int[][] { // 경기 경우의 수
            {0, 1},
            {0, 2},
            {0, 3},
            {0, 4},
            {0, 5},
            {1, 2},
            {1, 3},
            {1, 4},
            {1, 5},
            {2, 3},
            {2, 4},
            {2, 5},
            {3, 4},
            {3, 5},
            {4, 5}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        A: for (int t = 0; t < 4; t++) {
            st = new StringTokenizer(br.readLine());
            table = new int[6][3];
            flag = false;

            for (int i = 0; i < 6; i++) {
                int cnt = 0;
                for (int j = 0; j < 3; j++) {
                    table[i][j] = Integer.parseInt(st.nextToken());
                    cnt += table[i][j];
                }
                if (cnt != 5) {
                    sb.append(0 + " ");
                    continue A;
                }
            }

            checkResult(0);
            if(flag) sb.append(1 + " ");
            else sb.append(0 + " ");
        }
        System.out.println(sb);
    }

    private static void checkResult(int match) {
        if(match == 15) {
            flag = true;
            return;
        }
        int home = game[match][0];
        int away = game[match][1];

        // 홈팀이 이긴 경우
        if(table[home][0] > 0 && table[away][2] > 0) {
            table[home][0]--;
            table[away][2]--;
            checkResult(match+1);
            table[home][0]++;
            table[away][2]++;
        }
        // 어웨이 팀이 이긴 경우
        if(table[home][2] > 0 && table[away][0] > 0) {
            table[home][2]--;
            table[away][0]--;
            checkResult(match+1);
            table[home][2]++;
            table[away][0]++;
        }
        // 비긴 경우
        if(table[home][1] > 0 && table[away][1] > 0) {
            table[home][1]--;
            table[away][1]--;
            checkResult(match+1);
            table[home][1]++;
            table[away][1]++;
        }
    }
}
