package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 백준 2164 카드2
 *
 * 조건
 *
 * 카드 개수 ( 1 ~ 500000)
 * 제일 위 카드는 버리고, 그 다음 카드를 제일 아래로 옮기는 과정을 한 장 남을 때까지 반복
 *
 * 문제에서 구하고자 하는 것
 *
 * 마지막 한 장 남은 카드
 *
 * 문제 해결 프로세스
 *
 * 카드의 개수를 입력 받는다
 * ArrayDeque으로 Queue를 생성한다
 * 개수에 맡게 1부터 순서대로 N까지 반복하여 큐에 값을 집어 넣는다
 * 첫번째 원소를 삭제한다
 * 그 다음 원소를 삭제하면서 해당 값을 변수에 저장한다
 * 변수 값을 큐에 넣는다
 * 3개의 과정을 size가 1일때까지 반복한다
 * 큐 결과를 출력한다
 *
 * 고려한 시간 복잡도
 *
 * 500000(큐에 값 삽입) + 3(연산) * (500000 + ...+)
 *
 * */

public class BJ2164 { // 메모리 : 31676kb, 시간 : 132ms
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new ArrayDeque<>();

        for(int i = 1; i <= n; i++) {
            queue.offer(i);
        }

        while(queue.size() > 1) {
            queue.poll();
            int temp = queue.poll();
            queue.offer(temp);
        }

        System.out.println(queue.poll());
    }

}