package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 10814 나이순 정렬
 *
 * 조건
 * 회원수 N : 1 ~ 100000
 * 나이 : 1 ~ 200
 * 이름 : 알파벳 대소문자, 길이- 1 ~ 100
 * 입력 순서 = 가입 순서
 *
 * 문제에서 구하고자 하는 것
 * 회원을 나이 오름차순으로 정렬, 만약 같으면 가입한 순서로 정렬 후 출력
 *
 * 문제 해결 프로세스
 * 배열을 n * 3으로 만들어서 마지막에 가입 순서를 명시하자
 * Arrays.sort의 람다식을 이용해서 정렬
 *
 * 고려한 시간 복잡도
 *
 * 300000log300000 = 1500000 + 300000log9375 => 2000000도 안됨! 3초 충분
 * */

public class BJ10814 { // 메모리 : 63156kb, 시간 : 980ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        String[][] array = new String[n][3];
        int index = 0;

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 2; j++) {
                array[i][j] = st.nextToken();
            }
            array[i][2] = index + "";
            index++;
        }

        Arrays.sort(array, (a, b) -> {
            int o1 = Integer.parseInt(a[0]);
            int o2 = Integer.parseInt(b[0]);
            if(o1 == o2) return Integer.parseInt(a[2]) - Integer.parseInt(b[2]);
            return o1 - o2;
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
