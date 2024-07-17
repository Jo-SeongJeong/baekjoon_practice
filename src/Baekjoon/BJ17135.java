package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 17135 캐슬 디펜스
 *
 * 조건 맵 N x M : 각 3 ~ 15 각 칸에 있는 적의 수는 최대 1 N+1 행은 성이 있다고 가정 궁수 3명 배치 (1칸에 1명
 * 가능) 궁수 1명 당 적 1명 공격 가능, 모든 궁수는 동시에 공격 공격 가능 거리 : D -> |r1 - r2| + |c1 - c2|
 * (1 ~ 10) 궁수가 공격하는 적 : D 이하인 적 중 가장 가까운 적, 거리가 같다면 가장 왼쪽에 있는 적 같은 적은 여러 궁수에게
 * 공격당할 수 있다(공격 받으면 사망) 공격이 끝나면 적 이동(아래로 한 칸 이동), 성이 있는 칸으로 이동하면 게임에서 제외 모든 적이
 * 격자판에서 제외되면 게임 종료 0 : 빈칸 1 : 적
 *
 * 문제에서 구하고자 하는 것 궁수의 공격으로 제거할 수 있는 적의 최대 수
 *
 * 문제 해결 프로세스 궁수 위치에 대한 조합을 뽑는다(nC3) n-1의 0열부터 적이 있다면 해당 위치와 궁수 위치를 구한다 최소값, 거리가
 * D 이하면 갱신하는 과정을 (N-1-D)번 반복(어차피 최단 거리는 직선 거리이기 때문) 위 과정을 3번 반복 정해진 좌표 3개를 비교,
 * 좌표값이 중복인 경우 고려하여 죽인 적 cnt 한칸씩 민다, 만약 모든 적이 없어지면 종료
 *
 * 고려한 시간 복잡도 15C3 * 3 * 15 *15 = 307,125
 *
 */

public class BJ17135 { // 메모리 : 10884kb, 시간 : 268ms
    static int n;
    static int m;
    static int d;
    static int[][] map;
    static int[][] picked = new int[3][2];
    static List<int[]> list;
    static int max = 0;
    static int enemy;
    static int member;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1)
                    enemy++;
            }
        }

        member = enemy;

        combi(0, 0);

        System.out.println(max);
    }

    private static void combi(int index, int depth) {
        if (depth == 3) {
            enemy = member;
            int[][] temp = new int[n][m];

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    temp[i][j] = map[i][j];
                }
            }

            int kill = shoot(temp);

            max = Math.max(kill, max);

            return;
        }

        for (int i = index; i < m; i++) {
            picked[depth][0] = n;
            picked[depth][1] = i;

            combi(i + 1, depth + 1);
        }

    }

    private static int shoot(int[][] temp) {
        int kill = 0;
        list = new ArrayList<>();

        while (enemy != 0) {
            for (int l = 0; l < 3; l++) {
                int min = Integer.MAX_VALUE;
                int[] loc = new int[2];
                loc[0] = n+1;
                loc[1] = m;
                for (int i = n - 1; i > n - 1 - d; i--) {
                    if(i < 0) break;

                    for (int j = 0; j < m; j++) {
                        if (temp[i][j] == 1) {
                            int distance = Math.abs(picked[l][0] - i) + Math.abs(picked[l][1] - j);

                            if (distance <= d && distance < min) {
                                min = distance;
                                loc[0] = i;
                                loc[1] = j;
                            }
                            else if(distance <= d && distance == min) {
                                if(j < loc[1]) {
                                    min = distance;
                                    loc[0] = i;
                                    loc[1] = j;
                                }

                            }

                        }
                    }
                }
                if(loc[0] == n+1 && loc[1] == m) continue;
                list.add(loc);
            }

            int cnt = 0;

            for (int i = 0; i < list.size(); i++) {
                if (temp[list.get(i)[0]][list.get(i)[1]] == 1) {
                    temp[list.get(i)[0]][list.get(i)[1]] = 0;
                    cnt++;
                }
            }

            list.clear();
            kill += cnt;

            temp = move(temp);

        }

        return kill;
    }

    private static int[][] move(int[][] temp) {
        enemy = 0;
        for (int i = n - 1; i > 0; i--) {
            for (int j = 0; j < m; j++) {
                temp[i][j] = temp[i - 1][j];
            }
        }

        for (int i = 0; i < m; i++) {
            temp[0][i] = 0;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (temp[i][j] == 1)
                    enemy++;
            }
        }
        return temp;
    }

}
