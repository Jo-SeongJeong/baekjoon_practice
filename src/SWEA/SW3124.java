package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SWEA 3124 최소 스패닝 트리
 * <p>
 * 조건
 * 입력으로 주어지는 그래프는 하나의 연결 그래프임을 보장
 * testCase 수 : 1 ~ 10
 * 정점 개수 V : 1 ~ 100000
 * 간선 개수 E : 1 ~ 200000
 * C : 가중치 (-1000000 < C < 1000000)
 * <p>
 * 문제에서 구하고자 하는 것
 * 최소 스패닝 트리의 가중치
 * <p>
 * 문제 해결 프로세스
 * 최소스패닝 트리(kruskal)
 * 가중치 기준으로 오름차순 정렬을 한다
 * 각 정점에 대해 서로소 집합을 생성한다
 * find : 정점이 속한 집합의 대표자(루트(부모))를 찾는다
 * 만약 이 결과가 서로 같다면 싸이클이므로 선택하지 않는다
 * 결과가 서로 다르다면 Union하여 루트를 합친다
 * 반복하여 간선의 개수가 V-1개가 될 때까지 진행한다
 * <p>
 * 고려한 시간 복잡도
 * 99999..?
 */
public class SW3124 { // 메모리 : 125120kb, 시간 : 2159ms
    static class Edge {
        int a;
        int b;
        long c;

        public Edge(int a, int b, long c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    static int v;
    static int e;
    static Edge[] edgeList;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase; t++) {
            st = new StringTokenizer(br.readLine());

            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            edgeList = new Edge[e];

            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                long c = Integer.parseInt(st.nextToken());

                edgeList[i] = new Edge(a, b, c);
            }

            Arrays.sort(edgeList, (a, b) -> Long.compare(a.c, b.c));

            makeSet();
            long sum = 0;
            int count = 0;
            for(int i = 0; i < e; i++) {
                if(!union(edgeList[i].a, edgeList[i].b)) continue;
                sum += edgeList[i].c;
                count++;

                if(count == v-1)
                    break;
            }

            System.out.println("#" + t + " " + sum);
        }
    }

    // 서로소 집합 만들기
    private static void makeSet() {
        parents = new int[v+1];
        for(int i = 1; i < v+1; i++) {
            parents[i] = i;
        }
    }

    private static int find(int a) {
        if(parents[a] == a) return a; // 루트와 a가 같으면 바로 return

        return parents[a] = find(parents[a]); // 다르면 root 찾으러 가

    }
    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) // 서로 root가 같으면, 싸이클 생성이므로 합치지 않음
            return false;

        parents[aRoot] = bRoot;
        return true;
    }
}
