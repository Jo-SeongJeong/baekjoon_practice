package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 17471 게리맨더링
 *
 * 조건
 * 구역 개수 N개(1번부터) : 2 ~ 10
 * 구역의 인구 수 : 1 ~ 100
 * 구역은 두 개의 선거구로 나누어야 한다
 * 각 구역은 두 선거구 중 하나에 포함되어야 한다
 * 선거구는 구역을 적어도 하나 포함해야 한다
 * 한 선거구에 포함되어 있는 구역은 모두 연결되어 있어야 한다
 * 연결 : 구역 A에서 B로 갈 수 있을 때(중간에 인접한 구역을 통해 거쳐가도 됨)
 * 인접한 구역 : 모두 같은 선거구에 포함된 구역이어야 함
 *
 * 문제에서 구하고자 하는 것
 * 각 구역들을 두 개의 선거구로 나누었을 때 인구 차이가 최소가 되는 값 구하기
 * 두 선거구로 나눌 수 없을 때 : -1 출력
 *
 * 문제 해결 프로세스
 * 인접리스트를 생성
 * 정점에 대한 부분집합 생성
 * 뽑히지 않은 정점들에 대한 집합을 하나 더 생성
 * 두 집합에 대해 각각 DFS로 정점을 이어봄
 * 만약 모두 이어지지 않으면 return
 * 모두 이어지면 해당 인구수를 return해 더해봄
 * 최솟값 갱신
 *
 * 고려한 시간 복잡도 2^10 * 10 = 10240
 *
 */

public class BJ17471 { // 메모리 : 12732kb, 시간 : 96ms
    static int n;
    static int[] population;
    static int[][] adjMatrix;
    static boolean[] isChecked;
    static List<Integer> sectionA;
    static List<Integer> sectionB;
    static boolean[] adjCheck;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        population = new int[n + 1];
        isChecked = new boolean[n + 1];
        adjMatrix = new int[n + 1][n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                int adjV = Integer.parseInt(st.nextToken());
                adjMatrix[i][adjV] = 1;
                adjMatrix[adjV][i] = 1;
            }
        }

        subset(1);

        if(min == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        System.out.println(min);
    }

    private static void subset(int length) {
        if (length == n + 1) {
            sectionA = new ArrayList<>();
            sectionB = new ArrayList<>();

            for(int i = 1; i < n+1; i++) {
                if(isChecked[i])
                    sectionA.add(i);
                else
                    sectionB.add(i);
            }

            if(sectionA.size() == 0 || sectionB.size() == 0) return;

            adjCheck = new boolean[sectionA.size()];
            adjCheck[0] = true;
            int cnt = sectionCheck(sectionA.get(0), sectionA, adjCheck);


            adjCheck = new boolean[sectionB.size()];
            adjCheck[0] = true;
            cnt += sectionCheck(sectionB.get(0), sectionB, adjCheck);

            if(cnt == n) substract(0, 0);

            return;
        }

        isChecked[length] = true;
        subset(length + 1);
        isChecked[length] = false;
        subset(length + 1);
    }

    private static int sectionCheck(int current, List<Integer> list, boolean[] adjCheck) {
        int cnt = 1;

        for(int i = 0; i < list.size(); i++) {
            if(adjMatrix[current][list.get(i)] == 0 || adjCheck[i]) continue;
            adjCheck[i] = true;
            cnt += sectionCheck(list.get(i), list, adjCheck);
        }

        return cnt;
    }

    private static void substract(int a, int b) {

        for(int i = 0; i < sectionA.size(); i++) {
            a += population[sectionA.get(i)];
        }

        for(int i = 0; i < sectionB.size(); i++) {
            b += population[sectionB.get(i)];
        }

        int sub = Math.abs(a - b);
        min = Math.min(sub, min);
    }
}