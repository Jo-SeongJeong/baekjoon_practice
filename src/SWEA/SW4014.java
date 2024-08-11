package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 활주로 건설
 *
 * 조건
 * 맵 크기 N * N : 6 ~ 20
 * 각 칸의 숫자 = 지형의 높이 : 1 ~ 6
 * 활주로 : 가로 또는 세로로 건설
 * 건설 조건
 * 1. 높이가 동일한 구간에서 건설 가능
 * 2. 높이가 다른 경우, 경사로를 설치해야 함
 * 경사로 : 높이 1, 길이 x : 2 ~ 4
 * 활주로 건설 불가 경우
 * 경사로 길이가 놓는 지형의 지형보다 긴 경우, 경사로가 경계 밖으로 나가는 경우
 * 경사로는 겹칠 수 없다
 * 경사로를 세워서 사용할 수 없다
 *
 * 문제에서 구하고자 하는 것
 * 지형에서 경사로를 설치할 수 있는 경우의 수
 *
 * 문제 해결 프로세스
 * 배열의 모든 행, 열을 살피자
 * 시작점부터 각 행/열에 끝까지 지나가며 다음 칸이
 * 같은 높이 지형이면 길이++
 * 높은 지형이면 1 높은지 확인, 아니면 불가
 * 1 높았을 때, 현재까지 쌓아온 길이가 경사로 길이보다 긴지 확인, 아니면 불가
 * 낮은 지형이면 1 낮은지 확인, 아니면 불가
 * 1 낮았을 떄, 길이 1로 초기화, 경사로 길이만큼 같은 높이의 지형인지 확인, 아니면 불가
 * 반복
 *
 * 고려한 시간 복잡도
 * 20 * 20 * 20 * 20 = 160000 * 50 = 83000000
 * */

public class SW4014 { // 메모리 : 33528kb, 시간 : 122ms
    static int n;
    static int x;
    static int count;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for(int t = 1; t <= testCase; t++) {
            sb.append("#" + t + " ");

            count = 0;

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());

            map = new int[n][n];

            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = 0; i < n; i++) {
                checkAirStripRow(i);
                checkAirStripCol(i);
            }
            sb.append(count + "\n");
        }
        System.out.println(sb);
    }

    private static void checkAirStripRow(int start) {
        int len = 1;
        int high = map[start][0];

        for(int d = 1; d < n; d++) {
            if(map[start][d] == high) len++;

            else if(map[start][d] > high) {
                if(map[start][d]-1 != high) return;
                if(len < x) return;
                len = 1;
                high = map[start][d];
            }
            else {
                if(map[start][d] != high-1) return;
                len = 1;
                for(int i = d+1; i < d+x; i++) {
                    if(i >= n) return;
                    if(map[start][d] != map[start][i]) return;
                    len++;
                }
                if(len != x) return;
                high = map[start][d];
                d += x-1;
                len = 0;
            }
        }

        count++;
    }

    private static void checkAirStripCol(int start) {
        int len = 1;
        int high = map[0][start];

        for(int d = 1; d < n; d++) {
            if(map[d][start] == high) len++;

            else if(map[d][start] > high) {
                if(map[d][start]-1 != high) return;
                if(len < x) return;
                len = 1;
                high = map[d][start];
            }
            else {
                if(map[d][start] != high-1) return;
                len = 1;
                for(int i = d+1; i < d+x; i++) {
                    if(i >= n) return;
                    if(map[d][start] != map[i][start]) return;
                    len++;
                }
                if(len != x) return;
                high = map[d][start];
                d += x-1;
                len = 0;
            }
        }

        count++;
    }
}
