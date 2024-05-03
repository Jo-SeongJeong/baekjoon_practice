package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 1916 최소비용 구하기
 *
 * 조건
 * 도시 개수 N : 1 ~ 1000
 * 버스 개수 M : 1 ~ 100000 (단방향 가중치 그래프)
 *
 * 문제에서 구하고자 하는 것
 * 출발점에서 도착지까지 가는데 드는 최소 비용(최소 버스 비용)
 *
 * 문제 해결 프로세스
 * 다익스트라
 * 1. 인접 리스트 생성
 * 2. 출발지로부터 다른 도시까지의 최소비용을 담을 배열 생성
 *  본인은 0, 나머지는 무한으로 초기화
 * 3. 우선순위큐로 풀자 (우선 순위는 가중치)
 *  현재 배열의 값 > 도착지 배열의 값 + 현재 가중치
 *      갱신 후 큐에 담기(현재 배열의 값, 갱신된 가중치)
 *
 * 고려한 시간 복잡도
 *
 * */

public class BJ1916 { // 메모리 : 56640kb, 시간 : 624ms
    static int n;
    static int[] distance;
    static List<Node>[] list;
    public static class Node {
        int to;
        int weight;

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return to + " " + weight;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int INF = Integer.MAX_VALUE;
        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        list = new List[n+1];

        for(int i = 1; i < n+1; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list[from].add(new Node(to, weight));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int finish = Integer.parseInt(st.nextToken());

        distance = new int[n+1];
        Arrays.fill(distance, INF);
        distance[start] = 0;

//        System.out.println(Arrays.toString(list));

        findMin(start, 0);

        System.out.println(distance[finish]);

//        System.out.println(Arrays.toString(distance));
    }

    private static void findMin(int start, int temp) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b)-> a.weight - b.weight);

        boolean[] visited = new boolean[n+1];

        pq.offer(new Node(start, temp));

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if(visited[node.to]) continue;
            visited[node.to] = true;
            for(Node current : list[node.to]) {
                if(distance[current.to] > distance[node.to] + current.weight) {
                    distance[current.to] = distance[node.to] + current.weight;
                    pq.offer(new Node(current.to, distance[current.to]));
                }
            }
        }
    }
}
