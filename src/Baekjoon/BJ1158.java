package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 1158 요세푸스 문제
 *
 * 조건
 *
 * 1 ~ N번까지 원을 이루고 있음
 * K가 주어지면, K번째 사람이 될 때마다 제거
 * 제거될 때마다 원을 계속 이루고 있음
 * K, N < 50000
 *
 * 문제에서 구하고자 하는 것
 *
 * 제거되는 사람의 순번을 순서대로 출력
 *
 * 문제 해결 프로세스
 *
 * N을 입력 받아 Linked List를 만든다
 * 이 때 tail을 설정해 head와 연결한다
 * K를 입력받는다
 * 1부터 리스트를 순회하는데, K번째가 되면 해당 원소를 제거하고 연결하고, K는 0으로 설정한다
 * 제거되면 해당 숫자를 출력한다
 * 해당 과정을 반복하고 모든 사람이 제거되면 종료한다
 *
 * 고려한 시간 복잡도
 * 5000 * 5000 -> 25000000
 *
 * */

public class BJ1158 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Integer> number = new LinkedList<>();

        for(int i = 1; i <= n; i++) {
            number.add(i);
        }

        System.out.print("<");
        int temp = 1;
        int i = 0; // 배열 인덱스 값
        while(number.size() > 0) {
            if(temp == k) {
                System.out.print(number.remove(i));
                i--; // 배열 인덱스는 고정 (삭제로 하나 줄어드니까)
                temp = 0; // temp 초기화 (밑에서 ++ 해줌)
                if(number.size() > 0) {
                    System.out.print(", ");
                }
            }
            temp++;
            i++;
            if(i == number.size()) {
                i = 0;
            }
        }

        System.out.print(">");
    }
}

// 메모리: 16916kb, 시간: 328ms