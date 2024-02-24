package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SWEA 1238 Contact
 *
 * 조건
 * 최대 인원 : 100(정점) -> 100 x 100 인접행렬
 * 번호 : 1 ~ 100
 * 중간 중간 비어있는 번호가 있을 수 있다
 * 이미 연락을 받은 상태이면 pass
 * 연락을 못 받는 사람이 있을 수 있다
 * 입력은 from, to의 쌍으로 구성된다
 *
 * 문제에서 구하고자 하는 것
 * BFS의 가장 마지막 level 중 가장 높은 숫자 구하기
 *
 * 문제 해결 프로세스
 * 인접행렬에 대해 BFS 탐색을 진행, 한 level에 대한 순회를 진행할 때 그 중 최대값을 구해놓는다
 * 제일 마지막에 구해진 최대값이 정답
 *
 * 고려한 시간 복잡도
 * 100 x 100 -> 10000
 * */

public class SW1238 { // 메모리 : 18012kb, 시간 : 114ms
    static int[][] adjMatrix;
    static int edge;
    static int start;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int t = 1; t <= 10; t++) {
            st = new StringTokenizer(br.readLine());
            edge = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());
            adjMatrix = new int[101][101];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < edge/2; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                adjMatrix[from][to] = 1;
            }

            answer = bfs(start);

            System.out.println("#" + t + " " + answer);
        }
    }
    private static int bfs(int s) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] isChecked = new boolean[101];
        int max = 0;

        queue.offer(s);
        isChecked[s] = true;
        int size = 1;
        while(!queue.isEmpty()) {
            int len = size;
            size = 0;
            int temp = 0;
            for(int i = 0; i < len; i++) {
                int current = queue.poll();
                temp = Math.max(temp, current);
                for(int j = 1; j < 101; j++) {
                    if(!isChecked[j] && adjMatrix[current][j] == 1) {
                        isChecked[j] = true;
                        queue.offer(j);
                        size++;
                    }
                }
            }
            max = temp;
        }
        return max;
    }
}
