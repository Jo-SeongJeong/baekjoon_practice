package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 11650 좌표 정렬하기
 * 
 * 조건
 * 점 N : 행 (1 ~ 100000)
 * 열 : 2
 * 배열 안 값 xi, yi : -100000 ~ 100000
 * 위치가 같은 두 점은 없음
 * 
 * 문제에서 구하고자 하는 것
 * 행 기준 오름차순 -> 같으면 열 기준 오름차순 정렬 후 출력
 * 
 * 문제 해결 프로세스
 * 람다식 통해 정렬하기
 * 
 * 고려한 시간 복잡도
 * 200000log200000 -> 1초는 가능할듯
 * */

public class BJ11650 { // 메모리 : 62652kb, 시간 : 832ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[][] array = new int[n][2];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 2; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(array, (a, b) -> {
            if(a[0] == b[0])
                return a[1]-b[1];
            return a[0]-b[0];
        });

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < 2; j++) {
                sb.append(array[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
