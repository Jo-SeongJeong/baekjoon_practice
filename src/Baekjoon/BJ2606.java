package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2606 { // 메모리 : 11768kb, 시간 : 76ms
    static int n;
    static int m;
    static List<Integer>[] adjList;
    static int count;
    static Queue<Integer> queue;
    static boolean[] isChecked;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        isChecked = new boolean[n+1];
        adjList = new List[n+1];

        for(int i = 1; i < n+1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            adjList[to].add(from);
            adjList[from].add(to);
        }

        bfs(1);
        System.out.println(count);
    }

    private static void bfs(int current) {
        queue = new ArrayDeque<>();
        queue.offer(current);
        isChecked[current] = true;

        while(!queue.isEmpty()) {
            int temp = queue.poll();

            for(int index : adjList[temp]) {
                if(isChecked[index]) continue;

                queue.offer(index);
                isChecked[index] = true;
                count++;
            }
        }
    }
}
