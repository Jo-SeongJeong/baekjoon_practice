package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * SWEA 1228 암호문1
 *
 * 조건
 *
 * 숫자 1개는 6글자로 이루어짐
 * 명령어 : I x y s : x의 위치 바로 뒤에 s로 이루어진 y개의 숫자 삽입
 *
 *
 * 문제에서 구하고자 하는 것
 *
 * 최종 수정된 처음 10개의 숫자
 *
 * 문제 해결 프로세스
 *
 * 10개의 tC
 * 원본 암호문의 길이 입력
 * 원본 암호문 입력 -> 6글자 후 공백으로 나누어짐
 * 명령어의 개수 입력
 * 각 명령어 입력 -> I로 나누어짐
 * x 뒤에 s를 삽입한다(Linked List)
 * 명령어 개수만큼 반복
 * 처음 10글자 출력(6개 10쌍)
 *
 * 고려한 시간 복잡도
 *
 * 20 * 10..?
 * */

public class SW1228 { // 메모리 : 19636kb, 시간 : 139ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int t = 1; t <= 10; t++) {
            int n = Integer.parseInt(br.readLine());

            List<String> cyper = new LinkedList<>();
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                cyper.add(st.nextToken());
            }

            int cmd = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < cmd; i++) {
                String insert = st.nextToken();
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                String s = "";
                for (int j = 0; j < y; j++) {
                    cyper.add(x++, st.nextToken());
                }
            }
            System.out.print("#" + t+ " ");
            for(int i = 0; i < 10; i++) {
                System.out.print(cyper.get(i) + " ");
            }
            System.out.println();
        }
    }
}
