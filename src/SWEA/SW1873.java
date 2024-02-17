package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SWEA 1873 상호의 배틀필드
 *
 * 조건
 *
 *    .    평지(전차가 들어갈 수 있다.)
 *    *    벽돌로 만들어진 벽
 *    #    강철로 만들어진 벽
 *    -    물(전차는 들어갈 수 없다.)
 *    ^    위쪽을 바라보는 전차(아래는 평지이다.)
 *    v    아래쪽을 바라보는 전차(아래는 평지이다.)
 *    <    왼쪽을 바라보는 전차(아래는 평지이다.)
 *     >    오른쪽을 바라보는 전차(아래는 평지이다.)
 *     U : 위로 방향전환, 평지면 위 한칸 이동
 *  D : 아래 방향 전환, ''
 *  L : 왼쪽 방향 전환, ''
 *  R : 오른 방향 전환, ''
 *  S : 현재 방향 슈웃, ''
 * 포탄 발사 시
 * 벽 -> 평지, 포탄 소멸
 * 강철 벽 -> 강철벽, 포탄 소멸
 * 전차는 1개
 * 맵 크기 (2 ~ 20)
 * 커멘드 개수(0~100)
 * 문제에서 구하고자 하는 것
 *
 * 모든 과정 후 맵의 상태
 *
 * 문제 해결 프로세스
 * 맵 크기에 대한 입력을 받는다 (h: 행, w: 열)
 * 맵을 구성할 문자 배열을 만들어 문자를 입력 받는다
 * 이때 전차의 초기 좌표값을 저장한다
 * 커멘드 개수를 입력받는다(n)
 * 큐를 만들어 커멘드를 저장한다
 * 4방향 델타를 만든다
 * 조건에 맞는 과정을 실행하며, 큐 원소가 끝날때까지 반복한다
 * 최종 맵 상태를 출력한다
 *
 * */

public class SW1873 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            char[][] map = new char[h][w];
            int row = 0; // 전차의 행
            int col = 0; // 전차의 열
            char tank = 0;
            for(int i = 0; i < h; i++) {
                String str = br.readLine();
                for(int j = 0; j < w; j++) {
                    map[i][j] = str.charAt(j);

                    switch(map[i][j]) {
                        case '<' : case '>' : case '^' : case 'v' :
                            row = i;
                            col = j;
                            tank = map[i][j];
                            break;
                    }
                }
            }

            int n = Integer.parseInt(br.readLine());

            String str = br.readLine();

            Queue<Character> command = new ArrayDeque<>();

            for(int i = 0; i < n; i++) {
                command.offer(str.charAt(i));
            }

            int[][] delta = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

            while(command.size() > 0) {
                switch(command.poll()) {
                    case 'S' :
                        switch(tank) {
                            case '<' :
                                for(int i = 1; i < w; i++) {
                                    int c = col + i * delta[0][1];
                                    if (c < 0 || map[row][c] == '#') break;
                                    if(map[row][c] == '*') {
                                        map[row][c] = '.';
                                        break;
                                    }
                                }
                                break;
                            case '>' :
                                for(int i = 1; i < w; i++) {
                                    int c = col + i * delta[1][1];
                                    if (c >= w || map[row][c] == '#') break;
                                    if(map[row][c] == '*') {
                                        map[row][c] = '.';
                                        break;
                                    }
                                }
                                break;
                            case '^' :
                                for(int i = 1; i < h; i++) {
                                    int r = row + i * delta[2][0];
                                    if (r < 0 || map[r][col] == '#') break;
                                    if(map[r][col] == '*') {
                                        map[r][col] = '.';
                                        break;
                                    }
                                }
                                break;
                            case 'v' :
                                for(int i = 1; i < h; i++) {
                                    int r = row + i * delta[3][0];
                                    if (r >= h || map[r][col] == '#') break;
                                    if(map[r][col] == '*') {
                                        map[r][col] = '.';
                                        break;
                                    }
                                }
                                break;
                        }
                        break;
                    case 'L' :
                        if(tank == '<') {
                            if(col+delta[0][1] < 0) break;
                            if(map[row][col+delta[0][1]] == '.') {
                                map[row][col] = '.';
                                map[row][col+delta[0][1]] = '<';
                                col = col+delta[0][1];
                            }
                        }
                        else {
                            map[row][col] = '<';
                            tank = '<';
                            if(col+delta[0][1] < 0) break;
                            if(map[row][col+delta[0][1]] == '.') {
                                map[row][col] = '.';
                                map[row][col+delta[0][1]] = '<';
                                col = col+delta[0][1];
                            }
                        }
                        break;
                    case 'R' :
                        if(tank == '>') {
                            if(col+delta[1][1] >= w) break;
                            if(map[row][col+delta[1][1]] == '.') {
                                map[row][col] = '.';
                                map[row][col+delta[1][1]] = '>';
                                col = col+delta[1][1];
                            }
                        }
                        else {
                            map[row][col] = '>';
                            tank = '>';
                            if(col+delta[1][1] >= w) break;
                            if(map[row][col+delta[1][1]] == '.') {
                                map[row][col] = '.';
                                map[row][col+delta[1][1]] = '>';
                                col = col+delta[1][1];
                            }
                        }
                        break;
                    case 'U' :
                        if(tank == '^') {
                            if(row+delta[2][0] < 0) break;
                            if(map[row+delta[2][0]][col] == '.') {
                                map[row][col] = '.';
                                map[row+delta[2][0]][col] = '^';
                                row = row+delta[2][0];
                            }
                        }
                        else {
                            map[row][col] = '^';
                            tank = '^';
                            if(row+delta[2][0] < 0) break;
                            if(map[row+delta[2][0]][col] == '.') {
                                map[row][col] = '.';
                                map[row+delta[2][0]][col] = '^';
                                row = row+delta[2][0];
                            }
                        }
                        break;
                    case 'D' :
                        if(tank == 'v') {
                            if(row+delta[3][0] >= h) break;
                            if(map[row+delta[3][0]][col] == '.') {
                                map[row][col] = '.';
                                map[row+delta[3][0]][col] = 'v';
                                row = row+delta[3][0];
                            }
                        }
                        else {
                            map[row][col] = 'v';
                            tank = 'v';
                            if(row+delta[3][0] >= h) break;
                            if(map[row+delta[3][0]][col] == '.') {
                                map[row][col] = '.';
                                map[row+delta[3][0]][col] = 'v';
                                row = row+delta[3][0];
                            }
                        }
                        break;
                }
            }

            System.out.print("#" + t+ " ");
            for(int i = 0; i < h; i++) {
                for(int j = 0; j < w; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
        }
    }
}

// 메모리 : 30876kb, 시간 : 180ms