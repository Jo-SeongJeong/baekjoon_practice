package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 백준 11286 절댓값 힙
 *
 * 조건
 * 연산 개수 n : 1 ~ 100000
 * 연산 종류
 *     0 : 배열에서 절댓값이 가장 작은 값 출력 후 제거
 *  나머지 : 배열에 해당 값 추가
 *  입력되는 정수 : -2^31 ~ 2^31 -> int 가능
 *
 * 문제에서 구하고자 하는 것
 * 0 연산이 주어졌을 때 절댓값이 제일 작은 값을 출력
 *
 * 문제 해결 프로세스
 * PreorderQueue를 생성한다 <- 이때 출력 순서는 최소값일 것
 * 큐 생성 시 절대값을 기준으로 오름차순 정렬, 같은 경우 원본값 오름차순 정렬
 * poll을 하여 원본 값을 출력
 * 저장하는 경우 해당 값으로 저장
 * 비어있는 경우 0을 출력
 *
 * 고려한 시간 복잡도
 * 100000 * log100000 -> 1600000 충분
 * */

public class BJ11286 { // 메모리 : 31996kb, 시간 : 860ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int comp = Math.abs(a) - Math.abs(b);
            if(comp == 0)
                return a-b;
            return comp;});

        for(int i = 0; i < n; i++) {
            int cmd =Integer.parseInt(br.readLine());

            if(cmd == 0) {
                if(pq.size() == 0) {
                    System.out.println(0);
                }
                else {
                    System.out.println(pq.poll());
                }

            }
            else {
                pq.add(cmd);
            }
        }
    }
}
