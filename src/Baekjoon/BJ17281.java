package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 17281 ⚾
 *
 * 조건
 * 하나의 이닝 -> 공격, 수비
 * 한 이닝에 3아웃이 발생 -> 종료
 * 공 수 교대 3아웃이 발생하지 않은 상태 : 9번타자 -> 1번타자
 * 타순은 이닝이 변경되어도 순서 유지
 * 1, 2, 3루, 홈 -> 1득점
 * 중간에 머물러 있을 수 있음
 * 이닝이 시작될 때는 주자 x
 * 안타 : 타자와 모든 주자는 한 루씩 이동, 1
 * 2루타 : 두루씩 이동, 2
 * 3루터 : 세 루씩 이동, 3
 * 홈런 : 홈까지 진루, 4
 * 아웃 : 진루 불가, 공격 팀 아웃 증가, 0
 * 각 이닝에는 아웃을 기록하는 타자가 적어도 한 명 존재한다
 * 1번 선수 -> 4번타자 나머지 선수
 * 타순 정해야 함 이닝 수 N : 2 ~ 50
 *
 * 문제에서 구하고자 하는 것
 * 1번 선수가 4번 타자로 정해져있을 때 가장 많은 득점을 하는 타순을 찾고, 그 때의 득점 구하기
 * 문제 해결 프로세스
 * 순열 : 1번 타자가 4번인 경우를 고정하고, 나머지 선수의 타순에 대한 순열을 구한다
 * 순열이 구해지면(타순이 정해지면), 배열 인덱스 값에 맞는 각 이닝 성적을 통해 야구게임을 진행한다
 *
 * 고려한 시간 복잡도
 * 8! x 20 x 27 = 21,772,800
 */

public class BJ17281 { // 메모리 : 92064kb, 시간 : 512ms
    static int n;
    static int[] player;
    static List<int[]> list;
    static int[] picked;
    static boolean[] isChecked;
    static int[] base;
    static int maxScore;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            player = new int[10];
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < 10; j++) {
                player[j] = Integer.parseInt(st.nextToken());
            }

            list.add(player);
        }

        picked = new int[10];
        isChecked = new boolean[10];
        picked[4] = 1;
        permu(1);

        System.out.println(maxScore);

    }

    private static void permu(int index) {

        if (index == 9) {
            int score = baseballPlay();

            maxScore = Math.max(score, maxScore);
            return;
        }

        for (int i = 2; i < 10; i++) {
            if (isChecked[i])
                continue;

            if(index >= 4)
                picked[index+1] = i;
            else
                picked[index] = i;
            isChecked[i] = true;
            permu(index + 1);
            isChecked[i] = false;
        }

    }

    private static int baseballPlay() {
        int order = 1;
        int score = 0;
        for (int i = 0; i < n; i++) {
            int outCount = 0;
            int[] inning = list.get(i);

            base = new int[5];
            while (true) {
                int num = picked[order];
                switch (inning[num]) {
                    case 0:
                        outCount++;
                        break;
                    case 1:
                        base[4] = base[3];
                        base[3] = base[2];
                        base[2] = base[1];
                        base[1] = 1;
                        break;
                    case 2:
                        base[4] = base[2] + base[3];
                        base[3] = base[1];
                        base[2] = 1;
                        base[1] = 0;
                        break;
                    case 3:
                        base[4] = base[1] + base[2] + base[3];
                        base[3] = 1;
                        base[2] = 0;
                        base[1] = 0;
                        break;
                    case 4:
                        base[4] = 1 + base[1] + base[2] + base[3];
                        base[3] = 0;
                        base[2] = 0;
                        base[1] = 0;
                        break;
                }

                order++;
                if(order == 10) order = 1;

                if (outCount == 3)
                    break;

                if (base[4] > 0) {
                    score += base[4];
                    base[4] = 0;
                }

            }
        }

        return score;
    }
}
