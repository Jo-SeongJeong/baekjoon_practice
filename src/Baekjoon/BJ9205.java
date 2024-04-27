package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 9205 맥주 마시면서 걸어가기
 *
 * 조건
 * 맥주 한 박스 : 맥주 20개
 * 맥주 1병 -> 50미터
 * 50미터를 가려면 그 직전에 맥주 한 병을 마셔야 함
 * 맥주는 중간에 살 수 있음 이 때 빈병도 버릴 수 있음
 * 박스에 들어있는 맥주는 20병을 넘을 수 없음
 * 편의점을 나선 직후도, 50미터를 가기 전 맥주 한 병을 마셔야 함
 * 테스트케이스 수 t : 1 ~ 50
 * 맥주를 파는 편의점 수 n : 0 ~ 100
 * 좌표 x, y : -32768 ~ 32767
 * 두 좌표 사이의 거리 = |x1 - x2| + |y1 - y2|
 *
 * 문제에서 구하고자 하는 것
 * 페스티벌에 갈 수 있으면 happy, 갈 수 없으면 sad 출력
 *
 * 문제 해결 프로세스
 * 상근이 집을 출발지로 잡는다
 * 나머지 좌표는 list에 저장한다
 * 리스트를 순회하며 거리가 1000이하이면, 해당 위치로 이동하며 페스티벌 좌표로 도착할 수 있으면 happy
 * 안되면 sad
 * 어떻게 가는지에 따라 다를 수도 있으니, 방문 배열을 잘 유지, 초기화 해보자
 *
 * 고려한 시간 복잡도
 * 101 * 101 * 50 = 510050
 * */

public class BJ9205 { // 메모리 : 16584kb, 시간 : 192ms
    static int n;
    static int er;
    static int ec;
    static List<int[]> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for(int t = 0; t < testCase; t++) {
            n = Integer.parseInt(br.readLine());
            list = new ArrayList<>();
            st = new StringTokenizer(br.readLine());

            int sr = Integer.parseInt(st.nextToken());
            int sc = Integer.parseInt(st.nextToken());

            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                list.add(new int[] {r, c});
            }

            st = new StringTokenizer(br.readLine());
            er = Integer.parseInt(st.nextToken());
            ec = Integer.parseInt(st.nextToken());

            boolean flag = bfs(sr, sc);

            if(flag) sb.append("happy" + "\n");
            else sb.append("sad" + "\n");
        }

        System.out.println(sb);
    }

    private static boolean bfs(int r, int c) {
        boolean flag = false;
        if(Math.abs(r - er) + Math.abs(c - ec) <= 1000) {
            flag = true;
            return flag;
        }

        int level = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n+1][n];

        queue.offer(new int[] {r, c});

        while(!queue.isEmpty()) {
            int size = queue.size();

            if(level == n+1) break;

            for(int i = 0; i < size; i++) {
                int[] loc = queue.poll();
                int x = loc[0];
                int y = loc[1];

                if(Math.abs(x - er) + Math.abs(y - ec) <= 1000) {
                    flag = true;
                    return flag;
                }

                for(int j = 0; j < list.size(); j++) {
                    if(x == list.get(j)[0] && y == list.get(j)[1]) continue;
                    if(visited[level][j]) continue;
                    if(Math.abs(x - list.get(j)[0]) + Math.abs(y - list.get(j)[1]) > 1000) continue;

                    visited[level][j] = true;
                    queue.offer(new int[] {list.get(j)[0], list.get(j)[1]});
                }
            }

            level++;
        }

        return flag;
    }

}
