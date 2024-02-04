package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2072 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] array = new int[19][19];

        for(int i = 0; i < 19; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < 19; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int win = 0;
        int r = 0;
        int c = 0;
        int[][] delta = new int[][] {{0, 1}, {1, 0}, {-1, 1}, {1, 1}};

        // 6목 체크
        int[][] six = new int[][] {{0, -1}, {-1, 0}, {1, -1}, {-1, -1}};

        for(int i = 0; i < 19; i++) {
            for(int j = 0; j < 19; j++) {
                int num = array[i][j];

                if(num > 0) {
                    for(int k = 0; k < 4; k++) {
                        int bCount = 1;
                        int wCount = 1;

                        // 6목 체크 부분
                        int rowCheck = i + six[k][0];
                        int colCheck = j + six[k][1];
                        if(rowCheck >= 0 && rowCheck < 19 && colCheck >= 0 && colCheck < 19) {
                            if(array[rowCheck][colCheck] == num) {
                                continue;
                            }
                        }

                        // 오목 확인
                        for(int l = 1; l < 7; l++) {
                            int row = i+delta[k][0]*l;
                            int col = j+delta[k][1]*l;
                            if(row >= 0 && row < 19 && col >= 0 && col < 19) {
                                if(num != array[row][col]) {
                                    break;
                                }
                                if(num == array[row][col] && num == 1) {
                                    bCount++;
                                }
                                else if(num == array[row][col] && num == 2) {
                                    wCount++;
                                }
                            }
                        }

                        if(bCount == 5) {
                            win = 1;
                            r = i+1;
                            c = j+1;
                        }
                        else if(wCount == 5) {
                            win = 2;
                            r = i+1;
                            c = j+1;
                        }
                    }
                }
            }
        }
        if(win > 0) {
            System.out.println(win);
            System.out.println(r +" " + c);
        }
        else {
            System.out.println(win);
        }
    }
}
