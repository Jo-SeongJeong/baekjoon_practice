package Baekjoon;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ17952 { // 메모리 : 229032kb, 시간: 868ms
    static int n;
    static int[][] schedule;
    static Deque<int[]> stack;
    static int score;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        schedule = new int[n][3];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            schedule[i][0] = Integer.parseInt(st.nextToken());

            if(schedule[i][0] == 0) continue;

            schedule[i][1] = Integer.parseInt(st.nextToken());
            schedule[i][2] = Integer.parseInt(st.nextToken());
        }
        task();
        System.out.println(score);

    }

    private static void task() {
        stack = new ArrayDeque<>();
        int a = 0;
        int t = 0;

        for(int i  = 0; i < n; i++) {
            if(a == 0 && t == 0) {
                if(schedule[i][0] == 1) {
                    a = schedule[i][1];
                    t = schedule[i][2];
                }
            }

            else {
                if(schedule[i][0] == 1) {
                    stack.push(new int[] {a, t});
                    a = schedule[i][1];
                    t = schedule[i][2];
                }
            }

            t--;

            if(t==0) {
                score += a;

                if(!stack.isEmpty()) {
                    int[] temp = stack.pop();
                    a = temp[0];
                    t = temp[1];
                }

                else {
                    a = 0;
                    t = 0;
                }
            }
        }
    }
}
