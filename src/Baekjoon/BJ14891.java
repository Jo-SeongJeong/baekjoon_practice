package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 14891 톱니바퀴
 *
 * 조건
 * 회전 횟수 K : 1 ~ 100
 * 자석 회전 방향
 * 시계방향 : 1
 * 반시계 방향 -1
 * N극 : 0
 * S극 : 1
 * 입력 정보 : 화살표 위치부터 시계방향 순서로 주어짐
 * 회전 입력 : 회전시키려는 자석 번호 , 회전 방향
 * 판 : 4개의 자석이 있음
 * 자석 : 8개의 날이 있음
 * 각 날은 N극과 S극의 자성 가짐
 * 규칙
 * 하나의 자석이 1칸 회전될때 붙어있는 자석은 서로 붙어있는 날의 자성과 다를 경우에만 반대방향으로 1칸 회전
 * 돌리기 직전 상태에서 붙어있는 날의 자성 기준!
 * 점수 계산
 * 1번 : 빨간 날 S극이면 1점
 * 2번 : 2점
 * 3번 : 4점
 * 4번 : 8점
 * N극은 모두 0점
 *
 * 문제에서 구하고자 하는 것
 * 자석을 최종적으로 회전시킨 후 획득하는 점수의 총합
 *
 * 문제 해결 프로세스
 * 각 톱니바퀴를 3차원 배열로 만들자
 * 크기는 [k+1][8][5]
 * 0행은 입력받은 초기 값
 * 각 톱니바퀴 i행 0열이 계산될 값
 * 2, 6열이 붙어있는 날(1,4 번 톱니는 각각 2열, 6열 만)
 * 행을 증가시키면서 회전시키기
 * 회전 후, 영향 밭는 톱니바퀴가 있는지 확인
 * 비교는 현재 행렬값으로(회전시킨 결과는 다음 행에 있을 것)
 *
 * 고려한 시간 복잡도
 * 100 * 8 * 4 *4 = 12800
 * */

public class BJ14891 { // 메모리 : 11652kb, 시간 : 84ms
    static int k;
    static int[][][] magnet;
    static boolean[] flag;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String[] str = new String[5];
        for(int i = 1; i < 5; i++) {
            str[i] = br.readLine();
        }

        k = Integer.parseInt(br.readLine());

        magnet = new int[k+1][8][5];
        for(int i = 1; i < 5; i++) {
            for(int j = 0; j < 8; j++) {
                magnet[0][j][i] = Integer.parseInt(str[i].charAt(j) +"");
            }
        }

        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            flag = new boolean[5];
            flag[num] = true;
            turnMagnet(i, num, dir);
        }

        //            for(int l = 1; l < 5; l++) {
        //                System.out.println("자석 " + l + "번");
        //                for(int i = 0; i < k+1; i++) {
        //                    for(int j = 0; j < 8; j++) {
        //                        System.out.print(magnet[i][j][l] + " ");
        //                    }
        //                    System.out.println();
        //                }
        //            }

        int sum = magnet[k][0][1] + 2 * magnet[k][0][2] + 4 * magnet[k][0][3] + 8 * magnet[k][0][4];

        System.out.println(sum);

    }

    private static void turnMagnet(int row, int num, int dir) {
        if(dir == 1) {
            int temp = magnet[row][7][num];
            for(int j = 1; j < 8; j++) {
                magnet[row+1][j][num] = magnet[row][j-1][num];
            }
            magnet[row+1][0][num] = temp;
        }

        else {
            int temp = magnet[row][0][num];
            for(int j = 6; j >= 0; j--) {
                magnet[row+1][j][num] = magnet[row][j+1][num];
            }
            magnet[row+1][7][num] = temp;
        }

        checkSide(row, num, dir);

    }

    private static void checkSide(int row, int num, int dir) {
        dir = -dir;

        switch(num) {
            case 1 :
                if(!flag[num+1]) {
                    if(magnet[row][2][num] != magnet[row][6][num+1]) {
                        flag[num+1] = true;
                        turnMagnet(row, num+1, dir);
                    }
                    else remain(row, num, 1);
                }
                break;
            case 2 :
                if(!flag[num+1]) {
                    if(magnet[row][2][num] != magnet[row][6][num+1]) {
                        flag[num+1] = true;
                        turnMagnet(row, num+1, dir);
                    }
                    else remain(row, num, 1);
                }

                if(!flag[num-1]) {
                    if(magnet[row][6][num] != magnet[row][2][num-1]) {
                        flag[num-1] = true;
                        turnMagnet(row, num-1, dir);
                    }
                    else remain(row, num, -1);
                }
                break;
            case 3 :
                if(!flag[num+1]) {
                    if(magnet[row][2][num] != magnet[row][6][num+1]) {
                        flag[num+1] = true;
                        turnMagnet(row, num+1, dir);
                    }
                    else remain(row, num, 1);
                }

                if(!flag[num-1]) {
                    if(magnet[row][6][num] != magnet[row][2][num-1]) {
                        flag[num-1] = true;
                        turnMagnet(row, num-1, dir);
                    }
                    else remain(row, num, -1);
                }
                break;
            case 4 :
                if(!flag[num-1]) {
                    if(magnet[row][6][num] != magnet[row][2][num-1]) {
                        flag[num-1] = true;
                        turnMagnet(row, num-1, dir);
                    }
                    else remain(row, num, -1);
                }
                break;
        }
    }

    private static void remain(int row, int num, int delta) {
        if(delta == 1) {
            for(int i = num+1; i < 5; i++) {
                for(int j = 0; j < 8; j++) {
                    magnet[row+1][j][i] = magnet[row][j][i];
                }
            }
        }
        else {
            for(int i = num-1; i > 0; i--) {
                for(int j = 0; j < 8; j++) {
                    magnet[row+1][j][i] = magnet[row][j][i];
                }
            }
        }
    }
}
