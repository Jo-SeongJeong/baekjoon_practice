package Programmers;

/**
 * 프로그래머스 공원
 *
 * 조건
 * 깔 수 있는 돗자리들 : mats (1 ~ 10)
 * 돗자리 크기 : 1 ~ 20
 * 자리 배치도 : park(1 ~ 250)
 * 빈자리 : -1
 * 깔린 자리 : 알파벳
 *
 * 문제에서 구하고자 하는 것
 * 공원에 깔 수 있는 가장 큰 돗자리
 *
 * 문제 해결 프로세스
 * 자리 하나씩 완탐 (우, 하 방향으로 깔아보자)
 *
 * 고려한 시간 복잡도
 * 250 * 10 * 20 = 50000
 */

public class ProPark {
    static int max;

    public static void main(String[] args) {
        int[] mats = new int[]{5, 3, 2};
        String[][] park = new String[][] {{"A", "A", "-1", "B", "B", "B", "B", "-1"}, {"A", "A", "-1", "B", "B", "B", "B", "-1"}, {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"}, {"D", "D", "-1", "-1", "-1", "-1", "E", "-1"}, {"D", "D", "-1", "-1", "-1", "-1", "-1", "F"}, {"D", "D", "-1", "-1", "-1", "-1", "E", "-1"}};

        int answer = solution(mats, park);

        System.out.println(answer);
    }

    public static int solution(int[] mats, String[][] park) {
        int answer = 0;

        for(int i = 0; i < park.length; i++) {
            for(int j = 0; j < park[0].length; j++) {
                if(!park[i][j].equals("-1")) continue;
                findMySeat(i, j, mats, park);
            }
        }

        answer = max == 0 ? -1 : max;
        return answer;
    }

    private static void findMySeat(int r, int c, int[] mats, String[][] park) {
        for(int i = 0; i < mats.length; i++) {
            int count = 0;
            A: for(int j = r; j < r + mats[i]; j++) {
                for(int k = c; k < c +mats[i]; k++) {
                    if(j < 0 || j >= park.length || k < 0 || k >= park[0].length) break A;
                    if(!park[j][k].equals("-1")) break A;
                    count++;
                }
            }
            if(count != mats[i] * mats[i]) continue;
            max = Math.max(max, mats[i]);
        }
    }
}
