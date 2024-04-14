package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 1753 최단경로
 *
 * 조건
 * 방향 그래프
 * 간선 가중치 : 1 ~ 10
 * 정점 개수 v : 1 ~ 20000
 * 간선 개수 e : 300000
 * 시작점 k : 1 ~ v
 *
 * 문제에서 구하고자 하는 것
 * 시작점이 주어졌을 때, 각각의 노드까지 가는 최단경로(가중치 합), 자신은 0
 *
 * 문제 해결 프로세스
 * 다익스트라
 * 1. 클래스를 만들자 -> 시작점은 정해져 있으니 도착지와 가중치로
 * 2. 우선순위 큐를 통해 항상 최소값을 갱신하자 (우선순위는 가중치 순)
 * 3. 가장 우선순위가 높은 노드를 poll
 * 4. 현재까지의 최소값으로 저장되어 있는 정점의 거리 > 뽑은 노드까지의 최소 거리 + 뽑은 노드로부터 도착지의 가중치 인경우
 *  갱신, 큐에 넣기
 * 5. 큐가 빌때까지 계속!
 *
 * 고려한 시간 복잡도
 * 20000 * 20000 = 400000000
 * */

public class BJ1753 {
    static class Node {
        int from;
        int weight;

        public Node(int from, int weight) {
            this.from = from;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        List<Node>[] adjList = new ArrayList[v+1];

        for(int i = 1; i < v+1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[to].add(new Node(from, weight));
        }

        int[] distance = new int[v+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);

        pq.offer(new Node(start, 0)); // 시작 할 값 <- 도착지로 봐서, 여기에 뻗어있는 간선들부터 볼거임

        while(!pq.isEmpty()) {
            Node node = pq.poll(); // 시작점

            for(Node temp : adjList[node.from]) { // 뽑은 노드 기준 연결되어 있는 정점들을 탐색
                if(distance[temp.from] > distance[node.from] + temp.weight) { // 현재까지의 최소값으로 저장되어 있는 정점의 거리 > 뽑은 노드까지의 최소 거리 + 뽑은 노드로부터 도착지의 가중치
                    distance[temp.from] = distance[node.from] + temp.weight;
                    pq.offer(new Node(temp.from, distance[temp.from]));
                }
            }
        }

        for(int i = 1; i < v+1; i++) {
            if(distance[i] == Integer.MAX_VALUE) sb.append("INF\n");
            else sb.append(distance[i] + "\n");
        }

        System.out.println(sb);
    }
}
