package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 2252 줄 세우기
 *
 * 조건
 * 학생 인원 수 N : 1 ~ 32000
 * 키 비교 횟수 M : 1 ~ 100000
 * A B <- A가 B 앞에 서야 한다
 *
 * 문제에서 구하고자 하는 것
 * 학생들을 앞에서부터 줄을 세운 결과 출력
 *
 * 문제 해결 프로세스
 * 인접 행렬을 만든다
 * 입력에 대해 to from 형식으로 받음
 * 들어오는 간선에 대한 depth를 셈
 * 0인 것만 찾아서 BFS
 *
 * 고려한 시간 복잡도
 * 32000 * 32000 최대..?
 *
 * */

public class BJ2252 { // 메모리 : 46184kb, 시간 : 424ms
    static int n;
    static int m;
    static List<Integer>[] adjList;
    static Queue<Integer> queue;
    static int[] isChecked;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adjList = new List[n+1];

        for(int i = 1; i < n+1; i++)
            adjList[i] = new ArrayList<>();

        isChecked = new int[n+1];

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());

            adjList[to].add(from);

            isChecked[from] += 1;
        }

        bfs();

        System.out.println(sb);

    }

    private static void bfs() {
        queue = new ArrayDeque<>();

        for(int i = 1; i < n+1; i++) {
            if(isChecked[i] == 0)
                queue.offer(i);
        }

        while(!queue.isEmpty()) {
            int current = queue.poll();
            sb.append(current + " ");

            for(int i = 0; i < adjList[current].size(); i++) {
                isChecked[adjList[current].get(i)] -= 1;
                if(isChecked[adjList[current].get(i)] == 0) {
                    queue.offer(adjList[current].get(i));
                }
            }
        }
    }
}
