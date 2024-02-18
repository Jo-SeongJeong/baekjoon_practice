package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 16927 배열돌리기2
 *
 * 조건 n * m 배열 배열 원소는 반시계 방향으로 돌아간다 n 과 m 중 작은 것은 짝수 -> 작은 것 / 2의 몫 만큼 회전 n, m
 * :2 ~ 300 r (회전수) : 1 ~ 10^9
 *
 * 문제에서 구하고자 하는 것 주어진 배열에서 r번 회전했을 때의 배열 모습
 *
 * 문제 해결 프로세스 배열을 입력 받는다 영향을 받을 회전 인덱스 값에 대해서 짝을 맞춰 회전시킨다 회전 방향은 델타로 구하여 회전 회전 수
 * 가지치기 -> 회전 사이클은 r % (2 * (n + m - 2 - (4 * b)))번 하면 1바퀴 돔
 *
 *
 * 고려한 시간 복잡도 min(N, M) / r % (2 * (n + m - 2 - (4 * b)))*(n*r)
 */

public class BJ16927 {
    static int n;
    static int m;
    static int r;
    static int[][] array;
    static int[][] delta = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } }; // 하 우 상 좌
    static int min;
    static int box;

    public static void main(String[] args) throws Exception { // 메모리 : 32484kb, 시간 : 528ms
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        array = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        min = Math.min(n, m);
        box = min / 2;

        rotation();

        for (int j = 0; j < n; j++) {
            for (int k = 0; k < m; k++) {
                sb.append(array[j][k]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void rotation() {
        for (int b = 0; b < box; b++) {
            int cycle = r % (2 * (n + m - 2 - (4 * b)));
            int time = 0;

            while (time < cycle) {
                int startR = b;
                int startC = b;
                int endR = n - b;
                int endC = m - b;
                int bound = Math.max(endR, endC);
                int tempR = startR;
                int tempC = startC;

                for (int i = 0; i < 4; i++) {
                    for (int j = 1; j <= bound; j++) {
                        int nextR = tempR + delta[i][0] * j;
                        int nextC = tempC + delta[i][1] * j;

                        if (nextR >= startR && nextR < endR && nextC >= startC && nextC < endC) {
                            int temp = array[nextR][nextC];
                            array[nextR][nextC] = array[startR][startC];
                            array[startR][startC] = temp;
                        }

                        else if (nextR >= endR) {
                            tempR = nextR - 1;
                            break;
                        } else if (nextC >= endC) {
                            tempC = nextC - 1;
                            break;
                        } else if (nextR < startR) {
                            tempR = nextR + 1;
                            break;
                        } else if (nextC < startC) {
                            break;
                        }
                    }
                }
                time++;

            }

        }
    }

}
