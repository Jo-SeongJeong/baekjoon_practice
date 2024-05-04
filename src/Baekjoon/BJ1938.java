package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 기차 놀이
 *
 * 조건
 * 맵 n * n : 4 ~ 50
 * 0 : 빈칸
 * 1 : 나무
 * 기차 놀이 : 출발 위치에서 도착 위치로 이동하면 종료
 * 출발 위치 : BBB
 * 시작 위치 : EEE
 * 기차의 길이 : 3
 * 기차 이동 방법
 * U : 위로 한 칸
 * D : 아래 한 칸
 * L : 좌로 한 칸
 * R : 우로 한 칸
 * T : 중심 기준, 90도 회전
 * 나무가 있으면 이동 불가
 * 대각선으로 놓일 수 없음
 * -> 회전하기 위해선 3 * 3 칸에 나무 없어야 함
 *
 * 문제에서 구하고자 하는 것
 * 도착 지점으로 가기 위한 최소 이동 횟수
 *
 * 문제 해결 프로세스
 * BFS로 되려나?
 * 1. 시작위치인 3개의 위치를 잡아야 함
 * 2. 방문 배열을 3차원으로 해서 이동 할때의 모양으로 잡아보자(어떤 방향인지가 중요)
 * 	-> 같은 커맨드로 온 경우 의미가 없음
 * 3. 이동은 사방탐색 + 회전까지 고려해서 구현
 * 4. bfs니까 한번 가면 끝
 *
 * 고려한 시간 복잡도
 * 50*50*5*5 = 6,250
 * */

public class BJ1938 { // 메모리 : 12428kb, 시간 : 104ms
    static int n;
    static String[][] map;
    static boolean[][][] visited;
    static int endDir;
    static int[] end;
    static int count;
    static Queue<int[]> queue;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new String[n][n];
        visited = new boolean[n][n][2];
        int[] start = new int[2];
        end = new int[2];

        int startR = 0;
        int startDir = 0;
        int endR = 0;
        int startCnt = 0;
        int endCnt = 0;
        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            for(int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j)+"";

                if(map[i][j].equals("B")) {
                    if(startCnt == 0) startR = i;

                    if(startCnt == 1) {
                        start[0] = i;
                        start[1] = j;
                    }

                    else {
                        if(startR != i) startDir = 0;
                        else startDir = 1;
                    }
                    startCnt++;
                }

                if(map[i][j].equals("E")) {
                    if(endCnt == 0) endR = i;

                    if(endCnt == 1) {
                        end[0] = i;
                        end[1] = j;
                    }

                    else {
                        if(endR != i) endDir = 0;
                        else endDir = 1;
                    }
                    endCnt++;
                }
            }
        }
        queue = new ArrayDeque<>();

        visited[start[0]][start[1]][startDir] = true;
        queue.offer(new int[] {start[0], start[1], startDir});

        bfs();

        System.out.println(count);
    }

    private static void bfs() {
        //상, 하, 좌, 우
        int[][] delta = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int i = 0; i < size; i++) {
                int[] loc = queue.poll();
                if(loc[0] == end[0] && loc[1] == end[1] && loc[2] == endDir) return;

                int dir = loc[2];

                for(int d = 0; d < 4; d++) {
                    if(dir == 0) { // 세로
                        if(d > 1) { // 좌, 우
                            int nr1 = loc[0] + delta[d][0]-1;
                            int nr2 = loc[0] + delta[d][0];
                            int nr3 = loc[0] + delta[d][0]+1;
                            int nc = loc[1] + delta[d][1];

                            if(checkBound(nr1, nc)) continue;
                            if(checkBound(nr2 , nc)) continue;
                            if(checkBound(nr3, nc)) continue;

                            if(checkTree(nr1, nc)) continue;
                            if(checkTree(nr2, nc)) continue;
                            if(checkTree(nr3, nc)) continue;

                            if(checkVisited(nr2, nc, dir)) continue;

                            visited[nr2][nc][dir] = true;
                            queue.offer(new int[] {nr2, nc, dir});

                        }
                        else { // 상, 하
                                int nr = loc[0] + delta[d][0];
                                int nc = loc[1] + delta[d][1];

                                if(checkBound(nr, nc)) continue;
                                if(checkTree(nr, nc)) continue;
                                if(checkVisited(nr, nc, dir)) continue;

                                if(d == 0) {
                                    if(checkBound(nr-1, nc)) continue;
                                    if(checkTree(nr-1, nc)) continue;
                                }
                                else {
                                    if(checkBound(nr+1, nc)) continue;
                                    if(checkTree(nr+1, nc)) continue;
                                }
                                visited[nr][nc][dir] = true;
                                queue.offer(new int[] {nr, nc, dir});

                        }
                    }
                    else { // 가로
                        if(d < 2) { // 상, 하
                            int nr = loc[0] + delta[d][0];
                            int nc1 = loc[1] + delta[d][1]-1;
                            int nc2 = loc[1] + delta[d][1];
                            int nc3 = loc[1] + delta[d][1]+1;

                            if(checkBound(nr, nc1)) continue;
                            if(checkBound(nr, nc2)) continue;
                            if(checkBound(nr, nc3)) continue;

                            if(checkTree(nr, nc1)) continue;
                            if(checkTree(nr, nc2)) continue;
                            if(checkTree(nr, nc3)) continue;

                            if(checkVisited(nr, nc2, dir)) continue;

                            visited[nr][nc2][dir] = true;
                            queue.offer(new int[] {nr, nc2, dir});
                        }
                        else { // 좌, 우
                                int nr = loc[0] + delta[d][0];
                                int nc = loc[1] + delta[d][1];

                                if(checkBound(nr, nc)) continue;
                                if(checkTree(nr, nc)) continue;
                                if(checkVisited(nr, nc, dir)) continue;
                                if(d == 2) {
                                    if(checkBound(nr, nc-1)) continue;
                                    if(checkTree(nr, nc-1)) continue;
                                }
                                else {
                                    if(checkBound(nr, nc+1)) continue;
                                    if(checkTree(nr, nc+1)) continue;
                                }

                                visited[nr][nc][dir] = true;
                                queue.offer(new int[] {nr, nc, dir});

                        }
                    }
                }

                //회전
                boolean flag = true;
                for(int d = 0; d < 8; d++) {
                    int nr = loc[0] + delta[d][0];
                    int nc = loc[1] + delta[d][1];

                    if(checkBound(nr, nc)) {
                        flag = false;
                        break;
                    }
                    if(checkTree(nr, nc)) {
                        flag = false;
                        break;
                    }
                }

                if(flag) {
                    if(dir == 0) { // 세로
                        if(checkVisited(loc[0], loc[1], 1)) continue;
                        visited[loc[0]][loc[1]][1] = true;
                        queue.offer(new int[] {loc[0], loc[1], 1});
                    }
                    else { // 가로
                        if(checkVisited(loc[0], loc[1], 0)) continue;
                        visited[loc[0]][loc[1]][0] = true;
                        queue.offer(new int[] {loc[0], loc[1], 0});
                    }
                }
            }
            count++;
        }

        count = 0;
    }

    private static boolean checkBound(int nr, int nc) {
        if(nr < 0 || nr >= n || nc < 0 || nc >= n) return true;
        else return false;
    }

    private static boolean checkTree(int nr, int nc) {
        if(map[nr][nc].equals("1")) return true;
        else return false;
    }

    private static boolean checkVisited(int nr, int nc, int dir) {
        if(visited[nr][nc][dir]) return true;
        else return false;
    }
}

