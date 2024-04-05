package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 백준 2239 스도쿠
 *
 * 조건
 * 스도쿠
 * 판 : 9*9
 * 행, 열 3*3 보드에 1 ~ 9까지 중복 숫자가 없어야 함
 * 0 : 아직 빈칸
 * 나머지 숫자 : 이미 채워진 수
 *
 * 문제에서 구하고자 하는 것
 * 정답 수도쿠 판, 정답이 여러개인 경우, 81개의 수 중 가장 작은 값 출력
 *
 * 문제 해결 프로세스
 * 입력을 받을 때, 0인 칸에 대한 좌표를 리스트에 저장
 *
 * 해당 좌표 기준에 대한 isChecked 배열(1 ~ 9 인덱스)로 들어갈 수 있는 숫자가 있는지 확인
 * 행, 열, 3*3에 숫자가 있으면 true -> false인 것 중 제일 작은 숫자를 넣자
 * 안되면 backtracking
 * list 순회하기
 *
 * 고려한 시간 복잡도
 * 81 * 9 * 9 * 9 = 59,049
 * */

public class BJ2239 { // 메모리 :134132kb, 시간 : 444ms
    static int[][] map = new int[10][10];
    static List<int[]> list;
    static boolean flag;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        list = new ArrayList<>();

        for(int i = 1; i < 10; i++) {
            String str = br.readLine();
            for(int j = 1; j < 10; j++) {
                map[i][j] = Integer.parseInt(str.charAt(j-1)+"");
                if(map[i][j] == 0) list.add(new int[] {i, j});
            }
        }

        write(0);


        for(int i = 1; i < 10; i++) {
            for(int j =1; j <10; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void write(int idx) {
        if(idx == list.size()) {
            flag = true;
            return;
        }

        int r = list.get(idx)[0];
        int c = list.get(idx)[1];
        boolean[] isChecked = new boolean[10];

        rowCheck(r, isChecked);

        colCheck(c, isChecked);

        int sr = 0;
        if(r >= 1 && r < 4) sr = 1;
        else if(r >= 4 && r < 7) sr = 4;
        else sr = 7;

        int sc = 0;
        if(c >= 1 && c < 4) sc = 1;
        else if(c >= 4 && c < 7) sc = 4;
        else sc = 7;

        boxCheck(sr, sc, isChecked);

        for(int i = 1; i < 10; i++) {
            if(isChecked[i]) continue;
            map[r][c] = i;

            write(idx+1);

            if(flag) break;

            map[r][c] = 0;

        }
    }


    private static void rowCheck(int r, boolean[] isChecked) {
        for(int i = 1; i < 10; i++) {
            if(map[r][i] == 0) continue;
            isChecked[map[r][i]] = true;
        }

    }

    private static void colCheck(int c, boolean[] isChecked) {
        for(int i = 1; i < 10; i++) {
            if(map[i][c] == 0) continue;
            isChecked[map[i][c]] = true;
        }

    }

    private static void boxCheck(int sr, int sc, boolean[] isChecked) {
        for(int i = sr; i < sr+3; i++) {
            for(int j = sc; j < sc+3; j++) {
                if(map[i][j] == 0) continue;
                isChecked[map[i][j]] = true;
            }
        }

    }
}