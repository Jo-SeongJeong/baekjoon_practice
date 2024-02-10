package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SWEA 1225 암호생성기
 *
 * 조건
 *
 * 제일 앞 원소를 제일 뒤로 옮길 때마다 감소하는 숫자가 1씩 증가한다
 * 모든 원소가 한바퀴를 돌면 감소 숫자가 1부터로 재설정된다
 * 감소한 숫자가 0이하가 되는 경우 종료되며 최종 암호가 된다
 * 주어지는 각 수는 integer 범위를 넘지 않는다
 * 최종 암호배열은 모두 한 자리수로 구성되어 있다
 *
 * 문제에서 구하고자 하는 것
 *
 * 조건 사이클을 돌렸을 때 종료되는 최종 숫자배열
 *
 * 문제 해결 프로세스
 *
 * ArrayDeque으로 큐를 생성한다
 * 숫자 8개를 순서대로 입력받는다
 * 모든 원소가 동일한 값을 뺀 사이클의 합은 15
 * 큐에서 제일 작은 값 / 15 -> 모든 원소가 공평히 돈만큼의 값이 나옴
 * 각 원소 - (몫-1) * 15를 해 0이 나오는 공평 사이클 전까지 돈 것으로 침
 * 반복문을 통해 조건에 해당하는 로직을 완성한다
 * 반복문이 종료되면 해당 큐의 순서대로 원소를 출력한다
 *
 * 고려한 시간 복잡도
 *
 * 8(큐삽입) + 8 * 2 (가장 작은 원소 찾는 과정) + 5(사이클) * 8(공평한 한바퀴)
 *  */

public class SW1225 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int t = 1; t <= 10; t++) {
            Queue<Integer> password = new ArrayDeque<Integer>();

            int testCase = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            int min = Integer.MAX_VALUE;

            for(int i = 0; i < 8; i++) {
                int num = Integer.parseInt(st.nextToken());
                password.offer(num);
                min = Math.min(num, min);
            }

            int count = (min / 15) - 1;

            for(int i = 0; i < 8; i++) {
                int temp = password.poll() - (15 * count);
                password.offer(temp);
            }

            int cycle = 1;
            while(true) {
                int temp = password.poll() - cycle;

                if(temp <= 0) {
                    temp = 0;
                    password.offer(temp);
                    break;
                }

                password.offer(temp);
                cycle++;

                if(cycle == 6) cycle = 1;
            }

            System.out.print("#"+ t + " ");

            for(int i = 0 ; i < 8; i++) {
                System.out.print(password.poll() + " ");
            }

            System.out.println();
        }
    }
}

// 메모리 : 18,468kb, 시간 : 104ms