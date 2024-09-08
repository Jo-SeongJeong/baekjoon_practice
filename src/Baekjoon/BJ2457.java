package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2457 { // 메모리 : 58340kb, 시간 : 724ms
    static int n;
    static int[][] garden;
    static int len;
    static int month;
    static int day;
    static int count;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        garden = new int[n][4];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++) {
                garden[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(garden, (int[] a, int[] b) -> {
            if(a[2] == b[2]) {
                return b[3] - a[3];
            }
            return b[2] - a[2];
        });

        len = garden.length;
        month = 3;
        day = 1;
        count = 0;

        for(int i = 0; i < garden.length; i++) {
            select();
            if(month == 12) break;
        }

        if(month != 12) System.out.println(0);
        else System.out.println(count);
    }
    private static void select() {
        for(int i = 0; i < len; i++) {
            if(garden[i][0]  < month) {
                len = i;
                month = garden[i][2];
                day = garden[i][3];
                count++;
            }
            else if(garden[i][0] == month) {
                if(garden[i][1] <= day) {
                    len = i;
                    month = garden[i][2];
                    day = garden[i][3];
                    count++;
                }
            }
        }
    }
}
