package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 17472 다리 만들기 2
 *
 * 조건
 * 지도 크기 N * M : 3 ~ 100
 * 섬의 개수 : 2 ~ 6
 * 0 : 바다
 * 1 : 땅
 * 섬 : 땅이 상하좌우로 붙어있는 덩어리
 * 다리 설치 : 바다에만 건설 가능
 * 다리 길이 : 다리가 격자에서 차지하는 칸의 수
 * 연결 : 섬 A에서 B로 갈 수 있을 때, A, B는 연결된 것
 * 다리의 양 끝은 섬과 인접한 바다 위에 있어야 함
 * 한 다리의 방향이 중간에 바뀔 수 x -> 방향은 가로 / 세로이어야 함
 * 다리의 길이는 2 이상이어야 함
 * 가로 다리 : 다리 양 끝이 가로 방향으로 섬과 인접해야 함
 * 세로 다리 : 다리 양 끝이 세로 방향으로 섬과 인접해야 함
 * 섬 A와 B를 연결하는 다리가 중간에 섬 C와 인접한 바다를 지나가는 경우 : C는 A, B와 연결되어있는 것이 아님
 * 다리는 교차할 수 있음 : 교차하는 다리를 계산하는 경우, 각 칸이 다리의 길이에 모두 포함되어야 함
 * 1:1로 연결 될 필요는 없음
 *
 * 문제에서 구하고자 하는 것
 * 모든 섬을 연결하는 다리 길이의 최솟값 구하기
 *
 * 문제 해결 프로세스
 * 1. 섬에 번호를 붙이자(노드 번호)
 *  flood fill(bfs)로 섬을 구한다
 * 2. 섬 - 섬 연결 가능한 다리를 모두 만들자(간선 만들기)
 *  해당 섬 중, 다음 칸이 바다인 경우, 다리를 놓아보자
 *  다음 칸이 바다인 곳으로 방향 정하고, 해당 방향으로 섬이 나올 때까지 쭉 직진, 경계 벗어나면 다리를 놓을 수 없는 것
 *  섬이 나오면, 간선리스트에 저장
 * 3. 간선리스트를 통해 kruskal 알고리즘으로 해결하자(가중치 오름차순)
 *  union-find 통해 모든 섬이 방문되면, 그것이 최소값
 *
 * 고려한 시간 복잡도
 * 100 * 6 + 4 * 100 * 6 + log(6) = 3000
 *
 * */

public class BJ17472 { // 메모리 : 18388kb, 시간 : 232ms
    static int n;
    static int m;
    static int[][] map;
    static boolean[][] visited;
    static int[][] delta = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static List<int[]> edgeList;

    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int landNum = 1;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];
        edgeList = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(visited[i][j]) continue;
                if(map[i][j] != 1) continue;
                findLand(i, j, landNum);
                landNum++;
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 0) continue;
                addBridge(i, j, map[i][j]);
            }
        }

        int min = 0;
        int count = 0;
        Collections.sort(edgeList, (a, b) -> a[2] - b[2]);

        makeSet(landNum);

        for(int[] idx : edgeList) {
            if(!union(idx[0], idx[1])) continue;
            min += idx[2];
            count++;

            if(count == landNum-1) break;

        }

        if(min == 0 || count != landNum-2) min = -1;

        System.out.println(min);

        for(int[] idx : map) {
            System.out.println(Arrays.toString(idx));
        }
        for(int[] idx : edgeList) {
            System.out.println(Arrays.toString(idx));
        }

    }

    // 섬 라벨링(번호로 나누기)
    private static void findLand(int sr, int sc, int landNum) {
        Queue<int[]> queue = new ArrayDeque<>();

        visited[sr][sc] = true;
        map[sr][sc] = landNum;
        queue.offer(new int[] {sr, sc});

        while(!queue.isEmpty()) {
            int[] loc = queue.poll();

            for(int d = 0; d < 4; d++) {
                int nr = loc[0] + delta[d][0];
                int nc = loc[1] + delta[d][1];

                if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if(visited[nr][nc]) continue;
                if(map[nr][nc] != 1) continue;

                visited[nr][nc] = true;
                map[nr][nc] = landNum;
                queue.offer(new int[] {nr, nc});
            }
        }
    }

    // 다리 놓기(간선 리스트 구하기)
    private static void addBridge(int r, int c, int to) {
        for(int d = 0; d< 4; d++) {
            int nr = r + delta[d][0];
            int nc = c + delta[d][1];

            if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
            if(map[nr][nc] != 0) continue;

            int weight = 1;
            while(nr >= 0 && nr < n && nc >= 0 && nc < m) {
                if(map[nr][nc] != 0) {
                    if(weight-1 <= 1) break;

                    edgeList.add(new int[] {map[r][c], map[nr][nc], weight-1});
                    break;
                }

                weight++;
                nr = r + delta[d][0] * weight;
                nc = c + delta[d][1] * weight;
            }
        }

    }

    // 크루스칼 : union-find
    private static void makeSet(int v) {
        parents = new int[v];

        for(int i = 1; i < v; i++) {
            parents[i] = i;
        }

    }

    private static int find(int a) {
        if(parents[a] == a) return a;

        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;

        parents[aRoot] = bRoot;
        return true;
    }
}
