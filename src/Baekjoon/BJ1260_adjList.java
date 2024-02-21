package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 1260 DFS와 BFS
 *
 * 조건
 * 정점의 개수 N : 1 ~ 1000
 * 간선의 개수 M : 1 ~ 10000
 * 탐색을 시작할 정점의 번호 V : 1번부터 N번까지
 * 간선은 양 방향
 * 정점이 여러개라면 작은 순으로 탐색
 *
 * 문제에서 구하고자 하는 것
 * 그래프의 DFS 결과와 BFS 수행 결과 출력
 *
 * 문제 해결 프로세스
 * 인접리스트를 만든다 (양방향이므로 i,j / j,i에 모두 1을 넣음)
 * 리스트 정렬(작은 것부터 확인하기 위해)
 * isChecked를 통해 방문한 곳은 pass
 * 재귀를 통해 DFS 진행
 *
 * Queue를 통해 BFS 진행
 *
 *
 * 고려한 시간 복잡도
 * 1000 * 1000 * 2 = 2000000 2초 충분
 *
 * */

public class BJ1260_adjList { // 메모리 : 17036kb, 시간 : 180ms
    static int n;
    static int m;
    static int v;
    static List<Integer>[] adjList;
    static boolean[] isChecked;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        adjList = new List[n+1];

        for(int i = 1; i < n+1; i++) {
            adjList[i] = new ArrayList<>();
        }


        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            adjList[start].add(end);
            adjList[end].add(start);
        }

        for(int i = 1; i < n+1; i++) {
            Collections.sort(adjList[i]);
        }

        for(int i = 1; i < adjList.length; i++) {
            for(int j = 0; j < adjList[i].size(); j++) {
                System.out.print(adjList[i].get(j) + " ");
            }
            System.out.println();
        }

        sb = new StringBuilder();
        isChecked = new boolean[n+1];
        dfs(v);
        System.out.println(sb);

        sb = new StringBuilder();
        isChecked = new boolean[n+1];
        bfs(v);
        System.out.println(sb);

    }

    private static void dfs(int v) {
        sb.append(v).append(" ");
        isChecked[v] = true;
        for(int i = 0; i < adjList[v].size(); i++) {
            if(isChecked[adjList[v].get(i)]) continue;

            dfs(adjList[v].get(i));
        }

        return;
    }

    private static void bfs(int v) {
        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(v);
        isChecked[v] = true;
        sb.append(v).append(" ");

        while(!queue.isEmpty()) {
            int current = queue.poll();

            for(int i = 0; i < adjList[current].size(); i++) {
                if(!isChecked[adjList[current].get(i)]) {
                    queue.offer(adjList[current].get(i));
                    isChecked[adjList[current].get(i)] = true;
                    sb.append(adjList[current].get(i)).append(" ");
                }
            }

        }
    }
}
