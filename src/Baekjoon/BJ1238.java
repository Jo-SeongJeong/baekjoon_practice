package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 1238 파티
 *
 * 조건
 * 학생 수(마을 개수) N : 1 ~ 1000
 * 도로 개수 M : 1 ~ 10000
 * 해당 도로의 소요 시간 t(가중치) :
 * 파티에 참석 후, 다시 돌아가야 함
 * 도로는 단방향(단방향 그래프)
 * 시작과 끝이 같은 도로는 없음
 * 시작접과 한 도시 A에서 다른 도시 B로 가는 도로의 개수는 최대 1개
 * 모든 학생들은 이동 가능한 데이터만 입력
 *
 * 문제에서 구하고자 하는 것
 * N명의 학생들 중 오고 가는 데 가장 오래 걸리는 학생의 소요시간
 *
 * 문제 해결 프로세스
 * 다익스트라
 * 인접리스트를 2개 만들기(단방향이므로)
 * 시작점이 2인 경우로 다익스트라 돌려서 최단 거리 구하자
 * -> 우선 순위 큐로!
 *
 * 고려한 시간 복잡도
 *
 * * */

public class BJ1238 { // 메모리 : 21472kb, 시간 : 332ms
    static int x;

    static class Node {
        private int from;
        private int weight;

        public Node(int from, int weight) {
            this.from = from;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        ArrayList<Node>[] goAdjList = new ArrayList[n+1];
        ArrayList<Node>[] comeAdjList = new ArrayList[n+1];

        for(int i = 1; i < n+1; i++) {
            goAdjList[i] = new ArrayList<>();
            comeAdjList[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            goAdjList[t].add(new Node(f, w));
            comeAdjList[f].add(new Node(t, w));
        }
        
        // 갈 때 최단 거리
        int[] goDistance = new int[n+1];
        Arrays.fill(goDistance, Integer.MAX_VALUE);
        goDistance[x] = 0;
        dijkstra(goDistance, goAdjList);

        // 올 때 최단 거리
        int[] comeDistance = new int[n+1];
        Arrays.fill(comeDistance, Integer.MAX_VALUE);
        comeDistance[x] = 0;
        dijkstra(comeDistance, comeAdjList);

        int max = 0;
        for(int i = 1; i < n+1; i++) {
            if(i == x) continue;
            max = Math.max(max, goDistance[i] + comeDistance[i]);
        }
        System.out.println(max);
    }

    private static void dijkstra(int[] minDistance, List<Node>[] adjList) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.offer(new Node(x, 0));

        while(!pq.isEmpty()) {
            Node node = pq.poll();

            for(Node temp : adjList[node.from]) {
                if(minDistance[temp.from] > minDistance[node.from] + temp.weight) {
                    minDistance[temp.from] = minDistance[node.from] + temp.weight;
                    pq.offer(new Node(temp.from, minDistance[temp.from]));
                }
            }
        }
    }
}
