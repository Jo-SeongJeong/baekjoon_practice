package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 16935 배열 돌리기 3
 *
 * 조건
 * 배열 크기 : N x M (2 ~ 100), 짝수
 * 연산 횟수 : R(1 ~ 1000)
 * 연산은 공백으로 구분, 순서대로 적용
 *
 * 문제에서 구하고자 하는 것
 * 원본 배열에서 모든 연산이 적용되어 변경된 배열을 출력
 *
 * 문제 해결 프로세스
 * 연산 완료시 마다 값을 저장할 임시배열을 만든다
 * 한 연산을 완료하면 원본 배열에 복사하고, 임시는 초기화한다
 * 1번 연산
 * 배열을 행의 역순, 열의 정순으로 찍어 변경한다
 * 2번 연산
 * 배열을 열의 역순, 행의 정순으로 찍어 변경한다
 * 3번 연산
 * 배열을 행의 역순, 열의 정순으로 찍되, 행을 j로 둔다
 * 4번 연산
 * 배열을 열의 역순, 행의 정순으로 찍되, 행을 j로 둔다
 * 5번 연산
 * 경계를 N % (N/2), M % (M/2)로 두어 해당 값부터 값+(N/2), 값+(M/2)까지로 섹션을 나눈다
 * 섹션에 포함된 원소값을 다음 섹션으로 이동시킨다
 * 델타는 우 하 좌 상 순으로 흘러가게 한다
 * 6번 연산
 * 5번과 동일하지만, 델타를 하 우 상 좌 순으로 흘러가게 한다
 *
 * 고려한 시간 복잡도
 *  4 * 100 * 100(1번, 2, 3, 4) + 2 * 4 * 50 * 50(5, 6번) + 배열 복사(60000) -> 120000?
 *
 * */

public class BJ16935 { // 메모리 : 49496kb, 시간 : 408ms
    static int[][] array;
    static int[][] temp;
    static int n;
    static int m;
    static int r;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        array = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < r; i++) {
            switch(Integer.parseInt(st.nextToken())) {
                case 1:
                    cal1();
                    break;
                case 2:
                    cal2();
                    break;
                case 3:
                    cal3();
                    break;
                case 4:
                    cal4();
                    break;
                case 5:
                    cal5();
                    break;
                case 6:
                    cal6();
                    break;
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                sb.append(array[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void cal1() {
        temp = new int[n][m];
        int index = 0;
        for(int i = n-1; i >= 0; i--) {
            for(int j = 0; j < m; j++) {
                temp[index][j] = array[i][j];
            }
            index++;
        }
        array = temp;

    }

    private static void cal2() {
        temp = new int[n][m];
        for(int i = 0; i < n; i++) {
            int index = 0;
            for(int j = m-1; j >= 0; j--) {
                temp[i][index] = array[i][j];
                index++;
            }
        }
        array = temp;
    }

    private static void cal3() {
        int swap = n;
        n = m;
        m = swap;
        temp = new int[n][m];
        for(int i = 0; i < n; i++) {
            int index = 0;
            for(int j = m-1; j >= 0; j--) {
                temp[i][index] = array[j][i];
                index++;
            }
        }
        array = temp;
    }

    private static void cal4() {
        int swap = n;
        n = m;
        m = swap;
        temp = new int[n][m];
        int index = 0;
        for(int i = n-1; i >= 0; i--) {
            for(int j = 0; j < m; j++) {
                temp[index][j] = array[j][i];
            }
            index++;
        }
        array = temp;
    }

    private static void cal5() {
        int rowB = n/2;
        int colB = m/2;

        temp = new int[rowB][colB];

        for(int i = 0; i < rowB; i++) {
            for(int j = 0; j < colB; j++) {
                temp[i][j]= array[i][j];
            }
        }

        int index = 0;
        for(int i = rowB; i < n; i++) {
            for(int j = 0; j < colB; j++) {
                array[index][j] = array[i][j];
            }
            index++;
        }


        for(int i = rowB; i < n; i++) {
            index = 0;
            for(int j = colB; j < m; j++) {
                array[i][index] = array[i][j];
                index++;
            }
        }

        index = rowB;
        for(int i = 0; i < rowB; i++) {
            for(int j = colB; j < m; j++) {
                array[index][j] = array[i][j];
            }
            index++;
        }

        for(int i = 0; i < rowB; i++) {
            index = colB;
            for(int j = 0; j < colB; j++) {
                array[i][index] = temp[i][j];
                index++;
            }
        }

    }

    private static void cal6() {
        int rowB = n/2;
        int colB = m/2;

        temp = new int[rowB][colB];

        for(int i = 0; i < rowB; i++) {
            for(int j = 0; j < colB; j++) {
                temp[i][j]= array[i][j];
            }
        }

        int index;
        for(int i = 0; i < rowB; i++) {
            index = 0;
            for(int j = colB; j < m; j++) {
                array[i][index] = array[i][j];
                index++;
            }
        }

        index = 0;
        for(int i = rowB; i < n; i++) {
            for(int j = colB; j < m; j++) {
                array[index][j] = array[i][j];
            }
            index++;
        }

        for(int i = rowB; i < n; i++) {
            index = colB;
            for(int j = 0; j < colB; j++) {
                array[i][index] = array[i][j];
                index++;
            }

        }

        index = rowB;
        for(int i = 0; i < rowB; i++) {
            for(int j = 0; j < colB; j++) {
                array[index][j] = temp[i][j];
            }
            index++;
        }
    }
}
