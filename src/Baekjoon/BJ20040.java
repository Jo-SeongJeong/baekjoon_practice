package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 20040 사이클 게임
 *
 * 조건
 * 두 명의 플레이어가 차례대로 돌아가며 진행
 * 평면 상의 점(정점) n : 3 ~ 500000(0부터 사작)
 * 차례를 나타내는 정수 m : 3 ~ 1000000
 * 매 차례마다 플레이어가 두 점을 선택해서 선을 그림(간선 그리기)
 * 같은 선분은 또 그릴 수 없지만, 교차는 가능
 * 사이클 완성하면 게임 종료
 * 사이클 : 사이클에 속한 임의의 선분의 한 끝점에서 출발하여 모든 선분을 한 번씩만 지나서 다시 돌아오는 것
 *
 * 문제에서 구하고자 하는 것
 * 사이클이 몇번째 차례에서 완성되는지(미완인 경우 0)
 *
 * 문제 해결 프로세스
 * union-find
 * 1. 각 정점에 대한 서로소 집합을 만들자 -> parent 만들기(본인이 본인 정점의 root)
 * 2. find(a) -> 각 정점의 root 찾기
 * 3. union(a, b) -> 만약 각 정점의 루트가 같지 않다면 합치기!
 * 이 과정에서 union이 불가하면 게임 종료
 *
 * 고려한 시간 복잡도
 * log500000
 * */

public class BJ20040 { // 메모리 : 162516kb, 시간 : 484ms
    static int[] parents;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parents = new int[n];

        setParent();
        int count = 1;
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(!union(a, b)) {
                System.out.println(count);
                return;
            }
            count++;
        }

        System.out.println(0);
    }
    private static void setParent() {
        for(int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    private static int find(int a) {
        if(a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }
    private static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB) return false;

        parents[rootA] = rootB;
        return true;
    }
}
