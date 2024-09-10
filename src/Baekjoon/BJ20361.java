package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ20361 { // 메모리 : 37860kb, 시간 : 304ms

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = 0;
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        boolean[] cup = new boolean[n + 1];
        cup[x] = true;

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            boolean temp = cup[a];
            cup[a] = cup[b];
            cup[b] = temp;

        }

        for(int i = 1; i < n+1; i++) {
            if(cup[i]) {
                answer = i;
                break;
            }
        }
        System.out.println(answer);
    }
}
