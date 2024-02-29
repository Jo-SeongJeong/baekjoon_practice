package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 10971 외판원 순회2
 * 
 * 조건
 * 도시 번호 1 ~ N : 2 ~ 10
 * 도시들 사이에는 길이 있다(없을 수도 있음)
 * 한 번 갔던 도시로는 다시 갈 수 없다
 * 어느 한 도시에서 출발해 N개의 도시를 모두 거쳐 다시 원래의 도시로 돌아와야 함
 * 각 도시간 이동하는데 드는 비용 W[i][j] :i에서 j로 가는 데 드는 비용 -> 가중치, 1000000(까지)
 * 비용은 대칭적이지 않다 -> 단방향 그래프
 * i에서 j로 갈 수 없는 경우는 0
 * 
 * 문제에서 구하고자 하는 것
 * 가장 적은 비용을 들이는 순회에 필요한 최소 비용
 * 
 * 문제 해결 프로세스
 * dfs <- 시작점 위치에서 모두 해보자
 * 만약 중간에 최소값이 넘어가면 return해 가지치기
 * 
 * 고려한 시간복잡도
 * 10 * 10 * 10 * 10 = 100000
 * */

public class BJ10971 { // 메모리 : 13180kb, 시간 : 108ms
    static int n;
    static int[][] adjMatrix;
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;
    static int weight;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        adjMatrix = new int[n][n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++) {
            visited = new boolean[n];
            dfs(i, i, 0, 0);
        }
        System.out.println(min);
    }

    private static void dfs(int start, int current, int weight, int depth) {
        if(depth == n) {
            min = Math.min(min, weight);
            return;
        }

        if(weight > min) return;

        if(depth == n-1) {
            if(adjMatrix[current][start] == 0) return;
            if(visited[start]) return;
            dfs(start, start, weight + adjMatrix[current][start], depth+1);
        }
        else {
            for(int i = 0; i < n; i++) {
                if(adjMatrix[current][i] == 0) continue;
                if(visited[i]) continue;
                if(i == start) continue;

                visited[i] = true;
                dfs(start, i, weight + adjMatrix[current][i], depth+1);
                visited[i] = false;
            }
        }

    }
}
