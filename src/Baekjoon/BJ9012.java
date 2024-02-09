package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
/**
 * 백준 9012 괄호
 *
 * stack을 이용하여 구현
 *  -> 열린 괄호 한개는 꼭 있어야 함
 *     열린 괄호가 있을 때 닫힌 괄호가 나오면 pop을 통해 한 쌍 없애기
 *     열린괄호의 경우 계속 push
 *     반복해서 stack이 비어있으면 유효함
 *     */
public class BJ9012 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        A : for(int i = 0; i <t; i++) {
            String str = br.readLine();
            Deque<Character> stack = new ArrayDeque<>();

            for(int j = 0; j < str.length(); j++) {
                if(stack.size() == 0) {
                    if(str.charAt(j) == ')') {
                        System.out.println("NO");
                        continue A;
                    }
                    stack.push(str.charAt(j));
                }

                else {
                    if(str.charAt(j) == ')') {
                        stack.pop();
                    }
                    else {
                        stack.push(str.charAt(j));
                    }
                }
            }

            if(stack.size() == 0)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}

// 메모리 : 11936kb, 시간 : 88ms