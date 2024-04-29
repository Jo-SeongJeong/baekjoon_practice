package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * SWEA 1954 달팽이 숫자
 * 조건
 * 이차원 배열 0,0을 시작점으로 1부터 N까지의 숫자가 시계방향(우, 하, 좌, 상)으로 채워진다
 * <p>
 * 문제에서 구하고자 하는 것
 * 이차원 배열 모양의 행렬에 조건에 맞게 숫자를 채워 넣어 이 형태로 출력한다
 * <p>
 * 문제해결 프로세스
 * 사방탐색 델타를 우, 하, 좌, 상 순으로 만들어 배열 값이 0인 경우에 값을 채운다
 * 배열의 크기 N을 입력 받는다
 * N*N 배열을 생성 및 초기화 한다
 * 방향 배열을 우 하 좌 상 순으로 만든다
 * 반복문을 통해 값을 채운다
 * 값을 채운 조건은 인덱스를 넘어가지 않고, 해당 배열 값이 0인 경우에만 값을 채워 넣는다
 * 상 이후 다시 우로 간다
 * 해당 배열을 행렬 형태로 출력한다
 * <p>
 * 고려한 시간 복잡도
 * N(행) * N(열) * 4(방향)
 */

public class SW1954Delta { // 메모리 : 18664 , 시간 : 109ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        for (int t = 1; t <= testCase; t++) {
            int n = Integer.parseInt(br.readLine());
            int[][] array = new int[n][n];
            
            // 시작점 1로 저장
            int count = 1;
            array[0][0] = 1;
            
            // 현재 좌표 값
            int curRow = 0;
            int curCol = 0;
            
            // delta 탐색 방향 설정(우 하 좌 상)
            for (int i = 0; i < 4; i++) {
                // 현재 진행방향 끝까지 탐색
                for (int j = 1; j < n+1; j++) {
                    int nextRow = curRow + j * dir[i][0];
                    int nextCol = curCol + j * dir[i][1];
                    
                    // 인덱스 값이 범위 내인 경우
                    if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n) {
                        // 다음 좌표값이 0인 경우 숫자 대입
                        if (array[nextRow][nextCol] == 0) {
                            array[nextRow][nextCol] = ++count;

                        }
                        // 아닌 경우 이미 탐색했던 곳이므로 현재 좌표값 저장 후 다음 탐색
                        else {
                            if(i == 0) {
                                curRow = nextRow;
                                curCol = nextCol - 1;
                                break;
                            }
                            else if(i == 1) {
                                curRow = nextRow - 1;
                                curCol = nextCol;
                                break;
                            }
                            else if(i == 2) {
                                curRow = nextRow;
                                curCol = nextCol + 1;
                                break;
                            }
                            else if(i == 3) {
                                curRow = nextRow + 1;
                                curCol = nextCol;
                                break;
                            }
                        }
                    } 
                    
                    // 범위 밖인 경우 현재 좌표값 저장 후 다음 탐색
                    else if (nextRow < 0) {
                        curRow = nextRow + 1;
                        break;
                    } 
                    else if (nextRow >= n) {
                        curRow = nextRow - 1;
                        break;
                    } 
                    else if (nextCol < 0) {
                        curCol = nextCol + 1;
                        break;
                    } 
                    else if (nextCol >= n) {
                        curCol = nextCol - 1;
                        break;
                    }
                }
                
                // 아직 순회가 끝나지 않았다면 다시 순회
                if(i == 3 && count < n*n) {
                    i = -1;
                }
            }

            // 출력
            System.out.println("#"+t);
            for (int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    System.out.print(array[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}