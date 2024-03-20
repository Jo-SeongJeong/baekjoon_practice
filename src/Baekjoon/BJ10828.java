package Baekjoon;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * 백준 10828 스택
 *
 * 조건
 * 명령의 수 N : 1 ~ 10000
 * 주어지는 정수 : 1 ~ 100000(int)
 * 명령의 종류
 * push X : 정수 X를 스택에 넣음
 * pop : 스택 pop, 그 수 출력, 스택이 빈 경우 -1 출력
 * size : 스택 size 출력
 * empty : 스택 비어있으면 1 아니면 0 출력
 * top : 스택의 가장 위에 있는 정수 출력, 빈 경우 -1 출력
 *
 * 문제에서 구하고자하는 것
 * 출력하는 명령의 경우 조건에 맞게 출력하기
 *
 * 문제 해결 프로세스
 * 순차 명령 -> 큐로 명령을 offer
 * push인 경우를 위해 큐를 하나 더 만들어 숫자만 offer
 * 큐 poll하면서 stack 구성 밑 조건에 맞게 출력
 *
 * 고려한 시간 복잡도
 * 20000(명령 입력 + 명령 출력 반복)
 * */

public class BJ10828 { // 메모리 : 31680kb, 시간 : 360ms
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Queue<String> queue = new ArrayDeque<>();
        Queue<Integer> num = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            String str = sc.next();
            queue.offer(str);
            if(str.equals("push")) num.offer(sc.nextInt());
        }

        Stack<Integer> stack = new Stack<>();
        while(!queue.isEmpty()) {
            String command = queue.poll();

            switch (command) {
                case "push":
                    stack.push(num.poll());
                    break;
                case "pop":
                    if(stack.isEmpty()) sb.append(-1).append("\n");
                    else sb.append(stack.pop()).append("\n");
                    break;
                case "size":
                    sb.append(stack.size()).append("\n");
                    break;
                case "empty":
                    if(stack.isEmpty()) sb.append(1).append("\n");
                    else sb.append(0).append("\n");
                    break;
                case "top":
                    if(stack.isEmpty()) sb.append(-1).append("\n");
                    else sb.append(stack.peek()).append("\n");
                    break;

            }
        }
        System.out.println(sb);
    }
}
