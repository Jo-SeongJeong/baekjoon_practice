package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * SWEA 2383 점심 식사시간
 *
 * 조건
 * 맵 크기 N x N : 4 ! ~ 10
 * 방 안 사람들 P : 1 ~ 10
 * 계단 입구 S : 2개
 * 계단 길이 K : 2 ~ 10
 * 이동 완료 시간 : 모든 사람들이 계단을 내려가 아래 층으로 이동을 완료한 시간
 * 아래층으로 이동하는 시간 : 계단 입구까지 이동 시간 + 계단을 내려가는 시간
 * 계단 입구까지 이동 시간 : |pr - sr| + |pc - sc|
 * 계단 내려가는 시간 : 1분 후 출발 가능, K분 걸림, 동시 최대 3명까지만 이동 가능
 *
 * 문제에서 구하고자 하는 것
 * 최소 이동 완료 시간
 *
 * 문제 해결 프로세스
 * 2HP 중복 순열로 구해, 해당 계단으로 이동하는 모든 경우의 수로 구해보자
 * 그 다음 최소 이동 완료 시간으로 갱신
 *
 * 고려한 시간 복잡도
 * 2^10
 * */

public class SW2383 { // 메모리 : 26864kb, 시간: 140ms
    static int n;
    static int[][] map;
    static int[] picked;
    static List<int[]> list;
    static List<int[]> people;
    static List<Integer> down1;
    static List<Integer> down2;
    static int min;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++) {
            n = Integer.parseInt(br.readLine());

            map = new int[n][n];
            list = new ArrayList<>();
            people = new ArrayList<>();
            min = Integer.MAX_VALUE;

            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());

                    if(map[i][j] == 1) people.add(new int[] {i, j});
                    if(map[i][j] > 1) list.add(new int[] {i, j});
                }
            }

            picked = new int[people.size()];
            dupPermu(0);
            sb.append("#" + t + " ");
            sb.append(min + "\n");

        }
        System.out.println(sb);
    }

    private static void dupPermu(int index) {
        if(index == people.size()) {
            down1 = new ArrayList<>();
            down2 = new ArrayList<>();
            int time1 = 0;
            int time2 = 0;

            for(int i = 0; i < index; i++) {
                int[] person = people.get(i);
                if(picked[i] == 0) {
                    int[] stair = list.get(0);
                    int in = Math.abs(stair[0] - person[0]) + Math.abs(stair[1] - person[1]) + 1;
                    down1.add(in);

                }
                else {
                    int[] stair = list.get(1);
                    int in = Math.abs(stair[0] - person[0]) + Math.abs(stair[1] - person[1]) + 1;
                    down2.add(in);
                }
            }

            Collections.sort(down1);
            Collections.sort(down2);

            if(down1.size() > 0) {
                int[] loc = list.get(0);
                time1 = downStair(down1, map[loc[0]][loc[1]]);
            }

            if(down2.size() > 0) {
                int[] loc = list.get(1);
                time2 = downStair(down2, map[loc[0]][loc[1]]);
            }

            int time = Math.max(time1, time2);
            min = Math.min(min, time);

            return;
        }

        for(int i = 0; i < 2; i ++) {
            picked[index] = i;
            dupPermu(index+1);
        }

    }

    private static int downStair(List<Integer> down, int k) {
        int time = down.get(0);
        int count = 0;
        int index = 0;
        Queue<int[]> queue = new ArrayDeque<>();

        for(int i : down) {
            if(time < i) break;
            if(count >= 3) break;
            count++;
            index++;
        }

        queue.offer(new int[]{time, count});

        while(index < down.size() || !queue.isEmpty()) {
            time++;

            if(index < down.size()) {
                if (count < 3) {
                    if (down.get(index) <= time) {
                        int num = 0;
                        for (int i = index; i < index + 3 - count; i++) {
                            if(i >= down.size()) break;
                            if(down.get(i) <= time) num++;
                        }
                        queue.offer(new int[] {time, num});
                        index += num;
                        count += num;
                    }
                }
            }

            if(!queue.isEmpty()) {
                if (time == queue.peek()[0] + k - 1) {
                    int[] loc = queue.poll();
                    count -= loc[1];
                }
            }

        }
        return time+1;
    }
}
