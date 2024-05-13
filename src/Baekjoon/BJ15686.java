package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 15686 치킨 배달
 *
 * 조건
 * N : 2 ~ 50 (행렬)
 * M : 1 ~ 13 (최대 남을 치킨집 개수)
 * 0 : 빈칸
 * 1 : 집(r,c), 1 ~ 2N
 * 2 : 치킨집(r,c), M ~ 13
 * 두 칸 사이의 거리 : |r2- r1| + |c2 - c1|
 * 치킨 거리 : 한 집 기준, 치킨집과의 거리가 가장 가까운 거리
 *
 * 문제에서 구하고자 하는 것
 * 도시의 치킨거리의 최소값
 *
 * 문제 해결 프로세스
 * 배열을 입력받을 때, 집과 치킨집에 대한 List를 만들어 놓는다 (이 리스트는 좌표값들)
 * 치킨집 List 중 M개에 대한 조합을 구하고, 그 때마다 집 List에 있는 치킨 거리를 구한다
 * 모든 조합 중 치킨거리 최소값을 찾는다
 *
 * 고려한 시간 복잡도
 * 50 * 50(입력) + 13C6(최악의 조합 경우) * 100(집 최대) * 6(횟수) 434,500 = 1초 가능
 * */

public class BJ15686 { // 메모리 : 14964kb, 시간 : 176ms
    static int n;
    static int m;
    static int result = Integer.MAX_VALUE;

    static int[][] map;
    static List<int[]> home;
    static List<int[]> chicken;
    static int[][]  picked;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());


        home = new ArrayList<>();
        chicken = new ArrayList<>();

        map = new int[n+1][n+1];
        for(int i = 1; i < n+1; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 1; j < n+1; j++) {

                map[i][j] = Integer.parseInt(st.nextToken());
                int[] lotation = new int[2];

                if(map[i][j] == 1) {
                    lotation[0] = i;
                    lotation[1] = j;
                    home.add(lotation);
                }

                else if(map[i][j] == 2) {
                    lotation[0] = i;
                    lotation[1] = j;
                    chicken.add(lotation);
                }
            }
        }

        picked = new int[m][2];
        combi(0, 0);

        System.out.println(result);


    }

    private static void combi(int index, int r) {
        if(r == m) {
            int temp = 0;

            for(int i = 0; i < home.size(); i++) {
                int chickenDis = Integer.MAX_VALUE;

                for(int j = 0; j < m; j++) {
                    int distance = Math.abs(home.get(i)[0] - picked[j][0]) + Math.abs(home.get(i)[1] - picked[j][1]);

                    if(chickenDis > distance) {
                        chickenDis = distance;
                    }
                }

                temp += chickenDis;
            }

            if(result > temp) result = temp;
            return;
        }

        for(int i = index; i < chicken.size(); i++) {
            picked[r]= chicken.get(i);
            combi(i+1, r+1);
        }
    }
}
