package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 13023 ABCDE
 *
 * 조건
 * 사람 수(정점) N (0번부터) : 5 ~ 2000
 * 친구 관계의 수(간선) M : 1 ~ 2000
 *
 * 문제에서 구하고자 하는 것
 * 그래프의 한 정점을 기준으로 탐색했을 때, 5개의 정점으로 이동이 가능한지 확인
 * 가능하면 1, 불가능하면 0 출력
 *
 * 문제 해결 프로세스
 * 인접 행렬은 불가
 * 인접 리스트를 생성한다
 * 시작점 0번부터 N-1번까지 탐색 진행
 * 탐색 할 때 재귀를 호출한다면 cnt+1
 * 만약 cnt가 5가 되면 break 후 정답 출력
 * 끝까지 돌았을 떄 5가 안되면 0 출력
 *
 * 고려한 시간 복잡도
 * 2000 * 2000 = 4000000
 * */

public class BJ13023 { // 메모리 : 17416kb, 시간 : 228ms
    static int n;
    static int m;
    static List<Integer>[] adjList;
    static boolean[] isChecked;
    static int result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adjList = new List[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());

            adjList[to].add(from);
            adjList[from].add(to);
        }


        for(int i = 0; i < n; i++) {
            isChecked = new boolean[n];
            findFriends(i, 1);
            if(result == 1) break;
        }

        System.out.println(result);
    }

    private static void findFriends(int to, int cnt) {
        if(cnt == 5) {
            result = 1;
            return ;
        }


        for(int i = 0; i < adjList[to].size(); i++) {
            if(isChecked[adjList[to].get(i)]) continue;

            isChecked[to] = true;
            findFriends(adjList[to].get(i), cnt+1);
            isChecked[to] = false;
        }

    }
}
