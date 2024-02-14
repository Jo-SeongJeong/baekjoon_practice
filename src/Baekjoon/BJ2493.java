package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 백준 2493 탑
 *
 * 조건
 *
 * 탑의 개수 : 1 ~ 500000(N)
 * 탑의 높이 : 1 ~ 100000000
 * 레이저 : 왼쪽 방향, 본인 보다 높아야 함+
 *
 * 문제에서 구하고자 하는 것
 *
 * 각 탑에서 발사한 레이저를 수신한 탑의 번호(1 ~ N)
 *
 * 문제 해결 프로세스
 *
 * 탑의 개수(N)를 입력 받는다
 * 각 탑의 높이를 입력 받을 배열을 생성하여 입력받는다
 * 수신의 입장에서 푼다
 * 1번 탑의 경우 -> 0
 * 그 이후 탑의 높이가 수신 탑보다 낮으면 해당 탑, 아니면 0
 * 만약 다음 탑이 이전탑보다 높으면 수신탑 변경
 * 출력
 *
 *
 * 고려한 시간 복잡도
 * 500000 * 3 * ?
 * */

public class BJ2493 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        Stack<int[]> tower = new Stack<>();

        tower.push(new int[]{0, Integer.MAX_VALUE});
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            int height = Integer.parseInt(st.nextToken());
            while(true) {
                if (height < tower.peek()[1]) {
                    sb.append(tower.peek()[0] + " ");
                    tower.push(new int[]{i, height});
                    break;
                } else {
                    tower.pop();
                }
            }
        }
        System.out.print(sb);
    }
}

// 메모리 : 165184kb, 시간 : 864ms